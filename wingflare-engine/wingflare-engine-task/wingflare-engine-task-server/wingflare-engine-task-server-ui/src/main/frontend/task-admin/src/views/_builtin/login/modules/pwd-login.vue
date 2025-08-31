<script setup lang="ts">
import { reactive, ref } from 'vue';
import Vcode from 'vue3-puzzle-vcode';
import { md5 } from '@/utils/common';
import { $t } from '@/locales';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import { useAuthStore } from '@/store/modules/auth';

defineOptions({
  name: 'PwdLogin'
});

const devMode = import.meta.env.DEV;

const authStore = useAuthStore();
const { formRef, validate } = useNaiveForm();
const { defaultRequiredRule } = useFormRules();

interface FormModel {
  userName: string;
  password: string;
}

const model: FormModel = reactive({
  userName: devMode ? 'admin' : '',
  password: devMode ? 'admin' : ''
});

type RuleKey = Extract<keyof FormModel, 'userName' | 'password'>;

const rules: Record<RuleKey, App.Global.FormRule> = {
  userName: defaultRequiredRule,
  password: defaultRequiredRule
};

async function handleSubmit() {
  const password = md5(model.password);
  await authStore.login(model.userName, password);
}

const codeShow = ref(false);

const validateCode = async () => {
  const { VITE_LOGIN_CODE } = import.meta.env;
  await validate();
  if (VITE_LOGIN_CODE === 'Y') {
    codeShow.value = true;
    return;
  }
  handleSubmit();
};

const onClose = () => {
  codeShow.value = false;
};

const onSuccess = () => {
  handleSubmit();
};

const codePopoverStytle = {
  padding: 0
};
</script>

<template>
  <NForm ref="formRef" :model="model" :rules="rules" size="large" :show-label="false" @keyup.enter="handleSubmit">
    <NFormItem path="userName">
      <NInput v-model:value="model.userName" :placeholder="$t('page.login.common.userNamePlaceholder')" />
    </NFormItem>
    <NFormItem path="password">
      <NInput
        v-model:value="model.password"
        type="password"
        show-password-on="click"
        :placeholder="$t('page.login.common.passwordPlaceholder')"
      />
    </NFormItem>
    <NSpace vertical :size="24">
      <NPopover :show="codeShow" row :style="codePopoverStytle">
        <template #trigger>
          <NButton type="primary" size="large" round block :loading="authStore.loginLoading" @click="validateCode">
            {{ $t('page.login.common.login') }}
          </NButton>
        </template>
        <NCard :title="$t('page.login.common.codeTip')" :header-style="{ padding: '10px 24px' }">
          <template #header-extra>
            <NButton text @click="onClose">
              <template #icon>
                <SvgIcon local-icon="close" />
              </template>
            </NButton>
          </template>
          <Vcode type="inside" show @success="onSuccess" @close="onClose" />
        </NCard>
      </NPopover>
    </NSpace>
  </NForm>
</template>

<style scoped></style>
