<script setup lang="tsx">
import { ref } from 'vue';
import { executorTypeRecord, operationReasonRecord, taskBatchStatusRecord } from '@/constants/business';
import { $t } from '@/locales';
import { tagColor } from '@/utils/common';
import { fetchJobBatchRetry } from '@/service/api';

defineOptions({
  name: 'JobBatchDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.JobBatch.JobBatch | null;
  log?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  log: false,
  rowData: null
});

const visible = defineModel<boolean>('visible', {
  default: false
});

const taskData = ref<Api.Job.JobTask>();
const logShow = ref(false);

async function openLog(row: Api.Job.JobTask) {
  logShow.value = true;
  taskData.value = row;
}

async function retry() {
  const { error } = await fetchJobBatchRetry(props.rowData!.id!);
  if (!error) {
    window.$message?.success($t('common.operateSuccess'));
  }
}
</script>

<template>
  <DetailDrawer v-model="visible" :title="$t('page.jobBatch.detail')" :width="['50%', '90%']">
    <NTabs type="segment" animated :default-value="log ? 1 : 0">
      <NTabPane :name="0" :tab="$t('page.log.info')">
        <NDescriptions class="pt-16px" label-placement="top" bordered :column="2">
          <NDescriptionsItem :label="$t('page.jobBatch.groupName')">{{ rowData?.groupName }}</NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.jobBatch.jobName')">{{ rowData?.jobName }}</NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.jobBatch.taskBatchStatus')">
            <NTag :type="tagColor(rowData?.taskBatchStatus!)">
              {{ $t(taskBatchStatusRecord[rowData?.taskBatchStatus!]) }}
            </NTag>
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.jobBatch.executionAt')">{{ rowData?.executionAt }}</NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.jobBatch.operationReason')">
            <NTag :type="tagColor(rowData?.operationReason!)">
              {{ $t(operationReasonRecord[rowData?.operationReason!]) }}
            </NTag>
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.jobBatch.executorType')">
            <NTag :type="tagColor(rowData?.executorType!)">
              {{ $t(executorTypeRecord[rowData?.executorType!]) }}
            </NTag>
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('page.jobBatch.executorInfo')" :span="2">
            {{ rowData?.executorInfo }}
          </NDescriptionsItem>
          <NDescriptionsItem :label="$t('common.createDt')" :span="2">{{ rowData?.createDt }}</NDescriptionsItem>
        </NDescriptions>
      </NTabPane>
      <NTabPane :name="1" :tab="$t('page.log.title')" display-directive="if">
        <JobTaskListTable :row-data="rowData" @show-log="openLog" @retry="retry" />
      </NTabPane>
    </NTabs>
  </DetailDrawer>
  <!--  <LogDrawer v-model:show="logShow" :title="$t('page.log.title')" :task-data="taskData" />-->
  <LogDrawer v-model:show="logShow" :title="$t('page.log.title')" :task-data="taskData" />
</template>

<style scoped>
:deep(.n-tab-pane) {
  padding-top: 0 !important;
}
</style>
