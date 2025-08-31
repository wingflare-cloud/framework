<script setup lang="ts">
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { enableStatusNumberOptions } from '@/constants/business';
import SelectGroup from '@/components/common/select-group.vue';

defineOptions({
  name: 'JobTaskSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.Job.JobSearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm btn-span="24 s:12 m:18 l:18 xl:18" :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.jobTask.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.jobTask.jobName')" path="jobName" class="pr-24px">
      <NInput v-model:value="model.jobName" :placeholder="$t('page.jobTask.form.jobName')" clearable />
    </NFormItemGi>
    <NFormItemGi
      span="24 s:12 m:6"
      :label="$t('page.jobTask.executorInfo')"
      path="executorInfo"
      class="pr-24px"
      :label-width="100"
    >
      <NInput v-model:value="model.executorInfo" :placeholder="$t('page.jobTask.form.executorInfo')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.jobTask.jobStatus')" path="jobStatus" class="pr-24px">
      <NSelect
        v-model:value="model.jobStatus"
        :placeholder="$t('page.jobTask.form.jobStatus')"
        :options="translateOptions(enableStatusNumberOptions)"
        clearable
      />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.jobTask.ownerName')" path="ownerId" class="pr-24px">
      <SystemUser v-model:value="model.ownerId" clearable />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
