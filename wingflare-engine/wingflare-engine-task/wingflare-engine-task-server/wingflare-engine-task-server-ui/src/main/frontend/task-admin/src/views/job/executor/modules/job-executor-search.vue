<script setup lang="ts">
import { $t } from '@/locales';
import SelectGroup from '@/components/common/select-group.vue';

defineOptions({
  name: 'JobExecutorSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.Job.JobExecutorParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm btn-span="24 s:12 m:12" :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.jobTask.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
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
  </SearchForm>
</template>

<style scoped></style>
