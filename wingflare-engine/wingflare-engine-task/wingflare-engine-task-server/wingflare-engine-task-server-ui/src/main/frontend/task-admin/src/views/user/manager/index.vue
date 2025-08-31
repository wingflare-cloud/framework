<script setup lang="tsx">
import { NButton, NPopconfirm, NTag } from 'naive-ui';
import { ref } from 'vue';
import { useBoolean } from '@sa/hooks';
import { fetchBatchDelteUser, fetchDelUser, fetchGetUserPageList } from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { roleRecord } from '@/constants/business';
import UserManagerOperateDrawer from './modules/user-manager-operate-drawer.vue';
import UserManagerSearch from './modules/user-manager-search.vue';
import UserManagerDetailDrawer from './modules/user-manager-detail-drawer.vue';

const appStore = useAppStore();

/** 详情页属性数据 */
const detailData = ref<Api.UserManager.UserManager | null>();
/** 详情页可见状态 */
const { bool: detailVisible, setTrue: openDetail } = useBoolean(false);

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetUserPageList,
  apiParams: {
    page: 1,
    size: 10,
    // if you want to use the searchParams in Form, you need to define the following properties, and the value is null
    // the value can not be undefined, otherwise the property in Form will not be reactive
    username: null
  },
  columns: () => [
    {
      key: 'permissions',
      align: 'center',
      type: 'expand',
      minWidth: 36,
      renderExpand: row => {
        return (
          <div>
            {
              <n-h5 prefix="bar" type="warning">
                <n-text type="warning">{$t('page.userManager.permissionList')}:</n-text>
              </n-h5>
            }
            {!row.permissions ? (
              <NTag type="info">ALL</NTag>
            ) : (
              row.permissions?.map(option => (
                <span>
                  <NTag type="info">
                    <span style="font-weight: bolder;">{option.groupName}</span>({option.namespaceName})
                  </NTag>
                  {<n-divider vertical />}
                </span>
              ))
            )}
          </div>
        );
      }
    },
    {
      type: 'selection'
    },
    {
      key: 'id',
      title: $t('common.index'),
      align: 'left',
      minWidth: 50
    },
    {
      key: 'username',
      title: $t('page.userManager.username'),
      align: 'left',
      minWidth: 120,
      render: row => {
        function showDetailDrawer() {
          detailData.value = row || null;
          openDetail();
        }

        return (
          <n-button text tag="a" type="primary" onClick={showDetailDrawer} class="ws-normal">
            {row.username}
          </n-button>
        );
      }
    },
    {
      key: 'role',
      title: $t('page.userManager.role'),
      align: 'left',
      minWidth: 50,
      render: row => {
        if (row.role === null) {
          return null;
        }
        const tagMap: Record<Api.UserManager.Role, NaiveUI.ThemeColor> = {
          1: 'info',
          2: 'warning'
        };
        const label = $t(roleRecord[row.role]);

        return <NTag type={tagMap[row.role!]}>{label}</NTag>;
      }
    },
    {
      key: 'createDt',
      title: $t('common.createDt'),
      align: 'left',
      minWidth: 50
    },
    {
      key: 'updateDt',
      title: $t('common.updateDt'),
      align: 'left',
      minWidth: 50
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      fixed: 'right',
      width: 130,
      render: row => (
        <div class="flex-center gap-8px">
          <NButton type="primary" ghost size="small" text onClick={() => edit(row.id!)}>
            {$t('common.edit')}
          </NButton>
          {(row.id as any) !== 1 ? (
            <>
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
            </>
          ) : (
            ''
          )}
        </div>
      )
    }
  ]
});

const { drawerVisible, operateType, editingData, handleAdd, handleEdit, checkedRowKeys, onDeleted, onBatchDeleted } =
  useTableOperate(data, getData);

async function handleDelete(id: string) {
  const { error } = await fetchDelUser(id as any);
  if (error) return;
  onDeleted();
}

async function handleBatchDelete() {
  const { error } = await fetchBatchDelteUser(checkedRowKeys.value);
  if (error) return;
  onBatchDeleted();
}

function edit(id: string) {
  handleEdit(id);
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <UserManagerSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <NCard
      :title="$t('page.userManager.title')"
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
      <UserManagerOperateDrawer
        v-model:visible="drawerVisible"
        :operate-type="operateType"
        :row-data="editingData"
        @submitted="getData"
      />
      <UserManagerDetailDrawer v-model:visible="detailVisible" :row-data="detailData" />
    </NCard>
  </div>
</template>

<style scoped></style>
