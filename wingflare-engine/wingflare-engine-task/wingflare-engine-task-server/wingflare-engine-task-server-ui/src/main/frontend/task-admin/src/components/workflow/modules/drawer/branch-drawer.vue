<script setup lang="ts">
import { ref, watch } from 'vue';
import type { FormInst, FormRules } from 'naive-ui';
import EditableInput from '@/components/common/editable-input.vue';
import { fetchCheckNodeExpression } from '@/service/api';
import { expressionOptions } from '@/constants/business';

defineOptions({
  name: 'BranchDrawer'
});

interface Props {
  open?: boolean;
  len?: number;
  modelValue?: Workflow.ConditionNodeType;
}

const props = withDefaults(defineProps<Props>(), {
  open: false,
  len: 0,
  modelValue: () => ({})
});

interface Emits {
  (e: 'update:open', open: boolean): void;
  (e: 'save', form: Workflow.NodeDataType): void;
}

const emit = defineEmits<Emits>();

const drawer = ref<boolean>(false);
const form = ref<Workflow.ConditionNodeType>({
  decision: {
    logicalCondition: 1,
    expressionType: 1,
    nodeExpression: '',
    checkContents: []
  }
});

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

const nodeExpressionFeedback = ref('');
const nodeExpressionStatus = ref<'error' | 'success'>('success');

const checkNodeExpression = async () => {
  nodeExpressionStatus.value = 'error';
  if (!form.value.decision?.nodeExpression) {
    nodeExpressionFeedback.value = '请填写条件表达式';
    return;
  }
  const { error, data } = await fetchCheckNodeExpression(form.value.decision!);
  if (!error) {
    if (data.key !== 1 || !data.value) {
      nodeExpressionFeedback.value = data.value || '请检查条件表达式';
      return;
    }
  } else {
    nodeExpressionFeedback.value = '接口请求失败';
    return;
  }
  nodeExpressionStatus.value = 'success';
  nodeExpressionFeedback.value = '表达式校验通过';
};

const rules: FormRules = {
  decision: {
    expressionType: [{ required: true, message: '请选择表达式类型', trigger: 'change', type: 'number' }],
    nodeExpression: [{ required: true, message: '请填写条件表达式', trigger: 'blur' }]
  }
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

      <NForm ref="formRef" :rules="rules" :model="form" label-align="left" label-width="100px">
        <NFormItem path="decision.expressionType" label="表达式类型">
          <NRadioGroup v-model:value="form.decision!.expressionType">
            <NSpace>
              <NRadio
                v-for="strategy in expressionOptions"
                :key="strategy.value"
                :label="strategy.label"
                :value="strategy.value"
              />
            </NSpace>
          </NRadioGroup>
        </NFormItem>
        <NFormItem
          path="decision.nodeExpression"
          label="条件表达式"
          :validation-status="nodeExpressionStatus"
          :feedback="nodeExpressionFeedback"
        >
          <CodeMirror v-model="form.decision!.nodeExpression" placeholder="请输入条件表达式" />
        </NFormItem>
        <NFormItem
          path="decision.checkContents"
          label="模拟上下文"
          :show-feedback="form.decision?.checkContents && form.decision.checkContents.length === 0"
        >
          <DynamicInput v-model:value="form.decision!.checkContents!" path="decision.checkContents" />
        </NFormItem>
        <NFormItem :show-label="false" :show-feedback="false">
          <NButton type="primary" ghost block @click="checkNodeExpression">校验条件表达式</NButton>
        </NFormItem>
      </NForm>

      <template #footer>
        <NButton type="primary" @click="save">保存</NButton>
        <NButton class="ml-12px" @click="close">取消</NButton>
      </template>
    </NDrawerContent>
  </NDrawer>
</template>

<style scoped lang="scss">
.drawer-title {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>

<style>
.sj-code-mirror {
  width: 100%;

  .cm-scroller::-webkit-scrollbar {
    width: 8px;
    height: 8px;
  }

  .cm-scroller::-webkit-scrollbar-thumb {
    background: #9c9c9c9c;
    -webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.3);
  }

  .cm-scroller::-webkit-scrollbar-track {
    background: #282c34;
  }
}
</style>
