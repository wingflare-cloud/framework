<script setup lang="ts">
import { ref } from 'vue';
import { $t } from '@/locales';
import { fetchGetUserSimpleList } from '@/service/api';

defineOptions({
  name: 'SystemUser'
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
const userNameList = ref();

async function getUserNameList() {
  const { data, error } = await fetchGetUserSimpleList();
  if (!error) {
    userNameList.value = data;
  }
}

const handleUpdate = (value: string) => {
  emit('update:modelValue', value);
};

getUserNameList();
</script>

<template>
  <NSelect
    v-model:value="model"
    :placeholder="$t('page.userManager.form.ownerName')"
    :options="userNameList"
    value-field="id"
    label-field="username"
    :disabled="props.disabled"
    :clearable="props.clearable"
    filterable
    @update:value="handleUpdate"
  />
</template>

<style scoped></style>
