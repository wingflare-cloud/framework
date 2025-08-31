<script setup lang="ts">
import { computed, nextTick, onMounted, reactive, ref, watch } from 'vue';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import OperateDrawer from '@/components/common/operate-drawer.vue';
import { $t } from '@/locales';
import {
  fetchAddNotify,
  fetchEditNotify,
  fetchGetJobList,
  fetchGetNotifyRecipientList,
  fetchGetRetrySceneList,
  fetchGetWorkflowNameList
} from '@/service/api';
import {
  enableStatusNumberOptions,
  jobNotifySceneOptions,
  retryNotifySceneOptions,
  systemTaskTypeOptions,
  workflowNotifySceneOptions
} from '@/constants/business';
import { translateOptions } from '@/utils/common';
import SelectGroup from '@/components/common/select-group.vue';

defineOptions({
  name: 'NotifyConfigOperateDrawer'
});

interface Props {
  /** the type of operation */
  operateType: NaiveUI.TableOperateType;
  /** the edit row data */
  rowData?: Api.NotifyConfig.NotifyConfig | null;
}

const notifyRecipientList = ref<CommonType.Option<number>[]>([]);
const retryScenes = ref<Api.RetryScene.Scene[]>([]);
const jobs = ref<Api.Job.Job[]>([]);
const workflows = ref<Api.Workflow.Workflow[]>([]);

const props = defineProps<Props>();

interface Emits {
  (e: 'update:value', value: Api.NotifyConfig.RetryNotifyScene): void;
}

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const visible = defineModel<boolean>('visible', {
  default: false
});

const retryNotifyStatusDisable = defineModel<boolean>('retryNotifyStatusDisable', {
  default: true
});

const retrySceneDisable = defineModel<boolean>('retrySceneDisable', {
  default: true
});

const notifySceneOptions = ref<CommonType.Option<string | number>[]>([]);

const { formRef, validate, restoreValidation } = useNaiveForm();
const { defaultRequiredRule } = useFormRules();

const title = computed(() => {
  const titles: Record<NaiveUI.TableOperateType, string> = {
    add: $t('page.notifyConfig.addNotifyConfig'),
    edit: $t('page.notifyConfig.editNotifyConfig')
  };
  return titles[props.operateType];
});

type Model = Pick<
  Api.NotifyConfig.NotifyConfig,
  | 'id'
  | 'groupName'
  | 'recipientIds'
  | 'systemTaskType'
  | 'notifyName'
  | 'notifyStatus'
  | 'notifyScene'
  | 'notifyThreshold'
  | 'rateLimiterStatus'
  | 'rateLimiterThreshold'
  | 'description'
>;

onMounted(() => {
  nextTick(() => {
    getNotifyRecipientList();
  });
});

async function getNotifyRecipientList() {
  const res = await fetchGetNotifyRecipientList();
  notifyRecipientList.value = res.data as CommonType.Option<number>[];
}

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    groupName: null,
    recipientIds: [],
    systemTaskType: null,
    notifyName: '',
    notifyStatus: 1,
    notifyScene: null,
    notifyThreshold: 16,
    rateLimiterStatus: 1,
    rateLimiterThreshold: 100,
    description: ''
  };
}

type RuleKey = Extract<
  keyof Model,
  | 'groupName'
  | 'systemTaskType'
  | 'recipientIds'
  | 'notifyName'
  | 'notifyStatus'
  | 'notifyScene'
  | 'rateLimiterStatus'
  | 'notifyThreshold'
>;

const rules: Record<RuleKey, App.Global.FormRule> = {
  groupName: defaultRequiredRule,
  systemTaskType: defaultRequiredRule,
  notifyName: defaultRequiredRule,
  notifyStatus: defaultRequiredRule,
  notifyScene: defaultRequiredRule,
  recipientIds: defaultRequiredRule,
  rateLimiterStatus: defaultRequiredRule,
  notifyThreshold: defaultRequiredRule
};

