<script setup lang="ts">
import { computed, ref } from 'vue';
import { $t } from '@/locales';
import { translateOptions } from '@/utils/common';
import { blockStrategyRecordOptions } from '@/constants/business';

defineOptions({
  name: 'BlockStrategy'
});

interface Props {
  ignore?: Api.Common.BlockStrategy[];
}

const props = defineProps<Props>();

const valueRef = ref<Api.Common.BlockStrategy>();
const emit = defineEmits<Emits>();

interface Emits {
  (e: 'update:value', value: Api.Common.BlockStrategy): void;
}

const handleUpdate = (value: Api.Common.BlockStrategy) => {
  emit('update:value', value);
};

const options = computed(() => {
  const list = translateOptions(blockStrategyRecordOptions);
  if (!props.ignore) {
    return list;
  }
  return list.filter(opt => !props.ignore?.includes(opt.value as Api.Common.BlockStrategy));
});
</script>

<template>
  <NSelect
    v-model:value="valueRef"
    :placeholder="$t('common.blockStrategy.form')"
    :options="options"
    @update:value="handleUpdate"
  />
</template>

<style scoped></style>
