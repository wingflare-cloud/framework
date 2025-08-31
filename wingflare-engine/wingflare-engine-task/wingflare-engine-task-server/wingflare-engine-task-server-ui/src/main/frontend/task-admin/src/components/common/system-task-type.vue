<script setup lang="ts">
import { ref } from 'vue';
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { systemTaskTypeOptions } from '@/constants/business';

defineOptions({
  name: 'SystemTaskType'
});

interface Emits {
  (e: 'update:value', value: Api.NotifyConfig.SystemTaskType): void;
}
const emit = defineEmits<Emits>();

const valueRef = ref<Api.NotifyConfig.SystemTaskType>();

const handleUpdate = (value: Api.NotifyConfig.SystemTaskType) => {
  emit('update:value', value);
};
</script>

<template>
  <NSelect
    v-model:value="valueRef"
    :placeholder="$t('page.notifyConfig.systemTaskType')"
    :options="translateOptions(systemTaskTypeOptions)"
    @update:value="handleUpdate"
  />
</template>

<style scoped></style>
