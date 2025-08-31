<script setup lang="tsx">
import { NButton, NDropdown, NPopconfirm, NTag, NTooltip } from 'naive-ui';
import { onMounted, ref } from 'vue';
import { useBoolean } from '~/packages/hooks';
import {
  fetchBatchDeleteRetry,
  fetchExecuteRetry,
  fetchGetAllGroupNameList,
  fetchGetRetryById,
  fetchGetRetryList,
  fetchUpdateRetryStatus
} from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { retryStatusTypeRecord, retryTaskTypeRecord } from '@/constants/business';
import { tagColor } from '@/utils/common';
import SvgIcon from '@/components/custom/svg-icon.vue';
import { useRouterPush } from '@/hooks/common/router';
import RetryTaskOperateDrawer from './modules/retry-operate-drawer.vue';
import RetryTaskBatchAddDrawer from './modules/retr-batch-add-drawer.vue';
import RetryTaskSearch from './modules/retry-search.vue';
import RetryTaskDetailDrawerVue from './modules/retry-detail-drawer.vue';

/** 详情页属性数据 */
const detailData = ref<Api.Retry.Retry | null>();
/** 详情页可见状态 */
const { bool: detailVisible, setTrue: openDetail } = useBoolean(false);
const { routerPushByKey } = useRouterPush();

const appStore = useAppStore();
const retryStatus = history.state.retryStatus;

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetRetryList,
  apiParams: {
    page: 1,
    size: 10,
    groupName: null,
    sceneName: null,
    idempotentId: null,
    bizNo: null,
    retryStatus: null
  },
  searchParams: {
    retryStatus
  },
  columns: () => [
    {
      type: 'selection',
      align: 'center',
      width: 48,
      disabled: row => row.retryStatus === 0
    },
    {
      key: 'id',
      align: 'center',
      width: 128,
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
      title: $t('page.retry.groupName'),
      align: 'center',
      width: 180
    },
    {
      key: 'sceneName',
      title: $t('page.retry.sceneName'),
      align: 'center',
      width: 180
    },
    {
      key: 'nextTriggerAt',
      title: $t('page.retry.nextTriggerAt'),
      align: 'center',
      width: 120
    },
    {
      key: 'retryCount',
      title: $t('page.retry.retryCount'),
      align: 'center',
      width: 100
    },
    {
      key: 'retryStatus',
      title: $t('page.retry.retryStatus'),
      align: 'center',
      width: 120,
      render: row => {
        if (row.retryStatus === null) {
          return null;
        }
        const label = $t(retryStatusTypeRecord[row.retryStatus!]);

        return <NTag type={tagColor(row.retryStatus!)}>{label}</NTag>;
      }
    },
    {
      key: 'taskType',
      title: $t('page.retry.taskType'),
      align: 'center',
      width: 120,
      render: row => {
        if (row.taskType === null) {
          return null;
        }
        const tagMap: Record<Api.Retry.TaskType, NaiveUI.ThemeColor> = {
          1: 'warning',
          2: 'error'
        };
        const label = $t(retryTaskTypeRecord[row.taskType!]);

        return <NTag type={tagMap[row.taskType!]}>{label}</NTag>;
      }
    },
    {
      key: 'idempotentId',
      title: $t('page.retry.idempotentId'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'bizNo',
      title: $t('page.retry.bizNo'),
      align: 'center',
      minWidth: 120
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
      width: 120,
      fixed: 'right',
      render: row => {
        const options = [
          {
            label: $t('common.execute'),
            key: 'execute',
            type: 'render',
            show: row.retryStatus !== 1,
            render: () => (
              <div class="flex-center">
                <NPopconfirm onPositiveClick={() => handleExecute(row.groupName!, row.id! as any)}>
                  {{
                    default: () => $t('common.confirmExecute'),
                    trigger: () => (
                      <NButton type="info" quaternary size="small">
                        {$t('common.execute')}
                      </NButton>
                    )
                  }}
                </NPopconfirm>
              </div>
            )
          },
          {
            type: 'divider',
            key: 'd2',
            show: row.retryStatus === 0
          },
          {
            label: $t('common.pause'),
            key: 'pause',
            type: 'render',
            show: row.retryStatus === 0,
            render: () => (
              <div class="flex-center">
                <NPopconfirm onPositiveClick={() => handlePause(Number(row.id!))}>
                  {{
                    default: () => $t('common.confirmPause'),
                    trigger: () => (
                      <NButton type="success" quaternary size="small">
                        {$t('common.pause')}
                      </NButton>
                    )
                  }}
                </NPopconfirm>
              </div>
            )
          },
          {
            type: 'divider',
            key: 'd2',
            show: row.retryStatus === 3
          },
          {
            label: $t('common.pause'),
            key: 'pause',
            type: 'render',
            show: row.retryStatus === 3,
            render: () => (
              <div class="flex-center">
                <NPopconfirm onPositiveClick={() => handleResume(Number(row.id!))}>
                  {{
                    default: () => $t('common.confirmResume'),
                    trigger: () => (
                      <NButton type="warning" quaternary size="small">
                        {$t('common.resume')}
                      </NButton>
                    )
                  }}
                </NPopconfirm>
              </div>
            )
          },
          {
            type: 'divider',
            key: 'd2',
            show: row.retryStatus !== 1 && row.retryStatus !== 2
          },
          {
            label: $t('common.finish'),
            key: 'finish',
            type: 'render',
            show: row.retryStatus !== 1 && row.retryStatus !== 2,
            render: () => (
              <div class="flex-center">
                <NPopconfirm onPositiveClick={() => handleFinish(Number(row.id!))}>
                  {{
                    default: () => $t('common.confirmFinish'),
                    trigger: () => (
                      <NButton type="primary" quaternary size="small">
                        {$t('common.finish')}
                      </NButton>
                    )
                  }}
                </NPopconfirm>
              </div>
            )
          },
          {
            type: 'divider',
            key: 'd2'
          },
          {
            key: 'delete',
            type: 'render',
            render: () => (
              <div class="flex-center">
                <NPopconfirm onPositiveClick={() => handleDelete(row.groupName!, row.id!)}>
                  {{
                    default: () => $t('common.confirmDelete'),
                    trigger: () => (
                      <NButton quaternary type="error" size="small">
                        {$t('common.delete')}
                      </NButton>
                    )
                  }}
                </NPopconfirm>
              </div>
            )
          }
        ];

        return (
          <div class="flex-center gap-8px">
            <NButton text type="warning" ghost size="small" onClick={() => goToRetryTask(row.id!)}>
              {$t('common.retryTaskList')}
            </NButton>

            <n-divider vertical />

            <NDropdown trigger="click" show-arrow={false} options={options} size="small">
              {{
                default: () => (
                  <NButton text type="primary" ghost size="small">
                    更多
                  </NButton>
                )
              }}
            </NDropdown>
          </div>
        );
      }
    }
  ]
});

