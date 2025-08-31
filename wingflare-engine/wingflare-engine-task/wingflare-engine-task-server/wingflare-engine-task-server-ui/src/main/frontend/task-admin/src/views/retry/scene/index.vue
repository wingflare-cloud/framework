<script setup lang="tsx">
import { NButton, NPopconfirm, NTag } from 'naive-ui';
import { ref } from 'vue';
import { useBoolean } from '@sa/hooks';
import {
  fetchBatchDeleteRetryScene,
  fetchDeleteRetryScene,
  fetchGetRetryScenePageList,
  fetchUpdateSceneStatus
} from '@/service/api';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useTable, useTableOperate } from '@/hooks/common/table';
import {
  DelayLevel,
  backOffRecord,
  blockStrategyRecord,
  groupConfigStatusRecord,
  routeKeyRecord
} from '@/constants/business';
import StatusSwitch from '@/components/common/status-switch.vue';
import { downloadFetch } from '@/utils/download';
import { useAuth } from '@/hooks/business/auth';
import LabelList from '@/components/common/label-list.vue';
import SceneOperateDrawer from './modules/scene-operate-drawer.vue';
import SceneSearch from './modules/scene-search.vue';
import SceneDetailDrawer from './modules/scene-detail-drawer.vue';

const { hasAuth } = useAuth();

const appStore = useAppStore();

/** 详情页属性数据 */
const detailData = ref<Api.RetryScene.Scene | null>();
/** 详情页可见状态 */
const { bool: detailVisible, setTrue: openDetail } = useBoolean(false);

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchGetRetryScenePageList,
  apiParams: {
    page: 1,
    size: 10,
    // if you want to use the searchParams in Form, you need to define the following properties, and the value is null
    // the value can not be undefined, otherwise the property in Form will not be reactive
    groupName: null,
    sceneName: null,
    sceneStatus: null,
    ownerId: null
  },
  columns: () => [
    {
      type: 'selection',
      align: 'center',
      width: 48
    },
    {
      key: 'sceneName',
      align: 'center',
      title: $t('page.retryScene.sceneName'),
      fixed: 'left',
      width: 120,
      render: row => {
        function showDetailDrawer() {
          detailData.value = row || null;
          openDetail();
        }

        return (
          <n-button text tag="a" type="primary" onClick={showDetailDrawer} class="ws-normal">
            {row.sceneName}
          </n-button>
        );
      }
    },
    {
      key: 'groupName',
      title: $t('page.retryScene.groupName'),
      align: 'center',
      width: 150
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
      key: 'ownerName',
      title: $t('page.retryScene.ownerName'),
      align: 'center',
      width: 60,
      render: row => {
        return row.ownerName ? row.ownerName : $t('common.none');
      }
    },
    {
      key: 'sceneStatus',
      title: $t('page.retryScene.sceneStatus'),
      align: 'center',
      width: 50,
      render: row => {
        const fetchFn = async (sceneStatus: Api.Common.EnableStatusNumber, callback: (flag: boolean) => void) => {
          const { error } = await fetchUpdateSceneStatus(row.id!, sceneStatus);
          if (!error) {
            row.sceneStatus = sceneStatus;
            window.$message?.success($t('common.updateSuccess'));
          }
          callback(!error);
        };

        return <StatusSwitch v-model:value={row.sceneStatus} onSubmitted={fetchFn} />;
      }
    },
    {
      key: 'backOff',
      title: $t('page.retryScene.backOff'),
      align: 'center',
      width: 80,
      render: row => {
        const label = $t(backOffRecord[row.backOff!]);
        return <NTag type="primary">{label}</NTag>;
      }
    },
    {
      key: 'routeKey',
      title: $t('page.retryScene.routeKey'),
      align: 'center',
      width: 80,
      render: row => {
        const label = $t(routeKeyRecord[row.routeKey!]);
        return <NTag type="primary">{label}</NTag>;
      }
    },
    {
      key: 'cbStatus',
      title: $t('page.retryScene.cbStatus'),
      align: 'center',
      width: 80,
      render: row => {
        const tagMap: Record<Api.Common.EnableStatusNumber, NaiveUI.ThemeColor> = {
          0: 'error',
          1: 'primary'
        };
        const label = $t(groupConfigStatusRecord[row.cbStatus!]);
        return <NTag type={tagMap[row.cbStatus!]}>{label}</NTag>;
      }
    },
    {
      key: 'maxRetryCount',
      title: $t('page.retryScene.maxRetryCount'),
      align: 'center',
      width: 80
    },
    {
      key: 'triggerInterval',
      title: $t('page.retryScene.triggerInterval'),
      align: 'center',
      width: 130,
      render: row => {
        if (row.backOff === 1) {
          return triggerInterval(row.backOff!, row.maxRetryCount!);
        }

        return row.triggerInterval;
      }
    },
    {
      key: 'blockStrategy',
      title: $t('page.retryScene.blockStrategy'),
      align: 'center',
      width: 80,
      render: row => {
        const label = $t(blockStrategyRecord[row.blockStrategy!]);
        return <NTag type="primary">{label}</NTag>;
      }
    },
    {
      key: 'executorTimeout',
      title: $t('page.retryScene.executorTimeout'),
      align: 'center',
      width: 80
    },
    {
      key: 'updateDt',
      title: $t('page.retryScene.updateDt'),
      align: 'center',
      width: 120
    },
    {
      key: 'description',
      title: $t('page.retryScene.description'),
      align: 'center',
      width: 120
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      fixed: 'right',
      width: 80,
      render: row => (
        <div class="flex-center gap-8px">
          <NButton type="primary" text ghost size="small" onClick={() => edit(row.id!)}>
            {$t('common.edit')}
          </NButton>
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
      )
    }
  ]
});

const { drawerVisible, operateType, editingData, handleAdd, handleEdit, checkedRowKeys, onDeleted, onBatchDeleted } =
  useTableOperate(data, getData);

function edit(id: string) {
  handleEdit(id);
}

async function handleDelete(id: string) {
  const { error } = await fetchDeleteRetryScene(id);
  if (error) return;
  onDeleted();
}

async function handleBatchDelete() {
  const { error } = await fetchBatchDeleteRetryScene(checkedRowKeys.value);
  if (error) return;
  onBatchDeleted();
}

function triggerInterval(backOff: number, maxRetryCount: number) {
  if (backOff !== 1) {
    return '';
  }

  let desc = '';
  for (let i = 1; i <= maxRetryCount; i += 1) {
    desc += `,${DelayLevel[i as keyof typeof DelayLevel]}`;
  }
  return desc.substring(1, desc.length);
}

function body(): Api.RetryScene.ExportScene {
  return {
    sceneIds: checkedRowKeys.value,
    groupName: searchParams.groupName,
    sceneName: searchParams.sceneName,
    sceneStatus: searchParams.sceneStatus,
    ownerId: searchParams.ownerId
  };
}

function handleExport() {
  downloadFetch('/scene-config/export', body(), $t('page.retryScene.title'));
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <SceneSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <DeleteAlert />
    <NCard
      :title="$t('page.retryScene.title')"
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
            <FileUpload action="/scene-config/import" accept="application/json" @refresh="getData" />
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
      <SceneOperateDrawer
        v-model:visible="drawerVisible"
        :operate-type="operateType"
        :row-data="editingData"
        @submitted="getData"
      />
      <SceneDetailDrawer v-model:visible="detailVisible" :row-data="detailData" />
    </NCard>
  </div>
</template>

<style scoped></style>
