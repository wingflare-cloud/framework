<script setup lang="ts">
import { reactive, watch } from 'vue';
import { $t } from '@/locales';
import { fetchTriggerWorkflowParams } from '@/service/api';
import { parseContent, stringToContent } from '@/utils/common';

defineOptions({
  name: 'WorkflowTriggerModal'
});

interface Props {
  /** the edit row data */
  rowData?: Api.Workflow.Workflow | null;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const visible = defineModel<boolean>('visible', {
  default: false
});

type Model = Api.Workflow.WorkflowTriggerParams & {
  wfContexts: { key: string; value: string | number | boolean; type: string }[];
};

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    workflowId: props.rowData?.id,
    tmpWfContext: '',
    wfContexts: []
  };
}

function handleUpdateModelWhenEdit() {
  const rowData = props.rowData;
  if (!rowData) {
    Object.assign(model, createDefaultModel());
    return;
  }

  const wfContext = rowData?.wfContext;
  if (wfContext) {
    model.wfContexts = stringToContent(rowData?.wfContext) || [];
  }
}

function closeDrawer() {
  visible.value = false;
}

watch(visible, () => {
  if (visible.value) {
    handleUpdateModelWhenEdit();
  }
});

async function handleSubmit() {
  const tmpWfContext = JSON.stringify(parseContent(model.wfContexts) || {});
  const { error } = await fetchTriggerWorkflowParams({ workflowId: props.rowData?.id, tmpWfContext });
  if (error) return;

  window.$message?.success($t('common.executeSuccess'));

  closeDrawer();
  emit('submitted');
}
</script>

<template>
  <NModal v-model:show="visible" class="max-w-90% w-600px" preset="card" title="执行工作流" :bordered="false">
    <NForm :model="model">
      <NFormItem path="wfContexts" label="工作流上下文" :show-feedback="model.wfContexts?.length ? false : true">
        <DynamicInput v-model:value="model.wfContexts" path="wfContexts" />
      </NFormItem>
    </NForm>
    <template #footer>
      <NSpace justify="end" :size="16">
        <NButton @click="closeDrawer">{{ $t('common.cancel') }}</NButton>
        <NButton type="primary" @click="handleSubmit">执行</NButton>
      </NSpace>
    </template>
  </NModal>
</template>

<style scoped>
.http-method {
  width: 130px !important;
}
</style>
