<script setup lang="ts">
import { computed, reactive, ref, watch } from 'vue';
import { useClipboard } from '@vueuse/core';
import { useFormRules, useNaiveForm } from '@/hooks/common/form';
import { $t } from '@/locales';
import { groupConfigStatusOptions, groupConfigYesOrNoOptions } from '@/constants/business';
import { fetchAddGroupConfig, fetchEditGroupConfig, fetchGetPartitionTableList } from '@/service/api/group';

defineOptions({
  name: 'GroupOperateDrawer'
});

interface Props {
  /** the type of operation */
  operateType: NaiveUI.TableOperateType;
  /** the edit row data */
  rowData?: Api.GroupConfig.GroupConfig | null;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const visible = defineModel<boolean>('visible', {
  default: false
});

const { copy, isSupported } = useClipboard();
const { formRef, validate, restoreValidation } = useNaiveForm();
const { defaultRequiredRule } = useFormRules();

const title = computed(() => {
  const titles: Record<NaiveUI.TableOperateType, string> = {
    add: $t('page.groupConfig.addGroupConfig'),
    edit: $t('page.groupConfig.editGroupConfig')
  };
  return titles[props.operateType];
});

const partitionList = ref<string[]>([]);

type Model = Pick<
  Api.GroupConfig.GroupConfig,
  'id' | 'groupName' | 'token' | 'groupStatus' | 'description' | 'idGeneratorMode' | 'initScene' | 'groupPartition'
>;

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    groupName: '',
    token: import.meta.env.VITE_APP_DEFAULT_TOKEN || '',
    groupStatus: 1,
    description: '',
    idGeneratorMode: 2,
    initScene: 1,
    groupPartition: 0
  };
}

type RuleKey = Extract<
  keyof Model,
  'groupName' | 'token' | 'groupStatus' | 'idGeneratorMode' | 'initScene' | 'groupPartition'
>;

const rules = {
  groupName: [
    {
      required: true,
      pattern: /^[A-Za-z0-9_-]{1,64}$/,
      trigger: 'change',
      message: $t('page.groupConfig.form.groupNameRule')
    }
  ],
  token: [defaultRequiredRule],
  groupStatus: [defaultRequiredRule],
  idGeneratorMode: [defaultRequiredRule],
  initScene: [defaultRequiredRule],
  groupPartition: [defaultRequiredRule]
} satisfies Record<RuleKey, App.Global.FormRule[]>;

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
    const { groupName, token, groupStatus, description, idGeneratorMode, initScene, groupPartition } = model;
    const { error } = await fetchAddGroupConfig({
      groupName,
      token,
      groupStatus,
      description,
      idGeneratorMode,
      initScene,
      groupPartition
    });
    if (error) return;
    window.$message?.success($t('common.addSuccess'));
  } else {
    const { groupName, token, groupStatus, description, idGeneratorMode, initScene, groupPartition } = model;
    const { error } = await fetchEditGroupConfig({
      groupName,
      token,
      groupStatus,
      description,
      idGeneratorMode,
      initScene,
      groupPartition
    });
    if (error) return;
    window.$message?.success($t('common.updateSuccess'));
  }

  closeDrawer();
  emit('submitted');
}

/** 设置 token */
function setToken() {
  model.token = generateToken(32);
}

/** 生成 token */
function generateToken(length: number) {
  const chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  let token = 'SJ_';
  for (let i = 0; i < length; i += 1) {
    const randomNumber = Math.floor(Math.random() * chars.length);
    token += chars.substring(randomNumber, randomNumber + 1);
  }
  return token;
}

const getAllPartitions = async () => {
  const { data } = await fetchGetPartitionTableList();
  partitionList.value = data!.map(p => String(p));
};

watch(visible, () => {
  if (visible.value) {
    getAllPartitions(); // 因为drawer会keepalive，onMounted不能报账每次打开drawer会调用刷新
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
    range.selectNode(document.getElementById('tokenOperateInput')!);
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
      <NCollapse :default-expanded-names="['1', '2']">
        <NCollapseItem :title="$t('page.groupConfig.commonConfig')" name="1">
          <NFormItem :label="$t('page.groupConfig.groupName')" path="groupName">
            <NInput
              v-model:value="model.groupName"
              :maxlength="64"
              show-count
              :placeholder="$t('page.groupConfig.form.groupName')"
              :disabled="props.operateType === 'edit'"
            />
          </NFormItem>
          <NFormItem :label="$t('page.groupConfig.groupStatus')" path="groupStatus">
            <NRadioGroup v-model:value="model.groupStatus" name="groupStatus">
              <NSpace>
                <NRadio
                  v-for="item in groupConfigStatusOptions"
                  :key="item.value"
                  :value="item.value"
                  :label="$t(item.label)"
                />
              </NSpace>
            </NRadioGroup>
          </NFormItem>
          <NFormItem :label="$t('page.groupConfig.token')" path="token">
            <NInputGroup>
              <NInput
                id="tokenOperateInput"
                v-model:value="model.token"
                :maxlength="64"
                :placeholder="$t('page.groupConfig.form.token')"
                :disabled="props.operateType === 'edit'"
              />
              <NTooltip v-if="props.operateType === 'edit'" trigger="hover">
                <template #trigger>
                  <NButton type="default" ghost @click="handleCopy(model.token)">
                    <icon-ic:round-content-copy class="text-icon" />
                  </NButton>
                </template>
                复制
              </NTooltip>
              <NTooltip v-else trigger="hover">
                <template #trigger>
                  <NButton type="default" ghost @click="setToken">
                    <icon-ic-round-refresh class="text-icon" />
                  </NButton>
                </template>
                {{ $t('page.groupConfig.generateToken') }}
              </NTooltip>
            </NInputGroup>
          </NFormItem>
          <NFormItem :label="$t('page.groupConfig.description')" path="description">
            <NInput
              v-model:value="model.description"
              type="textarea"
              :maxlength="256"
              show-count
              :placeholder="$t('page.groupConfig.form.description')"
              clearable
              round
            />
          </NFormItem>
        </NCollapseItem>
        <NCollapseItem :title="$t('page.groupConfig.retryConfig')" name="2">
          <!--          <NFormItem :label="$t('page.groupConfig.idGeneratorMode')" path="idGeneratorMode">-->
          <!--            <NSelect-->
          <!--              v-model:value="model.idGeneratorMode"-->
          <!--              :placeholder="$t('page.groupConfig.form.idGeneratorMode')"-->
          <!--              :options="translateOptions(groupConfigIdModeOptions)"-->
          <!--            />-->
          <!--          </NFormItem>-->
          <NFormItem :label="$t('page.groupConfig.initScene')" path="initScene">
            <NRadioGroup v-model:value="model.initScene" name="initScene">
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
          <!--          <NFormItem :label="$t('page.groupConfig.groupPartition')" path="groupPartition">-->
          <!--            <NSelect-->
          <!--              v-model:value="model.groupPartition"-->
          <!--              :placeholder="$t('page.groupConfig.form.groupPartition')"-->
          <!--              :options="translateOptions2(partitionList)"-->
          <!--            />-->
          <!--          </NFormItem>-->
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
