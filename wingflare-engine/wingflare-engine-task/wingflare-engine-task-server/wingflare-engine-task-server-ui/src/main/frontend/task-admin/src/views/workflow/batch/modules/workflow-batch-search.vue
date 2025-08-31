<script setup lang="ts">
import { ref, watch } from 'vue';
import type { SelectOption } from 'naive-ui';
import { $t } from '@/locales';
import SelectGroup from '@/components/common/select-group.vue';
import TaskBatchStatus from '@/components/common/task-batch-status.vue';
import DatetimeRange from '@/components/common/datetime-range.vue';

import { fetchGetWorkflowNameList } from '@/service/api';

defineOptions({
  name: 'WorkflowBatchSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}
const noSearchFlag = ref(false);

const emit = defineEmits<Emits>();
/** 工作流列表 */
const workflowList = ref<Api.Workflow.Workflow[]>([]);

const model = defineModel<Api.WorkflowBatch.WorkflowBatchSearchParams>('model', { required: true });
const keywords = ref<string>(model.value.workflowName as any);

function reset() {
  keywords.value = '';
  emit('reset');
}

function search() {
  emit('search');
}

async function keywordsUpdate() {
  const res = await fetchGetWorkflowNameList({ keywords: keywords.value, groupName: model.value.groupName });
  workflowList.value = res.data as Api.Workflow.Workflow[];
}

function handleSelect(value: number) {
  model.value.workflowId = value;
}

watch(
  () => keywords.value,
  (value: string) => {
    if (value.length !== 0) {
      keywordsUpdate();
    } else {
      noSearchFlag.value = false;
    }
  }
);

function translateOptions(options: Api.Workflow.Workflow[]) {
  return options.map(option => ({
    value: option.id,
    label: option.workflowName
  }));
}

function renderLabel(option: SelectOption) {
  return [option.label as string, `(${option.value})`];
}
</script>

<template>
  <SearchForm btn-span="12 s:24 m:10 l:12 xl:16" :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:8" :label="$t('page.workflowBatch.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
    </NFormItemGi>
    <NFormItemGi
      span="24 s:12 m:8"
      :label="$t('page.workflowBatch.workflowName')"
      :label-width="100"
      path="workflowName"
      class="pr-24px"
    >
      <NAutoComplete
        v-model:value="keywords"
        :placeholder="$t('page.workflowBatch.form.workflowName')"
        :options="translateOptions(workflowList)"
        :empty-visible="noSearchFlag"
        clearable
        filterable
        :render-label="renderLabel"
        @select="handleSelect"
      />
    </NFormItemGi>
    <NFormItemGi
      span="24 s:12 m:8"
      :label="$t('page.workflowBatch.taskBatchStatus')"
      path="taskBatchStatus"
      class="pr-24px"
    >
      <TaskBatchStatus v-model:value="model.taskBatchStatus" clearable />
    </NFormItemGi>
    <NFormItemGi
      span="24 s:24 m:14 l:12 xl:8"
      :label="$t('page.common.createTime')"
      path="datetimeRange"
      class="pr-24px"
    >
      <DatetimeRange v-model:value="model.datetimeRange!" />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
