<script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '@/store/modules/auth';

const route = useRoute();
const authStore = useAuthStore();

const type = route.name as string;

const text = computed(() => {
  if (type === 'job_task') {
    return '删除前请检查待删除定时任务是存在通知配置或者工作流任务；';
  }

  if (type === 'retry_scene') {
    return '删除前请检查待删除重试场景是存在通知配置或者重试任务；';
  }

  if (type === 'workflow_task') {
    return '删除前请检查待删除工作流任务是存在通知配置；';
  }

  if (type === 'notify_recipient') {
    return '删除前请检查通知配置是存在关联通知人；';
  }

  return null;
});

const show = computed(() => authStore.getDeleteAlert(type) !== false);

const handleClose = () => {
  authStore.setDeleteAlert(type, false);
  return true;
};
</script>

<template>
  <NAlert v-if="show" :show-icon="false" type="warning" closable @close="handleClose">
    <div class="color-warning font-500">
      <!-- <span class="font-600">提示:</span> -->
      📢 {{ text }}该删除为
      <span class="color-error font-600">物理删除</span>
      ，删除后不可恢复，必要时可以先导出备份
    </div>
  </NAlert>
</template>

<style scoped lang="scss">
.n-alert {
  --n-padding: 5px 13px !important;
  --n-close-margin: 5px 13px 0 0 !important;

  :deep(.n-alert-body) {
    padding-right: calc(var(--n-close-size) + 15px);
  }
}
</style>
