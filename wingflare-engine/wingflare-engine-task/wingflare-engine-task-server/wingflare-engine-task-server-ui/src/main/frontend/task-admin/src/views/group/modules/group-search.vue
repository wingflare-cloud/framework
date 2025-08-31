<script setup lang="ts">
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { groupConfigStatusOptions } from '@/constants/business';

defineOptions({
  name: 'GroupSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.GroupConfig.GroupConfigSearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.groupConfig.groupName')" path="groupName" class="pr-24px">
      <NInput v-model:value="model.groupName" :placeholder="$t('page.groupConfig.form.groupName')" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.groupConfig.groupStatus')" path="groupStatus" class="pr-24px">
      <NSelect
        v-model:value="model.groupStatus"
        :placeholder="$t('page.groupConfig.form.groupStatus')"
        :options="translateOptions(groupConfigStatusOptions)"
        clearable
      />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
