<script setup lang="ts">
import { reactive, watch } from 'vue';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import { $t } from '@/locales';

defineOptions({
  name: 'EmailForm'
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

type Model = Pick<Api.NotifyRecipient.EmailNotify, 'id' | 'recipientName' | 'notifyType' | 'tos' | 'description'>;
const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  const { tos } = JSON.parse(props.value.notifyAttribute!) as { tos: string[] };
  return {
    id: props.value.id,
    recipientName: props.value.recipientName,
    notifyType: 2,
    tos,
    description: props.value.description
  };
}

type RuleKey = Extract<keyof Model, 'recipientName' | 'notifyType' | 'tos'>;

const rules: Record<RuleKey, App.Global.FormRule> = {
  recipientName: defaultRequiredRule,
  notifyType: defaultRequiredRule,
  tos: defaultRequiredRule
};

const buildNotifyAttribute = (tos: string[]) => {
  return JSON.stringify({ tos });
};

watch(
  () => model,
  () => {
    const { id, recipientName, notifyType, tos, description } = model;
    const notifyAttribute = buildNotifyAttribute(tos);
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
    <NFormItem :label="$t('page.notifyRecipient.tos')" path="tos">
      <NDynamicTags v-model:value="model.tos" />
    </NFormItem>
    <NFormItem :label="$t('page.notifyRecipient.description')" path="description">
      <NInput
        v-model:value="model.description"
        type="textarea"
        :placeholder="$t('page.notifyRecipient.form.description')"
        round
        clearable
      />
    </NFormItem>
  </NForm>
</template>

<style scoped></style>
