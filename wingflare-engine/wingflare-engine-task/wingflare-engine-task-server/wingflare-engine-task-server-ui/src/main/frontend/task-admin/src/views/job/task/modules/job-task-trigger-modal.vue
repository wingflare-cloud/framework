<script setup lang="ts">
import { computed, reactive, ref, watch } from 'vue';
import { type FormInst, NInputNumber } from 'naive-ui';
import { useFormRules } from '@/hooks/common/form';
import { $t } from '@/locales';
import { fetchTriggerJobParams } from '@/service/api';
import CodeMirror from '@/components/common/code-mirror.vue';
import { isNotNull } from '@/utils/common';

defineOptions({
  name: 'JobTaskTriggerModal'
});

interface Props {
  /** the edit row data */
  rowData?: Api.Job.Job | null;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'submitted'): void;
}

const emit = defineEmits<Emits>();

const visible = defineModel<boolean>('visible', {
  default: false
});

const dynamicForm = reactive({
  args: [{ arg: '' }]
});

const shardNum = ref(0);
const customformRef = ref<FormInst | null>(null);
const { defaultRequiredRule } = useFormRules();

type Model = Api.Job.TriggerJobParams;

const model: Model = reactive(createDefaultModel());

function createDefaultModel(): Model {
  return {
    jobId: props.rowData?.id,
    tmpArgsStr: ''
  };
}

type HttpParams = {
  method: string;
  url: string;
  mediaType: string;
  body?: string;
  headers: {
    [key in string]: string;
  };
  timeout: number;
};

const httpHeaders = ref<{ key: string; value: string }[]>([]);

const httpParams = reactive<HttpParams>(createDefaultHttpParams());

function createDefaultHttpParams() {
  return {
    method: 'POST',
    url: '',
    headers: {},
    body: '',
    mediaType: 'application/json',
    timeout: 60
  };
}

const executorCustomOptions = [
  {
    label: 'Http 执行器',
    value: 'HttpExecutor'
  },
  {
    label: 'CMD 执行器',
    value: 'CMDJobExecutor'
  },
  {
    label: 'PowerShell 执行器',
    value: 'PowerShellJobExecutor'
  },
  {
    label: 'Shell 执行器',
    value: 'ShellJobExecutor'
  }
];

type ScriptParams = {
  method: string;
  scriptParams: string;
  charset: string;
};

const scriptParams = reactive<ScriptParams>(createDefaultScriptParams());

function createDefaultScriptParams() {
  return {
    method: 'LOCAL_SCRIPT',
    scriptParams: '',
    charset: ''
  };
}

function handleUpdateModelWhenEdit() {
  httpHeaders.value = [];
  Object.assign(httpParams, createDefaultHttpParams());
  Object.assign(scriptParams, createDefaultScriptParams());
  Object.assign(model, createDefaultModel());
  if (!props.rowData) {
    return;
  }

  Object.assign(model, props.rowData);

  const taskType = props.rowData.taskType;
  const argsStr = props.rowData.argsStr;
  if (!argsStr) {
    return;
  }

  // 1:集群 2:广播 4:Map
  model.tmpArgsStr = argsStr;

  // 任务类型 3:切片
  if (taskType === 3) {
    Object.assign(dynamicForm, {
      args: JSON.parse(argsStr).map((item: string) => {
        return { arg: item };
      })
    });
  }

  // 5:MapReduce
  if (taskType === 5) {
    const argsJson = JSON.parse(argsStr);
    shardNum.value = argsJson.shardNum;
    model.tmpArgsStr = argsJson.argsStr;
  }

  if (executorCustomOptions.map(item => item.value).includes(props.rowData.executorInfo)) {
    if (props.rowData.executorInfo === 'HttpExecutor') {
      Object.assign(httpParams, JSON.parse(argsStr));
      if (httpParams.headers) {
        httpHeaders.value = Object.keys(httpParams.headers).map((item: string) => {
          return { key: item, value: httpParams.headers![item] };
        });
      }
    } else {
      Object.assign(scriptParams, JSON.parse(argsStr));
    }
  }
}

