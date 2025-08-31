<script setup lang="ts">
import { computed, nextTick, onMounted, reactive, ref, watch } from 'vue';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import OperateDrawer from '@/components/common/operate-drawer.vue';
import RouteKey from '@/components/common/route-key.vue';
import { $t } from '@/locales';
import { fetchAddRetryScene, fetchEditRetryScene, fetchGetNotifyConfigSystemTaskTypeList } from '@/service/api';
import {
  DelayLevel,
  backOffRecordOptions,
  enableStatusNumberOptions,
  groupConfigYesOrNoOptions
} from '@/constants/business';
import { isNotNull, translateOptions } from '@/utils/common';
import BlockStrategy from '@/components/common/block-strategy.vue';

defineOptions({
  name: 'SceneOperateDrawer'
});

interface Props {
  /** the type of operation */
  operateType: NaiveUI.TableOperateType;
  /** the edit row data */
  rowData?: Api.RetryScene.Scene | null;
}

const ignoreRecovery = ref<Api.Common.BlockStrategy[]>([4]);

const delayLevelDesc = ref<string>('10s');
const callbackDelayLevelDesc = ref<string>('10s');

const notifyNameList = ref<CommonType.Option<number>[]>([]);
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
    add: $t('page.retryScene.addScene'),
    edit: $t('page.retryScene.editScene')
  };
  return titles[props.operateType];
});

type Model = Pick<
  Api.RetryScene.Scene,
  | 'id'
  | 'groupName'
  | 'notifyIds'
  | 'sceneName'
  | 'sceneStatus'
  | 'ownerId'
  | 'ownerName'
  | 'backOff'
  | 'maxRetryCount'
  | 'triggerInterval'
  | 'deadlineRequest'
  | 'executorTimeout'
  | 'description'
  | 'routeKey'
  | 'blockStrategy'
  | 'cbStatus'
  | 'cbTriggerType'
  | 'cbTriggerInterval'
  | 'cbMaxCount'
  | 'labelMap'
  | 'labels'
>;

onMounted(() => {
  nextTick(() => {
    getNotifyConfigSystemTaskTypeList();
  });
});

async function getNotifyConfigSystemTaskTypeList() {
  const res = await fetchGetNotifyConfigSystemTaskTypeList(1);
  notifyNameList.value = res.data as CommonType.Option<number>[];
}

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    groupName: undefined,
    sceneName: '',
    notifyIds: [],
    sceneStatus: 1,
    ownerId: undefined,
    ownerName: '',
    backOff: 2,
    maxRetryCount: 1,
    triggerInterval: '60',
    deadlineRequest: 60000,
    executorTimeout: 60,
    description: '',
    routeKey: 4,
    blockStrategy: 1,
    cbStatus: 0,
    cbTriggerType: 1,
    cbTriggerInterval: '',
    cbMaxCount: 16,
    labelMap: [],
    labels: ''
  };
}

type RuleKey = Extract<
  keyof Model,
  | 'groupName'
  | 'sceneName'
  | 'sceneStatus'
  | 'backOff'
  | 'maxRetryCount'
  | 'triggerInterval'
  | 'deadlineRequest'
  | 'executorTimeout'
  | 'routeKey'
  | 'blockStrategy'
  | 'cbStatus'
  | 'cbTriggerType'
  | 'cbTriggerInterval'
  | 'cbMaxCount'
>;

const rules = {
  groupName: [defaultRequiredRule],
  sceneName: [
    defaultRequiredRule,
    {
      required: true,
      pattern: /^[A-Za-z0-9_-]{1,64}$/,
      trigger: 'change',
      message: $t('page.retryScene.form.sceneName2')
    }
  ],
  sceneStatus: [defaultRequiredRule],
  backOff: [defaultRequiredRule],
  maxRetryCount: [defaultRequiredRule],
  triggerInterval: [
    { ...defaultRequiredRule, validator: () => isNotNull(model.triggerInterval) || model.backOff === 1 }
  ],
  deadlineRequest: [defaultRequiredRule],
  executorTimeout: [defaultRequiredRule],
  routeKey: [defaultRequiredRule],
  blockStrategy: [defaultRequiredRule],
  cbStatus: [defaultRequiredRule],
  cbTriggerType: [defaultRequiredRule],
  cbMaxCount: [defaultRequiredRule],
  cbTriggerInterval: [
    { ...defaultRequiredRule, validator: () => isNotNull(model.cbTriggerInterval) || model.cbTriggerType === 1 }
  ]
} satisfies Record<RuleKey, App.Global.FormRule[]>;

