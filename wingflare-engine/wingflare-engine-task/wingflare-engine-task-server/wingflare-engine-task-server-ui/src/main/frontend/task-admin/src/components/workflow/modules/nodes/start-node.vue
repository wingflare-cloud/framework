<script lang="ts" setup>
import { ref, watch } from 'vue';
import { $t } from '@/locales';
import { useWorkflowStore } from '@/store/modules/workflow';
import { blockStrategyRecord, taskBatchStatusEnum } from '@/constants/business';
import StartDetail from '../detail/start-detail.vue';
import StartDrawer from '../drawer/start-drawer.vue';
import AddNode from './add-node.vue';

defineOptions({
  name: 'StartNode'
});

interface Props {
  modelValue?: Workflow.NodeDataType;
  disabled?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
  modelValue: () => ({})
});

interface Emits {
  (e: 'update:modelValue', modelValue: Workflow.NodeDataType): void;
}

const emit = defineEmits<Emits>();

const store = useWorkflowStore();
const form = ref<Workflow.NodeDataType>({});
const nodeData = ref<Workflow.NodeDataType>({});

watch(
  () => props.modelValue,
  val => {
    nodeData.value = val;
  },
  { immediate: true }
);

watch(
  () => nodeData.value,
  val => {
    emit('update:modelValue', val);
  }
);

watch(
  () => nodeData.value?.groupName,
  val => {
    if (val) {
      store.setJobList(val);
    }
  },
  { immediate: true }
);

const drawer = ref<boolean>(false);
const detailDrawer = ref<boolean>(false);

const save = (val: Workflow.NodeDataType) => {
  nodeData.value = val;
};

const show = () => {
  if (store.type === 0) {
    form.value = JSON.parse(JSON.stringify(nodeData.value));
    drawer.value = true;
  } else {
    detailDrawer.value = true;
  }
};
</script>

<template>
  <div class="node-wrap">
    <div
      :class="`${disabled ? 'start-node-disabled' : 'node-wrap-box-hover'} ${store.type === 2 ? 'node-error-success' : ''}`"
      class="node-wrap-box start-node"
      @click="show"
    >
      <div class="title">
        <span class="text">
          <NBadge dot :color="nodeData.workflowStatus === 1 ? '#52c41a' : '#ff000d'" />
          <span class="text-#ff943e">
            &nbsp;{{
              nodeData.workflowName ? `${nodeData.workflowName} ${nodeData.id ? ` (${nodeData.id})` : ''}` : '请选择组'
            }}
          </span>
        </span>
      </div>
      <div v-if="nodeData.groupName" class="content">
        <div>
          <NEllipsis class="max-w-132px">
            <span class="content_label">组名称:&nbsp;</span>
            {{ nodeData.groupName }}
          </NEllipsis>
        </div>
        <div>
          <span class="content_label">阻塞策略:&nbsp;</span>
          {{ $t(blockStrategyRecord[nodeData.blockStrategy!]) }}
        </div>
        <div>.........</div>
      </div>
      <div v-else class="content min-h-85px">
        <span class="placeholder">请配置工作流</span>
      </div>
      <NTooltip v-if="store.type === 2">
        <template #trigger>
          <div class="error-tip text-24px" :style="{ color: taskBatchStatusEnum[3].color }">
            <SvgIcon :icon="taskBatchStatusEnum[3].icon" />
          </div>
        </template>
        {{ taskBatchStatusEnum[3].title }}
      </NTooltip>
    </div>
    <AddNode v-model="nodeData.nodeConfig!" :disabled="disabled"></AddNode>
    <StartDetail v-if="store.type !== 0" v-model:open="detailDrawer" v-model="nodeData" />
    <StartDrawer v-model:open="drawer" v-model="form" @save="save" />
  </div>
</template>

<style scoped>
.content {
  line-height: 136%;
}
</style>
