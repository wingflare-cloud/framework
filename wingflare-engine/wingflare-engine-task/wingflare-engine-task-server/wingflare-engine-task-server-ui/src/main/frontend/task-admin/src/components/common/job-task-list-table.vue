<script setup lang="tsx">
import type { DataTableRowKey } from 'naive-ui';
import { NButton, NCode, NTag } from 'naive-ui';
import hljs from 'highlight.js/lib/core';
import json from 'highlight.js/lib/languages/json';
import { ref, render } from 'vue';
import dayjs from 'dayjs';
import { taskStatusRecord, taskStatusRecordOptions } from '@/constants/business';
import { $t } from '@/locales';
import { isNotNull, parseArgsJson, translateOptions } from '@/utils/common';
import { useTable } from '@/hooks/common/table';
import { fetchGetJobTaskList, fetchGetJobTaskTree } from '@/service/api';

defineOptions({
  name: 'JobTaskListTable'
});

hljs.registerLanguage('json', json);

interface Props {
  /** row data */
  rowData?: Api.JobBatch.JobBatch | null;
  isRetry?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  rowData: null
});

interface Emits {
  (e: 'retry'): void;
  (e: 'showLog', rowData: Api.Job.JobTask): void;
}

const emit = defineEmits<Emits>();

const expandedRowKeys = ref<DataTableRowKey[]>([]);

const argsDomMap = ref<Map<string, boolean>>(new Map());
const resultDomMap = ref<Map<string, boolean>>(new Map());

