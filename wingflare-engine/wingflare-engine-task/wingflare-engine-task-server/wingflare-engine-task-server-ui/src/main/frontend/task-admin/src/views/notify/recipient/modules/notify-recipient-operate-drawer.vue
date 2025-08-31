<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import { useNaiveForm } from '@/hooks/common/form';
import OperateDrawer from '@/components/common/operate-drawer.vue';
import { $t } from '@/locales';
import { fetchAddNotifyRecipient, fetchEditNotifyRecipient } from '@/service/api';
import DingDingForm from './dingding-form.vue';
import LarkForm from './lark-form.vue';
import EmailForm from './email-form.vue';
import WeComForm from './wecom-form.vue';
import WebhookForm from './webhook-form.vue';

defineOptions({
  name: 'NotifyRecipientOperateDrawer'
});

interface Props {
  /** the type of operation */
  operateType: NaiveUI.TableOperateType;
  /** the edit row data */
  rowData?: Api.NotifyRecipient.NotifyRecipient | null;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const visible = defineModel<boolean>('visible', {
  default: false
});

const notifyTabPane = defineModel<Api.NotifyRecipient.AlarmType>('notifyTabPane', {
  default: 1
});

const { formRef, validate, restoreValidation } = useNaiveForm();

const title = computed(() => {
  const titles: Record<NaiveUI.TableOperateType, string> = {
    add: $t('page.notifyRecipient.addNotifyRecipient'),
    edit: $t('page.notifyRecipient.editNotifyRecipient')
  };
  return titles[props.operateType];
});

type Model = Pick<
  Api.NotifyRecipient.NotifyRecipient,
  'id' | 'recipientName' | 'notifyType' | 'notifyAttribute' | 'description'
>;
const model = ref<Model>(createDefaultModel());

function createDefaultModel(): Model {
  return {
    recipientName: '',
    notifyType: notifyTabPane.value!,
    notifyAttribute: '{}',
    description: ''
  };
}

function handleUpdateModelWhenEdit() {
  if (props.operateType === 'add') {
    model.value = createDefaultModel();
    notifyTabPane.value = 1;
    return;
  }

  if (props.operateType === 'edit' && props.rowData) {
    model.value = props.rowData;
    notifyTabPane.value = props.rowData.notifyType;
  }
}

async function handleSubmit() {
  await validate();
  // request
  if (props.operateType === 'add') {
    const { recipientName, notifyAttribute, notifyType, description } = model.value;
    const { error } = await fetchAddNotifyRecipient({ recipientName, notifyAttribute, notifyType, description });
    if (error) return;
    window.$message?.success($t('common.addSuccess'));
  }

  if (props.operateType === 'edit') {
    const { id, recipientName, notifyAttribute, notifyType, description } = model.value;
    const { error } = await fetchEditNotifyRecipient({ id, recipientName, notifyAttribute, notifyType, description });
    if (error) return;
    window.$message?.success($t('common.updateSuccess'));
  }
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
  <OperateDrawer v-model="visible" :min-size="480" :title="title">
    <NTabs v-model:value="notifyTabPane" type="segment" animated>
      <NTabPane :name="1" tab="钉钉" :disabled="notifyTabPane !== 1 && props.operateType === 'edit'">
        <DingDingForm ref="formRef" v-model:value="model" />
      </NTabPane>
      <NTabPane :name="2" tab="邮箱" :disabled="notifyTabPane !== 2 && props.operateType === 'edit'">
        <EmailForm ref="formRef" v-model:value="model" />
      </NTabPane>
      <NTabPane :name="3" tab="企业微信" :disabled="notifyTabPane !== 3 && props.operateType === 'edit'">
        <WeComForm ref="formRef" v-model:value="model" />
      </NTabPane>
      <NTabPane :name="4" tab="飞书" :disabled="notifyTabPane !== 4 && props.operateType === 'edit'">
        <LarkForm ref="formRef" v-model:value="model" />
      </NTabPane>
      <NTabPane :name="5" tab="Webhook" :disabled="notifyTabPane !== 5 && props.operateType === 'edit'">
        <WebhookForm ref="formRef" v-model:value="model" />
      </NTabPane>
    </NTabs>
    <template #footer>
      <NSpace :size="16">
        <NButton @click="closeDrawer">{{ $t('common.cancel') }}</NButton>
        <NButton type="primary" @click="handleSubmit">{{ $t('common.save') }}</NButton>
      </NSpace>
    </template>
  </OperateDrawer>
</template>

<style scoped></style>
