<script setup lang="tsx">
import { NButton, NDropdown, NPopconfirm, NPopover, NTag } from 'naive-ui';
import { useBoolean } from '@sa/hooks';
import { ref } from 'vue';
import { fetchBatchDeleteJob, fetchGetJobPage, fetchUpdateJobStatus } from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { blockStrategyRecord, taskTypeRecord, triggerTypeRecord } from '@/constants/business';
import StatusSwitch from '@/components/common/status-switch.vue';
import { useRouterPush } from '@/hooks/common/router';
import { useAuth } from '@/hooks/business/auth';
import { downloadFetch } from '@/utils/download';
import LabelList from '@/components/common/label-list.vue';
import JobTaskOperateDrawer from './modules/job-task-operate-drawer.vue';
import JobTaskTriggerModal from './modules/job-task-trigger-modal.vue';
import JobTaskSearch from './modules/job-task-search.vue';
import JobTaskDetailDrawer from './modules/job-task-detail-drawer.vue';

const { hasAuth } = useAuth();

const appStore = useAppStore();
const { routerPushByKey } = useRouterPush();

/** 详情页属性数据 */
const detailData = ref<Api.Job.Job | null>();
/** 详情页可见状态 */
const { bool: detailVisible, setTrue: openDetail } = useBoolean(false);
const triggerData = ref<Api.Job.Job | null>();
const { bool: triggerVisible, setTrue: openTriggerModal } = useBoolean(false);

