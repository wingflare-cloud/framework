<script setup lang="ts">
import { computed, reactive, ref, watch } from 'vue';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import OperateDrawer from '@/components/common/operate-drawer.vue';
import { $t } from '@/locales';
import { fetchAddRetry, fetchIdempotentIdGenerate } from '@/service/api';
import { translateOptions } from '@/utils/common';
import { retryStatusTypeOptions } from '@/constants/business';
import CodeMirror from '@/components/common/code-mirror.vue';
import SelectGroup from '@/components/common/select-group.vue';
import SelectScene from '@/components/common/select-scene.vue';

defineOptions({
  name: 'RetryTaskOperateDrawer'
});

interface Props {
  /** the type of operation */
  operateType: NaiveUI.TableOperateType;
  /** the edit row data */
  rowData?: Api.Retry.Retry | null;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const visible = defineModel<boolean>('visible', {
  default: false
});

const argsList = ref<string[]>([]);
const { formRef, validate, restoreValidation } = useNaiveForm();
const { defaultRequiredRule } = useFormRules();

const title = computed(() => {
  const titles: Record<NaiveUI.TableOperateType, string> = {
    add: $t('page.retry.addRetry'),
    edit: $t('page.retry.editRetry')
  };
  return titles[props.operateType];
});

type Model = CommonType.RecordNullable<
  Pick<
    Api.Retry.Retry,
    | 'groupName'
    | 'sceneName'
    | 'idempotentId'
    | 'bizNo'
    | 'executorName'
    | 'argsStr'
    | 'retryStatus'
    | 'labels'
    | 'labelMap'
  >
>;

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    groupName: undefined,
    sceneName: undefined,
    idempotentId: '',
    bizNo: '',
    executorName: '',
    argsStr: '',
    retryStatus: 0,
    labels: '{}',
    labelMap: []
  };
}

type RuleKey = Extract<
  keyof Model,
  'groupName' | 'sceneName' | 'idempotentId' | 'bizNo' | 'executorName' | 'retryStatus'
>;

const rules: Record<RuleKey, App.Global.FormRule> = {
  groupName: defaultRequiredRule,
  sceneName: defaultRequiredRule,
  idempotentId: defaultRequiredRule,
  bizNo: defaultRequiredRule,
  executorName: defaultRequiredRule,
  // argsStr: { ...defaultRequiredRule, required: false, validator: validatorArgsStr },
  retryStatus: defaultRequiredRule
};

// function validatorArgsStr() {
//   if (argsList.value.length === 0) {
//     return false;
//   }

//   try {
//     argsList.value.forEach(arg => {
//       if (!isNotNull(arg)) {
//         throw new Error($t('form.required'));
//       }
//     });
//   } catch {
//     return false;
//   }

//   return true;
// }

function handleUpdateModelWhenEdit() {
  argsList.value = [];
  model.labelMap = [];

  if (props.operateType === 'add') {
    Object.assign(model, createDefaultModel());
    return;
  }

  if (props.operateType === 'edit' && props.rowData) {
    Object.assign(model, props.rowData);
    argsList.value = JSON.parse(props.rowData.argsStr || '[]');
    if (props.rowData.labels) {
      model.labelMap = Object.entries(JSON.parse(props.rowData.labels)).map(([key, value]) => {
        return { key, value: value as string };
      });
    }
  }
}

function closeDrawer() {
  visible.value = false;
}

async function handleSubmit() {
  await validate();

  const labels: Record<string, string> = {};
  model.labelMap?.forEach(item => {
    labels[item.key] = item.value;
  });

  if (props.operateType === 'add') {
    const { groupName, sceneName, idempotentId, bizNo, executorName, retryStatus } = model;
    const { error } = await fetchAddRetry({
      groupName,
      sceneName,
      idempotentId,
      bizNo,
      executorName,
      argsStr: JSON.stringify(argsList.value),
      retryStatus,
      labels: JSON.stringify(labels)
    });
    if (error) return;
    window.$message?.success($t('common.addSuccess'));
  }

  closeDrawer();
  emit('submitted');
}

watch(visible, () => {
  if (visible.value) {
    handleUpdateModelWhenEdit();
    restoreValidation();
  }
});

async function setIdempotentId() {
  const groupName = model.groupName;
  const sceneName = model.sceneName;
  const executorName = model.executorName;
  const argsStr = JSON.stringify(argsList.value);
  const { data: idempotentId, error } = await fetchIdempotentIdGenerate({
    groupName,
    sceneName,
    executorName,
    argsStr
  });
  if (!error) {
    model.idempotentId = idempotentId;
  }
}
</script>

<template>
  <OperateDrawer v-model="visible" :title="title" @handle-submit="handleSubmit">
    <NForm ref="formRef" :model="model" :rules="rules">
      <NFormItem :label="$t('page.retryTask.groupName')" path="groupName">
        <SelectGroup v-model:value="model.groupName" :disabled="props.operateType === 'edit'" />
      </NFormItem>
      <NFormItem :label="$t('page.retryTask.sceneName')" path="sceneName">
        <SelectScene
          v-model:value="model.sceneName"
          :group-name="model.groupName as string"
          :disabled="props.operateType === 'edit'"
        />
      </NFormItem>
      <NFormItem :label="$t('page.retryTask.bizNo')" path="bizNo">
        <NInput
          v-model:value="model.bizNo"
          :placeholder="$t('page.retryTask.form.bizNo')"
          :disabled="props.operateType === 'edit'"
        />
      </NFormItem>
      <NFormItem :label="$t('page.retry.executorName')" path="executorName">
        <NInput
          v-model:value="model.executorName"
          :placeholder="$t('page.retry.form.executorName')"
          :disabled="props.operateType === 'edit'"
        />
      </NFormItem>
      <NFormItem
        :label="$t('page.retry.labels')"
        path="labelMap"
        :show-feedback="model.labelMap?.length ? false : true"
      >
        <LabelsInput v-model:value="model.labelMap!" path="labelMap" />
      </NFormItem>
      <NFormItem :label="$t('page.retry.argsStr')" path="argsStr">
        <NDynamicInput v-model:value="argsList" :on-create="() => ''">
          <template #default="{ index }">
            <NFormItem
              class="w-full"
              ignore-path-change
              :show-label="false"
              :show-feedback="false"
              :path="`argsStr[${index}]`"
            >
              <CodeMirror v-model="argsList[index]" lang="json" :placeholder="$t('page.retry.argsStr')" />
            </NFormItem>
          </template>
        </NDynamicInput>
      </NFormItem>
      <NFormItem :label="$t('page.retryTask.idempotentId')" path="idempotentId">
        <NInputGroup>
          <NInput
            v-model:value="model.idempotentId"
            :placeholder="$t('page.retryTask.form.idempotentId')"
            :disabled="props.operateType === 'edit'"
          />
          <NTooltip trigger="hover">
            <template #trigger>
              <NButton type="default" ghost :disabled="props.operateType === 'edit'" @click="setIdempotentId">
                <icon-clarity:thin-client-solid class="text-icon" />
              </NButton>
            </template>
            {{ $t('page.retry.generateIdempotentId') }}
          </NTooltip>
        </NInputGroup>
      </NFormItem>
      <NFormItem :label="$t('page.retry.retryStatus')" path="retryStatus">
        <NSelect
          v-model:value="model.retryStatus"
          :placeholder="$t('page.retry.form.retryStatus')"
          :options="translateOptions(retryStatusTypeOptions)"
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
