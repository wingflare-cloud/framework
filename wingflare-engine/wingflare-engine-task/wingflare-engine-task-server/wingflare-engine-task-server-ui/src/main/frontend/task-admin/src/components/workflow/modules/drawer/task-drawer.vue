<script setup lang="tsx">
import { ref, watch } from 'vue';
import { type FormInst, NTag, NTooltip } from 'naive-ui';
import { useWorkflowStore } from '@/store/modules/workflow';
import { $t } from '@/locales';
import { failStrategyOptions, taskTypeRecord, workFlowNodeStatusOptions } from '@/constants/business';
import EditableInput from '@/components/common/editable-input.vue';

defineOptions({
  name: 'TaskDrawer'
});

interface Props {
  modelValue?: Workflow.ConditionNodeType;
  open?: boolean;
  len?: number;
}

const props = withDefaults(defineProps<Props>(), {
  open: false,
  len: 0,
  modelValue: () => ({})
});

interface Emits {
  (e: 'update:open', open: boolean): void;
  (e: 'save', form: Workflow.ConditionNodeType): void;
}

const emit = defineEmits<Emits>();

const store = useWorkflowStore();
const drawer = ref<boolean>(false);
const form = ref<Workflow.ConditionNodeType>({});
const jobList = ref<Pick<Api.Job.Job, 'id' | 'jobName' | 'executorInfo' | 'taskType'>[]>([]);

watch(
  () => store.jobList,
  val => {
    jobList.value = val;
  },
  { immediate: true }
);

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
        close();
        emit('save', form.value);
      }
    })
    .catch(() => window.$message?.warning('请检查表单信息'));
};

const rules = {
  failStrategy: [{ required: true, message: '请选择失败策略' }],
  workflowNodeStatus: [{ required: true, message: '请选择工作流状态' }],
  jobTask: {
    jobId: [{ required: true, message: '请选择任务' }]
  }
};

const jobTaskChange = (_: string, option: { jobName: string; labels: string }) => {
  form.value.jobTask!.jobName = option.jobName;
  form.value.jobTask!.labels = option.labels;
};

const renderTaskLabel = (option: Api.Job.Job) => {
  return (
    <NTooltip trigger="hover" placement="left">
      {{
        trigger: () => (
          <div class="flex-y-center gap-6px">
            {option.taskType ? (
              <NTag type="info" size="small">
                {$t(taskTypeRecord[option.taskType])}
              </NTag>
            ) : null}
            <span>{option.jobName}</span>
          </div>
        ),
        default: () => (
          <div>
            {option.jobName} ( {option.executorInfo} )
          </div>
        )
      }}
    </NTooltip>
  );
};
</script>

<template>
  <NDrawer v-model:show="drawer" display-directive="if" :width="500" @after-leave="close">
    <NDrawerContent>
      <template #header>
        <div class="w-460px flex-center">
          <EditableInput v-model="form.nodeName" class="mr-16px max-w-320px min-w-320px" />

          <NSelect
            v-model:value="form.priorityLevel"
            class="max-w-110px"
            :options="
              Array(len)
                .fill(0)
                .map((_, index) => {
                  return {
                    label: '优先级 ' + (index + 1),
                    value: index + 1
                  };
                })
            "
          />
        </div>
      </template>

      <NForm ref="formRef" :model="form" :rules="rules" label-align="left" label-width="100px">
        <NFormItem path="jobTask.jobId" label="所属任务" placeholder="请选择任务">
          <NSelect
            v-model:value="form.jobTask!.jobId"
            filterable
            :render-label="renderTaskLabel"
            label-field="jobName"
            value-field="id"
            :options="jobList"
            @update:value="jobTaskChange"
          />
        </NFormItem>
        <NFormItem path="failStrategy" label="失败策略">
          <NRadioGroup v-model:value="form.failStrategy">
            <NSpace>
              <NRadio
                v-for="(options, index) in failStrategyOptions"
                :key="index"
                :label="$t(options.label)"
                :value="options.value"
              />
            </NSpace>
          </NRadioGroup>
        </NFormItem>
        <NFormItem path="workflowNodeStatus" label="任务状态">
          <NRadioGroup v-model:value="form.workflowNodeStatus">
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
      </NForm>

      <template #footer>
        <NButton type="primary" @click="save">保存</NButton>
        <NButton class="ml-12px" @click="close">取消</NButton>
      </template>
    </NDrawerContent>
  </NDrawer>
</template>
