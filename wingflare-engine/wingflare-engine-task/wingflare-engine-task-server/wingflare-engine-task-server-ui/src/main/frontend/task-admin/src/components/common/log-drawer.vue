<script setup lang="tsx">
import {
  NCard,
  NCollapse,
  NCollapseItem,
  NDivider,
  NEmpty,
  NScrollbar,
  NSpin,
  NVirtualList,
  type VirtualListInst
} from 'naive-ui';
import { defineComponent, nextTick, onBeforeUnmount, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import type { UseWebSocketReturn } from '@vueuse/core';
import { useWebSocket } from '@vueuse/core';
import ButtonIcon from '@/components/custom/button-icon.vue';
import { initWebSocketUrl } from '@/utils/websocket';
import { generateRandomString } from '@/utils/common';

defineOptions({
  name: 'LogDrawer'
});

interface Props {
  title?: string;
  drawer?: boolean;
  type?: 'job' | 'retry';
  fetchType?: 'ws';
  taskData?: Api.Job.JobTask | Api.RetryTask.RetryTask;
  modelValue?: Api.JobLog.JobMessage[];
}

const props = withDefaults(defineProps<Props>(), {
  title: undefined,
  drawer: true,
  type: 'job',
  fetchType: 'ws',
  taskData: undefined,
  modelValue: () => []
});

const visible = defineModel<boolean>('show', {
  default: false
});

const isAutoScroll = ref(false);
const isFullscreen = ref(true);
const expandedNames = ref<string[]>([]);
const virtualListInst = ref<VirtualListInst>();
const syncTime = ref(1);
const logList = ref<Api.JobLog.JobMessage[]>([]);
const websocket = ref<UseWebSocketReturn<any>>();
const interval = ref<NodeJS.Timeout>();
const finished = ref<boolean>(true);

const pauseLog = () => {
  finished.value = true;
  clearTimeout(interval.value);
  interval.value = undefined;
};

const stopLogByWs = () => {
  websocket.value?.close();
};

const stopLog = () => {
  stopLogByWs();
};

async function getLogList() {
  getLogListByWs();
}

function getLogListByWs() {
  finished.value = false;
  logList.value = [];
  websocket.value?.open();
  if (props.type === 'job') {
    const taskData = props.taskData! as Api.Job.JobTask;
    const msg = {
      taskBatchId: taskData.taskBatchId,
      taskId: taskData.id
    };
    websocket.value?.send(JSON.stringify(msg));
  }

  if (props.type === 'retry') {
    const taskData = props.taskData! as Api.RetryTask.RetryTask;
    const msg = {
      groupName: taskData.groupName,
      retryTaskId: taskData.id
    };
    websocket.value?.send(JSON.stringify(msg));
  }
}

onBeforeUnmount(() => {
  stopLog();
});

watch(
  () => visible.value,
  async val => {
    if (val) {
      logList.value = [];
      if (props.modelValue) {
        logList.value = [...props.modelValue];
      }
    }

    if (!val && props.drawer) {
      stopLog();
      return;
    }

    if (((val && props.drawer) || !props.drawer) && props.type && props.taskData) {
      finished.value = false;
      let url: string | null = '';

      const scene = props.type === 'job' ? 'JOB_LOG_SCENE' : 'RETRY_LOG_SCENE';
      url = initWebSocketUrl(scene, props.taskData.id);

      if (!url) {
        window.$message?.error('Token 失效');
        visible.value = false;
        return;
      }
      websocket.value = useWebSocket(url, {
        immediate: false,
        autoConnect: false,
        autoReconnect: {
          // 重连最大次数
          retries: 3,
          // 重连间隔
          delay: 1000,
          onFailed() {
            window.$message?.error('websocket 连接失败');
            visible.value = false;
          }
        },
        onMessage: (_, e) => {
          if (e.data !== 'END') {
            const data = JSON.parse(e.data) as Api.JobLog.JobMessage;
            data.key = `${data.time_stamp}-${generateRandomString(16)}`;
            logList.value.push(data);
            nextTick(() => {
              if (isAutoScroll.value) virtualListInst.value?.scrollTo({ position: 'bottom', debounce: true });
            });
          } else {
            finished.value = true;
            stopLogByWs();
          }
        }
      });
      getLogListByWs();
    }
  },
  { immediate: true }
);

function timestampToDate(timestamp: string): string {
  const date = new Date(Number.parseInt(timestamp?.toString(), 10));
  const year = date.getFullYear();
  const month =
    (date.getMonth() + 1)?.toString().length === 1 ? `0${date.getMonth() + 1}` : (date.getMonth() + 1)?.toString();
  const day = date.getDate()?.toString().length === 1 ? `0${date.getDate()}` : date.getDate()?.toString();
  const hours = date.getHours()?.toString().length === 1 ? `0${date.getHours()}` : date.getHours()?.toString();
  const minutes = date.getMinutes()?.toString().length === 1 ? `0${date.getMinutes()}` : date.getMinutes()?.toString();
  const seconds = date.getSeconds()?.toString().length === 1 ? `0${date.getSeconds()}` : date.getSeconds()?.toString();
  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}.${date.getMilliseconds()}`;
}

const router = useRouter();

function openNewTab() {
  let query;
  if (props.type === 'job') {
    query = {
      type: props.type,
      taskBatchId: (props.taskData as Api.Job.JobTask).taskBatchId,
      jobId: (props.taskData as Api.Job.JobTask).jobId,
      taskId: (props.taskData as Api.Job.JobTask).id
    };
  }

  if (props.type === 'retry') {
    query = {
      type: props.type,
      groupName: (props.taskData as Api.RetryTask.RetryTask | Api.Retry.Retry).groupName,
      retryTaskId: (props.taskData as Api.RetryTask.RetryTask).id
    };
  }
  const url = router.resolve({ path: '/log', query });
  window.open(url.href);
}

const handleSyncSelect = async (time: number) => {
  if (time === -1) {
    if (finished.value) {
      finished.value = false;
      await getLogList();
    }
    return;
  }

  syncTime.value = time;

  if (time === 0) {
    pauseLog();
    return;
  }

  finished.value = false;
  await getLogList();
};

const SnailLogComponent = defineComponent({
  setup() {
    if (finished.value && logList.value.length === 0) {
      return () => <NEmpty class="h-full flex-center" size="huge" />;
    }

    const throwableComponent = (message: Api.JobLog.JobMessage) => {
      const throwable = message.throwable;
      if (!throwable) {
        return <></>;
      }
      const firstLine = throwable.match(/^.+/m);
      if (!firstLine) {
        return <></>;
      }
      const restOfText = throwable.replace(/^.+(\n|$)/m, '');
      return (
        <NCollapseItem title={firstLine[0]} name={`throwable-${message.key}`}>
          <NScrollbar
            content-class="p-8px"
            class="message-scroll-body"
            y-placement="left"
          >{`${restOfText}`}</NScrollbar>
        </NCollapseItem>
      );
    };

    const messageComponent = (message: Api.JobLog.JobMessage) => {
      const msg = message.message;
      if (!msg) {
        return <></>;
      }
      const firstLine = msg.match(/^.+/m);
      if (!firstLine) {
        return <></>;
      }
      const restOfText = msg.replace(/^.+(\n|$)/m, '').replaceAll('\n', '\n - ');
      if (restOfText) {
        return (
          <NCollapseItem title={firstLine[0]} name={`message-${message.key}`}>
            <NScrollbar
              content-class="p-8px"
              class="message-scroll-body"
              y-placement="left"
            >{` - ${restOfText}`}</NScrollbar>
          </NCollapseItem>
        );
      }
      return <div class="pl-6px">- {`${msg}`}</div>;
    };

    const handleUpdateExpanded = (val: string[]) => {
      expandedNames.value = val;
    };

    const handleResize = (_: ResizeObserverEntry) => {
      expandedNames.value = [];
    };

    return () => (
      <code class="snail-log">
        <NCollapse
          accordion
          v-model:expanded-names={expandedNames.value}
          on-update:expanded-names={handleUpdateExpanded}
        >
          <NVirtualList
            ref={virtualListInst}
            style={{ height: '100%' }}
            class="virtual-list"
            itemSize={85}
            paddingBottom={16}
            items={logList.value}
            scrollbarProps={{ xScrollable: true }}
            onResize={handleResize}
          >
            {{
              default: ({ item: message }: { item: Api.JobLog.JobMessage; index: number }) => (
                <pre key={message.key} class="min-h-85px min-w-full">
                  <div>
                    <span class="log-hljs-time inline-block">{timestampToDate(message.time_stamp)}</span>
                    <span
                      class={`log-hljs-level-${message.level} ml-12px mr-12px inline-block`}
                    >{`${message.level}`}</span>
                    <span class="log-hljs-thread mr-12px inline-block">{`[${message.host}:${message.port}]`}</span>
                    <span class="log-hljs-thread mr-12px inline-block">{`[${message.thread}]`}</span>
                  </div>
                  <div class="log-hljs-location">{`${message.location}: `}</div>
                  <div>{messageComponent(message)}</div>
                  <div>{throwableComponent(message)}</div>
                  <NDivider />
                </pre>
              )
            }}
          </NVirtualList>
        </NCollapse>
      </code>
    );
  }
});
</script>

<template>
  <NDrawer
    v-if="drawer"
    v-model:show="visible"
    :width="isFullscreen ? '100%' : '50%'"
    display-directive="if"
    :auto-focus="false"
  >
    <NDrawerContent closable>
      <template #header>
        <div class="flex items-center justify-between" :class="`tool-header${isFullscreen ? '-full' : ''}`">
          <div class="flex-center">
            <NTooltip v-if="finished">
              <template #trigger>
                <icon-material-symbols:check-circle class="text-20px color-success" />
              </template>
              日志加载完成
            </NTooltip>
            <NTooltip v-else>
              <template #trigger>
                <NSpin size="small">
                  <template #icon>
                    <icon-nonicons:loading-16 />
                  </template>
                </NSpin>
              </template>
              日志正在加载
            </NTooltip>
            <span class="ml-6px">{{ title }}</span>
          </div>
          <div class="flex-center">
            <ButtonIcon
              v-if="fetchType === 'ws'"
              size="tiny"
              class="mr-6px"
              icon="solar:refresh-outline"
              tooltip-content="刷新"
              @click="handleSyncSelect(-1)"
            />
            <ButtonIcon
              size="tiny"
              :tooltip-content="isAutoScroll ? '关闭自动滚动' : '开启自动滚动'"
              @click="() => (isAutoScroll = !isAutoScroll)"
            >
              <icon-streamline:synchronize-disable v-if="isAutoScroll" />
              <icon-streamline:interface-arrows-vertical-scroll-point-move-scroll-vertical v-else />
            </ButtonIcon>
            <ButtonIcon
              size="tiny"
              icon="hugeicons:share-01"
              tooltip-content="在新标签页打开"
              class="ml-6px"
              @click="openNewTab"
            />
            <ButtonIcon
              size="tiny"
              :tooltip-content="isFullscreen ? '半屏' : '全屏'"
              @click="() => (isFullscreen = !isFullscreen)"
            >
              <icon-material-symbols:close-fullscreen-rounded v-if="isFullscreen" />
              <icon-material-symbols:open-in-full-rounded v-else />
            </ButtonIcon>
          </div>
        </div>
      </template>
      <div v-if="logList.length === 0" class="empty-height flex-center">
        <NEmpty v-if="logList.length === 0 && finished" />
        <NSpin v-if="logList.length === 0 && !finished" />
      </div>
      <SnailLogComponent v-if="logList.length > 0" />
    </NDrawerContent>
  </NDrawer>
  <NCard v-else :bordered="false" :title="title" size="small" class="h-full sm:flex-1-hidden card-wrapper">
    <template #header-extra>
      <div class="flex items-center">
        <ButtonIcon
          size="tiny"
          class="mr-12px"
          :tooltip-content="isAutoScroll ? '关闭自动滚动' : '开启自动滚动'"
          @click="() => (isAutoScroll = !isAutoScroll)"
        >
          <icon-streamline:synchronize-disable v-if="isAutoScroll" />
          <icon-streamline:interface-arrows-vertical-scroll-point-move-scroll-vertical v-else />
        </ButtonIcon>
        <NTooltip v-if="finished">
          <template #trigger>
            <icon-material-symbols:check-circle class="text-20px color-success" />
          </template>
          日志加载完成
        </NTooltip>
        <NTooltip v-else>
          <template #trigger>
            <NSpin size="small">
              <template #icon>
                <icon-nonicons:loading-16 />
              </template>
            </NSpin>
          </template>
          日志正在加载
        </NTooltip>
      </div>
    </template>
    <div v-if="logList.length === 0" class="h-full flex-center">
      <NEmpty v-if="logList.length === 0 && finished" />
      <NSpin v-if="logList.length === 0 && !finished" />
    </div>
    <SnailLogComponent />
  </NCard>
</template>

<style lang="scss">
.snail-log {
  padding: 0;

  .virtual-list {
    min-height: calc(100vh - 101px);
    max-height: calc(100vh - 101px);
  }

  .v-vl {
    min-height: calc(100vh - 101px);
  }

  .v-vl-items {
    min-height: calc(100vh - 101px - 16px) !important;
  }

  .n-divider:not(.n-divider--vertical) {
    margin-top: 6px;
    margin-bottom: 6px;
  }

  pre {
    // white-space: pre-wrap;
    word-break: break-word;
    margin: 0;
    font-size: 16px;
    color: #333639;
  }

  .log-hljs {
    &-time {
      color: #2db7f5;
    }

    &-level {
      &-DEBUG {
        color: #2647cc;
      }

      &-INFO {
        color: #5c962c;
      }

      &-WARN {
        color: #da9816;
      }

      &-ERROR {
        color: #dc3f41;
      }
    }

    &-thread {
      color: #00a3a3;
    }

    &-location {
      color: #a771bf;
    }
  }
}

.dark {
  .snail-log {
    background-color: #1e1f22;

    pre {
      color: #ffffffe6;
    }
  }
}
</style>

<style scoped>
:deep(.n-spin) {
  height: 18px !important;
  width: 18px !important;
  font-size: 18px !important;
  margin-right: 6px;
}

.tool-header-full {
  width: calc(100vw - 72px);
}

.tool-header {
  width: calc(50vw - 72px);
}

.empty-height {
  min-height: calc(100vh - 101px);
  max-height: calc(100vh - 101px);
}

:deep(.n-collapse-item__content-inner) {
  padding-top: 0 !important;
}

:deep(.v-vl-items) {
  display: inline-block !important;
  min-width: 100%;
}

:deep(.message-scroll-body) {
  margin-top: 6px;
  max-height: 150px;
  border: 1px solid rgb(239, 239, 245);
  border-radius: var(--n-border-radius);
}

:deep(.dark .message-scroll-body) {
  border: 1px solid rgba(255, 255, 255, 0.09) !important;
}
</style>
