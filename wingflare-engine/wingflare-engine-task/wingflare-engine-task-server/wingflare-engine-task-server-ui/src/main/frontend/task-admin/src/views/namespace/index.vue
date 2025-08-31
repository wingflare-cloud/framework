<script setup lang="tsx">
import { NButton, NPopconfirm, NPopover } from 'naive-ui';
import { ref } from 'vue';
import { useClipboard } from '@vueuse/core';
import { fetchDeleteNamespace, fetchGetNamespaceList } from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import { localStg } from '@/utils/storage';
import { useAuthStore } from '@/store/modules/auth';
import SvgIcon from '@/components/custom/svg-icon.vue';
import NamespaceOperateDrawer from './modules/namespace-operate-drawer.vue';
import NamespaceSearch from './modules/namespace-search.vue';

const appStore = useAppStore();
const authStore = useAuthStore();
const namespaceId = ref<string>(localStg.get('namespaceId')!);
const { copy, isSupported } = useClipboard();

const handleChange = (uniqueId: string) => {
  namespaceId.value = uniqueId;
  authStore.setNamespaceId(uniqueId);
};

async function handleCopy(source: string) {
  if (!isSupported) {
    window.$message?.error('您的浏览器不支持 Clipboard API');
    return;
  }

  if (!source) {
    return;
  }

  if (navigator.clipboard && window.isSecureContext) {
    await copy(source);
  } else {
    const input = document.createElement('input');
    input.value = source;
    document.body.appendChild(input);
    input.select();
    document.execCommand('copy');
    document.body.removeChild(input);
  }
  window.$message?.success('复制成功');
}

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetNamespaceList,
  apiParams: {
    page: 1,
    size: 10,
    keyword: null
  },
  columns: () => [
    {
      key: 'id',
      title: $t('common.index'),
      align: 'center',
      width: 64
    },
    {
      key: 'name',
      title: $t('page.namespace.name'),
      align: 'left',
      width: 120
    },
    {
      key: 'status',
      title: $t('common.active'),
      align: 'center',
      width: 40,
      render: row => (
        <div class="flex justify-center">
          {namespaceId.value === row.uniqueId! ? (
            <SvgIcon icon="material-symbols:check-circle" class="text-20px color-success" />
          ) : (
            <SvgIcon icon="material-symbols:cancel" class="text-20px color-gray400" />
          )}
        </div>
      )
    },
    {
      key: 'uniqueId',
      title: $t('page.namespace.uniqueId'),
      align: 'left',
      width: 180,
      render: row => (
        <NPopover>
          {{
            trigger: () => (
              <NButton text type="primary" onClick={() => handleCopy(row.uniqueId)}>
                {row.uniqueId}
              </NButton>
            ),
            default: () => <span>点击复制</span>
          }}
        </NPopover>
      )
    },
    {
      key: 'createDt',
      title: $t('page.common.createTime'),
      align: 'left',
      width: 130
    },
    {
      key: 'updateDt',
      title: $t('page.common.upadteTime'),
      align: 'left',
      width: 130
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      width: 80,
      render: row => (
        <div class="flex-center gap-8px">
          <NButton type="primary" text ghost size="small" onClick={() => edit(row.id!)}>
            {$t('common.edit')}
          </NButton>
          {namespaceId.value !== row.uniqueId! ? (
            <>
              <n-divider vertical />
              <NButton type="warning" text ghost size="small" onClick={() => handleChange(row.uniqueId!)}>
                {$t('common.switch')}
              </NButton>
              <n-divider vertical />
              <NPopconfirm onPositiveClick={() => handleDelete(row.uniqueId!)}>
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
  onDeleted
  // closeDrawer
} = useTableOperate(data, getData);

function edit(id: string) {
  handleEdit(id);
}

async function handleDelete(uniqueId: string) {
  const { error } = await fetchDeleteNamespace(uniqueId);
  if (error) return;
  onDeleted();
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <NamespaceSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <DeleteAlert />
    <NCard
      :title="$t('page.namespace.title')"
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
          :show-delete="false"
          @add="handleAdd"
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
      <NamespaceOperateDrawer
        v-model:visible="drawerVisible"
        :operate-type="operateType"
        :row-data="editingData"
        @submitted="getData"
      />
    </NCard>
  </div>
</template>

<style scoped></style>