function handleUpdateModelWhenEdit() {
  if (props.operateType === 'add') {
    Object.assign(model, createDefaultModel());
    retrySceneDisable.value = true;
    retryNotifyStatusDisable.value = true;
    notifySceneOptions.value = [];
    return;
  }

  if (props.operateType === 'edit' && props.rowData) {
    Object.assign(model, props.rowData);
    systemTaskTypeChange(model.systemTaskType);
    retrySceneChange(model.notifyScene);
  }
}

function closeDrawer() {
  visible.value = false;
}

async function handleSubmit() {
  await validate();
  // request
  if (props.operateType === 'add') {
    const {
      groupName,
      recipientIds,
      systemTaskType,
      notifyName,
      notifyStatus,
      notifyScene,
      notifyThreshold,
      rateLimiterStatus,
      rateLimiterThreshold,
      description
    } = model;
    const { error } = await fetchAddNotify({
      groupName,
      recipientIds,
      systemTaskType,
      notifyName,
      notifyStatus,
      notifyScene,
      notifyThreshold,
      rateLimiterStatus,
      rateLimiterThreshold,
      description
    });
    if (error) return;
  }

  if (props.operateType === 'edit') {
    const {
      id,
      groupName,
      recipientIds,
      notifyStatus,
      notifyName,
      systemTaskType,
      notifyScene,
      notifyThreshold,
      rateLimiterStatus,
      rateLimiterThreshold,
      description
    } = model;
    const { error } = await fetchEditNotify({
      id,
      groupName,
      recipientIds,
      systemTaskType,
      notifyName,
      notifyStatus,
      notifyScene,
      notifyThreshold,
      rateLimiterStatus,
      rateLimiterThreshold,
      description
    });
    if (error) return;
  }
  window.$message?.success($t('common.updateSuccess'));
  closeDrawer();
  emit('submitted');
}

async function systemTaskTypeChange(value: number | null) {
  if (value === 1) {
    const res = await fetchGetRetrySceneList({ groupName: model.groupName });
    retryScenes.value = res.data as Api.RetryScene.Scene[];
    notifySceneOptions.value = translateOptions(retryNotifySceneOptions);
  } else if (value === 3) {
    const res = await fetchGetJobList({ groupName: model.groupName });
    jobs.value = res.data?.map(i => {
      i.id = String(i.id);
      return i;
    }) as Api.Job.Job[];
    notifySceneOptions.value = translateOptions(jobNotifySceneOptions);
  } else if (value === 4) {
    const res = await fetchGetWorkflowNameList({ groupName: model.groupName });
    workflows.value = res.data?.map(i => {
      i.id = String(i.id);
      return i;
    }) as Api.Workflow.Workflow[];
    notifySceneOptions.value = translateOptions(workflowNotifySceneOptions);
  }
  await retrySceneChange(model.notifyScene);
  let notifySceneEmpty = false;
  notifySceneOptions.value.map(i => {
    if (i.value === model.notifyScene) {
      notifySceneEmpty = true;
    }
    return String(i.value);
  });
  if (!notifySceneEmpty) {
    model.notifyScene = null;
  }
}

async function retrySceneChange(
  value:
    | Api.NotifyConfig.JobNotifyScene
    | Api.NotifyConfig.RetryNotifyScene
    | Api.NotifyConfig.WorkflowNotifyScene
    | null
) {
  retrySceneDisable.value = !(model.systemTaskType === 1 && (value === 1 || value === 2 || value === 5 || value === 6));
  if (value === 7) {
    model.notifyThreshold = 0;
  }
}

function groupNameUpdate(groupName: string) {
  handleUpdateModelWhenEdit();
  model.groupName = groupName;
  systemTaskTypeChange(1);
  retrySceneChange(1);
}

watch(visible, () => {
  if (visible.value) {
    handleUpdateModelWhenEdit();
    restoreValidation();
  }
});
</script>

