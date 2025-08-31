<script setup lang="ts">
import { $t } from '@/locales';

defineOptions({
  name: 'NamespaceSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.Namespace.NamespaceSearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.namespace.keyword')" path="keyword" class="pr-24px">
      <NInput v-model:value="model.keyword" :placeholder="$t('page.namespace.form.keyword')" clearable />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
