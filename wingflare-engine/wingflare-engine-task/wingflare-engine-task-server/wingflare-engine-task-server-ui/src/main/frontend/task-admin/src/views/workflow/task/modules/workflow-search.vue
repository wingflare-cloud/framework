<script setup lang="ts">
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { enableStatusNumberOptions } from '@/constants/business';
import SelectGroup from '@/components/common/select-group.vue';

defineOptions({
  name: 'WorkflowSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.Workflow.WorkflowSearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.workflow.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
    </NFormItemGi>
    <NFormItemGi
      span="24 s:12 m:6"
      :label="$t('page.workflow.workflowName')"
      path="workflowName"
      class="pr-24px"
      :label-width="100"
    >
      <NInput v-model:value="model.workflowName" :placeholder="$t('page.workflow.form.workflowName')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.workflow.workflowStatus')" path="workflowStatus" class="pr-24px">
      <NSelect
        v-model:value="model.workflowStatus"
        :placeholder="$t('page.workflow.form.workflowStatus')"
        :options="translateOptions(enableStatusNumberOptions)"
        clearable
      />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.workflow.ownerName')" path="ownerId" class="pr-24px">
      <SystemUser v-model:value="model.ownerId" clearable />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
