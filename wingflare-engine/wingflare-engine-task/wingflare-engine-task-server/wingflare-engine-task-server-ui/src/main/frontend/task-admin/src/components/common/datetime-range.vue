<script setup lang="ts">
import { $t } from '@/locales';
import { dayRange, monthRange } from '@/utils/common';

defineOptions({
  name: 'DatetimeRange'
});

// 注意：格式与 day.js 大小写不一样
const DATETIME_FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss";

const modelValue = defineModel<[string, string] | null>('value');

const createShortcuts = () => {
  const shortcuts: any = {};
  shortcuts[$t('common.today')] = dayRange(1);
  shortcuts[$t('common.lastWeek')] = dayRange(7);
  shortcuts[$t('common.lastMonth')] = monthRange(1, 'month');
  shortcuts[$t('common.currentMonth')] = monthRange(0, 'month');
  shortcuts[$t('common.lastMonth')] = monthRange(1, 'month');
  shortcuts[$t('common.lastTwoMonth')] = monthRange(2, 'month');
  return shortcuts;
};
</script>

<template>
  <NDatePicker
    v-model:formatted-value="modelValue"
    type="datetimerange"
    :value-format="DATETIME_FORMAT_ISO8601"
    clearable
    :default-time="['00:00:00', '23:56:56']"
    :shortcuts="createShortcuts()"
    :actions="['clear', 'confirm']"
  />
</template>

<style scoped lang="scss"></style>