<template>
  <OperateDrawer v-model="visible" :title="title" :min-size="480" @handle-submit="handleSubmit">
    <NForm ref="formRef" :model="model" :rules="rules">
      <NFormItem :label="$t('page.notifyConfig.groupName')" path="groupName">
        <SelectGroup v-model:value="model.groupName" @update:model-value="groupNameUpdate" />
      </NFormItem>
      <NFormItem :label="$t('page.notifyConfig.systemTaskType')" path="systemTaskType">
        <NSelect
          v-model:value="model.systemTaskType"
          :placeholder="$t('page.notifyConfig.form.systemTaskType')"
          :options="translateOptions(systemTaskTypeOptions)"
          @update:value="systemTaskTypeChange"
        />
      </NFormItem>
      <NFormItem :label="$t('page.notifyConfig.notifyScene')" path="notifyScene">
        <NSelect
          v-model:value="model.notifyScene"
          :placeholder="$t('page.notifyConfig.form.notifyScene')"
          :options="notifySceneOptions"
          @update:value="retrySceneChange"
        />
      </NFormItem>
      <NFormItem :label="$t('page.notifyConfig.notifyRecipient')" path="recipientIds">
        <NSelect
          v-model:value="model.recipientIds"
          :placeholder="$t('page.notifyConfig.form.notifyRecipient')"
          :options="notifyRecipientList"
          clearable
          multiple
        />
      </NFormItem>
      <NFormItem :label="$t('page.notifyConfig.notifyName')" path="notifyName">
        <NInput
          v-model:value="model.notifyName"
          :placeholder="$t('page.notifyConfig.form.notifyName')"
          :maxlength="32"
        />
      </NFormItem>
      <NGrid cols="2 s:1 m:2" responsive="screen" x-gap="20">
        <NGi>
          <NFormItem :label="$t('page.notifyConfig.notifyStatus')" path="notifyStatus">
            <NRadioGroup v-model:value="model.notifyStatus" name="notifyStatus">
              <NSpace>
                <NRadio
                  v-for="item in enableStatusNumberOptions"
                  :key="item.value"
                  :value="item.value"
                  :label="$t(item.label)"
                />
              </NSpace>
            </NRadioGroup>
          </NFormItem>
        </NGi>
        <NGi>
          <NFormItem :label="$t('page.notifyConfig.notifyThreshold')" path="notifyThreshold">
            <NInputNumber
              v-model:value="model.notifyThreshold"
              :min="1"
              :placeholder="$t('page.notifyConfig.form.notifyThreshold')"
              :disabled="retrySceneDisable"
            />
          </NFormItem>
        </NGi>
      </NGrid>
      <NGrid cols="2 s:1 m:2" responsive="screen" x-gap="20">
        <NGi>
          <NFormItem :label="$t('page.notifyConfig.rateLimiterStatus')" path="rateLimiterStatus">
            <NRadioGroup v-model:value="model.rateLimiterStatus" name="rateLimiterStatus" :disabled="retrySceneDisable">
              <NSpace>
                <NRadio
                  v-for="item in enableStatusNumberOptions"
                  :key="item.value"
                  :value="item.value"
                  :label="$t(item.label)"
                />
              </NSpace>
            </NRadioGroup>
          </NFormItem>
        </NGi>
        <NGi>
          <NFormItem :label="$t('page.notifyConfig.rateLimiterThreshold')" path="notifyThreshold">
            <NInputNumber
              v-model:value="model.rateLimiterThreshold"
              :min="1"
              :placeholder="$t('page.notifyConfig.form.notifyThreshold')"
              :disabled="retrySceneDisable"
            />
          </NFormItem>
        </NGi>
      </NGrid>
      <NFormItem :label="$t('page.notifyConfig.description')" path="description">
        <NInput
          v-model:value="model.description"
          type="textarea"
          :placeholder="$t('page.notifyConfig.form.description')"
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
