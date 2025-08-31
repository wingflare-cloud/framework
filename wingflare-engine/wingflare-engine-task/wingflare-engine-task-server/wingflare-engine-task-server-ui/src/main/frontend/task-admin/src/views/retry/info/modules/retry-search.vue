<script setup lang="ts">
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { retryStatusTypeOptions } from '@/constants/business';
import SelectGroup from '@/components/common/select-group.vue';
import SelectScene from '@/components/common/select-scene.vue';

defineOptions({
  name: 'RetryTaskSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.Retry.RetrySearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retry.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retry.sceneName')" path="sceneName" class="pr-24px">
      <SelectScene v-model:value="model.sceneName" :group-name="model.groupName as string" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retry.idempotentId')" path="idempotentId" class="pr-24px">
      <NInput v-model:value="model.idempotentId" :placeholder="$t('page.retry.form.idempotentId')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retry.bizNo')" path="bizNo" class="pr-24px">
      <NInput v-model:value="model.bizNo" :placeholder="$t('page.retry.form.bizNo')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retry.retryStatus')" path="retryStatus" class="pr-24px">
      <NSelect
        v-model:value="model.retryStatus"
        :placeholder="$t('page.retry.form.retryStatus')"
        :options="translateOptions(retryStatusTypeOptions)"
        clearable
      />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
