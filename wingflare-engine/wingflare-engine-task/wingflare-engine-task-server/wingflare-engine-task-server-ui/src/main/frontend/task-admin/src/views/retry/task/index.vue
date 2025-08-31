<script setup lang="tsx">
import { NButton, NPopconfirm, NTag, NTooltip } from 'naive-ui';
import { ref } from 'vue';
import { useBoolean } from '~/packages/hooks';
import {
  fetchBatchDeleteRetryTask,
  fetchDeleteRetryTask,
  fetchRetryTaskById,
  fetchRetryTaskPageList,
  fetchStopRetryTask
} from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { retryOperationReasonRecord, retryTaskStatusTypeRecord, retryTaskTypeRecord } from '@/constants/business';
import { monthRangeISO8601, tagColor } from '@/utils/common';
import SvgIcon from '@/components/custom/svg-icon.vue';
import RetryLogSearch from './modules/retry-task-search.vue';
import RetryLogDetailDrawer from './modules/retry-task-detail-drawer.vue';

const appStore = useAppStore();

/** 详情页属性数据 */
const detailData = ref<Api.RetryTask.RetryTask | null>();
/** 详情页可见状态 */
const { bool: detailVisible, setTrue: openDetail } = useBoolean(false);
const retryId = history.state.retryId;

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchRetryTaskPageList,
  apiParams: {
    page: 1,
    size: 10,
    // if you want to use the searchParams in Form, you need to define the following properties, and the value is null
    // the value can not be undefined, otherwise the property in Form will not be reactive
    groupName: null,
    sceneName: null,
    taskStatus: null,
    datetimeRange: monthRangeISO8601()
  },
  searchParams: {
    retryId
  },
  columns: () => [
    {
      type: 'selection',
      align: 'center',
      width: 48,
      disabled: row => row.taskStatus === 1
    },
    {
      key: 'id',
      align: 'center',
      width: 120,
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
        async function showDetailDrawer() {
          await loadRetryInfo(row);
          openDetail();
        }

        return (
          <n-button text tag="a" type="primary" onClick={showDetailDrawer} class="ws-normal">
            {row.id}
          </n-button>
        );
      }
    },
    {
      key: 'groupName',
      title: $t('page.retryTask.groupName'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'sceneName',
      title: $t('page.retryTask.sceneName'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'retryId',
      title: $t('page.retryTask.retryId'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'taskType',
      title: $t('page.retryTask.taskType'),
      align: 'center',
      minWidth: 80,
      render: row => {
        if (row.taskType === null) {
          return null;
        }
        const label = $t(retryTaskTypeRecord[row.taskType!]);

        return <NTag type={tagColor(row.taskType!)}>{label}</NTag>;
      }
    },
    {
      key: 'taskStatus',
      title: $t('page.retryTask.taskStatus'),
      align: 'center',
      minWidth: 80,
      render: row => {
        if (row.taskStatus === null) {
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

        const label = $t(retryTaskStatusTypeRecord[row.taskStatus!]);

        return <NTag type={tagMap[row.taskStatus!]}>{label}</NTag>;
      }
    },
    {
      key: 'operationReason',
      title: $t('page.retryTask.operationReason'),
      align: 'center',
      width: 240,
      render: row => {
        if (row.operationReason === null) {
          return null;
        }
        const label = $t(retryOperationReasonRecord[row.operationReason!]);

        return <NTag type={tagColor(row.operationReason!)}>{label}</NTag>;
      }
    },
    {
      key: 'createDt',
      title: $t('page.retryTask.createDt'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'updateDt',
      title: $t('page.retryTask.updateDt'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      width: 80,
      render: row => {
        const stopBtn = () => {
          if (row.taskStatus === 1 || row.taskStatus === 2) {
            return (
              <>
                <n-divider vertical />
                <NPopconfirm onPositiveClick={() => handleStopRetry(row.id!)}>
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
            );
          }
          return null;
        };
        return (
          <div class="flex-center gap-8px">
            {stopBtn()}
            {row.taskStatus !== 1 && row.taskStatus !== 2 ? (
              <NPopconfirm onPositiveClick={() => handleDelete(row.id)}>
                {{
                  default: () => $t('common.confirmDelete'),
                  trigger: () => (
                    <NButton type="error" text ghost size="small">
                      {$t('common.delete')}
                    </NButton>
                  )
                }}
              </NPopconfirm>
            ) : (
              ''
            )}
          </div>
        );
      }
    }
  ]
});

const { checkedRowKeys, onDeleted, onBatchDeleted } = useTableOperate(data, getData);

async function handleBatchDelete() {
  const { error } = await fetchBatchDeleteRetryTask(checkedRowKeys.value as any[]);
  if (error) return;
  onBatchDeleted();
}

async function handleDelete(id: any) {
  const { error } = await fetchDeleteRetryTask(id);
  if (error) return;
  onDeleted();
}

async function loadRetryInfo(row: Api.RetryTask.RetryTask) {
  const res = await fetchRetryTaskById(row.id!);
  detailData.value = (res.data as Api.RetryTask.RetryTask) || null;
}

async function handleStopRetry(id: string) {
  const { error } = await fetchStopRetryTask(id! as any);
  if (!error) {
    window.$message?.success($t('common.operateSuccess'));
    getData();
  }
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <RetryLogSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <NCard
      :title="$t('page.retryTask.title')"
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
    <RetryLogDetailDrawer v-model:visible="detailVisible" :row-data="detailData" />
  </div>
</template>

<style scoped></style>