const { columns, searchParams, columnChecks, data, getData, loading, mobilePagination } = useTable({
  apiFn: fetchGetJobTaskList,
  apiParams: {
    page: 1,
    size: 10,
    groupName: props.rowData?.groupName,
    taskBatchId: props.rowData?.id,
    startId: 0,
    fromIndex: 0,
    taskStatus: undefined
    // if you want to use the searchParams in Form, you need to define the following properties, and the value is null
    // the value can not be undefined, otherwise the property in Form will not be reactive
  },
  columns: () => [
    {
      key: 'id',
      title: $t('page.jobBatch.jobTask.id'),
      align: 'left',
      width: 100,
      ellipsis: {
        tooltip: true
      }
    },
    {
      key: 'index',
      title: $t('common.log'),
      align: 'center',
      width: 64,
      render: row => (
        <NButton type="info" text onClick={() => emit('showLog', row)}>
          <span class="w-28px ws-break-spaces">{$t('page.log.view')}</span>
        </NButton>
      )
    },
    {
      key: 'groupName',
      title: $t('page.jobBatch.jobTask.groupName'),
      align: 'left',
      minWidth: 180
    },
    {
      key: 'taskName',
      title: $t('page.jobBatch.jobName'),
      align: 'left',
      minWidth: 180
    },
    {
      key: 'taskStatus',
      title: $t('page.jobBatch.jobTask.taskStatus'),
      align: 'left',
      minWidth: 80,
      render: row => {
        if (row.taskStatus === null) {
          return null;
        }
        const label = $t(taskStatusRecord[row.taskStatus!]);
        const tagMap: Record<number, NaiveUI.ThemeColor> = {
          1: 'info',
          2: 'info',
          3: 'info',
          4: 'error',
          5: 'error',
          6: 'error'
        };
        return <NTag type={tagMap[row.taskStatus!]}>{label}</NTag>;
      }
    },
    {
      key: 'clientInfo',
      title: $t('page.jobBatch.jobTask.clientInfo'),
      align: 'left',
      minWidth: 150,
      render: row => {
        if (row.clientInfo) {
          const parts = row.clientInfo?.split('@');
          const result = parts.length > 1 ? parts[1] : '';
          return <div>{result}</div>;
        }
        return <div>{row.clientInfo}</div>;
      }
    },
    {
      key: 'argsStr',
      title: $t('page.jobBatch.jobTask.argsStr'),
      align: 'center',
      titleAlign: 'center',
      minWidth: 120,
      render: row => {
        const argsDom = () => (
          <td class="n-data-table-td n-data-table-td--last-col" colspan={columns.value.length || 9}>
            <NCode
              class={`max-h-300px overflow-auto ${String(row.parentId) !== '0' ? 'pl-36px' : ''}`}
              hljs={hljs}
              code={parseArgsJson(row.argsStr)}
              language="json"
              show-line-numbers
            />
          </td>
        );

        const handleView = () => {
          if (resultDomMap.value.get(row.id)) {
            const tr = document.querySelector(`#job-task-result-${row.id}`);
            tr?.remove();
            resultDomMap.value.set(row.id, false);
          }
          if (argsDomMap.value.get(row.id)) {
            return;
          }
          const tr = document.querySelector(`#job-task-${row.id}`);
          const argsTr = document.createElement('tr');
          argsTr.setAttribute('id', `job-task-args-${row.id}`);
          argsTr.setAttribute('class', 'n-data-table-tr n-data-table-tr--expanded');
          tr?.after(argsTr);
          render(argsDom(), argsTr!);
          argsDomMap.value.set(row.id, true);
        };

        const handleClose = () => {
          if (!argsDomMap.value.get(row.id)) {
            return;
          }
          const tr = document.querySelector(`#job-task-args-${row.id}`);
          tr?.remove();
          argsDomMap.value.set(row.id, false);
        };

        return (
          <>
            {argsDomMap.value.get(row.id) ? (
              <NButton type="primary" text onClick={() => handleClose()}>
                <span class="w-28px ws-break-spaces">{`收起`}</span>
              </NButton>
            ) : (
              <NButton type="primary" text disabled={!isNotNull(row.argsStr)} onClick={() => handleView()}>
                <span class="w-28px ws-break-spaces">{`查看\n参数`}</span>
              </NButton>
            )}
          </>
        );
      }
    },
    {
      key: 'resultMessage',
      title: $t('page.jobBatch.jobTask.resultMessage'),
      align: 'left',
      minWidth: 120,
      render: row => {
        const argsDom = () => (
          <td class="n-data-table-td n-data-table-td--last-col" colspan={columns.value.length || 9}>
            <NCode
              class={`max-h-300px overflow-auto ${String(row.parentId) !== '0' ? 'pl-36px' : ''}`}
              hljs={hljs}
              code={parseArgsJson(row.resultMessage)}
              language="json"
              show-line-numbers
            />
          </td>
        );

        const handleView = () => {
          if (argsDomMap.value.get(row.id)) {
            const tr = document.querySelector(`#job-task-args-${row.id}`);
            tr?.remove();
            argsDomMap.value.set(row.id, false);
          }
          if (resultDomMap.value.get(row.id)) {
            return;
          }
          const tr = document.querySelector(`#job-task-${row.id}`);
          const argsTr = document.createElement('tr');
          argsTr.setAttribute('id', `job-task-result-${row.id}`);
          argsTr.setAttribute('class', 'n-data-table-tr n-data-table-tr--expanded');
          tr?.after(argsTr);
          render(argsDom(), argsTr!);
          resultDomMap.value.set(row.id, true);
        };

        const handleClose = () => {
          if (!resultDomMap.value.get(row.id)) {
            return;
          }
          const tr = document.querySelector(`#job-task-result-${row.id}`);
          tr?.remove();
          resultDomMap.value.set(row.id, false);
        };

        return (
          <>
            {resultDomMap.value.get(row.id) ? (
              <NButton type="primary" text onClick={() => handleClose()}>
                <span class="w-28px ws-break-spaces">{`收起`}</span>
              </NButton>
            ) : (
              <NButton type="primary" text disabled={!isNotNull(row.resultMessage)} onClick={() => handleView()}>
                <span class="w-28px ws-break-spaces">{`查看\n结果`}</span>
              </NButton>
            )}
          </>
        );
      }
    },
    {
      key: 'retryCount',
      title: $t('page.jobBatch.jobTask.retryCount'),
      align: 'left',
      minWidth: 64
    },
    {
      key: 'createDt',
      title: $t('page.jobBatch.jobTask.createDt'),
      align: 'left',
      minWidth: 130
    },
    {
      key: 'duration',
      title: $t('page.jobBatch.duration'),
      align: 'center',
      width: 120,
      render: row => {
        if (row.taskStatus === 3) {
          return Math.round(dayjs(row.updateDt).diff(dayjs(row.createDt)) / 1000);
        }
        return null;
      }
    }
  ]
});

