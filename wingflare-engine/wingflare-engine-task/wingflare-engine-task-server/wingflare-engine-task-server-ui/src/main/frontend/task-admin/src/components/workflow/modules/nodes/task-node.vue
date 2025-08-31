<script setup lang="ts">
import { nextTick, ref, watch } from 'vue';
import { useMessage } from 'naive-ui';
import { fetchNodeRetry, fetchNodeStop } from '@/service/api';
import { $t } from '@/locales';
import { useWorkflowStore } from '@/store/modules/workflow';
import { failStrategyRecord, taskBatchStatusEnum } from '@/constants/business';
import LabelList from '@/components/common/label-list.vue';
import TaskDrawer from '../drawer/task-drawer.vue';
import TaskDetail from '../detail/task-detail.vue';
import DetailCard from '../common/detail-card.vue';
import AddNode from './add-node.vue';

defineOptions({
  name: 'TaskNode'
});

interface Props {
  modelValue?: Workflow.NodeModelType;
  disabled?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
  modelValue: () => ({})
});

interface Emits {
  (e: 'refresh'): void;
  (e: 'update:modelValue', modelValue: Workflow.NodeModelType): void;
}

const emit = defineEmits<Emits>();

const store = useWorkflowStore();
const message = useMessage();
const nodeConfig = ref<Workflow.NodeModelType>({});

watch(
  () => props.modelValue,
  val => {
    nodeConfig.value = val;
  },
  { immediate: true }
);

const addTerm = () => {
  const len = nodeConfig.value.conditionNodes!.length + 1;
  nodeConfig.value.conditionNodes?.push({
    nodeName: `${$t('workflow.node.task.name')} ${len}`,
    priorityLevel: len,
    failStrategy: 1,
    workflowNodeStatus: 1,
    jobTask: {
      jobId: undefined
    }
  });
  emit('update:modelValue', nodeConfig.value);
};

const reData = (data: Workflow.NodeModelType, addData: Workflow.NodeModelType) => {
  if (!data.childNode) {
    data.childNode = addData;
  } else {
    reData(data.childNode, addData);
  }
};

const delTerm = (currentIndex: number) => {
  if (nodeConfig.value.conditionNodes!.length === 1) {
    if (nodeConfig.value.childNode) {
      if (nodeConfig.value.conditionNodes![0].childNode) {
        reData(nodeConfig.value.conditionNodes![0].childNode, nodeConfig.value.childNode);
      } else {
        nodeConfig.value.conditionNodes![0].childNode = nodeConfig.value.childNode;
      }
    }
    nextTick(() => {
      emit('update:modelValue', nodeConfig.value.conditionNodes![0].childNode!);
    });
  } else {
    nodeConfig.value.conditionNodes?.splice(currentIndex, 1);
  }
};

const arrTransfer = (index: number, type: number = 1) => {
  nodeConfig.value.conditionNodes![index] = nodeConfig.value.conditionNodes!.splice(
    index + type,
    1,
    nodeConfig.value.conditionNodes![index]
  )[0];
  nodeConfig.value.conditionNodes?.map((item, i) => (item.priorityLevel = i + 1));
  emit('update:modelValue', nodeConfig.value);
};

const index = ref<number>(0);
const drawer = ref<boolean>(false);
const form = ref<Workflow.ConditionNodeType>({});

const save = (val: Workflow.ConditionNodeType) => {
  const oldLevel = nodeConfig.value.conditionNodes![index.value].priorityLevel;
  const newLevel = val.priorityLevel;
  nodeConfig.value.conditionNodes![index.value] = val;
  if (oldLevel !== newLevel) {
    arrTransfer(index.value, newLevel! - oldLevel!);
  }
  emit('update:modelValue', nodeConfig.value);
};

const show = (currentIndex: number) => {
  if (!props.disabled) {
    index.value = currentIndex;
    form.value = JSON.parse(JSON.stringify(nodeConfig.value.conditionNodes![currentIndex]));
    drawer.value = true;
  }
};

