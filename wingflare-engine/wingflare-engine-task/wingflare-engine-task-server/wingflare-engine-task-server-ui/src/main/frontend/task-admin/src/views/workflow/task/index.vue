<script setup lang="tsx">
import { NButton, NDropdown, NPopconfirm, NPopover, NTag } from 'naive-ui';
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import { useBoolean } from '@sa/hooks';
import { fetchBatchDeleteWorkflow, fetchGetWorkflowPageList, fetchUpdateWorkflowStatus } from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { triggerTypeRecord } from '@/constants/business';
import StatusSwitch from '@/components/common/status-switch.vue';
import { tagColor } from '@/utils/common';
import { useAuth } from '@/hooks/business/auth';
import { downloadFetch } from '@/utils/download';
import { useRouterPush } from '@/hooks/common/router';
import WorkflowSearch from './modules/workflow-search.vue';
import WorkflowTriggerModal from './modules/workflow-trigger-modal.vue';

const { hasAuth } = useAuth();

const router = useRouter();
const appStore = useAppStore();
const { routerPushByKey } = useRouterPush();

const triggerData = ref<Api.Workflow.Workflow | null>();
const { bool: triggerVisible, setTrue: openTriggerModal } = useBoolean(false);

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetWorkflowPageList,
  apiParams: {
    page: 1,
    size: 10,
    // if you want to use the searchParams in Form, you need to define the following properties, and the value is null
    // the value can not be undefined, otherwise the property in Form will not be reactive
    workflowName: null,
    groupName: null,
    workflowStatus: null,
    ownerId: null
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
      width: 120
    },
    {
      key: 'workflowName',
      title: $t('page.workflow.workflowName'),
      align: 'center',
      minWidth: 120,
      render: row => {
        function showDetailDrawer() {
          detail(row.id!);
        }

        return (
          <n-button text tag="a" type="primary" onClick={showDetailDrawer} class="ws-normal">
            {row.workflowName}
          </n-button>
        );
      }
    },
    {
      key: 'groupName',
      title: $t('page.workflow.groupName'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'ownerName',
      title: $t('page.workflow.ownerName'),
      align: 'center',
      width: 180
    },
    {
      key: 'nextTriggerAt',
      title: $t('page.workflow.nextTriggerAt'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'workflowStatus',
      title: $t('page.workflow.workflowStatus'),
      align: 'center',
      minWidth: 120,
      render: row => {
        const fetchFn = async (workflowStatus: Api.Common.EnableStatusNumber, callback: (flag: boolean) => void) => {
          const { error } = await fetchUpdateWorkflowStatus({ id: row.id!, status: workflowStatus });
          if (!error) {
            row.workflowStatus = workflowStatus;
            window.$message?.success($t('common.updateSuccess'));
          }
          callback(!error);
        };

        return <StatusSwitch v-model:value={row.workflowStatus} onSubmitted={fetchFn} />;
      }
    },
    {
      key: 'triggerType',
      title: $t('page.workflow.triggerType'),
      align: 'center',
      width: 140,
      render: row => {
        if (!row.triggerType) {
          return null;
        }

        const label = $t(triggerTypeRecord[row.triggerType!]);
        return <NTag type={tagColor(row.triggerType)}>{label}</NTag>;
      }
    },
    {
      key: 'triggerInterval',
      title: $t('page.workflow.triggerInterval'),
      align: 'center',
      minWidth: 140,
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
      key: 'executorTimeout',
      title: $t('page.workflow.executorTimeout'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'updateDt',
      title: $t('page.workflow.updateDt'),
      align: 'center',
      minWidth: 120
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      fixed: 'right',
      width: 200,
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

            <NButton type="error" text ghost size="small" onClick={() => execute(row)}>
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
  checkedRowKeys,
  onBatchDeleted,
  onDeleted
  // closeDrawer
} = useTableOperate(data, getData);

async function handleBatchDelete() {
  const { error } = await fetchBatchDeleteWorkflow(checkedRowKeys.value);
  if (error) return;
  onBatchDeleted();
}

async function handleDelete(id: string) {
  // request
  const { error } = await fetchBatchDeleteWorkflow([id!]);
  if (error) return;
  onDeleted();
}

function edit(id: string) {
  router.push({ path: '/workflow/form/edit', query: { id } });
}

function handleAdd() {
  router.push({ path: '/workflow/form/add' });
}

function detail(id: string) {
  router.push({ path: '/workflow/form/detail', query: { id } });
}

function copy(id: string) {
  router.push({ path: '/workflow/form/copy', query: { id } });
}

// function batch(id: string) {
//   router.push({ path: '/workflow/batch', state: { workflowId: id } });
// }

async function execute(row: Api.Workflow.Workflow) {
  triggerData.value = row;
  openTriggerModal();
}

function body(): Api.Workflow.ExportWorkflow {
  return {
    workflowIds: checkedRowKeys.value,
    groupName: searchParams.groupName,
    workflowName: searchParams.workflowName,
    workflowStatus: searchParams.workflowStatus,
    ownerId: searchParams.ownerId
  };
}

function handleExport() {
  downloadFetch('/workflow/export', body(), $t('page.workflow.title'));
}
function goToBatch(workflowId: string) {
  const findItem = data.value.find(item => item.id === workflowId)!;
  routerPushByKey('workflow_batch', { state: { workflowId, workflowName: findItem.workflowName } });
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <WorkflowSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <DeleteAlert />
    <NCard
      :title="$t('page.workflow.title')"
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
          :show-delete="hasAuth('R_ADMIN')"
          @add="handleAdd"
          @delete="handleBatchDelete"
          @refresh="getData"
        >
          <template #addAfter>
            <FileUpload action="/workflow/import" accept="application/json" @refresh="getData" />
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
        :scroll-x="1300"
        :loading="loading"
        remote
        :row-key="row => row.id"
        :pagination="mobilePagination"
        class="sm:h-full"
      />
    </NCard>
    <WorkflowTriggerModal v-model:visible="triggerVisible" :row-data="triggerData" />
  </div>
</template>

<style scoped></style>
