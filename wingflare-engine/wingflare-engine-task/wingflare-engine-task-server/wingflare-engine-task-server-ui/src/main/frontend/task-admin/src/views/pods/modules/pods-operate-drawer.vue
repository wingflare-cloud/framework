<script setup lang="ts">
import { reactive, watch } from 'vue';
import { useNaiveForm } from '@/hooks/common/form';
import OperateDrawer from '@/components/common/operate-drawer.vue';
import { $t } from '@/locales';
import { fetchUpdatePodsLabels } from '@/service/api/dashboard';
import { nodeTypeOptions } from '@/constants/business';
import { translateOptions } from '@/utils/common';

defineOptions({
  name: 'PodsOperateDrawer'
});

interface Props {
  /** the edit row data */
  rowData?: Api.Dashboard.DashboardPod | null;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const visible = defineModel<boolean>('visible', {
  default: false
});

const { formRef, validate, restoreValidation } = useNaiveForm();

type Model = Pick<
  Api.Dashboard.DashboardPod,
  'id' | 'labels' | 'labelMap' | 'nodeType' | 'groupName' | 'hostIp' | 'hostPort'
>;

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    id: '',
    labels: '{}',
    labelMap: [],
    nodeType: 1,
    groupName: '',
    hostIp: '',
    hostPort: ''
  };
}

function handleUpdateModelWhenEdit() {
  model.labelMap = [];

  Object.assign(model, props.rowData);
  if (model.labels) {
    model.labelMap = Object.entries(JSON.parse(model.labels))
      .filter(([key, _]) => key !== 'state')
      .map(([key, value]) => {
        return { key, value: value as string };
      });
  }
}

async function handleSubmit() {
  await validate();

  const labels: Record<string, string> = {};
  model.labelMap?.forEach(item => {
    labels[item.key] = item.value;
  });

  // request
  const { error } = await fetchUpdatePodsLabels({ id: model.id, labels: JSON.stringify(labels) });
  if (error) return;
  window.$message?.success($t('common.updateSuccess'));

  closeDrawer();
  emit('submitted');
}

function closeDrawer() {
  visible.value = false;
}

watch(visible, () => {
  if (visible.value) {
    handleUpdateModelWhenEdit();
    restoreValidation();
  }
});
</script>

<template>
  <OperateDrawer v-model="visible" :min-size="480" title="编辑在线机器">
    <NForm ref="formRef" :model="model">
      <NFormItem label="ID" path="id">
        <NInput v-model:value="model.id" disabled />
      </NFormItem>
      <NFormItem label="类型" path="nodeType">
        <NSelect v-model:value="model.nodeType" :options="translateOptions(nodeTypeOptions)" disabled />
      </NFormItem>
      <NFormItem label="组名称" path="groupName">
        <NInput v-model:value="model.groupName" disabled />
      </NFormItem>
      <NFormItem label="IP" path="hostIp">
        <NInput v-model:value="model.hostIp" disabled />
      </NFormItem>
      <NFormItem label="端口" path="hostPort">
        <NInput v-model:value="model.hostPort" disabled />
      </NFormItem>
      <NFormItem label="标签" path="labelMap" :show-feedback="!model.labelMap?.length">
        <LabelsInput v-model:value="model.labelMap" path="labelMap" />
      </NFormItem>
    </NForm>
    <template #footer>
      <NSpace :size="16">
        <NButton @click="closeDrawer">{{ $t('common.cancel') }}</NButton>
        <NButton type="primary" @click="handleSubmit">{{ $t('common.save') }}</NButton>
      </NSpace>
    </template>
  </OperateDrawer>
</template>

<style scoped></style>