const getClass = (item: Workflow.ConditionNodeType) => {
  if (props.disabled) {
    if (store.type === 2) {
      return `node-error node-error-${
        item.taskBatchStatus && taskBatchStatusEnum[item.taskBatchStatus]
          ? taskBatchStatusEnum[item.taskBatchStatus].name
          : 'default'
      }`;
    }
    return 'node-error';
  }
  return 'auto-judge-def auto-judge-hover';
};

const detailId = ref<string>();
const detailIds = ref<string[]>();
const cardDrawer = ref(false);
const detailDrawer = ref<boolean[]>([]);

const showDetail = (node: Workflow.ConditionNodeType, detailIndex: number) => {
  detailIds.value = [];
  if (store.type === 2) {
    node.jobBatchList
      ?.sort((a, b) => a.taskBatchStatus - b.taskBatchStatus)
      .forEach(item => {
        if (item.id) {
          detailIds.value?.push(item.id);
        } else if (item.jobId) {
          detailId.value = item.jobId?.toString();
        }
      });
    if (node.jobTask?.jobId) {
      detailId.value = node.jobTask?.jobId?.toString();
    }
    cardDrawer.value = true;
  } else if (store.type === 1) {
    detailDrawer.value[detailIndex] = true;
  } else {
    show(detailIndex);
  }
};

const retry = async (node: Workflow.ConditionNodeType) => {
  const { error } = await fetchNodeRetry(node.id!, store.id!);
  if (!error) {
    message.success('执行重试成功');
    emit('refresh');
  }
};

const stop = async (node: Workflow.ConditionNodeType) => {
  const { error } = await fetchNodeStop(node.id!, store.id!);
  if (!error) {
    message.success('停止任务成功');
    emit('refresh');
  }
};

const isRetry = (taskBatchStatus: number) => {
  return taskBatchStatus === 4 || taskBatchStatus === 5 || taskBatchStatus === 6;
};

const isStop = (taskBatchStatus: number) => {
  return taskBatchStatus === 1 || taskBatchStatus === 2;
};

const isShow = (taskBatchStatus: number) => {
  return isRetry(taskBatchStatus!) || isStop(taskBatchStatus!);
};
</script>

