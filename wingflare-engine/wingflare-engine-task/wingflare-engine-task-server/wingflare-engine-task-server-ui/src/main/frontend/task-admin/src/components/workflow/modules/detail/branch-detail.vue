<script setup lang="ts">
import { ref, watch } from 'vue';
import BranchDesc from './branch-desc.vue';

defineOptions({
  name: 'BranchDetail'
});

interface Props {
  modelValue?: Workflow.ConditionNodeType;
  open?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  open: false,
  modelValue: () => ({})
});

interface Emits {
  (e: 'update:open', open: boolean): void;
}

const emit = defineEmits<Emits>();

const visible = ref(false);

const onClose = () => {
  emit('update:open', false);
};

watch(
  () => props.open,
  val => {
    visible.value = val;
  },
  { immediate: true }
);
</script>

<template>
  <DetailDrawer v-model="visible" title="决策详情" :width="['500px', '90%']" @after-leave="onClose">
    <BranchDesc v-if="visible" :model-value="modelValue" />
  </DetailDrawer>
</template>