const clearDoms = () => {
  const resultEntries = resultDomMap.value.entries();
  for (const [id, _] of resultEntries) {
    const tr = document.querySelector(`#job-task-result-${id}`);
    tr?.remove();
    resultDomMap.value.set(id, false);
  }

  const argsEntries = argsDomMap.value.entries();
  for (const [id, _] of argsEntries) {
    const tr = document.querySelector(`#job-task-args-${id}`);
    tr?.remove();
    argsDomMap.value.set(id, false);
  }
};

const onLoad = (row: Record<string, any>) => {
  return new Promise<void>((resolve, reject) => {
    fetchGetJobTaskTree({
      groupName: row.groupName,
      taskBatchId: row.taskBatchId,
      startId: 0,
      fromIndex: 0,
      parentId: row.id
    })
      .then(res => {
        row.children = res.data || [];
        resolve();
      })
      .catch(e => {
        reject(e);
      });
  });
};

const onExpandedRowKeys = (keys: DataTableRowKey[]) => {
  expandedRowKeys.value = keys;
};

const onUpdatePage = (_: number) => {
  expandedRowKeys.value = [];
  clearDoms();
};

async function flushed() {
  searchParams.taskStatus = undefined;
  expandedRowKeys.value = [];
  clearDoms();
  await getData();
}

const retry = async () => {
  emit('retry');
};

const isRetry = () => {
  return (
    props.rowData?.taskBatchStatus === 4 || props.rowData?.taskBatchStatus === 5 || props.rowData?.taskBatchStatus === 6
  );
};

const init = () => {
  columnChecks.value = columnChecks.value.filter(column => {
    if (!['4', '5'].includes(String(props.rowData?.taskType) || '-1')) {
      return column.key !== 'taskName';
    }

    return true;
  });
};

init();
</script>

<template>
  <NCard
    :bordered="false"
    size="small"
    class="sm:flex-1-hidden card-wrapper pt-16px"
    :content-style="{ padding: 0 }"
    :header-style="{ padding: 0 }"
  >
    <template #header>
      <NSelect
        v-model:value="searchParams.taskStatus"
        clearable
        class="max-w-180px"
        :options="translateOptions(taskStatusRecordOptions)"
        placeholder="请选择状态"
        @update:value="getData"
      />
    </template>
    <template #header-extra>
      <NButton class="mr-16px" @click="flushed">
        <template #icon>
          <icon-ant-design:sync-outlined class="text-icon" />
        </template>
        刷新
      </NButton>
      <NButton v-if="isRetry()" @click="retry">
        <template #icon>
          <icon-ant-design:redo-outlined class="text-icon" />
        </template>
        重试
      </NButton>
    </template>
    <NDataTable
      :columns="columns"
      :data="data"
      :loading="loading"
      remote
      :scroll-x="1000"
      :row-key="row => row.id"
      :pagination="mobilePagination"
      :indent="16"
      :cascade="false"
      allow-checking-not-loaded
      :expanded-row-keys="expandedRowKeys"
      class="mt-16px sm:h-full"
      :row-props="row => ({ id: `job-task-${row.id}` })"
      @update:expanded-row-keys="onExpandedRowKeys"
      @update:page="onUpdatePage"
      @load="onLoad"
    />
  </NCard>
</template>

<style scoped></style>
