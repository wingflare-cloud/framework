<script setup lang="ts">
import { reactive, watch } from 'vue';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import { $t } from '@/locales';

defineOptions({
  name: 'LarkForm'
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
  Api.NotifyRecipient.DingDingNotify,
  'id' | 'recipientName' | 'notifyType' | 'webhookUrl' | 'ats' | 'description' | 'notifyAttribute'
>;
const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  const { webhookUrl, ats } = JSON.parse(props.value.notifyAttribute!) as { webhookUrl: string; ats: string[] };

  return {
    id: props.value.id,
    recipientName: props.value.recipientName,
    notifyType: 4,
    webhookUrl,
    ats,
    description: props.value.description
  };
}

type RuleKey = Extract<keyof Model, 'recipientName' | 'notifyType' | 'webhookUrl' | 'ats'>;

const rules: Record<RuleKey, App.Global.FormRule> = {
  recipientName: defaultRequiredRule,
  notifyType: defaultRequiredRule,
  webhookUrl: defaultRequiredRule,
  ats: defaultRequiredRule
};

const buildNotifyAttribute = (webhookUrl: string, ats: string[]) => {
  return JSON.stringify({ webhookUrl, ats });
};

watch(
  () => model,
  () => {
    const { id, recipientName, notifyType, webhookUrl, ats, description } = model;
    const notifyAttribute = buildNotifyAttribute(webhookUrl, ats);
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
      <NInput
        v-model:value="model.recipientName"
        :placeholder="$t('page.notifyRecipient.form.recipientName')"
        clearable
      />
    </NFormItem>
    <NFormItem :label="$t('page.notifyRecipient.webhookUrl')" path="webhookUrl">
      <NInput v-model:value="model.webhookUrl" :placeholder="$t('page.notifyRecipient.form.webhookUrl')" clearable />
    </NFormItem>
    <NFormItem path="ats">
      <template #label>
        <a href="#">
          <NTooltip trigger="hover">
            <template #trigger>
              {{ $t('page.notifyRecipient.ats') }}
            </template>
            {{ $t('page.notifyRecipient.form.larkAts') }}
          </NTooltip>
        </a>
      </template>
      <NDynamicTags v-model:value="model.ats" />
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
