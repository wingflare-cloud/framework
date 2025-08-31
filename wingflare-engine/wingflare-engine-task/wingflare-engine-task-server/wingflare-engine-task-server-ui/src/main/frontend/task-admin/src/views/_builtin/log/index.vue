<script setup lang="ts">
import { useRoute } from 'vue-router';
import { computed, ref } from 'vue';
import { useRouterPush } from '@/hooks/common/router';
import { $t } from '@/locales';

const route = useRoute();
const { routerPushByKey } = useRouterPush();

const type = ref<'job' | 'retry'>(route.query.type as 'job' | 'retry');
const taskData = ref();
const { taskBatchId, jobId, taskId, groupName, uniqueId } = route.query as { [key: string]: string };

function init() {
  if (!['job', 'retry'].includes(type.value)) {
    routerPushByKey('404');
  }

  if (type.value === 'job') {
    taskData.value = { taskBatchId, jobId, id: taskId };
  }

  if (type.value === 'retry') {
    taskData.value = { groupName, uniqueId };
  }
}

init();

const title = computed(() => {
  if (type.value === 'job') {
    return `${$t('common.systemTaskType.job') + $t('page.log.title')} ------ JobId: ${jobId}, TaskId: ${taskId}, TaskBatchId: ${taskBatchId}`;
  }

  if (type.value === 'retry') {
    return `${$t('common.systemTaskType.retry') + $t('page.log.title')} ------ ${$t('page.retryTask.groupName')}: ${groupName}, ${$t('page.retryTask.title')}: ${uniqueId}`;
  }

  return $t('page.log.title');
});
</script>

<template>
  <div class="h-full">
    <LogDrawer :drawer="false" :title="title" :type="type" :task-data="taskData" />
  </div>
</template>

<style scoped>
:deep(.virtual-list) {
  max-height: calc(100vh - 66px);
}
</style>
