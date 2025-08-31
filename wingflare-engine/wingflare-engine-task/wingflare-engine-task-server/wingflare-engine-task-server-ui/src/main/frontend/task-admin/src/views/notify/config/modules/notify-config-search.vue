<script setup lang="ts">
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { enableStatusNumberOptions } from '@/constants/business';

defineOptions({
  name: 'NotifyConfigSearch'
});

interface Emits {
  (e: 'reset'): void;

  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.NotifyConfig.NotifySearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm btn-span="12 s:24 m:24 1:24 xl:24" :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.notifyConfig.notifyName')" path="notifyName" class="pr-24px">
      <NInput v-model:value="model.notifyName" :placeholder="$t('page.notifyConfig.form.notifyName')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.notifyConfig.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
    </NFormItemGi>
    <NFormItemGi
      span="24 s:12 m:6"
      :label="$t('page.notifyConfig.systemTaskType')"
      path="systemTaskType"
      class="pr-24px"
    >
      <SystemTaskType v-model:value="model.systemTaskType" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.notifyConfig.notifyStatus')" path="notifyStatus" class="pr-24px">
      <NSelect
        v-model:value="model.notifyStatus"
        :placeholder="$t('page.notifyConfig.notifyStatus')"
        :options="translateOptions(enableStatusNumberOptions)"
        clearable
      />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
