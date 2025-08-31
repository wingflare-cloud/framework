<script setup lang="ts">
import { computed, reactive, watch } from 'vue';
import { nanoid } from '@sa/utils';
import { useClipboard } from '@vueuse/core';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import OperateDrawer from '@/components/common/operate-drawer.vue';
import { $t } from '@/locales';
import { fetchAddNamespace, fetchEditNamespace } from '@/service/api';
import { useAuthStore } from '@/store/modules/auth';

defineOptions({
  name: 'NamespaceOperateDrawer'
});

interface Props {
  /** the type of operation */
  operateType: NaiveUI.TableOperateType;
  /** the edit row data */
  rowData?: Api.Namespace.Namespace | null;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const { copy, isSupported } = useClipboard();
const authStore = useAuthStore();
const visible = defineModel<boolean>('visible', {
  default: false
});
const { formRef, validate, restoreValidation } = useNaiveForm();
const { defaultRequiredRule } = useFormRules();

const title = computed(() => {
  const titles: Record<NaiveUI.TableOperateType, string> = {
    add: $t('page.namespace.addNamespace'),
    edit: $t('page.namespace.editNamespace')
  };
  return titles[props.operateType];
});

type Model = Pick<Api.Namespace.Namespace, 'id' | 'name' | 'uniqueId'>;

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    name: '',
    uniqueId: ''
  };
}

type RuleKey = Extract<keyof Model, App.Global.FormRule>;

const rules: Record<RuleKey, App.Global.FormRule> = {
  name: defaultRequiredRule,
  uniqueId: {
    required: false,
    pattern: /^[A-Za-z0-9_-]{1,64}$/,
    trigger: 'change',
    message: $t('page.namespace.form.uniqueIdRule')
  }
};

function setUniqueId() {
  model.uniqueId = nanoid(32);
}

function handleUpdateModelWhenEdit() {
  if (props.operateType === 'add') {
    Object.assign(model, createDefaultModel());
    return;
  }

  if (props.operateType === 'edit' && props.rowData) {
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
    const { name, uniqueId } = model;
    const { error } = await fetchAddNamespace({ name, uniqueId });
    if (error) return;
    window.$message?.success($t('common.addSuccess'));
  }

  if (props.operateType === 'edit') {
    const { id, name, uniqueId } = model;
    const { error } = await fetchEditNamespace({ id, name, uniqueId });
    if (error) return;
    window.$message?.success($t('common.updateSuccess'));
  }

  await authStore.getUserInfo();
  closeDrawer();
  emit('submitted');
}

watch(visible, () => {
  if (visible.value) {
    handleUpdateModelWhenEdit();
    restoreValidation();
  }
});

async function handleCopy(source: string) {
  if (!isSupported) {
    window.$message?.error('您的浏览器不支持 Clipboard API');
    return;
  }

  if (!source) {
    return;
  }

  if (navigator.clipboard && window.isSecureContext) {
    await copy(source);
  } else {
    const range = document.createRange();
    range.selectNode(document.getElementById('uniqueIdInput')!);
    const selection = window.getSelection();
    if (selection?.rangeCount) selection.removeAllRanges();
    selection?.addRange(range);
    document.execCommand('copy');
  }
  window.$message?.success('复制成功');
}
</script>

<template>
  <OperateDrawer v-model="visible" :title="title" @submitted="handleSubmit">
    <NForm ref="formRef" :model="model" :rules="rules">
      <NFormItem :label="$t('page.namespace.uniqueId')" path="uniqueId">
        <NInputGroup>
          <NInput
            id="uniqueIdInput"
            v-model:value="model.uniqueId"
            :disabled="props.operateType === 'edit'"
            :placeholder="$t('page.namespace.form.uniqueId')"
          />
          <NTooltip v-if="props.operateType === 'edit'" trigger="hover">
            <template #trigger>
              <NButton type="default" ghost @click="handleCopy(model.uniqueId)">
                <icon-ic:round-content-copy class="text-icon" />
              </NButton>
            </template>
            复制
          </NTooltip>
          <NTooltip v-else trigger="hover">
            <template #trigger>
              <NButton type="default" ghost @click="setUniqueId">
                <icon-ic-round-refresh class="text-icon" />
              </NButton>
            </template>
            {{ $t('common.generateRandomly') }}
          </NTooltip>
        </NInputGroup>
      </NFormItem>
      <NFormItem :label="$t('page.namespace.name')" path="name">
        <NInput v-model:value="model.name" :placeholder="$t('page.namespace.form.name')" />
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
