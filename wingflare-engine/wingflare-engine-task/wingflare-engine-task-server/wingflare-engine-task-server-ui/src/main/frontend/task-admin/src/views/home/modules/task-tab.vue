<script setup lang="tsx">
import { computed, ref, watch } from 'vue';
import type { DataTableColumns } from 'naive-ui';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { fetchAllGroupName, fetchJobLine, fetchRetryLine } from '@/service/api';
import DatetimeRange from '@/components/common/datetime-range.vue';
import TaskLineChart from './task-line-chart.vue';
import TaskPieChart from './task-pie-chart.vue';

defineOptions({
  name: 'TaskTab'
});

interface Props {
  modelValue: Api.Dashboard.CardCount;
}

defineProps<Props>();

const taskType = ref<Api.Dashboard.TaskType>('JOB');
const appStore = useAppStore();
const gap = computed(() => (appStore.isMobile ? 0 : 16));
const data = ref<Api.Dashboard.DashboardLine>();
const groupOptions = ref();
const tabParams = ref<Api.Dashboard.DashboardLineParams>({
  type: 'WEEK',
  page: 1,
  size: 6,
  mode: 'JOB',
  datetimeRange: null
});

const getData = async () => {
  const { data: lineData, error } =
    taskType.value === 'RETRY' ? await fetchRetryLine(tabParams.value) : await fetchJobLine(tabParams.value);

  if (!error) {
    data.value = lineData;
  }
};

const getGroupNames = async () => {
  const { data: groupNames, error } = await fetchAllGroupName();

  if (!error) {
    groupOptions.value = groupNames.map(groupName => {
      return { label: groupName, value: groupName };
    });
  }
};

const onUpdateTab = (value: string) => {
  if (value === 'jobTask') {
    taskType.value = 'JOB';
    tabParams.value.mode = 'JOB';
  }
  if (value === 'retryTask') {
    taskType.value = 'RETRY';
    tabParams.value.mode = undefined;
  }
  if (value === 'workflow') {
    taskType.value = 'WORKFLOW';
    tabParams.value.mode = 'WORKFLOW';
  }
};

const onUpdateDate = (value?: [string, string] | null) => {
  if (value) {
    tabParams.value.type = 'OTHERS';
  }
};

const onClearDate = () => {
  tabParams.value.type = 'WEEK';
};

const onUpdateType = (value: string) => {
  if (value !== 'OTHERS') {
    tabParams.value.datetimeRange = null;
  }
};

const pagination = ref({
  page: tabParams.value.page,
  pageSize: tabParams.value.size,
  itemCount: data.value?.taskList.total
});

const createPanels = () => [
  {
    name: 'jobTask',
    tab: $t('page.home.jobTask')
  },
  {
    name: 'retryTask',
    tab: $t('page.home.retryTask.title')
  },
  {
    name: 'workflow',
    tab: $t('page.home.workflow')
  }
];

const panels = ref(createPanels());

const createColumns = (): DataTableColumns<Api.Dashboard.Task> => [
  {
    title: $t('page.home.retryTab.task.groupName'),
    key: 'groupName'
  },
  {
    title: $t('page.home.retryTab.task.run'),
    key: 'run',
    align: 'center',
    render: row => <span class="task-table-number">{row.run}</span>
  },
  {
    title: $t('page.home.retryTab.task.total'),
    key: 'total',
    align: 'center',
    render: row => <span class="task-table-number">{row.total}</span>
  }
];

const columns = ref(createColumns());

watch(
  () => appStore.locale,
  () => {
    panels.value = createPanels();
    columns.value = createColumns();
  }
);

watch(
  () => tabParams.value,
  () => {
    getData();
  },
  { deep: true }
);

getData();
getGroupNames();
</script>

