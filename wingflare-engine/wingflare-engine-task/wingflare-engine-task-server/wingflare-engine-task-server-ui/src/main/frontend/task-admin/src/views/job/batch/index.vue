<script setup lang="tsx">
import { NButton, NPopconfirm, NTag, NTooltip } from 'naive-ui';
import { useBoolean } from '@sa/hooks';
import { ref } from 'vue';
import dayjs from 'dayjs';
import {
  fetchBatchDeleteJobBatch,
  fetchDeleteJobBatch,
  fetchGetJobBatchList,
  fetchJobBatchRetry,
  fetchJobBatchStop
} from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { operationReasonRecord, taskBatchStatusRecord, taskTypeRecord } from '@/constants/business';
import { tagColor, weekRangeISO8601 } from '@/utils/common';
import SvgIcon from '@/components/custom/svg-icon.vue';
import JobBatchSearch from './modules/job-batch-search.vue';
import JobBatchDetailDrawer from './modules/job-batch-detail-drawer.vue';

const appStore = useAppStore();
/** 详情页属性数据 */
const detailData = ref<Api.JobBatch.JobBatch | null>();
/** 详情页可见状态 */
const { bool: detailVisible, setTrue: openDetail } = useBoolean(false);
const { bool: detailLog, setBool: setDetailLog } = useBoolean(false);
const jobName = history.state.jobName;
const jobId = history.state.jobId;
const taskBatchStatus = history.state.taskBatchStatus ? [history.state.taskBatchStatus] : [];

const { columnChecks, columns, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetJobBatchList,
  apiParams: {
    page: 1,
    size: 10,
    groupName: null,
    jobName: null,
    taskBatchStatus: null,
    jobId: null,
    datetimeRange: weekRangeISO8601()
  },
  searchParams: {
    jobId,
    jobName,
    taskBatchStatus
  },
  columns: () => [
    {
      type: 'selection',
      width: 30
    },
    {
      key: 'id',
      align: 'center',
      width: 100,
      fixed: 'left',
      title: () => {
        return (
          <div class="flex-center">
            <span>{$t('page.jobBatch.jobTask.id')}</span>
            <NTooltip trigger="hover">
              {{
                trigger: () => (
                  <span class="mb-2px ml-5px text-16px">
                    <SvgIcon icon="ant-design:info-circle-outlined" />
                  </span>
                ),
                default: () => <span>{$t('common.idDetailTip')}</span>
              }}
            </NTooltip>
          </div>
        );
      },
      render: row => {
        function showDetailDrawer() {
          detailData.value = row;
          setDetailLog(false);
          openDetail();
        }

        return (
          <NButton text tag="a" type="primary" onClick={showDetailDrawer} class="ws-normal">
            {row.id}
          </NButton>
        );
      }
    },
    {
      key: 'groupName',
      title: $t('page.jobBatch.groupName'),
      align: 'left',
      width: 150
    },
    {
      key: 'taskType',
      title: $t('page.jobBatch.taskType'),
      align: 'center',
      width: 120,
      render: row => {
        if (row.taskType === null) {
          return null;
        }
        const tagMap: Record<Api.Common.TaskType, NaiveUI.ThemeColor> = {
          1: 'info',
          2: 'success',
          3: 'error',
          4: 'primary',
          5: 'warning'
        };
        const label = $t(taskTypeRecord[row.taskType!]);

        return <NTag type={tagMap[row.taskType!]}>{label}</NTag>;
      }
    },
    {
      key: 'jobName',
      title: $t('page.jobBatch.jobName'),
      align: 'center',
      width: 150
    },
    {
      key: 'executionAt',
      title: $t('page.jobBatch.executionAt'),
      align: 'center',
      width: 120
    },
    {
      key: 'duration',
      title: $t('page.jobBatch.duration'),
      align: 'center',
      width: 120,
      render: row => {
        if (row.taskBatchStatus === 3) {
          return Math.round(dayjs(row.updateDt).diff(dayjs(row.executionAt)) / 1000);
        }
        return null;
      }
    },
    {
      key: 'taskBatchStatus',
      title: $t('page.jobBatch.taskBatchStatus'),
      align: 'center',
      width: 120,
      render: row => {
        if (row.taskBatchStatus === null) {
          return null;
        }
        const label = $t(taskBatchStatusRecord[row.taskBatchStatus!]);
        const tagMap: Record<number, NaiveUI.ThemeColor> = {
          1: 'info',
          2: 'info',
          3: 'info',
          4: 'error',
          5: 'error',
          6: 'error'
        };
        return <NTag type={tagMap[row.taskBatchStatus!]}>{label}</NTag>;
      }
    },
    {
      key: 'operationReason',
      title: $t('page.jobBatch.operationReason'),
      align: 'center',
      width: 120,
      render: row => {
        if (row.operationReason === null) {
          return null;
        }
        const label = $t(operationReasonRecord[row.operationReason!]);

        return <NTag type={tagColor(row.operationReason!)}>{label}</NTag>;
      }
    },
    {
      key: 'createDt',
      title: $t('common.createDt'),
      align: 'center',
      width: 120
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      width: 180,
      fixed: 'right',
      render: row => {
        const stopBtn = () => {
          if (row.taskBatchStatus === 1 || row.taskBatchStatus === 2) {
            return (
              <>
                <n-divider vertical />
                <NPopconfirm onPositiveClick={() => handleStopJob(row.id!)}>
                  {{
                    default: () => $t('common.confirmStop'),
                    trigger: () => (
                      <NButton type="error" text ghost size="small">
                        {$t('common.stop')}
                      </NButton>
                    )
                  }}
                </NPopconfirm>
              </>
            );
          }
          return null;
        };

        const retryBtn = () => {
          if (row.taskBatchStatus === 4 || row.taskBatchStatus === 5 || row.taskBatchStatus === 6) {
            return (
              <>
                <n-divider vertical />
                <NPopconfirm onPositiveClick={() => handleRetryJob(row.id!)}>
                  {{
                    default: () => $t('common.confirmRetry'),
                    trigger: () => (
                      <NButton type="error" text ghost size="small">
                        {$t('common.retry')}
                      </NButton>
                    )
                  }}
                </NPopconfirm>
              </>
            );
          }
          return null;
        };
        return (
          <div class="flex-center gap-8px">
            <NButton type="primary" text ghost size="small" onClick={() => handleLog(row)}>
              {$t('common.log')}
            </NButton>
            {stopBtn()}
            {retryBtn()}
            <n-divider vertical />
            <NPopconfirm onPositiveClick={() => handleDelete(row.id!)}>
              {{
                default: () => $t('common.confirmDelete'),
                trigger: () => (
                  <NButton type="error" text ghost size="small">
                    {$t('common.delete')}
                  </NButton>
                )
              }}
            </NPopconfirm>
          </div>
        );
      }
    }
  ]
});

