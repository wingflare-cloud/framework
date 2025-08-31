<script setup lang="ts">
import { ref, watch } from 'vue';
import type { FormInst, FormRules } from 'naive-ui';
import { $t } from '@/locales';
import EditableInput from '@/components/common/editable-input.vue';
import { contentTypeOptions, workFlowNodeStatusOptions } from '@/constants/business';

defineOptions({
  name: 'CallbackDrawer'
});

interface Props {
  modelValue?: Workflow.ConditionNodeType;
  open?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  open: false,
  modelValue: () => ({})
});

interface Emits {
  (e: 'update:open', open: boolean): void;
  (e: 'save', form: Workflow.ConditionNodeType): void;
}

const emit = defineEmits<Emits>();

const drawer = ref<boolean>(false);
const form = ref<Workflow.ConditionNodeType>({});

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

const rules: FormRules = {
  workflowNodeStatus: [{ required: true, message: '请选择工作流状态', trigger: 'change' }],
  callback: {
    webhook: [{ required: true, message: '请输入 webhook', trigger: 'change' }],
    contentType: [{ required: true, message: '请选择请求类型', trigger: 'change', type: 'number' }],
    secret: [{ required: true, message: '请输入秘钥', trigger: 'change' }]
  }
};
</script>

<template>
  <NDrawer v-model:show="drawer" display-directive="if" :width="500" @after-leave="close">
    <NDrawerContent>
      <template #header>
        <div class="w-460px flex items-center">
          <EditableInput v-model="form.nodeName" class="mr-16px max-w-320px min-w-320px" />
        </div>
      </template>

      <NForm ref="formRef" :model="form" :rules="rules" label-align="left" label-width="100px">
        <NFormItem path="callback.webhook" label="webhook">
          <NInput v-model:value="form.callback!.webhook" placeholder="请输入 webhook" />
        </NFormItem>
        <NFormItem path="callback.contentType" label="请求类型">
          <NSelect
            v-model:value="form.callback!.contentType"
            :options="contentTypeOptions"
            placeholder="请选择请求类型"
          />
        </NFormItem>
        <NFormItem path="callback.secret" label="秘钥">
          <NInput v-model:value="form.callback!.secret" placeholder="请输入秘钥" />
        </NFormItem>
        <NFormItem
          name="workflowNodeStatus"
          label="回调通知状态"
          :rules="[{ required: true, message: '请选择回调通知状态', trigger: 'change' }]"
        >
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