<template>
  <div class="relative">
    <NTabs type="line" animated @update:value="onUpdateTab">
      <NTabPane v-for="panel in panels" :key="panel.name" :tab="panel.tab" :name="panel.name">
        <NGrid :x-gap="gap" :y-gap="16" responsive="screen" item-responsive>
          <NGi span="24 s:24 m:16">
            <TaskLineChart v-model="data!" :type="taskType" />
          </NGi>
          <NGi span="24 s:24 m:8">
            <div class="task-tab-rank">
              <h4 class="task-tab-title">
                {{
                  taskType === 'RETRY' ? $t('page.home.retryTab.rank.titleRetry') : $t('page.home.retryTab.rank.title')
                }}
              </h4>
              <ul class="task-tab-rank__list">
                <li v-for="(item, index) in data?.rankList" :key="index" class="task-tab-rank__list--item">
                  <span>
                    <span class="task-tab-rank__list--index">
                      {{ index + 1 }}
                    </span>
                    <span>{{ item.name }}</span>
                  </span>
                  <span class="task-tab-badge">{{ item.total }}</span>
                </li>
              </ul>
            </div>
          </NGi>
        </NGrid>
        <NGrid :x-gap="gap" :y-gap="16" responsive="screen" item-responsive class="p-t-16px">
          <NGi span="24 s:24 m:16">
            <h4 class="task-tab-title">{{ $t('page.home.retryTab.task.title') }}</h4>
            <NDivider />
            <NDataTable
              min-height="300px"
              max-height="300px"
              :columns="columns"
              :data="data?.taskList.data"
              :bordered="false"
              :pagination="pagination"
            />
          </NGi>
          <NGi span="24 s:24 m:8">
            <h4 class="task-tab-title">{{ $t('page.home.retryTab.pie.title') }}</h4>
            <NDivider />
            <TaskPieChart v-model="modelValue!" :type="taskType" />
          </NGi>
        </NGrid>
      </NTabPane>
    </NTabs>
    <div
      class="absolute top--136px flex flex-col flex-wrap gap-16px 2xl:right-40px 2xl:top-0 lg:top--36px md:top--90px md:flex-row 2xl:flex-nowrap"
    >
      <NRadioGroup v-model:value="tabParams.type" @update:value="onUpdateType">
        <NRadioButton value="DAY" :label="$t('page.home.retryTab.params.day')" />
        <NRadioButton value="WEEK" :label="$t('page.home.retryTab.params.week')" />
        <NRadioButton value="MONTH" :label="$t('page.home.retryTab.params.month')" />
        <NRadioButton value="YEAR" :label="$t('page.home.retryTab.params.year')" />
      </NRadioGroup>
      <DatetimeRange
        v-model:value="tabParams.datetimeRange"
        class="w-300px lg:w-250px md:w-275px sm:w-300px xl:w-400px"
        @update:value="onUpdateDate"
        @clear="onClearDate"
      />
      <NSelect v-model:value="tabParams.groupName" :options="groupOptions" class="w-200px lg:w-150px md:w-170px" />
    </div>
  </div>
</template>

<style>
.task-table-number {
  padding: 3px 7px;
  background-color: #f4f4f4;
  color: #555;
  text-shadow: none !important;
  font-weight: 400;
  border-radius: 4px;
}

.dark .task-table-number {
  background: #2c2c2c;
  color: #d6d6d6;
}
</style>

<style scoped lang="scss">
.task-tab-title {
  font-size: 16px;
  font-weight: 600;
}

.task-tab-badge {
  float: right;
  padding: 3px 7px;
  background-color: #f4f4f4;
  color: #555;
  text-shadow: none !important;
  font-weight: 400;
  border-radius: 4px;
}

.task-tab-rank {
  height: 360px;
  overflow: hidden;

  &__list {
    padding: 0;
    height: 332px;
    overflow: auto;
    scrollbar-width: thin;
    scrollbar-color: rgba(0, 0, 0, 0.5) transparent;

    &--index {
      display: inline-block;
      width: 20px;
      height: 20px;
      line-height: 20px;
      text-align: center;
      border-radius: 50%;
      background-color: #314659;
      color: #fff;
      margin-right: 10px;
      font-size: 13px;
    }

    &--item {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 10px 15px;
      margin: 3px 0;
      border: 1px solid #efefefd2;
      border-radius: 4px;
      box-shadow: none;
      transition: all 0.5s;
    }

    &--item:hover {
      box-shadow: 1px 1px 8px rgba(0, 0, 0, 0.1);
    }
  }

  &__list {
    @include scrollbar();
  }
}

.dark {
  .task-tab-badge {
    background: #2c2c2c;
    color: #d6d6d6;
  }

  .task-tab-rank {
    &__list {
      &--index {
        color: #d6d6d6;
      }

      &--item {
        border: 1px solid #646464;
      }

      &--item:hover {
        box-shadow: 1px 1px 8px transparent;
      }
    }

    &__list {
      @include scrollbar();
    }
  }
}

.n-divider:not(.n-divider--vertical) {
  margin-top: 16px;
  margin-bottom: 12px;
}
</style>