function goToRetryTask(retryId: string) {
  routerPushByKey('retry_task', { state: { retryId } });
}

const {
  drawerVisible,
  operateType,
  handleAdd,
  checkedRowKeys,
  onBatchDeleted,
  onDeleted
  // closeDrawer
} = useTableOperate(data, getData);

const { bool: batchAddDrawerVisible, setTrue: openBatchAddDrawer } = useBoolean();

async function handleDelete(groupName: string, id: string) {
  const { error } = await fetchBatchDeleteRetry({ groupName, ids: [id] });
  if (error) return;
  onDeleted();
}

async function loadRetryInfo(row: Api.Retry.Retry) {
  const res = await fetchGetRetryById(row.id!, row.groupName!);
  detailData.value = (res.data as Api.Retry.Retry) || null;
}

async function handleBatchDelete() {
  const ids: string[] = checkedRowKeys.value as string[];
  if (ids.length === 0) return;
  const groupName = data.value[0].groupName;
  const { error } = await fetchBatchDeleteRetry({ groupName, ids });
  if (error) return;
  onBatchDeleted();
}

function handleBatchAdd() {
  openBatchAddDrawer();
}

function handleExecute(groupName: string, retryId: number) {
  fetchExecuteRetry({ groupName, retryIds: [retryId] });
}

function handleResume(id: number) {
  updateRetryTaskStatus(id, 0);
}

function handlePause(id: number) {
  updateRetryTaskStatus(id, 3);
}

function handleFinish(id: number) {
  updateRetryTaskStatus(id, 1);
}

async function updateRetryTaskStatus(id: number, status: Api.Retry.RetryStatusType) {
  const { error } = await fetchUpdateRetryStatus({ id, status });
  if (error) return;
  window.$message?.success($t('common.updateSuccess'));
  getData();
}

onMounted(async () => {
  const { error, data: groupList } = await fetchGetAllGroupNameList();
  if (!error && groupList.length > 0) {
    getData();
  }
});
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <RetryTaskSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <NCard
      :title="$t('page.retry.title')"
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
          @add="handleAdd"
          @delete="handleBatchDelete"
          @refresh="getData"
        >
          <template #addAfter>
            <NButton size="small" ghost type="primary" @click="handleBatchAdd">
              <template #icon>
                <icon-ic-round-plus class="text-icon" />
              </template>
              {{ $t('common.batchAdd') }}
            </NButton>
          </template>
        </TableHeaderOperation>
      </template>
      <NDataTable
        v-model:checked-row-keys="checkedRowKeys"
        :columns="columns"
        :data="data"
        :flex-height="!appStore.isMobile"
        :scroll-x="2000"
        :loading="loading"
        remote
        :row-key="row => row.id"
        :pagination="mobilePagination"
        class="sm:h-full"
      />
      <RetryTaskOperateDrawer v-model:visible="drawerVisible" :operate-type="operateType" @submitted="getData" />
      <RetryTaskBatchAddDrawer v-model:visible="batchAddDrawerVisible" @submitted="getData" />
      <RetryTaskDetailDrawerVue v-model:visible="detailVisible" :row-data="detailData" />
    </NCard>
  </div>
</template>

<style scoped></style>
