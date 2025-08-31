<script setup lang="ts">
import { computed, reactive } from 'vue';
import OperateDrawer from '@/components/common/operate-drawer.vue';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import { $t } from '@/locales';
import { md5 } from '@/utils/common';
import { fetchUpdateUserPassword } from '@/service/api';
import { useAppStore } from '@/store/modules/app';

defineOptions({
  name: 'ChangePassword'
});

const appStore = useAppStore();

const visible = defineModel<boolean>('visible', {
  default: false
});

type Model = Pick<Api.UserManager.UpdateUserPassword, 'oldPassword' | 'newPassword' | 'checkPassword'>;

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    oldPassword: '',
    newPassword: '',
    checkPassword: ''
  };
}

const { formRef, validate } = useNaiveForm();

type RuleRecord = Partial<Record<keyof Model, App.Global.FormRule[]>>;

const rules = computed<RuleRecord>(() => {
  const { formRules, createConfirmPwdRule, defaultRequiredRule } = useFormRules();

  return {
    oldPassword: [defaultRequiredRule],
    newPassword: formRules.pwd,
    checkPassword: createConfirmPwdRule(model.newPassword!)
  };
});

function closeDrawer() {
  visible.value = false;
}

async function handleSubmit() {
  await validate();

  const { oldPassword, newPassword } = model;
  const { error } = await fetchUpdateUserPassword({
    oldPassword: md5(oldPassword!),
    newPassword: md5(newPassword!)
  });
  if (error) return;
  window.$message?.success($t('common.updateSuccess'));

  closeDrawer();
  appStore.reloadPage(500);
}
</script>

<template>
  <OperateDrawer v-model="visible" :title="$t('common.changePassword')">
    <NForm ref="formRef" :model="model" :rules="rules">
      <NFormItem :label="$t('page.userManager.oldPassword')" path="oldPassword">
        <NInput
          v-model:value="model.oldPassword"
          type="password"
          show-password-on="click"
          :placeholder="$t('page.userManager.form.oldPassword')"
        />
      </NFormItem>
      <NFormItem :label="$t('page.userManager.newPassword')" path="newPassword">
        <NInput
          v-model:value="model.newPassword"
          type="password"
          show-password-on="click"
          :placeholder="$t('page.userManager.form.newPassword')"
        />
      </NFormItem>
      <NFormItem :label="$t('page.userManager.checkPassword')" path="checkPassword">
        <NInput
          v-model:value="model.checkPassword"
          type="password"
          show-password-on="click"
          :placeholder="$t('page.userManager.form.checkPassword')"
        />
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
