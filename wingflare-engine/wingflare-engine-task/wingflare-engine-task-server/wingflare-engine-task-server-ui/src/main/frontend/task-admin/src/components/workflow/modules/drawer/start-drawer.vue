<script setup lang="ts">
import { nextTick, onMounted, ref, watch } from 'vue';
import { type FormInst, type FormItemRule } from 'naive-ui';
import {
  blockStrategyRecordOptions,
  workflowTriggerTypeOptions as triggerTypeOptions,
  workFlowNodeStatusOptions
} from '@/constants/business';
import { $t } from '@/locales';
import { fetchGetAllGroupNameList, fetchGetNotifyConfigSystemTaskTypeList } from '@/service/api';
import { isNotNull, parseContent, stringToContent } from '@/utils/common';
import { useWorkflowStore } from '@/store/modules/workflow';
import EditableInput from '@/components/common/editable-input.vue';

defineOptions({
  name: 'StartDrawer'
});

interface Props {
  modelValue?: Workflow.NodeDataType;
  open?: boolean;
}

const notifyNameList = ref<CommonType.Option<number>[]>([]);
const props = withDefaults(defineProps<Props>(), {
  open: false,
  modelValue: () => ({
    wfContexts: []
  })
});

interface Emits {
  (e: 'update:open', open: boolean): void;
  (e: 'save', form: Workflow.NodeDataType): void;
}

onMounted(() => {
  nextTick(() => {
    getNotifyConfigSystemTaskTypeList();
  });
});

async function getNotifyConfigSystemTaskTypeList() {
  const res = await fetchGetNotifyConfigSystemTaskTypeList(4);
  notifyNameList.value = res.data as CommonType.Option<number>[];
}
const emit = defineEmits<Emits>();

const store = useWorkflowStore();

let title: string = '';
const drawer = ref<boolean>(false);
const form = ref<Workflow.NodeDataType>({
  wfContexts: [],
  notifyIds: []
});
const groupNameList = ref<string[]>([]);

watch(
  () => props.open,
  val => {
    drawer.value = val;
  },
  { immediate: true }
);

watch(
  () => props.modelValue,
  val => {
    form.value = val;
    if (!val.triggerInterval) {
      form.value.triggerType = 2;
      form.value.triggerInterval = '60';
    }
    if (val.triggerType === 2) {
      form.value.triggerInterval = val.triggerInterval;
    }
    if (val.workflowName) {
      title = val.workflowName;
    } else if (val.groupName) {
      title = val.groupName;
    } else {
      title = '请选择组';
    }
    if (val.wfContext) {
      form.value.wfContext = JSON.parse(val.wfContext);
      form.value.wfContexts = stringToContent(val.wfContext);
    }
  },
  { immediate: true }
);

const formRef = ref<FormInst>();

const close = () => {
  emit('update:open', false);
  drawer.value = false;
};

const save = () => {
  formRef.value
    ?.validate(errors => {
      if (!errors) {
        form.value.wfContext = JSON.stringify(parseContent(form.value.wfContexts) || {});
        close();
        emit('save', form.value);
      }
    })
    .catch(() => window.$message?.warning('请检查表单信息'));
};

const getGroupNameList = async () => {
  const { data, error } = await fetchGetAllGroupNameList();
  if (!error) {
    groupNameList.value = data;
  }
};

getGroupNameList();

const typeChange = (value: number) => {
  if (value === 3) {
    form.value.triggerInterval = '* * * * * ?';
  } else if (value === 2) {
    form.value.triggerInterval = '60';
  }
};

type Model = Pick<
  Workflow.NodeDataType,
  'groupName' | 'triggerType' | 'triggerInterval' | 'executorTimeout' | 'blockStrategy' | 'workflowStatus'
>;

type RuleKey = keyof Model;

const rules: Record<RuleKey, FormItemRule> = {
  groupName: { required: true, message: '请选择组' },
  triggerType: { required: true, message: '请选择触发类型' },
  triggerInterval: { required: true, message: '请输入触发间隔' },
  executorTimeout: { required: true, message: '请输入执行超时时间' },
  blockStrategy: { required: true, message: '请选择阻塞策略' },
  workflowStatus: { required: true, message: '请选择工作流状态' }
};
</script>

