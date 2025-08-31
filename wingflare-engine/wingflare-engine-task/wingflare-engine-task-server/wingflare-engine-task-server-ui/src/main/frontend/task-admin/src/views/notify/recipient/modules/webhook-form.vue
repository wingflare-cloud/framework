<script setup lang="ts">
import { reactive, watch } from 'vue';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { alarmWebhookTypeRecordOptions } from '@/constants/business';

defineOptions({
  name: 'WebhookForm'
});

interface Props {
  value: Api.NotifyRecipient.NotifyRecipient;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'update:value', value: Api.NotifyRecipient.NotifyRecipient): void;
}

const emit = defineEmits<Emits>();

const { formRef, validate, restoreValidation } = useNaiveForm();
const { defaultRequiredRule } = useFormRules();

type Model = Pick<
  Api.NotifyRecipient.WebhookNotify,
  'id' | 'recipientName' | 'notifyType' | 'webhookUrl' | 'secret' | 'description' | 'contentType'
>;
const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  const { webhookUrl, contentType, secret } = JSON.parse(props.value.notifyAttribute!) as {
    webhookUrl: string;
    contentType: string;
    secret: string;
  };

  return {
    id: props.value.id,
    recipientName: props.value.recipientName,
    notifyType: 5,
    contentType,
    webhookUrl,
    secret,
    description: props.value.description
  };
}

type RuleKey = Extract<keyof Model, 'recipientName' | 'notifyType' | 'webhookUrl' | 'secret' | 'contentType'>;

const rules: Record<RuleKey, App.Global.FormRule> = {
  contentType: defaultRequiredRule,
  recipientName: defaultRequiredRule,
  notifyType: defaultRequiredRule,
  webhookUrl: defaultRequiredRule,
  secret: defaultRequiredRule
};

const buildNotifyAttribute = (webhookUrl: string, contentType: string, secret?: string) => {
  return JSON.stringify({ webhookUrl, contentType, secret });
};

watch(
  () => model,
  () => {
    const { id, recipientName, notifyType, webhookUrl, secret, description, contentType } = model;
    const notifyAttribute = buildNotifyAttribute(webhookUrl, contentType, secret);
    emit('update:value', { id, recipientName, notifyType, notifyAttribute, description });
  },
  { immediate: true, deep: true }
);

defineExpose({
  validate,
  restoreValidation
});
</script>

<template>
  <NForm ref="formRef" :model="model" :rules="rules">
    <NFormItem :label="$t('page.notifyRecipient.recipientName')" path="recipientName">
      <NInput v-model:value="model.recipientName" :placeholder="$t('page.notifyRecipient.form.recipientName')" />
    </NFormItem>
    <NFormItem :label="$t('page.notifyRecipient.webhookUrl')" path="webhookUrl">
      <NInput v-model:value="model.webhookUrl" :placeholder="$t('page.notifyRecipient.form.webhookUrl')" clearable />
    </NFormItem>
    <NFormItem :label="$t('page.notifyRecipient.notifyType')" path="notifyType">
      <NSelect
        v-model:value="model.contentType"
        :options="translateOptions(alarmWebhookTypeRecordOptions)"
        :placeholder="$t('page.notifyRecipient.contentType')"
        clearable
      />
    </NFormItem>
    <NFormItem :label="$t('page.notifyRecipient.secret')" path="secret">
      <NInput v-model:value="model.secret" :placeholder="$t('page.notifyRecipient.form.secret')" clearable />
    </NFormItem>
    <NFormItem :label="$t('page.notifyRecipient.description')" path="description">
      <NInput
        v-model:value="model.description"
        type="textarea"
        :placeholder="$t('page.notifyRecipient.form.description')"
        clearable
        round
      />
    </NFormItem>
  </NForm>
</template>

<style scoped></style>