const {
  checkedRowKeys,
  onDeleted,
  onBatchDeleted
  // closeDrawer
} = useTableOperate(data, getData);

async function handleDelete(id: string) {
  const { error } = await fetchDeleteJobBatch(id);
  if (error) return;
  onDeleted();
}

async function handleBatchDelete() {
  const { error } = await fetchBatchDeleteJobBatch(checkedRowKeys.value);
  if (error) return;
  onBatchDeleted();
}

function handleLog(row: Api.JobBatch.JobBatch) {
  detailData.value = row;
  setDetailLog(true);
  openDetail();
}

async function handleRetryJob(id: string) {
  const { error } = await fetchJobBatchRetry(id);
  if (!error) {
    window.$message?.success($t('common.operateSuccess'));
    getData();
  }
}

async function handleStopJob(id: string) {
  const { error } = await fetchJobBatchStop(id);
  if (!error) {
    window.$message?.success($t('common.operateSuccess'));
    getData();
  }
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <JobBatchSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <NCard
      :title="$t('page.jobBatch.title')"
      :bordered="false"
      size="small"
      class="sm:flex-1-hidden card-wrapper"
      header-class="view-card-header"
    >
      <template #header-extra>
        <TableHeaderOperation
          v-model:columns="columnChecks"
          :disabled-delete="checkedRowKeys.length === 0"
          :loading="loading"
          :show-add="false"
          @delete="handleBatchDelete"
          @refresh="getData"
        />
      </template>
      <NDataTable
        v-model:checked-row-keys="checkedRowKeys"
        :columns="columns"
        :data="data"
        :flex-height="!appStore.isMobile"
        :scroll-x="1200"
        :loading="loading"
        remote
        :row-key="row => row.id"
        :pagination="mobilePagination"
        class="sm:h-full"
      />
    </NCard>
    <JobBatchDetailDrawer
      v-if="detailVisible"
      v-model:visible="detailVisible"
      v-model:log="detailLog"
      :row-data="detailData"
    />
  </div>
</template>

<style scoped></style>
