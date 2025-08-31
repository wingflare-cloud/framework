<script setup lang="ts">
import { computed, onMounted, reactive, ref, watch } from 'vue';
import type { OptionValue } from 'naive-ui/es/transfer/src/interface';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import OperateDrawer from '@/components/common/operate-drawer.vue';
import { $t } from '@/locales';
import { md5 } from '@/utils/common';
import { fetchAddUser, fetchEditUser, fetchGetAllGroupConfigList } from '@/service/api';
import { groupConfigYesOrNoOptions, roleRecordOptions } from '@/constants/business';
import Permission = Api.UserManager.Permission;

defineOptions({
  name: 'UserManagerOperateDrawer'
});

interface Props {
  /** the type of operation */
  operateType: NaiveUI.TableOperateType;
  /** the edit row data */
  rowData?: Api.UserManager.UserManager | null;
}

const valueRef = ref();
const groupConfigs = ref();
const updatePass = ref<number>(0);

const props = defineProps<Props>();

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const visible = defineModel<boolean>('visible', {
  default: false
});

const { formRef, validate, restoreValidation } = useNaiveForm();
const { defaultRequiredRule } = useFormRules();

const title = computed(() => {
  const titles: Record<NaiveUI.TableOperateType, string> = {
    add: $t('page.userManager.addUser'),
    edit: $t('page.userManager.editUser')
  };
  return titles[props.operateType];
});

type Model = Pick<
  Api.UserManager.UserManager,
  'id' | 'username' | 'password' | 'checkPassword' | 'role' | 'permissions'
>;

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    username: '',
    password: '',
    checkPassword: '',
    role: 1,
    permissions: []
  };
}

type RuleRecord = Partial<Record<keyof Model, App.Global.FormRule[]>>;

const rules = computed<RuleRecord>(() => {
  const { formRules, createConfirmPwdRule } = useFormRules();

  return {
    username: [defaultRequiredRule],
    password: formRules.pwd,
    checkPassword: createConfirmPwdRule(model.password!),
    role: [defaultRequiredRule],
    permissions: [defaultRequiredRule]
  };
});

function handleUpdateModelWhenEdit() {
  if (props.operateType === 'add') {
    valueRef.value = [];
    updatePass.value = 1;
    Object.assign(model, createDefaultModel());
    return;
  }

  if (props.operateType === 'edit' && props.rowData) {
    updatePass.value = 0;
    valueRef.value = props.rowData.permissions?.map(v => `${v.groupName}@${v.namespaceId}`);
    Object.assign(model, props.rowData);
  }
}

function closeDrawer() {
  visible.value = false;
}

async function handleSubmit() {
  await validate();
  // request
  if (props.operateType === 'add') {
    const { username, password, role, permissions } = model;
    const { error } = await fetchAddUser({
      username,
      password: md5(password!),
      role,
      permissions
    });
    if (error) return;
    window.$message?.success($t('common.addSuccess'));
  }

  if (props.operateType === 'edit') {
    const { id, username, password, role, permissions } = model;
    const { error } = await fetchEditUser({
      id,
      username,
      password: updatePass.value ? md5(password!) : null,
      role,
      permissions
    });
    if (error) return;
    window.$message?.success($t('common.updateSuccess'));
  }

  closeDrawer();
  emit('submitted');
}

const getAllGroupConfigList = async () => {
  const res = await fetchGetAllGroupConfigList([]);
  groupConfigs.value = res.data?.map(option => ({
    value: `${option.groupName}@${option.namespaceId}`,
    label: `${option.groupName}(${option.namespaceName})`
  }));
};

onMounted(() => {
  // 加载组列表数据
  getAllGroupConfigList();
});

watch(visible, () => {
  if (visible.value) {
    handleUpdateModelWhenEdit();
    restoreValidation();
  }
});

type PermissionModel = Pick<Permission, 'groupName' | 'namespaceId'>;

function getPermission(str: string): PermissionModel {
  const permissionModelComputedRef = computed<PermissionModel>(() => {
    const [groupName, namespaceId] = str.split('@'); // 将字符串分割成数组
    return {
      groupName,
      namespaceId
    };
  });

  return permissionModelComputedRef.value;
}

function updatePermissions(p: OptionValue[]) {
  // ['snail_job_demo_group@764d604ec6fc45f68cd92514c40e9e1a']
  model.permissions = p?.map(value => {
    return getPermission(value as string);
  });
}
</script>

<template>
  <OperateDrawer v-model="visible" :title="title">
    <NForm ref="formRef" :model="model" :rules="rules">
      <NFormItem :label="$t('page.userManager.username')" path="username">
        <NInput v-model:value="model.username" :placeholder="$t('page.userManager.form.username')" />
      </NFormItem>
      <NFormItem v-if="props.operateType === 'edit'" :label="$t('page.userManager.updatePassword')">
        <NRadioGroup v-model:value="updatePass">
          <NSpace>
            <NRadio
              v-for="item in groupConfigYesOrNoOptions"
              :key="item.value"
              :value="item.value"
              :label="$t(item.label)"
            />
          </NSpace>
        </NRadioGroup>
      </NFormItem>
      <NFormItem v-if="updatePass === 1" :label="$t('page.userManager.password')" path="password">
        <NInput
          v-model:value="model.password"
          type="password"
          show-password-on="click"
          :placeholder="$t('page.userManager.form.password')"
        />
      </NFormItem>
      <NFormItem v-if="updatePass === 1" :label="$t('page.userManager.checkPassword')" path="checkPassword">
        <NInput
          v-model:value="model.checkPassword"
          type="password"
          show-password-on="click"
          :placeholder="$t('page.userManager.form.checkPassword')"
        />
      </NFormItem>
      <NFormItem :label="$t('page.userManager.role')" path="role">
        <NRadioGroup v-model:value="model.role" name="role">
          <NSpace>
            <NRadio
              v-for="item in roleRecordOptions"
              :key="item.value"
              :value="item.value"
              :label="$t(item.label)"
              :disabled="operateType === 'edit' && model.id == '1'"
            />
          </NSpace>
        </NRadioGroup>
      </NFormItem>
      <NFormItem v-if="model.role === 1" :label="$t('page.userManager.permissions')" path="permissions">
        <NTransfer
          v-model:value="valueRef"
          virtual-scroll
          :options="groupConfigs"
          target-filterable
          source-filterable
          @update-value="updatePermissions"
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
