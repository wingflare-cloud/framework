<script setup lang="ts">
import { computed } from 'vue';
import { formatterWeek, parserWeek } from '../../shared/utils';
import { WEEK } from '../../shared/constants';

defineOptions({ name: 'InputNumber' });

interface Props {
  modelValue: number;
  range: [number, number];
  fieldValue?: string;
  locale?: I18n.LocaleType;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'update:modelValue', value: number): void;
  (e: 'change', value: number | null): void;
}

const emit = defineEmits<Emits>();

const value = computed({
  get() {
    return props.modelValue;
  },
  set(val) {
    emit('update:modelValue', val);
  }
});

const formatter = (val: number) => {
  return props.fieldValue === WEEK ? formatterWeek(val?.toString(), props.locale!) : null;
};

const parser = (val: string) => {
  return props.fieldValue === WEEK ? parserWeek(val, props.locale!) : null;
};

const onChange = (val: number | null): void => {
  emit('change', val);
};
</script>

<template>
  <NInputNumber
    v-model:value="value"
    :min="range[0]"
    :max="range[1]"
    class="w-90px"
    size="small"
    :formatter="formatter"
    :parser="parser"
    @update:value="onChange"
  />
</template>

<style scoped></style>