function closeDrawer() {
  visible.value = false;
}

function parseArgsStr() {
  if (props.rowData?.taskType === 3 && dynamicForm.args) {
    const slices = dynamicForm.args.map(item => item.arg.trim()).filter(item => Boolean(item));
    model.tmpArgsStr = slices.length > 0 ? JSON.stringify(slices) : '';
  }
  return model.tmpArgsStr;
}

const removeItem = (index: number) => {
  dynamicForm.args.splice(index, 1);
};

const addItem = () => {
  dynamicForm.args.push({ arg: '' });
};

watch(visible, () => {
  if (visible.value) {
    handleUpdateModelWhenEdit();
    customformRef.value?.restoreValidation();
  }
});

/** 分片参数变化, 解析并序列化到model.argsStr */
watch(dynamicForm, () => {
  if (visible.value && props.rowData?.taskType === 3) {
    parseArgsStr();
  }
});

/** 任务类型变化, 清理分片参数/方法参数 */
watch(
  () => props.rowData?.taskType,
  taskType => {
    if (visible.value) {
      if (taskType !== 3) {
        dynamicForm.args = [];
      }
      if (taskType !== 5) {
        shardNum.value = 1;
      }
      model.tmpArgsStr = '';
    }
  }
);

const httpMethodOptions = [
  {
    label: 'GET',
    value: 'get'
  },
  {
    label: 'POST',
    value: 'post'
  },
  {
    label: 'PUT',
    value: 'put'
  },
  {
    label: 'DELETE',
    value: 'delete'
  }
];

const scriptMethodOptions = [
  {
    label: '需下载脚本',
    value: 'DOWNLOAD'
  },
  {
    label: '脚本代码',
    value: 'SCRIPT_CODE'
  },
  {
    label: '使用本地脚本',
    value: 'LOCAL_SCRIPT'
  }
];

const executorCustomType = computed(() => {
  if (executorCustomOptions.map(item => item.value).includes(props.rowData!.executorInfo)) {
    return 1;
  }
  return 0;
});

async function handleSubmit() {
  let argsStr =
    props.rowData?.taskType === 5
      ? JSON.stringify({ shardNum: shardNum.value, argsStr: model.tmpArgsStr })
      : model.tmpArgsStr;

  if (executorCustomType.value === 1) {
    await customformRef.value?.validate();
    if (props.rowData?.executorInfo === 'HttpExecutor') {
      httpHeaders.value.forEach(item => {
        httpParams.headers[item.key] = item.value;
      });
      argsStr = JSON.stringify(httpParams);
    } else {
      argsStr = JSON.stringify(scriptParams);
    }
  }

  const { error } = await fetchTriggerJobParams({ jobId: props.rowData?.id, tmpArgsStr: argsStr });
  if (error) return;

  window.$message?.success($t('common.executeSuccess'));

  closeDrawer();
  emit('submitted');
}
</script>

