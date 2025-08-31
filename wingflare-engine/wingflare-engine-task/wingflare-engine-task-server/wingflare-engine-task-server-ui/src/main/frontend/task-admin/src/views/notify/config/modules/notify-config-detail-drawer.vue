<script setup lang="ts">
import { enableStatusNumberRecord, jobNotifyScene, retryNotifyScene, systemTaskType } from '@/constants/business';
import { $t } from '@/locales';
import { tagColor } from '@/utils/common';

defineOptions({
  name: 'NotifyConfigDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.NotifyConfig.NotifyConfig | null;
}

defineProps<Props>();

const visible = defineModel<boolean>('visible', {
  default: false
});
</script>

<template>
  <OperateDrawer v-model="visible" :title="$t('page.groupConfig.detail')">
    <NDescriptions label-placement="top" bordered :column="2">
      <NDescriptionsItem :label="$t('page.notifyConfig.notifyName')" :span="2">
        {{ rowData?.notifyName }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.groupConfig.groupName')" :span="2">
        {{ rowData?.groupName }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.notifyConfig.systemTaskType')" :span="1">
        <NTag :type="tagColor(rowData?.systemTaskType!)">{{ $t(systemTaskType[rowData?.systemTaskType!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.notifyConfig.notifyStatus')" :span="1">
        <NTag :type="tagColor(rowData?.notifyStatus!)">{{ $t(enableStatusNumberRecord[rowData?.notifyStatus!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem v-if="rowData?.systemTaskType === 1" :label="$t('page.notifyConfig.notifyScene')" :span="1">
        <NTag :type="tagColor(rowData?.notifyScene!)">
          {{ $t(retryNotifyScene[rowData?.notifyScene! as Api.NotifyConfig.RetryNotifyScene]) }}
        </NTag>
      </NDescriptionsItem>
      <NDescriptionsItem v-if="rowData?.systemTaskType === 3" :label="$t('page.notifyConfig.notifyScene')" :span="1">
        <NTag :type="tagColor(rowData?.notifyScene!)">
          {{ $t(jobNotifyScene[rowData?.notifyScene! as Api.NotifyConfig.JobNotifyScene]) }}
        </NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.notifyConfig.notifyThreshold')" :span="1">
        <NTag :type="tagColor(rowData?.notifyThreshold!)">{{ rowData?.notifyThreshold }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('common.createDt')" :span="2">
        {{ rowData?.createDt }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.notifyConfig.description')" :span="2">
        {{ rowData?.description }}
      </NDescriptionsItem>
    </NDescriptions>
  </OperateDrawer>
</template>

<style scoped></style>
