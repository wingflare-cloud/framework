<script setup lang="ts">
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { taskBatchStatusRecordOptions } from '@/constants/business';

defineOptions({
  name: 'TaskBatchStatus'
});

interface Props {
  disabled?: boolean;
  clearable?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
  clearable: false
});
const modelValue = defineModel<Api.Common.TaskBatchStatus>();
</script>

<template>
  <NSelect
    v-model:value="modelValue"
    :placeholder="$t('common.taskBatchStatus.form')"
    :options="translateOptions(taskBatchStatusRecordOptions)"
    :disabled="props.disabled"
    :clearable="props.clearable"
  />
</template>

<style scoped></style>
