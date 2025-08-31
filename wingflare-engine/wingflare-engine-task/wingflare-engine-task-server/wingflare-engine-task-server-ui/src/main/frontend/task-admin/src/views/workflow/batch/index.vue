<script setup lang="tsx">
import { NButton, NPopconfirm, NTag } from 'naive-ui';
import { useRouter } from 'vue-router';
import {
  fetchBatchDeleteWorkflowBatch,
  fetchDeleteWorkflowBatch,
  fetchGetWorkflowBatchList,
  fetchStopWorkflowBatch
} from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { operationReasonRecord, taskBatchStatusRecord } from '@/constants/business';
import { monthRangeISO8601, tagColor } from '@/utils/common';
import WorkflowBatchSearch from './modules/workflow-batch-search.vue';

const router = useRouter();

const appStore = useAppStore();
const workflowId = history.state.workflowId;
const workflowName = history.state.workflowName;
const taskBatchStatus = history.state.taskBatchStatus;

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetWorkflowBatchList,
  apiParams: {
    page: 1,
    size: 10,
    // if you want to use the searchParams in Form, you need to define the following properties, and the value is null
    // the value can not be undefined, otherwise the property in Form will not be reactive
    workflowId: null,
    workflowName: null,
    groupName: null,
    taskBatchStatus: null,
    datetimeRange: monthRangeISO8601()
  },
  searchParams: {
    workflowId,
    workflowName,
    taskBatchStatus
  },
  columns: () => [
    {
      type: 'selection'
    },
    {
      key: 'id',
      title: $t('common.index'),
      align: 'center',
      width: 120,
      render: row => {
        return (
          <NButton text tag="a" type="primary" onClick={() => handleDetail(row.id!)} class="ws-normal">
            {row.id}
          </NButton>
        );
      }
    },
    {
      key: 'workflowName',
      title: $t('page.workflowBatch.workflowName'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'groupName',
      title: $t('page.workflowBatch.groupName'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'executionAt',
      title: $t('page.workflowBatch.executionAt'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'taskBatchStatus',
      title: $t('page.workflowBatch.taskBatchStatus'),
      align: 'center',
      minWidth: 120,
      render: row => {
        if (!row.taskBatchStatus) {
          return null;
        }

        const tagMap: Record<number, NaiveUI.ThemeColor> = {
          1: 'info',
          2: 'info',
          3: 'info',
          4: 'error',
          5: 'error',
          6: 'error'
        };

        const label = $t(taskBatchStatusRecord[row.taskBatchStatus!]);
        return <NTag type={tagMap[row.taskBatchStatus]}>{label}</NTag>;
      }
    },
    {
      key: 'operationReason',
      title: $t('page.workflowBatch.operationReason'),
      align: 'center',
      minWidth: 120,
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
      title: $t('page.workflowBatch.createDt'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      minWidth: 130,
      render: row => (
        <div class="flex-center gap-8px">
          <NButton type="primary" text ghost size="small" onClick={() => handleDetail(row.id!)}>
            {$t('common.log')}
          </NButton>
          <n-divider vertical />
          {row?.taskBatchStatus === 1 || row?.taskBatchStatus === 2 ? (
            <>
              <NPopconfirm onPositiveClick={() => handleStop(row.id!)}>
                {{
                  default: () => $t('common.confirmStop'),
                  trigger: () => (
                    <NButton type="error" text ghost size="small">
                      {$t('common.stop')}
                    </NButton>
                  )
                }}
              </NPopconfirm>
              <n-divider vertical />
            </>
          ) : null}
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
      )
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
  const { error } = await fetchDeleteWorkflowBatch(id);
  if (error) return;
  onDeleted();
}

async function handleBatchDelete() {
  const { error } = await fetchBatchDeleteWorkflowBatch(checkedRowKeys.value);
  if (error) return;
  onBatchDeleted();
}

async function handleStop(id: string) {
  const { error } = await fetchStopWorkflowBatch(id);
  if (!error) {
    window.$message?.success($t('common.executeSuccess'));
    getData();
  }
}

function handleDetail(id: string) {
  router.push({ path: '/workflow/form/batch', query: { id } });
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <WorkflowBatchSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <NCard
      :title="$t('page.workflowBatch.title')"
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
        :scroll-x="962"
        :loading="loading"
        remote
        :row-key="row => row.id"
        :pagination="mobilePagination"
        class="sm:h-full"
      />
    </NCard>
  </div>
</template>

<style scoped></style>
