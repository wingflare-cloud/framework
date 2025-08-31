<script lang="ts" setup>
import { nextTick, ref, watch } from 'vue';
import { $t } from '@/locales';
import { useWorkflowStore } from '@/store/modules/workflow';
import { expressionRecord, taskBatchStatusEnum } from '@/constants/business';
import BranchDrawer from '../drawer/branch-drawer.vue';
import BranchDetail from '../detail/branch-detail.vue';
import DetailCard from '../common/detail-card.vue';
import BranchDesc from '../detail/branch-desc.vue';
import AddNode from './add-node.vue';

defineOptions({
  name: 'BranchNode'
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

const addTerm = () => {
  const len = nodeConfig.value.conditionNodes!.length;
  nodeConfig.value.conditionNodes!.splice(-1, 0, {
    nodeName: `${$t('workflow.node.condition.conditionNodes.nodeName')} ${len}`,
    priorityLevel: len,
    decision: {
      expressionType: 1,
      nodeExpression: undefined,
      logicalCondition: 1,
      defaultDecision: 0
    }
  });
  nodeConfig.value.conditionNodes![len].priorityLevel = len + 1;
};

const reData = (data: Workflow.NodeModelType, addData: Workflow.NodeModelType) => {
  if (!data.childNode) {
    data.childNode = addData;
  } else {
    reData(data.childNode, addData);
  }
};

const delTerm = (currentIndex: number) => {
  nodeConfig.value.conditionNodes?.splice(currentIndex, 1);
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

const toText = (node: Workflow.NodeModelType, currentIndex: number) => {
  const { nodeName, decision } = node.conditionNodes![currentIndex];
  const { expressionType, nodeExpression } = decision!;
  if (nodeExpression) {
    if (nodeName !== $t('workflow.node.condition.conditionNodes.otherNodeName')) {
      const text = `<span class="content_label">${$t('workflow.node.condition.conditionNodes.expressionType')}: </span>${
        expressionRecord[expressionType!]
      }\n<span class="content_label">${$t('workflow.node.condition.conditionNodes.nodeExpression')}: </span><span style="display: inline-block; vertical-align: bottom; width: 120px; overflow: hidden; text-overflow: ellipsis">${nodeExpression}<span/>`;
      return text;
    }
    return $t('workflow.node.condition.conditionNodes.otherNodeTip');
  }
  if (nodeName === $t('workflow.node.condition.conditionNodes.otherNodeName')) {
    return $t('workflow.node.condition.conditionNodes.otherNodeTip');
  }
  return null;
};

const currentIndex = ref<number>(0);
const drawer = ref<boolean>(false);
const detailDrawer = ref<boolean[]>([]);
const form = ref<Workflow.ConditionNodeType>({});

const save = (val: Workflow.ConditionNodeType) => {
  const oldLevel = nodeConfig.value.conditionNodes![currentIndex.value].priorityLevel;
  const newLevel = val.priorityLevel;
  nodeConfig.value.conditionNodes![currentIndex.value] = val;
  if (oldLevel !== newLevel) {
    arrTransfer(currentIndex.value, newLevel! - oldLevel!);
  }
  emit('update:modelValue', nodeConfig.value);
};

const show = (index: number) => {
  if (!props.disabled && index !== nodeConfig.value.conditionNodes!.length - 1) {
    currentIndex.value = index;
    form.value = JSON.parse(JSON.stringify(nodeConfig.value.conditionNodes![index]));
    drawer.value = true;
  } else if (store.type !== 0) {
    detailDrawer.value[index] = true;
  }
};

const cardDrawer = ref<boolean[]>([]);
const detailId = ref<string>('');
const detailIds = ref<string[]>([]);

const showDetail = (item: Workflow.ConditionNodeType, index: number) => {
  detailIds.value = [];

  if (item.nodeName !== $t('workflow.node.condition.conditionNodes.otherNodeName')) {
    if (store.type === 2) {
      item.jobBatchList?.forEach(job => {
        if (job.id) {
          detailIds.value?.push(job.id);
        } else if (job.jobId) {
          detailId.value = job.jobId?.toString();
        }
      });
      if (detailIds.value.length === 0) {
        detailDrawer.value[index] = true;
        return;
      }
      cardDrawer.value[index] = true;
    } else if (store.type === 1) {
      detailDrawer.value[index] = true;
    } else {
      show(index);
    }
  }
};

const getClass = (item: Workflow.ConditionNodeType) => {
  if (props.disabled) {
    if (store.type === 2) {
      if (item.nodeName === $t('workflow.node.condition.conditionNodes.otherNodeName')) {
        return `node-disabled node-error node-error-${
          item.taskBatchStatus ? taskBatchStatusEnum[item.taskBatchStatus].name : 'default'
        }`;
      }
      return `node-error node-error-${
        item.taskBatchStatus ? taskBatchStatusEnum[item.taskBatchStatus].name : 'default'
      }`;
    }
    if (item.nodeName === $t('workflow.node.condition.conditionNodes.otherNodeName')) {
      return 'node-disabled';
    }
    return 'node-error';
  }
  if (item.nodeName === $t('workflow.node.condition.conditionNodes.otherNodeName')) {
    return 'node-disabled';
  }
  return 'auto-judge-def auto-judge-hover';
};
</script>

<template>
  <div class="branch-wrap">
    <div class="branch-box-wrap">
      <div class="branch-box">
        <NButton v-if="!disabled" strong type="success" class="add-branch" @click="addTerm">
          {{ $t('workflow.node.condition.addBranch') }}
        </NButton>
        <div v-for="(item, index) in nodeConfig.conditionNodes" :key="index" class="col-box">
          <div class="condition-node">
            <div class="condition-node-box">
              <div class="auto-judge" :class="getClass(item)" @click="showDetail(item, index)">
                <div v-if="index !== 0" class="sort-left" @click.stop="arrTransfer(index, -1)">
                  <icon-ant-design:left-outlined />
                </div>
                <div class="title">
                  <span class="node-title">
                    <NBadge dot processing color="#52c41a" />
                    &nbsp;{{ item.nodeName }}
                    <span
                      v-if="item.id && item.nodeName !== $t('workflow.node.condition.conditionNodes.otherNodeName')"
                    >
                      &nbsp;({{ item.id }})
                    </span>
                    <NTooltip v-if="item.nodeName === $t('workflow.node.condition.conditionNodes.otherNodeName')">
                      <template #trigger>
                        <icon-ant-design:info-circle-outlined class="ml-3px text-16px" />
                      </template>
                      {{ $t('workflow.node.condition.conditionNodes.otherTip') }}
                    </NTooltip>
                  </span>
                  <span class="priority-title">
                    {{ $t('workflow.node.condition.conditionNodes.priority') }}{{ item.priorityLevel }}
                  </span>
                  <icon-ant-design:close-outlined v-if="!disabled" class="close" @click.stop="delTerm(index)" />
                </div>
                <div class="content">
                  <span v-if="toText(nodeConfig, index)" v-html="toText(nodeConfig, index)"></span>
                  <span v-else class="placeholder">
                    {{ $t('workflow.node.condition.conditionNodes.conditionTip') }}
                  </span>
                </div>
                <div
                  v-if="index !== nodeConfig.conditionNodes!.length - 2"
                  class="sort-right"
                  @click.stop="arrTransfer(index)"
                >
                  <icon-ant-design:right-outlined />
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
              <AddNode v-model="item.childNode!" :disabled="disabled"></AddNode>
            </div>
          </div>
          <slot v-if="item.childNode" :node="item"></slot>
          <div v-if="index == 0" class="top-left-cover-line"></div>
          <div v-if="index == 0" class="bottom-left-cover-line"></div>
          <div v-if="index == nodeConfig.conditionNodes!.length - 1" class="top-right-cover-line"></div>
          <div v-if="index == nodeConfig.conditionNodes!.length - 1" class="bottom-right-cover-line"></div>
          <BranchDetail v-model:open="detailDrawer[index]" v-model="nodeConfig.conditionNodes![index]" />

          <DetailCard :id="detailId" v-model:show="cardDrawer[index]" :ids="detailIds">
            <div class="header-border">
              <span class="pl-12px">决策节点详情</span>
            </div>
            <BranchDesc :model-value="nodeConfig.conditionNodes![index]" />
          </DetailCard>
        </div>
      </div>
      <AddNode v-model="nodeConfig.childNode!" :disabled="disabled"></AddNode>
    </div>
    <BranchDrawer v-model:open="drawer" v-model="form" v-model:len="nodeConfig.conditionNodes!.length" @save="save" />
  </div>
</template>

<style scoped lang="scss">
.th {
  padding: 16px 24px;
  color: rgba(0, 0, 0, 0.88);
  font-weight: normal;
  font-size: 14px;
  line-height: 1.5714285714285714;
  text-align: start;
  box-sizing: border-box;
  background-color: rgba(0, 0, 0, 0.02);
  border-inline-end: 1px solid rgba(5, 5, 5, 0.06);
}

.auto-judge {
  white-space: pre;
  min-height: 132px !important;
}

.top-tips {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
  color: #646a73;
}

.or-branch-link-tip {
  margin: 10px 0;
  color: #646a73;
}

.condition-group-editor {
  user-select: none;
  border-radius: 4px;
  border: 1px solid #e4e5e7;
  position: relative;
  margin-bottom: 16px;

  .branch-delete-icon {
    font-size: 18px;
  }

  .header {
    background-color: #f4f6f8;
    padding: 0 12px;
    font-size: 14px;
    color: #171e31;
    height: 36px;
    display: flex;
    align-items: center;

    span {
      flex: 1;
    }
  }

  .main-content {
    padding: 0 12px;

    .condition-relation {
      color: #9ca2a9;
      display: flex;
      align-items: center;
      height: 36px;
      display: flex;
      justify-content: space-between;
      padding: 0 2px;
    }

    .condition-content-box {
      display: flex;
      justify-content: space-between;
      align-items: center;

      div {
        width: 100%;
        min-width: 120px;
      }

      div:not(:first-child) {
        margin-left: 16px;
      }
    }

    .cell-box {
      div {
        padding: 16px 0;
        width: 100%;
        min-width: 120px;
        color: #909399;
        font-size: 14px;
        font-weight: 600;
        text-align: center;
      }
    }

    .condition-content {
      display: flex;
      flex-direction: column;

      :deep(.el-input__wrapper) {
        border-top-left-radius: 0;
        border-bottom-left-radius: 0;
      }

      .content {
        flex: 1;
        padding: 0 0 4px 0;
        display: flex;
        align-items: center;
        min-height: 31.6px;
        flex-wrap: wrap;
      }
    }
  }

  .sub-content {
    padding: 12px;
  }
}

.node-disabled {
  color: #8f959e;

  .content {
    white-space: normal;
    word-break: break-word;
  }

  .title .node-title {
    color: #8f959e !important;
  }

  .node-title {
    display: flex !important;
    align-items: center;
    justify-content: flex-start;
  }
}

.node-disabled:hover {
  cursor: default;
}

.header-border {
  margin: 20px 0;
  border-left: #1366ff 5px solid;
  font-size: medium;
  font-weight: bold;
}
</style>