const { columnChecks, columns, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetJobPage,
  apiParams: {
    page: 1,
    size: 10,
    groupName: null,
    jobName: null,
    jobStatus: null,
    ownerId: null,
    executorInfo: null
  },
  columns: () => [
    {
      type: 'selection',
      align: 'center',
      width: 48
    },
    {
      key: 'id',
      title: $t('common.index'),
      align: 'center',
      width: 48
    },
    {
      key: 'jobName',
      title: $t('page.jobTask.jobName'),
      align: 'center',
      width: 140,
      fixed: 'left',
      render: row => {
        async function showDetailDrawer() {
          detailData.value = row;
          openDetail();
        }

        return (
          <n-button text tag="a" type="primary" onClick={showDetailDrawer} class="ws-normal">
            {`${row.jobName}`}
          </n-button>
        );
      }
    },
    {
      key: 'groupName',
      title: $t('page.jobTask.groupName'),
      align: 'left',
      width: 150
    },
    {
      key: 'executorInfo',
      title: $t('page.jobTask.executorInfo'),
      align: 'left',
      width: 120
    },
    {
      key: 'ownerName',
      title: $t('page.jobTask.ownerName'),
      align: 'left',
      width: 120
    },
    {
      key: 'labels',
      title: $t('page.pods.labels'),
      align: 'center',
      width: 120,
      render: row => {
        return <LabelList labels={row.labels} />;
      }
    },
    {
      key: 'nextTriggerAt',
      title: $t('page.jobTask.nextTriggerAt'),
      align: 'center',
      width: 120
    },
    {
      key: 'jobStatus',
      title: $t('page.jobTask.jobStatus'),
      align: 'center',
      width: 60,
      render: row => {
        const fetchFn = async (jobStatus: Api.Common.EnableStatusNumber, callback: (flag: boolean) => void) => {
          const { error } = await fetchUpdateJobStatus({ id: row.id!, status: jobStatus });
          if (!error) {
            row.jobStatus = jobStatus;
            window.$message?.success($t('common.updateSuccess'));
          }
          callback(!error);
        };

        return <StatusSwitch v-model:value={row.jobStatus} onSubmitted={fetchFn} />;
      }
    },
    {
      key: 'taskType',
      title: $t('page.jobTask.taskType'),
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
      key: 'triggerType',
      title: $t('page.jobTask.triggerType'),
      align: 'center',
      width: 120,
      render: row => {
        if (row.triggerType === null) {
          return null;
        }
        const tagMap: Record<Api.Job.TriggerType, NaiveUI.ThemeColor> = {
          2: 'info',
          3: 'success',
          5: 'warning',
          99: 'error'
        };
        const label = $t(triggerTypeRecord[row.triggerType!]);

        return <NTag type={tagMap[row.triggerType!]}>{label}</NTag>;
      }
    },
    {
      key: 'triggerInterval',
      title: $t('page.jobTask.triggerInterval'),
      align: 'center',
      width: 130,
      render: row => {
        const parseDates = (interval: string): string[] | null => {
          try {
            const parsed = JSON.parse(interval);
            return Array.isArray(parsed) && parsed.every(item => typeof item === 'string') ? parsed : null;
          } catch {
            return null;
          }
        };
        const dates = parseDates(row.triggerInterval);
        if (!dates || dates.length === 0) return row.triggerInterval;

        const renderTag = (text: string) => (
          <NTag class="cursor-pointer" type="default">
            {text}
          </NTag>
        );
        if (dates.length === 1) return renderTag(dates[0]);
        const [first, second] = dates;
        const last = dates[dates.length - 1];
        if (dates.length === 2) {
          return (
            <div class="flex flex-wrap gap-4px">
              {renderTag(first)}
              {renderTag(second)}
            </div>
          );
        }
        // 多于两个日期，显示 tooltip
        return (
          <NPopover trigger="hover" class="cursor-pointer">
            {{
              trigger: () => (
                <div class="flex flex-center flex-wrap gap-4px">
                  {renderTag(first)}
                  {renderTag('...')}
                  {renderTag(last)}
                </div>
              ),
              default: () => (
                <div class="w-310px flex flex-wrap gap-4px">
                  {dates.map(date => (
                    <NTag class="cursor-pointer" key={date} type="default">
                      {date}
                    </NTag>
                  ))}
                </div>
              )
            }}
          </NPopover>
        );
      }
    },
    {
      key: 'blockStrategy',
      title: $t('page.jobTask.blockStrategy'),
      align: 'center',
      width: 80,
      render: row => {
        if (row.blockStrategy === null) {
          return null;
        }
        const tagMap: Record<Api.Common.BlockStrategy, NaiveUI.ThemeColor> = {
          1: 'info',
          2: 'success',
          3: 'error',
          4: 'warning'
        };
        const label = $t(blockStrategyRecord[row.blockStrategy!]);

        return <NTag type={tagMap[row.blockStrategy!]}>{label}</NTag>;
      }
    },
    {
      key: 'executorTimeout',
      title: $t('page.jobTask.executorTimeout'),
      align: 'center',
      width: 80
    },
    {
      key: 'updateDt',
      title: $t('page.jobTask.updateDt'),
      align: 'center',
      width: 120
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      width: 150,
      fixed: 'right',
      render: row => {
        const options = [
          {
            label: $t('common.copy'),
            key: 'copy',
            click: () => copy(row.id!)
          },
          {
            type: 'divider',
            key: 'd2'
          },
          {
            label: $t('common.batchList'),
            key: 'batchList',
            click: () => goToBatch(row.id!)
          },
          {
            type: 'divider',
            key: 'd2'
          },
          {
            type: 'render',
            key: 'delete',
            render: () => (
              <div class="flex-center">
                <NPopconfirm onPositiveClick={() => handleDelete(row.id!)}>
                  {{
                    default: () => $t('common.confirmDelete'),
                    trigger: () => (
                      <NButton quaternary size="small">
                        {$t('common.delete')}
                      </NButton>
                    )
                  }}
                </NPopconfirm>
              </div>
            )
          }
        ];

        const onSelect = (key: string) => {
          const fun = options.filter(o => o.key === key)[0].click;
          if (fun) fun();
        };

        return (
          <div class="flex-center gap-8px">
            <NButton text type="warning" ghost size="small" onClick={() => edit(row.id!)}>
              {$t('common.edit')}
            </NButton>

            <n-divider vertical />

            <NButton type="error" text ghost size="small" onClick={() => handleTriggerJob(row)}>
              {$t('common.execute')}
            </NButton>

            <n-divider vertical />

            <NDropdown trigger="click" show-arrow={false} options={options} size="small" on-select={onSelect}>
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

const {
  drawerVisible,
  operateType,
  editingData,
  handleAdd,
  handleEdit,
  handleCopy,
  checkedRowKeys,
  onDeleted,
  onBatchDeleted
  // closeDrawer
} = useTableOperate(data, getData);

async function handleDelete(id: string) {
  const { error } = await fetchBatchDeleteJob([id]);
  if (error) return;
  onDeleted();
}

async function handleBatchDelete() {
  const { error } = await fetchBatchDeleteJob(checkedRowKeys.value);
  if (error) return;
  onBatchDeleted();
}

function add() {
  editingData.value = null;
  handleAdd();
}

function edit(id: string) {
  handleEdit(id);
}

function copy(id: string) {
  handleCopy(id);
}

async function handleTriggerJob(job: Api.Job.Job) {
  triggerData.value = job;
  openTriggerModal();
}

function goToBatch(jobId: string) {
  const findItem = data.value.find(item => item.id === jobId)!;
  routerPushByKey('job_batch', { state: { jobId, jobName: findItem.jobName } });
}

function body(): Api.Job.ExportJob {
  return {
    jobIds: checkedRowKeys.value,
    groupName: searchParams.groupName,
    jobName: searchParams.jobName,
    jobStatus: searchParams.jobStatus,
    ownerId: searchParams.ownerId
  };
}

function handleExport() {
  downloadFetch('/job/export', body(), $t('page.jobTask.title'));
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <JobTaskSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <DeleteAlert />
    <NCard
      :title="$t('page.jobTask.title')"
      :bordered="false"
      size="small"
      class="sm:flex-1-hidden card-wrapper"
      header-class="view-card-header"
    >
      <template #header-extra>
        <TableHeaderOperation
          v-model:columns="columnChecks"
          :loading="loading"
          :disabled-delete="checkedRowKeys.length === 0"
          :show-delete="hasAuth('R_ADMIN')"
          @add="add"
          @delete="handleBatchDelete"
          @refresh="getData"
        >
          <template #addAfter>
            <FileUpload action="/job/import" accept="application/json" @refresh="getData" />
            <NPopconfirm @positive-click="handleExport">
              <template #trigger>
                <NButton size="small" ghost type="primary" :disabled="checkedRowKeys.length === 0 && hasAuth('R_USER')">
                  <template #icon>
                    <IconPajamasExport class="text-icon" />
                  </template>
                  {{ $t('common.export') }}
                </NButton>
              </template>
              <template #default>
                {{
                  checkedRowKeys.length === 0
                    ? $t('common.exportAll')
                    : $t('common.exportPar', { num: checkedRowKeys.length })
                }}
              </template>
            </NPopconfirm>
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
    </NCard>
    <JobTaskOperateDrawer
      v-model:visible="drawerVisible"
      :operate-type="operateType"
      :row-data="editingData"
      @submitted="getData"
    />
    <JobTaskDetailDrawer v-model:visible="detailVisible" :row-data="detailData" />
    <JobTaskTriggerModal v-model:visible="triggerVisible" :row-data="triggerData" />
  </div>
</template>

<style scoped></style>
