<script setup lang="ts">
import { ref, useAttrs, watch } from 'vue';
import type { InputProps } from 'naive-ui';
import { DEFAULT_CRON_EXPRESSION, DEFAULT_LOCALE } from '../shared/constants';
import CronModel from './internal/cron-model.vue';

defineOptions({ name: 'CronInput' });

const attrs: Partial<InputProps> = useAttrs();

interface Props {
  modelValue?: string;
  lang?: I18n.LocaleType;
}

const props = withDefaults(defineProps<Props>(), {
  modelValue: DEFAULT_CRON_EXPRESSION,
  lang: (JSON.parse(window.localStorage.getItem('lang')!) as I18n.LocaleType) || DEFAULT_LOCALE
});

interface Emits {
  (e: 'update:modelValue', value: string): void;
}

const emit = defineEmits<Emits>();
const cronModelRef = ref();
const cron = ref<string>(props.modelValue);

watch(
  () => cron.value,
  val => {
    emit('update:modelValue', val);
  },
  { deep: true }
);

defineExpose({
  validator: cronModelRef.value?.validator()
});
</script>

<template>
  <NPopover class="cron-popover" trigger="click" placement="bottom-start">
    <template #trigger>
      <NInput v-bind="attrs" v-model:value="cron" />
    </template>
    <CronModel ref="cronModelRef" v-model="cron" :lang="lang" />
  </NPopover>
</template>

<style>
.cron-popover {
  padding: 0 !important;
}
</style>
