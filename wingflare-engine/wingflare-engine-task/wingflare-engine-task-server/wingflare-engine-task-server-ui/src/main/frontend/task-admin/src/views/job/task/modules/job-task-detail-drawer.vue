<script setup lang="ts">
import { $t } from '@/locales';
import { tagColor } from '@/utils/common';
import {
  blockStrategyRecord,
  enableStatusNumberRecord,
  executorTypeRecord,
  routeKeyRecord,
  taskTypeRecord,
  triggerTypeRecord
} from '@/constants/business';

defineOptions({
  name: 'JobTaskDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.Job.Job | null;
}
defineProps<Props>();

const visible = defineModel<boolean>('visible', {
  default: false
});
</script>

<template>
  <DetailDrawer v-model="visible" :title="$t('page.jobTask.detail')" :width="['50%', '90%']">
    <NDescriptions label-placement="top" bordered :column="2">
      <NDescriptionsItem :label="$t('page.jobTask.groupName')">{{ rowData?.groupName }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.jobName')">{{ rowData?.jobName }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.taskType')">
        <NTag :type="tagColor(rowData?.taskType!)">{{ $t(taskTypeRecord[rowData?.taskType!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.argsStr')">{{ rowData?.argsStr }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.nextTriggerAt')">
        {{ rowData?.nextTriggerAt }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.jobStatus')">
        <NTag :type="tagColor(rowData?.jobStatus!)">{{ $t(enableStatusNumberRecord[rowData?.jobStatus!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.routeKey')">
        <NTag :type="tagColor(rowData?.routeKey!)">{{ $t(routeKeyRecord[rowData?.routeKey!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.executorType')">
        <NTag :type="tagColor(rowData?.executorType!)">{{ $t(executorTypeRecord[rowData?.executorType!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.executorInfo')">
        {{ rowData?.executorInfo }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.triggerType')">
        <NTag :type="tagColor(rowData?.triggerType!)">{{ $t(triggerTypeRecord[rowData?.triggerType!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.triggerInterval')">
        {{ rowData?.triggerInterval }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.blockStrategy')">
        <NTag :type="tagColor(rowData?.blockStrategy!)">{{ $t(blockStrategyRecord[rowData?.blockStrategy!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.executorTimeout')">
        {{ rowData?.executorTimeout }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.maxRetryTimes')">
        {{ rowData?.maxRetryTimes }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.retryInterval')">
        {{ rowData?.retryInterval }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.parallelNum')">
        {{ rowData?.parallelNum }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.jobTask.description')" :span="2">
        {{ rowData?.description }}
      </NDescriptionsItem>
    </NDescriptions>
  </DetailDrawer>
</template>

<style scoped></style>
