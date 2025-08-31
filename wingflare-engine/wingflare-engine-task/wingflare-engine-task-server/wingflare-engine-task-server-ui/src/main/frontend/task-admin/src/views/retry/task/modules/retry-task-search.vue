<script setup lang="ts">
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { retryTaskStatusTypeOptions } from '@/constants/business';
import SelectGroup from '@/components/common/select-group.vue';
import SelectScene from '@/components/common/select-scene.vue';
import DatetimeRange from '@/components/common/datetime-range.vue';

defineOptions({
  name: 'RetryLogSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.RetryTask.RetryTaskSearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm btn-span="24 s:12 m:9 l:12 xl:18" :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryTask.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryTask.sceneName')" path="sceneName" class="pr-24px">
      <SelectScene v-model:value="model.sceneName" :group-name="model.groupName as string" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryTask.retryId')" path="bizNo" class="pr-24px">
      <NInput v-model:value="model.retryId" :placeholder="$t('page.retryTask.form.retryId')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retry.retryStatus')" path="retryStatus" class="pr-24px">
      <NSelect
        v-model:value="model.taskStatus"
        :placeholder="$t('page.retry.form.retryStatus')"
        :options="translateOptions(retryTaskStatusTypeOptions)"
        clearable
      />
    </NFormItemGi>
    <NFormItemGi
      span="24 s:24 m:14 l:12 xl:6"
      :label="$t('page.common.createTime')"
      path="datetimeRange"
      class="pr-24px"
    >
      <DatetimeRange v-model:value="model.datetimeRange!" />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
