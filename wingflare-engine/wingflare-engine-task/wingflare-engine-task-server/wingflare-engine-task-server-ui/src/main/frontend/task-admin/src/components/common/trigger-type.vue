<script setup lang="ts">
import { ref } from 'vue';
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { triggerTypeOptions } from '@/constants/business';

defineOptions({
  name: 'TriggerType'
});

interface Emits {
  (e: 'update:value', value: Api.Common.TriggerType): void;
}
const emit = defineEmits<Emits>();

const valueRef = ref<Api.Common.TriggerType>();

const handleUpdate = (value: Api.Common.TriggerType) => {
  emit('update:value', value);
};
</script>

<template>
  <NSelect
    v-model:value="valueRef"
    :placeholder="$t('common.triggerType.form')"
    :options="translateOptions(triggerTypeOptions)"
    @update:value="handleUpdate"
  />
</template>

<style scoped></style>