function handleUpdateModelWhenEdit() {
  model.labelMap = [];

  if (props.operateType === 'add') {
    Object.assign(model, createDefaultModel());
    return;
  }

  if (props.operateType === 'edit' && props.rowData) {
    Object.assign(model, props.rowData);
    if (model.labels) {
      model.labelMap = Object.entries(JSON.parse(model.labels)).map(([key, value]) => {
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
  // request
  if (props.operateType === 'add') {
    const {
      groupName,
      sceneName,
      notifyIds,
      sceneStatus,
      ownerId,
      ownerName,
      backOff,
      maxRetryCount,
      triggerInterval,
      deadlineRequest,
      executorTimeout,
      routeKey,
      blockStrategy,
      description,
      cbStatus,
      cbTriggerType,
      cbTriggerInterval,
      cbMaxCount
    } = model;
    const { error } = await fetchAddRetryScene({
      groupName,
      sceneName,
      notifyIds,
      sceneStatus,
      ownerId,
      ownerName,
      backOff,
      maxRetryCount,
      triggerInterval,
      deadlineRequest,
      executorTimeout,
      routeKey,
      blockStrategy,
      description,
      cbStatus,
      cbTriggerType,
      cbTriggerInterval,
      cbMaxCount,
      labels: JSON.stringify(labels)
    });
    if (error) return;
    window.$message?.success($t('common.addSuccess'));
  }

  if (props.operateType === 'edit') {
    const {
      id,
      groupName,
      sceneName,
      notifyIds,
      sceneStatus,
      ownerId,
      ownerName,
      backOff,
      maxRetryCount,
      triggerInterval,
      deadlineRequest,
      executorTimeout,
      routeKey,
      blockStrategy,
      description,
      cbStatus,
      cbTriggerType,
      cbTriggerInterval,
      cbMaxCount
    } = model;
    const { error } = await fetchEditRetryScene({
      id,
      groupName,
      sceneName,
      notifyIds,
      sceneStatus,
      ownerId,
      ownerName,
      backOff,
      maxRetryCount,
      triggerInterval,
      deadlineRequest,
      executorTimeout,
      routeKey,
      blockStrategy,
      description,
      cbStatus,
      cbTriggerType,
      cbTriggerInterval,
      cbMaxCount,
      labels: JSON.stringify(labels)
    });
    if (error) return;
    window.$message?.success($t('common.updateSuccess'));
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

watch(
  () => model.backOff,
  backOff => {
    if (backOff === 1 && model.maxRetryCount > 26) {
      model.maxRetryCount = 1;
    }
  }
);

watch(
  () => model.maxRetryCount,
  () => {
    delayLevelDesc.value = Object.values(DelayLevel).slice(0, model.maxRetryCount).join(',');
  },
  { immediate: true }
);

watch(
  () => model.cbMaxCount,
  () => {
    callbackDelayLevelDesc.value = Object.values(DelayLevel).slice(0, model.cbMaxCount).join(',');
  },
  { immediate: true }
);
</script>

<template>
  <OperateDrawer v-model="visible" :title="title" :min-size="480" @handle-submit="handleSubmit">
    <NForm ref="formRef" :model="model" :rules="rules">
      <NCollapse :default-expanded-names="['1', '2']">
        <NCollapseItem :title="$t('page.retryScene.baseConfig')" name="1">
          <NFormItem :label="$t('page.retryScene.sceneName')" path="sceneName">
            <NInput
              v-model:value="model.sceneName"
              :disabled="props.operateType === 'edit'"
              :maxlength="64"
              show-count
              :placeholder="$t('page.retryScene.form.sceneName')"
            />
          </NFormItem>
          <NFormItem :label="$t('page.retryScene.groupName')" path="groupName">
            <SelectGroup v-model:value="model.groupName" :disabled="props.operateType === 'edit'" />
          </NFormItem>
          <NFormItem :label="$t('page.retryScene.ownerName')" path="ownerId">
            <SystemUser v-model:value="model.ownerId" :clearable="true" />
          </NFormItem>
          <NFormItem :label="$t('page.jobTask.labels')" path="labelMap" :show-feedback="!model.labelMap?.length">
            <LabelsInput v-model:value="model.labelMap" path="labelMap" />
          </NFormItem>
          <NFormItem :label="$t('page.retryScene.sceneStatus')" path="sceneStatus">
            <NRadioGroup v-model:value="model.sceneStatus" name="sceneStatus">
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
          <NGrid cols="2 s:1 m:2" responsive="screen" x-gap="20">
            <NGi>
              <NFormItem :label="$t('common.routeKey.routeLabel')" path="routeKey">
                <RouteKey v-model:value="model.routeKey" />
              </NFormItem>
            </NGi>
            <NGi>
              <NFormItem :label="$t('page.retryScene.blockStrategy')" path="blockStrategy">
                <BlockStrategy v-model:value="model.blockStrategy" :ignore="ignoreRecovery" />
              </NFormItem>
            </NGi>
          </NGrid>
          <NGrid cols="2 s:1 m:2" responsive="screen" x-gap="20">
            <NGi>
              <NFormItem :label="$t('page.retryScene.backOff')" path="backOff">
                <NSelect
                  v-model:value="model.backOff"
                  :placeholder="$t('page.retryScene.form.backOff')"
                  :options="translateOptions(backOffRecordOptions)"
                  clearable
                />
              </NFormItem>
            </NGi>
            <NGi>
              <NFormItem :label="$t('page.retryScene.maxRetryCount')" path="maxRetryCount">
                <NInputNumber
                  v-model:value="model.maxRetryCount"
                  :min="1"
                  :max="model.backOff === 1 ? 26 : 9999999"
                  :placeholder="$t('page.retryScene.form.maxRetryCount')"
                  clearable
                />
              </NFormItem>
            </NGi>
          </NGrid>
          <NFormItem path="triggerInterval">
            <SceneTriggerInterval
              v-if="model.backOff !== 1"
              v-model="model.triggerInterval"
              :back-off="model.backOff"
            />
            <NInput
              v-else
              v-model:value="delayLevelDesc"
              type="textarea"
              :autosize="{ minRows: 1, maxRows: 3 }"
              readonly
            />
            <template #label>
              <div class="flex-center">
                {{ $t('page.retryScene.triggerInterval') }}
                <NTooltip v-if="model.backOff === 1" trigger="hover">
                  <template #trigger>
                    <NButton text class="ml-6px">
                      <SvgIcon icon="ant-design:info-circle-outlined" class="mb-1px text-16px" />
                    </NButton>
                  </template>
                  延迟等级是参考RocketMQ的messageDelayLevel设计实现，具体延迟时间如下:
                  【10s,15s,30s,35s,40s,50s,1m,2m,4m,6m,8m,10m,20m,40m,1h,2h,3h,4h,5h,6h,7h,8h,9h,10h,11h,12h】
                  <br />
                  <NText strong>执行逻辑:</NText>
                  <NUl align-text>
                    <NLi>第一次执行间隔10s</NLi>
                    <NLi>第二次执行间隔15s</NLi>
                    <NLi>l第二次执行间隔30s</NLi>
                    <NLi>........... 依次类推</NLi>
                  </NUl>
                </NTooltip>
              </div>
            </template>
          </NFormItem>
          <NGrid cols="2 s:1 m:2" responsive="screen" x-gap="20">
            <NGi>
              <NFormItem :label="$t('page.retryScene.executorTimeout')" path="executorTimeout">
                <NInputGroup>
                  <NInputNumber
                    v-model:value="model.executorTimeout"
                    :min="1"
                    :max="60"
                    :placeholder="$t('page.retryScene.form.executorTimeout')"
                    clearable
                  />
                  <NInputGroupLabel>{{ $t('common.second') }}</NInputGroupLabel>
                </NInputGroup>
              </NFormItem>
            </NGi>
            <NGi>
              <NFormItem :label="$t('page.retryScene.deadlineRequest')" path="deadlineRequest">
                <NInputGroup>
                  <NInputNumber
                    v-model:value="model.deadlineRequest"
                    :min="100"
                    :max="60000"
                    :placeholder="$t('page.retryScene.form.deadlineRequest')"
                    clearable
                  />
                  <NInputGroupLabel>{{ $t('common.millisecond') }}</NInputGroupLabel>
                </NInputGroup>
              </NFormItem>
            </NGi>
          </NGrid>
          <NFormItem :label="$t('page.retryScene.notifyName')" path="notifyIds">
            <NSelect
              v-model:value="model.notifyIds"
              value-field="id"
              label-field="notifyName"
              :placeholder="$t('page.retryScene.form.notifyName')"
              :options="notifyNameList"
              clearable
              multiple
            />
          </NFormItem>
          <NFormItem :label="$t('page.retryScene.description')" path="description">
            <NInput
              v-model:value="model.description"
              type="textarea"
              :maxlength="256"
              :placeholder="$t('page.retryScene.form.description')"
              show-count
              clearable
            />
          </NFormItem>
        </NCollapseItem>
        <NCollapseItem :title="$t('page.retryScene.cbConfig')" name="2">
          <NFormItem :label="$t('page.retryScene.cbStatus')" path="cbStatus">
            <NRadioGroup v-model:value="model.cbStatus" name="cbStatus">
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
          <NGrid cols="2 s:1 m:2" responsive="screen" x-gap="20">
            <NGi>
              <NFormItem :label="$t('page.retryScene.cbTriggerType')" path="cbTriggerType">
                <NSelect
                  v-model:value="model.cbTriggerType"
                  :placeholder="$t('page.retryScene.form.cbTriggerType')"
                  :options="translateOptions(backOffRecordOptions)"
                  clearable
                />
              </NFormItem>
            </NGi>
            <NGi>
              <NFormItem :label="$t('page.retryScene.cbMaxCount')" path="cbMaxCount">
                <NInputNumber
                  v-model:value="model.cbMaxCount"
                  :min="1"
                  :max="model.backOff === 1 ? 26 : 9999999"
                  :placeholder="$t('page.retryScene.form.cbMaxCount')"
                  clearable
                />
              </NFormItem>
            </NGi>
          </NGrid>
          <NFormItem path="cbTriggerInterval">
            <SceneTriggerInterval
              v-if="model.cbTriggerType !== 1"
              v-model="model.cbTriggerInterval"
              :back-off="model.cbTriggerType"
            />
            <NInput
              v-else
              v-model:value="callbackDelayLevelDesc"
              type="textarea"
              :autosize="{ minRows: 1, maxRows: 3 }"
              readonly
            />
            <template #label>
              <div class="flex-center">
                {{ $t('page.retryScene.cbTriggerInterval') }}
                <NTooltip v-if="model.cbTriggerType === 1" trigger="hover">
                  <template #trigger>
                    <NButton text class="ml-6px">
                      <SvgIcon icon="ant-design:info-circle-outlined" class="mb-1px text-16px" />
                    </NButton>
                  </template>
                  延迟等级是参考RocketMQ的messageDelayLevel设计实现，具体延迟时间如下:
                  【10s,15s,30s,35s,40s,50s,1m,2m,4m,6m,8m,10m,20m,40m,1h,2h,3h,4h,5h,6h,7h,8h,9h,10h,11h,12h】
                  <br />
                  <NText strong>执行逻辑:</NText>
                  <NUl align-text>
                    <NLi>第一次执行间隔10s</NLi>
                    <NLi>第二次执行间隔15s</NLi>
                    <NLi>l第二次执行间隔30s</NLi>
                    <NLi>........... 依次类推</NLi>
                  </NUl>
                </NTooltip>
              </div>
            </template>
          </NFormItem>
        </NCollapseItem>
      </NCollapse>
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
