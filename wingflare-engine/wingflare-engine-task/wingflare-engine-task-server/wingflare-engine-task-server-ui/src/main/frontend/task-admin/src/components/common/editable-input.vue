<script setup lang="ts">
import type { InputInst } from 'naive-ui';
import { nextTick, ref, watch } from 'vue';

defineOptions({
  name: 'EditableInput'
});

interface Props {
  modelValue?: string;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'update:modelValue', modelValue: string): void;
}

const emit = defineEmits<Emits>();

const inputRef = ref<InputInst>();
const value = ref();
const idEdit = ref<boolean>(false);

watch(
  () => props.modelValue,
  val => {
    value.value = val;
  },
  { immediate: true }
);

const edit = () => {
  idEdit.value = true;
  nextTick(() => {
    inputRef.value?.focus();
  });
};

const save = () => {
  emit('update:modelValue', value.value!);
  idEdit.value = false;
};
</script>

<template>
  <NInput v-if="idEdit" ref="inputRef" v-model:value="value" type="text" @blur="save" />
  <NEllipsis v-else>
    <span class="flex items-center">
      {{ value }}
      <NButton text type="info" class="m-l-6px" @click="edit">
        <template #icon>
          <icon-ant-design:edit-outlined class="text-icon" />
        </template>
      </NButton>
    </span>
  </NEllipsis>
</template>

<style scoped lang="scss"></style>
