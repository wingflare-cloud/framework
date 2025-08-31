<script setup lang="ts">
import { nextTick, ref, watch } from 'vue';
import { $t } from '@/locales';
import { useWorkflowStore } from '@/store/modules/workflow';
import { contentTypeRecord, taskBatchStatusEnum } from '@/constants/business';
import CallbackDrawer from '../drawer/callback-drawer.vue';
import CallbackDetail from '../detail/callback-detail.vue';
import DetailCard from '../common/detail-card.vue';
import AddNode from './add-node.vue';

defineOptions({
  name: 'CallbackNode'
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
  (e: 'update:modelValue', modelValue: Workflow.NodeModelType): void;
}

const emit = defineEmits<Emits>();

const store = useWorkflowStore();

const nodeConfig = ref<Workflow.NodeModelType>({});

watch(
  () => props.modelValue,
  val => {
    nodeConfig.value = val;
  },
  { immediate: true }
);

const reData = (data: Workflow.NodeModelType, addData: Workflow.NodeModelType) => {
  if (!data.childNode) {
    data.childNode = addData;
  } else {
    reData(data.childNode, addData);
  }
};

const delTerm = () => {
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
};

const currentIndex = ref<number>(0);
const drawer = ref<boolean>(false);
const detailDrawer = ref<boolean>(false);
const form = ref<Workflow.ConditionNodeType>({});

const save = (val: Workflow.ConditionNodeType) => {
  nodeConfig.value.conditionNodes![currentIndex.value] = val;
  emit('update:modelValue', nodeConfig.value);
};

const show = (index: number) => {
  if (store.type === 0) {
    currentIndex.value = index;
    form.value = JSON.parse(JSON.stringify(nodeConfig.value.conditionNodes![index]));
    drawer.value = true;
  } else {
    detailDrawer.value = true;
  }
};

const cardDrawer = ref<boolean>(false);
const detailId = ref<string>('');
const detailIds = ref<string[]>([]);

const showDetail = (item: Workflow.ConditionNodeType, index: number) => {
  detailIds.value = [];
  if (store.type === 2) {
    item.jobBatchList?.forEach(job => {
      if (job.id) {
        detailIds.value?.push(job.id);
      } else if (job.jobId) {
        detailId.value = job.jobId?.toString();
      }
    });
    if (detailIds.value.length === 0) {
      detailDrawer.value = true;
      return;
    }
    cardDrawer.value = true;
  } else if (store.type === 1) {
    detailDrawer.value = true;
  } else {
    show(index);
  }
};

const getClass = (item: Workflow.ConditionNodeType) => {
  if (props.disabled) {
    if (store.type === 2) {
      return `node-error node-error-${
        item.taskBatchStatus ? taskBatchStatusEnum[item.taskBatchStatus].name : 'default'
      }`;
    }
    return 'node-error';
  }
  return 'auto-judge-def auto-judge-hover';
};
</script>

<template>
  <div class="node-wrap">
    <div class="branch-box">
      <div v-for="(item, index) in nodeConfig.conditionNodes" :key="index" class="col-box">
        <div class="condition-node min-h-230px">
          <div class="condition-node-box pt-0px">
            <NPopover :disabled="store.type !== 2 || true">
              <div class="popover">
                <NButton text>
                  <span class="popover-item">
                    <icon-ant-design:redo-outlined class="mb-3px text-24px font-bold" />
                    {{ $t('common.retry') }}
                  </span>
                </NButton>
                <NDivider vertical />
                <NButton text>
                  <span class="popover-item">
                    <icon-ant-design:dash-outlined class="mb-3px text-24px font-bold" />
                    <span>忽略</span>
                  </span>
                </NButton>
              </div>
              <template #trigger>
                <div class="auto-judge" :class="getClass(item)" @click="showDetail(item, index)">
                  <div class="title">
                    <span class="text text-#935af6">
                      <span class="flex items-center">
                        <NBadge processing dot :color="item.workflowNodeStatus === 1 ? '#52c41a' : '#ff4d4f'" />
                        &nbsp;{{ item.nodeName }}
                        <span v-if="item.id">&nbsp;({{ item.id }})</span>
                        <NTooltip>
                          <template #trigger>
                            <icon-ant-design:info-circle-outlined class="ml-3px text-16px" />
                          </template>
                          此节点后续将废弃，请使用定时任务中的 HTTP 内置执行器进行替换。
                        </NTooltip>
                      </span>
                    </span>
                    <icon-ant-design:close-outlined v-if="!disabled" class="close" @click.stop="delTerm" />
                  </div>
                  <div class="content min-h-81px">
                    <div v-if="!item.callback?.webhook" class="placeholder">
                      {{ $t('workflow.node.callback.conditionNodes.webhookTip') }}
                    </div>
                    <template v-if="item.callback?.webhook">
                      <div class="flex justify-between">
                        <span class="content_label">Webhook:</span>
                        <NEllipsis class="w-116px">{{ item.callback.webhook }}</NEllipsis>
                      </div>
                      <div>
                        <span class="content_label">
                          {{ $t('workflow.node.callback.conditionNodes.contentType') }}:
                        </span>
                        {{ contentTypeRecord[item.callback.contentType!] }}
                      </div>
                      <div>.........</div>
                    </template>
                  </div>
                  <NTooltip v-if="store.type === 2 && item.taskBatchStatus">
                    <template #trigger>
                      <div
                        class="error-tip text-24px"
                        :style="{ color: taskBatchStatusEnum[item.taskBatchStatus].color }"
                      >
                        <SvgIcon :icon="taskBatchStatusEnum[item.taskBatchStatus].icon" />
                      </div>
                    </template>
                    {{ taskBatchStatusEnum[item.taskBatchStatus].title }}
                  </NTooltip>
                </div>
              </template>
            </NPopover>
            <AddNode v-model="item.childNode!" :disabled="disabled"></AddNode>
          </div>
        </div>
        <slot v-if="item.childNode" :node="item"></slot>
        <div v-if="index == 0" class="top-left-cover-line"></div>
        <div v-if="index == 0" class="bottom-left-cover-line"></div>
        <div v-if="index == nodeConfig.conditionNodes!.length - 1" class="top-right-cover-line"></div>
        <div v-if="index == nodeConfig.conditionNodes!.length - 1" class="bottom-right-cover-line"></div>
      </div>
    </div>
    <AddNode v-if="nodeConfig.conditionNodes!.length > 1" v-model="nodeConfig.childNode!" :disabled="disabled" />
    <CallbackDetail v-if="store.type !== 0" v-model:open="detailDrawer" v-model="nodeConfig.conditionNodes![0]" />
    <CallbackDrawer v-model:open="drawer" v-model="form" @save="save" />

    <DetailCard v-if="store.type !== 0" :id="detailId" v-model:show="cardDrawer" :ids="detailIds">
      <div class="header-border">
        <span class="pl-18px">回调节点详情</span>
      </div>
      <NDescriptions :column="1" bordered :label-style="{ width: '120px' }">
        <NDescriptionsItem label="节点名称">{{ nodeConfig.conditionNodes![0].nodeName }}</NDescriptionsItem>
        <NDescriptionsItem label="webhook">{{ nodeConfig.conditionNodes![0].callback?.webhook }}</NDescriptionsItem>
        <NDescriptionsItem label="请求类型">
          {{ contentTypeRecord[nodeConfig.conditionNodes![0].callback?.contentType!] }}
        </NDescriptionsItem>
        <NDescriptionsItem label="密钥">{{ nodeConfig.conditionNodes![0].callback?.secret }}</NDescriptionsItem>
      </NDescriptions>
    </DetailCard>
  </div>
</template>

<style scoped lang="scss">
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

.header-border {
  margin: 20px 0;
  border-left: #1366ff 5px solid;
  font-size: medium;
  font-weight: bold;
}
</style>
