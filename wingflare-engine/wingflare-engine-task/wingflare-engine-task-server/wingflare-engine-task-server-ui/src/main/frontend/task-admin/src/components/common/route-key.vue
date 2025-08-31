<script setup lang="ts">
import { computed } from 'vue';
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { routeKeyRecordOptions } from '@/constants/business';

defineOptions({
  name: 'RouterKey'
});

interface Props {
  taskType?: Api.Common.TaskType;
}

const props = defineProps<Props>();

const modelValue = defineModel<Api.Common.RouteKey>('value', {
  default: 4
});

/** select 下拉选项 */
const selectOptions = computed(() => {
  // 2:广播, 3:切片 ==> 只能选择`轮询`
  if (props.taskType === 2 || props.taskType === 3) {
    return translateOptions(routeKeyRecordOptions.filter(o => o.value === 4));
  }
  // 默认undefined, 1:集群 ==> 选项不受限
  return translateOptions(routeKeyRecordOptions);
});
</script>

<template>
  <NSelect v-model:value="modelValue" :placeholder="$t('common.routeKey.routeForm')" :options="selectOptions" />
</template>

<style scoped></style>
