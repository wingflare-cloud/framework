<script setup lang="tsx">
import { NButton, NDivider, NPopconfirm, NTag, NTooltip } from 'naive-ui';
import { useAppStore } from '@/store/modules/app';
import { fetchPods, fetchUpdatePodsStatus } from '@/service/api';
import { $t } from '@/locales';
import { executorTypeRecord, podsType } from '@/constants/business';
import { useTable, useTableOperate } from '@/hooks/common/table';
import LabelList from '@/components/common/label-list.vue';
import PodsSearch from './modules/pods-search.vue';
import PodsOperateDrawer from './modules/pods-operate-drawer.vue';

const appStore = useAppStore();

const { columns, columnChecks, data, getData, loading, mobilePagination, searchParams, resetSearchParams } = useTable({
  apiFn: fetchPods,
  apiParams: {
    page: 1,
    size: 10,
    groupName: null
  },
  columns: () => [
    {
      key: 'hostId',
      title: $t('page.pods.hostId'),
      align: 'center',
      width: 80,
      render: row => {
        function middleEllipsis(str: string, frontLen = 4, endLen = 4) {
          if (!str) return '';
          const maxLen = frontLen + endLen + 3;
          if (str.length <= maxLen) return str;
          return `${str.slice(0, frontLen)}...${str.slice(-endLen)}`;
        }
        const display = middleEllipsis(row.hostId, 4, 4);
        return (
          <NTooltip>
            {{
              trigger: () => (
                <span style="max-width: 120px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; display: inline-block; vertical-align: middle;">
                  {display}
                </span>
              ),
              default: () => row.hostId
            }}
          </NTooltip>
        );
      }
    },
    {
      key: 'nodeType',
      title: $t('page.pods.nodeType'),
      align: 'center',
      width: 80,
      render: row => {
        if (row.nodeType === null) {
          return null;
        }

        const tagMap: Record<Api.Dashboard.DashboardPodsType, NaiveUI.ThemeColor> = {
          1: 'info',
          2: 'primary'
        };

        const label = $t(podsType[row.nodeType]);

        return <NTag type={tagMap[row.nodeType]}>{label}</NTag>;
      }
    },
    {
      key: 'groupName',
      title: $t('page.pods.groupName'),
      align: 'center',
      width: 120,
      resizable: true,
      minWidth: 120,
      maxWidth: 200
    },
    {
      key: 'labels',
      title: $t('page.pods.labels'),
      align: 'center',
      width: 140,
      render: row => {
        return <LabelList id={row.id} labels={row.labels} />;
      }
    },
    {
      key: 'hostIp',
      title: $t('page.pods.hostIp'),
      align: 'center',
      width: 120,
      render: row => {
        return `${row.hostIp}:${row.hostPort}`;
      }
    },
    {
      key: 'nodeStatus',
      title: '状态',
      align: 'center',
      width: 60,
      render: row => {
        if (!row.labels || row.nodeType === 2) {
          return <NTag type={'success'}>up</NTag>;
        }

        // 解析 labels
        const labels = JSON.parse(row.labels || '{}');
        const state = labels.state || '';
        const colorMap: Record<string, 'error' | 'success'> = {
          up: 'success',
          down: 'error'
        };
        const tagType = colorMap[state] || 'default';
        return <NTag type={tagType}>{state}</NTag>;
      }
    },
    {
      key: 'consumerBuckets',
      title: $t('page.pods.consumerBuckets'),
      align: 'center',
      width: 100,
      render: row => {
        if (row.nodeType === null) return null;
        const buckets = row.consumerBuckets || [];
        if (row.nodeType === 1) {
          return (
            <>
              Path: <NTag type="info">{row.contextPath ?? '/'}</NTag>
            </>
          );
        }
        if (buckets.length === 0) return null;
        if (buckets.length === 1) {
          return (
            <>
              <span>Bucket: </span>
              <NTag type="info" class="m-1 justify-center">
                {buckets[0]}
              </NTag>
            </>
          );
        }
        if (buckets.length === 2) {
          return (
            <>
              <NTag type="info" class="m-1 justify-center">
                {buckets[0]}
              </NTag>
              <NTag type="info" class="m-1 justify-center">
                {buckets[1]}
              </NTag>
            </>
          );
        }
        // 超过2个
        return (
          <>
            <NTag type="info" class="m-1 justify-center">
              {buckets[0]}
            </NTag>
            <n-popover trigger="hover" placement="top">
              {{
                trigger: () => (
                  <NTag type="info" class="m-1 justify-center">
                    ...
                  </NTag>
                ),
                default: () => (
                  <div class="grid grid-cols-16">
                    {buckets.map(bucket => (
                      <NTag type="info" key={bucket} class="m-1 justify-center">
                        {bucket}
                      </NTag>
                    ))}
                  </div>
                )
              }}
            </n-popover>
            <NTag type="info" class="m-1 justify-center">
              {buckets[buckets.length - 1]}
            </NTag>
          </>
        );
      }
    },
    {
      key: 'executorType',
      title: $t('page.pods.executorType'),
      align: 'center',
      width: 80,
      render: row => {
        const extAttrsObj = JSON.parse(row.extAttrs || '{}');
        const isEmptyObject = (obj: object) => {
          return Object.keys(obj).length === 0 && obj.constructor === Object;
        };

        if (isEmptyObject(extAttrsObj) || extAttrsObj === null) {
          return null;
        }
        const executorTypeValue = extAttrsObj?.executorType as Api.Common.ExecutorType;
        const label = executorTypeValue ? $t(executorTypeRecord[executorTypeValue!]) : $t('common.none');
        return <NTag type={'info'}>{label}</NTag>;
      }
    },
    {
      key: 'systemVersion',
      title: $t('page.pods.systemVersion'),
      align: 'center',
      width: 80,
      render: row => {
        const extAttrsObj = JSON.parse(row.extAttrs || '{}');
        const isEmptyObject = (obj: object) => {
          return Object.keys(obj).length === 0 && obj.constructor === Object;
        };

        if (isEmptyObject(extAttrsObj) || extAttrsObj === null) {
          return null;
        }
        return <NTag type={'info'}>{extAttrsObj?.systemVersion || $t('common.none')}</NTag>;
      }
    },
    {
      key: 'updateDt',
      title: $t('page.pods.updateDt'),
      align: 'center',
      width: 100
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      width: 80,
      render: row => {
        const editBtn = () => {
          if (row.nodeType === 2) {
            return null;
          }
          return (
            <NButton text type="warning" ghost size="small" onClick={() => edit(row.id!)}>
              {$t('common.edit')}
            </NButton>
          );
        };

        const podStatusBtn = () => {
          if (!row.labels || row.nodeType === 2) {
            return null;
          }

          const labels = JSON.parse(row.labels || '{}');
          let serverNodeStatus;
          if (labels.state === 'up') {
            serverNodeStatus = 2;
          } else {
            serverNodeStatus = 1;
          }

          return (
            <NPopconfirm onPositiveClick={() => updatePodStatus(row.id! as any, serverNodeStatus)}>
              {{
                default: () => (serverNodeStatus === 1 ? $t('page.pods.online') : $t('page.pods.offline')),
                trigger: () => (
                  <NButton type="error" text ghost size="small">
                    {serverNodeStatus === 1 ? $t('page.pods.online') : $t('page.pods.offline')}
                  </NButton>
                )
              }}
            </NPopconfirm>
          );
        };

        const buttons = [editBtn(), podStatusBtn()];

        return (
          <div class="flex-center gap-8px">
            {buttons.filter(Boolean).map((btn, index) => (
              <>
                {index !== 0 && <NDivider vertical />}
                {btn}
              </>
            ))}
          </div>
        );
      }
    }
  ]
});

async function updatePodStatus(id: number, serverNodeStatus: number) {
  const { error } = await fetchUpdatePodsStatus({ id, serverNodeStatus });
  if (error) return;
  getData();
}

const { checkedRowKeys, handleEdit, drawerVisible, editingData } = useTableOperate(data, getData);

function edit(id: string) {
  handleEdit(id);
}
</script>

<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <PodsSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getData" />
    <NCard
      :title="$t('page.pods.title')"
      :bordered="false"
      size="small"
      header-class="view-card-header"
      class="sm:flex-1-hidden card-wrapper"
    >
      <template #header-extra>
        <TableHeaderOperation
          v-model:columns="columnChecks"
          :disabled-delete="checkedRowKeys.length === 0"
          :loading="loading"
          :show-add="false"
          :show-delete="false"
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
        :row-key="row => row.hostId"
        :pagination="mobilePagination"
        class="sm:h-full"
      />
    </NCard>
    <PodsOperateDrawer v-model:visible="drawerVisible" :row-data="editingData" @submitted="getData" />
  </div>
</template>

<style scoped></style>
