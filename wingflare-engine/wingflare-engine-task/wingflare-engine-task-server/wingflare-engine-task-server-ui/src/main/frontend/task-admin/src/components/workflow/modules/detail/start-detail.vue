<script setup lang="ts">
import { ref, watch } from 'vue';
import { $t } from '@/locales';
import { blockStrategyRecord, triggerTypeRecord, workFlowNodeStatusRecord } from '@/constants/business';

defineOptions({
  name: 'StartDetail'
});

interface Props {
  modelValue?: Workflow.NodeDataType;
  open?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  open: false,
  modelValue: () => ({})
});

interface Emits {
  (e: 'update:open', open: boolean): void;
}

const emit = defineEmits<Emits>();

const visible = ref(false);

watch(
  () => props.open,
  val => {
    visible.value = val;
  },
  { immediate: true }
);

const onClose = () => {
  emit('update:open', false);
};
</script>

<template>
  <DetailDrawer v-model="visible" title="工作流详情" :width="['500px', '90%']" @after-leave="onClose">
    <NDescriptions :column="1" label-placement="left" bordered :label-style="{ width: '120px' }">
      <NDescriptionsItem label="工作流名称">{{ modelValue.workflowName }}</NDescriptionsItem>
      <NDescriptionsItem label="组名称">{{ modelValue.groupName }}</NDescriptionsItem>
      <NDescriptionsItem label="触发类型">{{ $t(triggerTypeRecord[modelValue.triggerType!]) }}</NDescriptionsItem>

      <NDescriptionsItem label="触发间隔">
        {{ modelValue.triggerInterval }} {{ modelValue.triggerType === 2 ? '秒' : null }}
      </NDescriptionsItem>

      <NDescriptionsItem label="执行超时时间">{{ modelValue.executorTimeout }} 秒</NDescriptionsItem>
      <NDescriptionsItem label="阻塞策略">{{ $t(blockStrategyRecord[modelValue.blockStrategy!]) }}</NDescriptionsItem>
      <NDescriptionsItem label="工作流上下文">{{ modelValue.wfContext }}</NDescriptionsItem>
      <NDescriptionsItem label="工作流状态">
        {{ $t(workFlowNodeStatusRecord[modelValue.workflowStatus!]) }}
      </NDescriptionsItem>
    </NDescriptions>
  </DetailDrawer>
</template>
