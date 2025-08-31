<script setup lang="tsx">
import { ref } from 'vue';
import type { SelectOption } from 'naive-ui';
import { NEllipsis } from 'naive-ui';
import { $t } from '@/locales';
import { translateOptions2 } from '@/utils/common';
import { fetchGetAllGroupNameList } from '@/service/api';

defineOptions({
  name: 'SelectGroup'
});

interface Props {
  disabled?: boolean;
  clearable?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
  clearable: false
});

const model = defineModel<string | null>();
interface Emits {
  (e: 'update:modelValue', value: string): void;
}
const emit = defineEmits<Emits>();

/** 可选组列表 */
const groupNameList = ref<string[]>([]);

async function getGroupNameList() {
  const { data, error } = await fetchGetAllGroupNameList();
  if (!error) {
    groupNameList.value = data;
  }
}

const handleUpdate = (value: string) => {
  emit('update:modelValue', value);
};

getGroupNameList();

const renderLabel = (option: SelectOption) => <NEllipsis>{option.label}</NEllipsis>;
</script>

<template>
  <NSelect
    v-model:value="model"
    :placeholder="$t('page.retryTask.form.groupName')"
    :options="translateOptions2(groupNameList)"
    :disabled="props.disabled"
    :clearable="props.clearable"
    filterable
    :render-label="renderLabel"
    @update:value="handleUpdate"
  />
</template>

<style scoped></style>
