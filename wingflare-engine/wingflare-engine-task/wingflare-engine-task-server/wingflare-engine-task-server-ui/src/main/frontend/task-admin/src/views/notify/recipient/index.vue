<script setup lang="tsx">
import { NButton, NDivider, NPopconfirm, NTag } from 'naive-ui';
import { ref } from 'vue';
import { useBoolean } from '@sa/hooks';
import { fetchDeleteNotifyRecipient, fetchGetNotifyRecipientPageList } from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { alarmTypeRecord } from '@/constants/business';
import { tagColor } from '@/utils/common';
import { useAuth } from '@/hooks/business/auth';
import { downloadFetch } from '@/utils/download';
import NotifyRecipientOperateDrawer from './modules/notify-recipient-operate-drawer.vue';
import NotifyRecipientSearch from './modules/notify-recipient-search.vue';
import NotifyRecipientDetailDrawer from './modules/notify-recipient-detail-drawer.vue';
const { hasAuth } = useAuth();

const appStore = useAppStore();

/** 详情页属性数据 */
const detailData = ref<Api.NotifyRecipient.NotifyRecipient | null>();
/** 详情页可见状态 */
const { bool: detailVisible, setTrue: openDetail } = useBoolean(false);

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetNotifyRecipientPageList,
  apiParams: {
    page: 1,
    size: 10,
    // if you want to use the searchParams in Form, you need to define the following properties, and the value is null
    // the value can not be undefined, otherwise the property in Form will not be reactive
    recipientName: null,
    notifyType: null
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
      width: 64
    },
    {
      key: 'recipientName',
      title: $t('page.notifyRecipient.recipientName'),
      align: 'center',
      minWidth: 120,
      render: row => {
        function showDetailDrawer() {
          detailData.value = row || null;
          openDetail();
        }

        return (
          <n-button text tag="a" type="primary" onClick={showDetailDrawer} class="ws-normal">
            {row.recipientName}
          </n-button>
        );
      }
    },
    {
      key: 'notifyType',
      title: $t('page.notifyRecipient.notifyType'),
      align: 'center',
      minWidth: 120,
      render: row => {
        const label = $t(alarmTypeRecord[row.notifyType!]);
        return <NTag type={tagColor(row.notifyType)}>{label}</NTag>;
      }
    },
    {
      key: 'description',
      title: $t('page.notifyRecipient.description'),
      align: 'left',
      titleAlign: 'center',
      minWidth: 120
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      fixed: 'right',
      width: 130,
      render: row => (
        <div class="flex-center gap-8px">
          <NButton type="primary" text ghost size="small" onClick={() => edit(row.id!)}>
            {$t('common.edit')}
          </NButton>
          {hasAuth('R_ADMIN') ? (
            <>
              <NDivider vertical />
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
            </>
          ) : (
            ''
          )}
        </div>
      )
    }
  ]
});

const {
  drawerVisible,
  operateType,
  editingData,
  handleAdd,
  handleEdit,
  checkedRowKeys,
  onBatchDeleted
  // closeDrawer
} = useTableOperate(data, getData);

async function handleBatchDelete() {
  const { error } = await fetchDeleteNotifyRecipient(checkedRowKeys.value);
  if (error) return;
  onBatchDeleted();
}

async function handleDelete(id: string) {
  // request
  const { error } = await fetchDeleteNotifyRecipient([id]);
  if (!error) {
    window.$message?.success($t('common.deleteSuccess'));
    getData();
  }
}

function edit(id: string) {
  handleEdit(id);
}

function body(): Api.NotifyRecipient.ExportNotifyRecipient {
  return {
    notifyRecipientIds: checkedRowKeys.value,
    notifyType: searchParams.notifyType,
    recipientName: searchParams.recipientName
  };
}

function handleExport() {
  downloadFetch('/notify-recipient/export', body(), $t('page.notifyRecipient.title'));
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <NotifyRecipientSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <DeleteAlert />
    <NCard
      :title="$t('page.notifyRecipient.title')"
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
            <FileUpload action="/notify-recipient/import" accept="application/json" @refresh="getData" />
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
        :scroll-x="962"
        :loading="loading"
        remote
        :row-key="row => row.id"
        :pagination="mobilePagination"
        class="sm:h-full"
      />
      <NotifyRecipientOperateDrawer
        v-model:visible="drawerVisible"
        :operate-type="operateType"
        :row-data="editingData"
        @submitted="getData"
      />
    </NCard>
    <NotifyRecipientDetailDrawer v-model:visible="detailVisible" :row-data="detailData" />
  </div>
</template>

<style scoped></style>
