<script setup lang="ts">
import { $t } from '@/locales';
import SelectGroup from '@/components/common/select-group.vue';

defineOptions({
  name: 'PodsSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.Dashboard.DashboardPodsParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.pods.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" :placeholder="$t('page.pods.form.groupName')" clearable />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
