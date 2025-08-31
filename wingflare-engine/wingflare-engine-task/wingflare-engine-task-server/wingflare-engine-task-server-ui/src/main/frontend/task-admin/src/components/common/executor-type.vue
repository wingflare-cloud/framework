<script setup lang="ts">
import { ref } from 'vue';
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { executorTypeRecordOptions } from '@/constants/business';

defineOptions({
  name: 'TriggerStrategy'
});

const valueRef = ref<Api.Common.TriggerType>();
const emit = defineEmits<Emits>();

interface Emits {
  (e: 'update:value', value: Api.Common.TriggerType): void;
}

const handleUpdate = (value: Api.Common.TriggerType) => {
  emit('update:value', value);
};
</script>

<template>
  <NSelect
    v-model:value="valueRef"
    :placeholder="$t('common.executorType.form')"
    :options="translateOptions(executorTypeRecordOptions)"
    @update:value="handleUpdate"
  />
</template>

<style scoped></style>