<template>
  <NDrawer v-model:show="drawer" display-directive="if" :width="610" @after-leave="close">
    <NDrawerContent :title="title">
      <template #header>
        <EditableInput v-model="form.workflowName" class="max-w-570px min-w-570px" />
      </template>
      <NForm ref="formRef" :model="form" :rules="rules" label-align="left" label-width="100px">
        <NFormItem path="groupName" label="组名称">
          <NSelect
            v-model:value="form.groupName"
            placeholder="请选择组"
            :disabled="store.type === 0 && isNotNull(store.id)"
            :options="
              groupNameList.map(groupName => {
                return {
                  label: groupName,
                  value: groupName
                };
              })
            "
          />
        </NFormItem>
        <NGrid :cols="form.triggerType === 5 ? '1' : '2 s:1 m:2'" responsive="screen" x-gap="20">
          <NGi>
            <NFormItem path="triggerType" label="触发类型">
              <NSelect
                v-model:value="form.triggerType"
                placeholder="请选择触发类型"
                :options="
                  triggerTypeOptions.map(option => {
                    return {
                      label: $t(option.label),
                      value: option.value
                    };
                  })
                "
                @update:value="typeChange"
              />
            </NFormItem>
          </NGi>
          <NGi>
            <NFormItem :label="$t('page.jobTask.triggerInterval')" path="triggerInterval">
              <JobTriggerInterval v-model="form.triggerInterval" :trigger-type="form.triggerType ?? 2" />
            </NFormItem>
          </NGi>
        </NGrid>
        <NGrid :cols="24" x-gap="20">
          <NGi :span="8">
            <NFormItem path="executorTimeout" label="执行超时时间">
              <NInputNumber v-model:value="form.executorTimeout" placeholder="请输入超时时间" :min="1">
                <template #suffix>秒</template>
              </NInputNumber>
            </NFormItem>
          </NGi>
          <NGi :span="16">
            <NFormItem path="blockStrategy" label="阻塞策略">
              <NRadioGroup v-model:value="form.blockStrategy">
                <NSpace>
                  <NRadio
                    v-for="(options, index) in blockStrategyRecordOptions"
                    :key="index"
                    :label="$t(options.label)"
                    :value="options.value"
                  />
                </NSpace>
              </NRadioGroup>
            </NFormItem>
          </NGi>
        </NGrid>
        <NFormItem path="wfContext" label="工作流上下文" :show-feedback="form.wfContexts?.length ? false : true">
          <DynamicInput v-model:value="form.wfContexts!" path="wfContexts" />
        </NFormItem>
        <NFormItem path="workflowStatus" label="节点状态">
          <NRadioGroup v-model:value="form.workflowStatus">
            <NSpace>
              <NRadio
                v-for="(options, index) in workFlowNodeStatusOptions"
                :key="index"
                :label="$t(options.label)"
                :value="options.value"
              />
            </NSpace>
          </NRadioGroup>
        </NFormItem>
        <NFormItem path="ownerId" label="负责人">
          <SystemUser v-model:value="form.ownerId" :clearable="true" />
        </NFormItem>
        <NFormItem path="notifyIds" label="告警通知">
          <NSelect
            v-model:value="form.notifyIds"
            value-field="id"
            label-field="notifyName"
            placeholder="请选择告警通知名称"
            :options="notifyNameList"
            clearable
            multiple
          />
        </NFormItem>
        <NFormItem path="description" label="描述">
          <NInput
            v-model:value="form.description"
            type="textarea"
            :autosize="{ minRows: 5 }"
            placeholder="请输入描述"
          />
        </NFormItem>
      </NForm>

      <template #footer>
        <NButton type="primary" @click="save">保存</NButton>
        <NButton class="ml-12px" @click="close">取消</NButton>
      </template>
    </NDrawerContent>
  </NDrawer>
</template>
