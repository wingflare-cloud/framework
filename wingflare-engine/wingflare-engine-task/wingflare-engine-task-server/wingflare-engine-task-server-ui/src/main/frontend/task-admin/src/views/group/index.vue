<script setup lang="tsx">
import { NButton, NPopconfirm, NTag } from 'naive-ui';
import { ref } from 'vue';
import { useBoolean } from '@sa/hooks';
import { fetchDeleteGroup, fetchGetGroupConfigList, fetchUpdateGroupStatus } from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { yesOrNoRecord } from '@/constants/business';
import { tagColor } from '@/utils/common';
import StatusSwitch from '@/components/common/status-switch.vue';
import { useAuth } from '@/hooks/business/auth';
import { downloadFetch } from '@/utils/download';
import GroupOperateDrawer from './modules/group-operate-drawer.vue';
import GroupDetailDrawer from './modules/group-detail-drawer.vue';
import GroupSearch from './modules/group-search.vue';
const { hasAuth } = useAuth();
const appStore = useAppStore();

/** 详情页属性数据 */
const detailData = ref<Api.GroupConfig.GroupConfig | null>();
/** 详情页可见状态 */
const { bool: detailVisible, setTrue: openDetail } = useBoolean(false);

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetGroupConfigList,
  apiParams: {
    page: 1,
    size: 10,
    groupName: null,
    groupStatus: null
  },
  columns: () => [
    {
      key: 'id',
      title: $t('common.index'),
      align: 'center',
      width: 64
    },
    {
      key: 'groupName',
      title: $t('page.groupConfig.groupName'),
      align: 'center',
      minWidth: 100,
      render: row => {
        function showDetailDrawer() {
          detailData.value = row || null;
          openDetail();
        }

        return (
          <n-button text tag="a" type="primary" onClick={showDetailDrawer} class="ws-normal">
            {row.groupName}
          </n-button>
        );
      }
    },
    {
      key: 'groupStatus',
      title: $t('page.groupConfig.groupStatus'),
      align: 'center',
      width: 120,
      render: row => {
        const fetchFn = async (groupStatus: Api.Common.EnableStatusNumber, callback: (flag: boolean) => void) => {
          const status = row.groupStatus === 1 ? 0 : 1;
          const { error } = await fetchUpdateGroupStatus({ groupName: row.groupName, groupStatus: status });
          if (!error) {
            row.groupStatus = groupStatus;
            window.$message?.success($t('common.updateSuccess'));
          }
          callback(!error);
        };
        return (
          <StatusSwitch v-model:value={row.groupStatus} onSubmitted={fetchFn} disabled={hasAuth('R_USER') as boolean} />
        );
      }
    },
    {
      key: 'initScene',
      title: $t('page.groupConfig.initScene'),
      align: 'center',
      minWidth: 120,
      render: row => {
        if (row.groupStatus === null) {
          return null;
        }

        const label = $t(yesOrNoRecord[row.initScene!]);

        return <NTag type={tagColor(row.initScene!)}>{label}</NTag>;
      }
    },
    {
      key: 'updateDt',
      title: $t('page.groupConfig.updateDt'),
      align: 'center',
      width: 120
    },
    {
      key: 'description',
      title: $t('page.groupConfig.description'),
      align: 'center',
      width: 250
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      width: 120,
      render: row => {
        if (hasAuth('R_USER')) {
          return <></>;
        }
        return (
          <div class="flex-center gap-8px">
            <NButton type="primary" text ghost size="small" onClick={() => edit(row.id!)}>
              {$t('common.edit')}
            </NButton>
            <n-divider vertical />
            <NPopconfirm onPositiveClick={() => handleDelete(row.groupName!)}>
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
  drawerVisible,
  operateType,
  editingData,
  handleAdd,
  handleEdit,
  checkedRowKeys,
  onDeleted
  // closeDrawer
} = useTableOperate(data, getData);

function edit(id: string) {
  handleEdit(id);
}

async function handleDelete(groupName: string) {
  const { error } = await fetchDeleteGroup(groupName);
  if (error) return;
  onDeleted();
}

function body(): Api.GroupConfig.ExportGroupConfig {
  return {
    groupName: searchParams.groupName,
    groupStatus: searchParams.groupStatus,
    groupIds: checkedRowKeys.value
  };
}

function handleExport() {
  downloadFetch('/group/export', body(), $t('page.groupConfig.title'));
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <GroupSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <DeleteAlert />
    <NCard
      :title="$t('page.groupConfig.title')"
      :bordered="false"
      size="small"
      header-class="view-card-header"
      class="sm:flex-1-hidden card-wrapper"
    >
      <template #header-extra>
        <TableHeaderOperation
          v-model:columns="columnChecks"
          :loading="loading"
          :show-add="hasAuth('R_ADMIN')"
          :show-delete="false"
          @add="handleAdd"
          @refresh="getData"
        >
          <template #addAfter>
            <FileUpload v-if="hasAuth('R_ADMIN')" action="/group/import" accept="application/json" @refresh="getData" />
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
      <GroupOperateDrawer
        v-model:visible="drawerVisible"
        :operate-type="operateType"
        :row-data="editingData"
        @submitted="getData"
      />
      <GroupDetailDrawer v-model:visible="detailVisible" :row-data="detailData" />
    </NCard>
  </div>
</template>

<style scoped></style>
