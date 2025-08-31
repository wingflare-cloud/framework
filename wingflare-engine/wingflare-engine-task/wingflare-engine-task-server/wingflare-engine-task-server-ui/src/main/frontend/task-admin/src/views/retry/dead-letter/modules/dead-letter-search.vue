<script setup lang="ts">
import { $t } from '@/locales';
import SelectGroup from '@/components/common/select-group.vue';
import SelectScene from '@/components/common/select-scene.vue';
import DatetimeRange from '@/components/common/datetime-range.vue';

defineOptions({
  name: 'RetryDeadLetterSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.RetryDeadLetter.RetryDeadLetterSearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm btn-span="24 s:12 m:9 l:12 xl:15" :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryTask.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryTask.sceneName')" path="sceneName" class="pr-24px">
      <SelectScene v-model:value="model.sceneName" :group-name="model.groupName as string" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retry.bizNo')" path="bizNo" class="pr-24px">
      <NInput v-model:value="model.bizNo" :placeholder="$t('page.retry.form.bizNo')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retry.idempotentId')" path="idempotentId" class="pr-24px">
      <NInput v-model:value="model.idempotentId" :placeholder="$t('page.retry.form.idempotentId')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:15 l:12 xl:9" :label="$t('page.common.createTime')" path="datetimeRange">
      <DatetimeRange v-model:value="model.datetimeRange!" />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
