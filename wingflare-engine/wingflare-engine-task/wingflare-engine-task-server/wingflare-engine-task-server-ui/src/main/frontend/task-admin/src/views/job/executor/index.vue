<script setup lang="tsx">
import { NButton, NPopconfirm, NTag } from 'naive-ui';
import { fetchBatchDeleteJobExecutor, fetchGetExecutorList } from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import JobExecutorSearch from '@/views/job/executor/modules/job-executor-search.vue';
import { executorTypeRecord } from '@/constants/business';
const appStore = useAppStore();

const { columns, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetExecutorList,
  apiParams: {
    page: 1,
    size: 10,
    groupName: null,
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
      key: 'groupName',
      title: $t('page.jobTask.groupName'),
      align: 'center',
      width: 120
    },
    {
      key: 'executorInfo',
      title: $t('page.jobTask.executorInfo'),
      align: 'center',
      width: 300
    },
    {
      key: 'executorType',
      title: $t('page.jobTask.executorType'),
      align: 'center',
      width: 80,
      render: row => {
        if (row.executorType === null) {
          return null;
        }
        const label = $t(executorTypeRecord[row.executorType!]);
        const tagMap: Record<number, NaiveUI.ThemeColor> = {
          1: 'info',
          2: 'info',
          3: 'info'
        };
        return <NTag type={tagMap[row.executorType!]}>{label}</NTag>;
      }
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
      width: 80,
      fixed: 'right',
      render: row => {
        return (
          <div class="flex-center gap-8px">
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
  const { error } = await fetchBatchDeleteJobExecutor([id]);
  if (error) return;
  onDeleted();
}

async function handleBatchDelete() {
  const { error } = await fetchBatchDeleteJobExecutor(checkedRowKeys.value);
  if (error) return;
  onBatchDeleted();
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <JobExecutorSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <DeleteAlert />
    <NCard
      title="执行器列表"
      :bordered="false"
      size="small"
      class="sm:flex-1-hidden card-wrapper"
      header-class="view-card-header"
    >
      <template #header-extra>
        <TableHeaderOperation
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
        :scroll-x="1000"
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