<template>
  <div class="node-wrap">
    <div class="branch-box">
      <NButton v-if="!disabled" class="add-branch" strong type="success" @click="addTerm">
        {{ $t('workflow.node.task.add') }}
      </NButton>
      <div v-for="(item, i) in nodeConfig.conditionNodes" :key="i" class="col-box">
        <div class="condition-node">
          <div class="condition-node-box">
            <NPopover :disabled="store.type !== 2 || !isShow(item.taskBatchStatus!)">
              <div class="popover">
                <NButton v-if="isRetry(item.taskBatchStatus!)" text @click="retry(item!)">
                  <span class="popover-item">
                    <icon-ant-design:redo-outlined class="mb-3px text-24px font-bold" />
                    {{ $t('common.retry') }}
                  </span>
                </NButton>
                <NDivider v-if="isStop(item.taskBatchStatus!) && isRetry(item.taskBatchStatus!)" vertical />
                <NButton v-if="isStop(item.taskBatchStatus!)" text @click="stop(item!)">
                  <span class="popover-item">
                    <icon-ant-design:stop-outlined />
                    {{ $t('common.stop') }}
                  </span>
                </NButton>
              </div>
              <template #trigger>
                <div class="auto-judge cursor-pointer" :class="getClass(item)" @click="showDetail(item!, i)">
                  <div v-if="i != 0" class="sort-left" @click.stop="arrTransfer(i, -1)">
                    <icon-ant-design:left-outlined />
                  </div>
                  <div class="title">
                    <span class="text color-#3296fa">
                      <NBadge processing dot :color="item.workflowNodeStatus === 1 ? '#52c41a' : '#ff4d4f'" />
                      &nbsp;{{ item.nodeName }}
                      <span v-if="item.id">&nbsp;({{ item.id }})</span>
                    </span>
                    <span class="priority-title">{{ $t('workflow.node.priority') }}{{ item.priorityLevel }}</span>
                    <icon-ant-design:close-outlined v-if="!disabled" class="close" @click.stop="delTerm(i)" />
                  </div>
                  <div class="content min-h-72px">
                    <div v-if="!item.jobTask?.jobId" class="placeholder">请选择任务</div>
                    <template v-if="item.jobTask?.jobId">
                      <div>
                        <NTooltip v-if="item.jobTask?.labels">
                          <template #trigger>
                            <div class="inline-block max-w-190px overflow-hidden whitespace-nowrap">
                              <div class="flex-y-center gap-8px">
                                <span class="content_label">标签:</span>
                                <div class="flex-y-center gap-6px">
                                  <LabelList :labels="item.jobTask?.labels" />
                                </div>
                              </div>
                            </div>
                          </template>
                          <div class="flex-y-center gap-6px">
                            <NTag
                              v-for="[key, value] in Object.entries(JSON.parse(item.jobTask?.labels || '{}'))"
                              :key="key"
                              type="default"
                            >
                              {{ key }}:{{ value }}
                            </NTag>
                          </div>
                        </NTooltip>
                      </div>
                      <div>
                        <NEllipsis class="max-w-123px">
                          <span class="content_label">任务名称:&nbsp;</span>
                          {{ `${item.jobTask?.jobName}(${item.jobTask?.jobId})` }}
                        </NEllipsis>
                      </div>
                      <div>
                        <span class="content_label">失败策略:&nbsp;</span>
                        {{ $t(failStrategyRecord[item.failStrategy!]) }}
                      </div>
                      <div>.........</div>
                    </template>
                  </div>
                  <div
                    v-if="i != nodeConfig.conditionNodes!.length - 1"
                    class="sort-right"
                    @click.stop="arrTransfer(i)"
                  >
                    <icon-ant-design:right-outlined />
                  </div>
                </div>
              </template>
            </NPopover>
            <NTooltip v-if="store.type === 2 && item.taskBatchStatus">
              <template #trigger>
                <div
                  class="task-error-tip text-24px"
                  :style="{ color: taskBatchStatusEnum[item.taskBatchStatus!].color }"
                >
                  <SvgIcon :icon="taskBatchStatusEnum[item.taskBatchStatus!].icon" />
                </div>
              </template>
              {{ taskBatchStatusEnum[item.taskBatchStatus!].title }}
            </NTooltip>
            <AddNode v-model="item.childNode!" :disabled="disabled"></AddNode>
          </div>
        </div>
        <slot v-if="item.childNode" :node="item"></slot>
        <div v-if="i == 0" class="top-left-cover-line"></div>
        <div v-if="i == 0" class="bottom-left-cover-line"></div>
        <div v-if="i == nodeConfig.conditionNodes!.length - 1" class="top-right-cover-line"></div>
        <div v-if="i == nodeConfig.conditionNodes!.length - 1" class="bottom-right-cover-line"></div>

        <TaskDetail
          v-if="store.type !== 0"
          v-model:open="detailDrawer[i]"
          :model-value="nodeConfig.conditionNodes![i]"
        />
      </div>
    </div>
    <AddNode v-if="nodeConfig.conditionNodes!.length > 1" v-model="nodeConfig.childNode!" :disabled="disabled" />

    <TaskDrawer
      v-if="store.type === 0"
      v-model:open="drawer"
      v-model="form"
      v-model:len="nodeConfig.conditionNodes!.length"
      @save="save"
    />
    <DetailCard v-if="store.type !== 0" :id="detailId" v-model:show="cardDrawer" :ids="detailIds" />
  </div>
</template>

<style scoped lang="scss">
.task-error-tip {
  cursor: default;
  position: absolute;
  top: 63px;
  left: 291px;
  font-size: 24px;
}

.popover {
  display: flex;
  align-items: center;
  justify-content: space-around;

  .popover-item {
    height: 42px;
    display: flex;
    font-size: 13px;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    text-align: center;

    span {
      margin-inline-start: 0;
    }

    .anticon {
      font-size: 22px;
      justify-content: center;
      align-items: center;
    }
  }
}
</style>
