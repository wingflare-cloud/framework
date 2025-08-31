<script setup lang="ts">
import { $t } from '@/locales';

defineOptions({
  name: 'RetryDeadLetterDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.RetryDeadLetter.DeadLetter | null;
}

const visible = defineModel<boolean>('visible', {
  default: false
});
defineProps<Props>();
</script>

<template>
  <OperateDrawer v-model="visible" :title="$t('page.retryDeadLetter.detail')">
    <NDescriptions label-placement="top" bordered :column="3">
      <NDescriptionsItem :label="$t('page.retry.groupName')" :span="3">{{ rowData?.groupName }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retry.sceneName')" :span="3">{{ rowData?.sceneName }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retry.bizNo')" :span="3">{{ rowData?.bizNo }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retry.idempotentId')" :span="3">
        {{ rowData?.idempotentId }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retry.executorName')" :span="3">
        {{ rowData?.executorName }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retry.argsStr')" :span="3">{{ rowData?.argsStr }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retry.serializerName')" :span="2">
        {{ rowData?.serializerName }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('common.createDt')" :span="3">{{ rowData?.createDt }}</NDescriptionsItem>
    </NDescriptions>
  </OperateDrawer>
</template>

<style scoped></style>