<template>
  <NModal v-model:show="visible" class="max-w-90% w-600px" preset="card" title="执行任务" :bordered="false">
    <NForm :model="model">
      <NFormItem
        v-if="rowData"
        :label="$t('page.jobTask.argsStr')"
        path="argsStr"
        :show-label="executorCustomType === 0"
        :show-feedback="executorCustomType === 0"
        :rule="rowData?.taskType === 3 ? defaultRequiredRule : undefined"
      >
        <template v-if="executorCustomType === 0">
          <NCard v-if="rowData?.taskType === 3" class="flex-col">
            <NFormItem
              v-for="(item, index) in dynamicForm.args"
              :key="index"
              :label="`分片参数 ${index + 1}`"
              :path="`args[${index}].arg`"
              :show-feedback="false"
              class="m-b-12px"
              :rule="{
                required: true,
                message: `${$t('page.jobTask.form.argsStr')} ${index + 1}`,
                trigger: ['input', 'blur'],
                validator() {
                  return !!item.arg;
                }
              }"
            >
              <CodeMirror v-model="item.arg" lang="json" :placeholder="$t('page.jobTask.form.argsStr')" />
              <NButton class="ml-12px" type="error" dashed @click="removeItem(index)">
                <icon-ic-round-delete class="text-icon" />
              </NButton>
            </NFormItem>
            <NButton block dashed attr-type="button" @click="addItem">
              <icon-ic-round-plus class="text-icon" />
            </NButton>
          </NCard>
          <CodeMirror v-else v-model="model.tmpArgsStr" lang="json" :placeholder="$t('page.jobTask.form.argsStr')" />
        </template>
        <template v-else-if="rowData.executorInfo === 'HttpExecutor'">
          <NForm ref="customformRef" class="w-full" :model="httpParams">
            <NFormItem label="请求参数" :rule="defaultRequiredRule" path="url">
              <NInputGroup>
                <NSelect v-model:value="httpParams.method" class="http-method" :options="httpMethodOptions" />
                <NInput v-model:value="httpParams.url" placeholder="请输入请求地址" class="w-full" />
              </NInputGroup>
            </NFormItem>
            <NFormItem label="Media Type">
              <NInput v-model:value="httpParams.mediaType" placeholder="请输入 Media Type" />
            </NFormItem>
            <div class="n-form-item-label">Header 参数</div>
            <NDynamicInput
              v-model:value="httpHeaders"
              :class="httpHeaders.length ? undefined : 'mb-24px'"
              item-style="margin-bottom: 0;"
              :on-create="() => ({ key: '', value: '' })"
              #="{ index }"
            >
              <div class="flex">
                <NFormItem
                  ignore-path-change
                  :show-label="false"
                  :path="`headers[${index}].key`"
                  :rule="{
                    required: true,
                    message: `请输入键`,
                    trigger: ['input', 'blur'],
                    validator: () => isNotNull(httpHeaders[index].key)
                  }"
                >
                  <NInput v-model:value="httpHeaders[index].key" placeholder="Key" @keydown.enter.prevent />
                </NFormItem>
                <div class="mx-8px h-34px text-center line-height-34px">=</div>
                <NFormItem
                  ignore-path-change
                  :show-label="false"
                  :path="`headers[${index}].value`"
                  :rule="{
                    required: true,
                    message: `请输入值`,
                    trigger: ['input', 'blur'],
                    validator: () => isNotNull(httpHeaders[index].value)
                  }"
                >
                  <NInput v-model:value="httpHeaders[index].value" placeholder="Value" @keydown.enter.prevent />
                </NFormItem>
              </div>
            </NDynamicInput>
            <NFormItem label="Body 参数">
              <CodeMirror v-model="httpParams.body" lang="json" placeholder="请输入 Body 参数" />
            </NFormItem>
            <NFormItem label="接口超时时间">
              <NInputGroup>
                <NInputNumber
                  v-model:value="httpParams.timeout"
                  class="w-full"
                  :min="1"
                  :max="99999999"
                  :placeholder="$t('page.jobTask.form.executorTimeout')"
                  clearable
                />
                <NInputGroupLabel>{{ $t('common.second') }}</NInputGroupLabel>
              </NInputGroup>
            </NFormItem>
          </NForm>
        </template>
        <template v-else>
          <NForm ref="customformRef" class="w-full" :model="scriptParams">
            <NFormItem label="脚本类型">
              <NSelect v-model:value="scriptParams.method" :options="scriptMethodOptions" />
            </NFormItem>
            <NFormItem label="脚本参数">
              <CodeMirror v-model="scriptParams.scriptParams" lang="json" placeholder="请输入脚本参数" />
            </NFormItem>
            <NFormItem label="编码格式">
              <NInput v-model:value="scriptParams.charset" placeholder="请输入编码格式" />
            </NFormItem>
          </NForm>
        </template>
      </NFormItem>
    </NForm>
    <template #footer>
      <NSpace justify="end" :size="16">
        <NButton @click="closeDrawer">{{ $t('common.cancel') }}</NButton>
        <NButton type="primary" @click="handleSubmit">执行</NButton>
      </NSpace>
    </template>
  </NModal>
</template>

<style scoped>
.http-method {
  width: 130px !important;
}
</style>
