<script setup lang="tsx">
import { $t } from '@/locales';

defineOptions({
  name: 'FlowLogDrawer'
});

interface Props {
  title?: string;
  taskData: Workflow.JobTaskType;
}

withDefaults(defineProps<Props>(), {
  title: $t('workflow.node.log.title')
});

const show = defineModel<boolean>('show', {
  default: true
});
</script>

<template>
  <LogDrawer v-model:show="show" :title="title" :task-data="taskData as any" />
</template>

<style scoped></style>
