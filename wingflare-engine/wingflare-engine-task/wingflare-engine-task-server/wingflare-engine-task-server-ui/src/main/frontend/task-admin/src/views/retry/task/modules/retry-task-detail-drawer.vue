<script setup lang="ts">
import { $t } from '@/locales';
import { tagColor } from '@/utils/common';
import { retryStatusTypeRecord, retryTaskStatusTypeRecord, retryTaskTypeRecord } from '@/constants/business';

defineOptions({
  name: 'SceneDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.RetryTask.RetryTask | null;
}

defineProps<Props>();

const visible = defineModel<boolean>('visible', {
  default: false
});
</script>

<template>
  <DetailDrawer v-model="visible" :title="$t('page.retryTask.detail')" :width="['50%', '90%']">
    <NTabs type="segment" animated>
      <NTabPane :name="0" :tab="$t('page.log.info')">
        <NDescriptions label-placement="top" bordered :column="2">
          <NDescriptionsItem :label="$t('page.retryTask.groupName')" :span="2">
            {{ rowData?.groupName }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retryTask.sceneName')" :span="2">
            {{ rowData?.sceneName }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retryTask.taskStatus')" :span="1">
            <NTag :type="tagColor(rowData?.taskStatus!)">
              {{ $t(retryTaskStatusTypeRecord[rowData?.taskStatus!]) }}
            </NTag>
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retryTask.taskType')" :span="1">
            <NTag :type="tagColor(rowData?.taskType!)">{{ $t(retryTaskTypeRecord[rowData?.taskType!]) }}</NTag>
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retry.retryCount')" :span="1">
            {{ rowData?.responseVO?.retryCount }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retry.retryStatus')" :span="1">
            <NTag :type="tagColor(rowData?.responseVO?.retryStatus!)">
              {{ $t(retryStatusTypeRecord[rowData?.responseVO?.retryStatus!]) }}
            </NTag>
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retry.bizNo')" :span="2">
            {{ rowData?.responseVO?.bizNo }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retry.idempotentId')" :span="2">
            {{ rowData?.responseVO?.idempotentId }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retry.executorName')" :span="2">
            {{ rowData?.responseVO?.executorName }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retry.argsStr')" :span="2">
            {{ rowData?.responseVO?.argsStr }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.retry.serializerName')" :span="2">
            {{ rowData?.responseVO?.serializerName }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('common.createDt')">{{ rowData?.createDt }}</NDescriptionsItem>
          <NDescriptionsItem :label="$t('common.updateDt')">{{ rowData?.updateDt }}</NDescriptionsItem>
        </NDescriptions>
      </NTabPane>
      <NTabPane :name="1" :tab="$t('page.log.title')" display-directive="if">
        <LogDrawer :drawer="false" type="retry" :task-data="rowData!" />
      </NTabPane>
    </NTabs>
  </DetailDrawer>
</template>

<style scoped>
:deep(.virtual-list) {
  height: calc(100vh - 166px) !important;
  max-height: calc(100vh - 166px) !important;
}
</style>
