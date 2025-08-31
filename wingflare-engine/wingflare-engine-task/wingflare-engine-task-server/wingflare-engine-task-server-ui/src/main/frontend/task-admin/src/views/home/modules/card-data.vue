<script setup lang="ts">
import { computed, nextTick, onUnmounted, reactive } from 'vue';
import { createReusableTemplate } from '@vueuse/core';
import { getPaletteColorByNumber } from '@sa/color';
import { $t } from '@/locales';
import { useThemeStore } from '@/store/modules/theme';
import { useRouterPush } from '@/hooks/common/router';
import DardRetryChart from './card-retry-chart.vue';

const { routerPushByKey } = useRouterPush();

const themeStore = useThemeStore();

defineOptions({
  name: 'CardData'
});

interface Props {
  modelValue?: Api.Dashboard.CardCount;
}

const state = reactive({ width: 0 });
const gridCol = computed(() => {
  if (state.width >= 1600) {
    return 4;
  } else if (state.width >= 1024) {
    return 2;
  }
  return 1;
});

const getState = () => {
  state.width = document.documentElement.clientWidth;
};

nextTick(() => {
  getState();
  window.addEventListener('resize', getState);
});

onUnmounted(() => {
  // 移除监听事件
  window.removeEventListener('resize', getState);
});

const props = withDefaults(defineProps<Props>(), {
  modelValue: () => ({
    jobTask: {
      successNum: 0,
      failNum: 0,
      cancelNum: 0,
      stopNum: 0,
      totalNum: 0,
      successRate: 0
    },
    workFlowTask: {
      successNum: 0,
      failNum: 0,
      cancelNum: 0,
      stopNum: 0,
      totalNum: 0,
      successRate: 0
    },
    retryTask: {
      totalNum: 0,
      runningNum: 0,
      finishNum: 0,
      maxCountNum: 0,
      suspendNum: 0
    },
    retryTaskBarList: [],
    onLineService: {
      total: 0,
      clientTotal: 0,
      serverTotal: 0
    }
  })
});

interface CardData {
  key: string;
  title: string;
  tip: string;
  value: number;
  click?: () => void;
  color: {
    start: string;
    end: string;
  };
  icon: string;
  bottom: { label: string; value: number; click?: () => void }[];
}

// eslint-disable-next-line complexity
const cardData = computed<CardData[]>(() => [
  {
    key: 'job_task',
    title: $t('page.home.jobTask'),
    tip: $t('page.home.jobTaskTip'),
    value: props.modelValue?.jobTask.totalNum ?? 0,
    click: () => routerPushByKey('job_task'),
    color: {
      start: '#f5b386',
      end: '#FFD6BA'
    },
    icon: 'ant-design:profile-outlined',
    bottom: [
      {
        label: $t('common.success'),
        value: props.modelValue?.jobTask.successNum ?? 0,
        click: () => routerPushByKey('job_batch', { state: { taskBatchStatus: 3 } })
      },
      {
        label: $t('common.fail'),
        value: props.modelValue?.jobTask.failNum ?? 0,
        click: () => routerPushByKey('job_batch', { state: { taskBatchStatus: 4 } })
      },
      {
        label: $t('common.stop'),
        value: props.modelValue?.jobTask.stopNum ?? 0,
        click: () => routerPushByKey('job_batch', { state: { taskBatchStatus: 5 } })
      },
      {
        label: $t('common.cancel'),
        value: props.modelValue?.jobTask.cancelNum ?? 0,
        click: () => routerPushByKey('job_batch', { state: { taskBatchStatus: 6 } })
      }
    ]
  },
  {
    key: 'retry_task',
    title: $t('page.home.retryTask.title'),
    tip: $t('page.home.retryTaskTip'),
    value: props.modelValue?.retryTask.totalNum ?? 0,
    click: () => routerPushByKey('retry_task'),
    unit: '',
    color: {
      start: '#40e9c5',
      end: '#BEE3DB'
    },
    icon: 'ant-design:schedule-outlined',
    bottom: [
      {
        label: $t('common.success'),
        value: props.modelValue?.retryTask.finishNum ?? 0,
        click: () => routerPushByKey('retry_info', { state: { retryStatus: 1 } })
      },
      {
        label: $t('common.running'),
        value: props.modelValue?.retryTask.runningNum ?? 0,
        click: () => routerPushByKey('retry_info', { state: { retryStatus: 0 } })
      },
      {
        label: $t('page.home.retryTask.status.maxRetryTimes'),
        value: props.modelValue?.retryTask.maxCountNum ?? 0,
        click: () => routerPushByKey('retry_info', { state: { retryStatus: 2 } })
      },
      {
        label: $t('page.home.retryTask.status.pauseRetry'),
        value: props.modelValue?.retryTask.suspendNum ?? 0,
        click: () => routerPushByKey('retry_info', { state: { retryStatus: 3 } })
      }
    ]
  },
  {
    key: 'workflow_task',
    title: $t('page.home.workflow'),
    tip: $t('page.home.workflowTip'),
    value: props.modelValue?.workFlowTask.totalNum,
    click: () => routerPushByKey('workflow_task'),
    unit: '',
    color: {
      start: '#ec6f6f',
      end: '#f99797'
    },
    icon: 'typcn:flow-merge',
    bottom: [
      {
        label: $t('common.success'),
        value: props.modelValue?.workFlowTask.successNum ?? 0,
        click: () => routerPushByKey('workflow_batch', { state: { taskBatchStatus: 3 } })
      },
      {
        label: $t('common.fail'),
        value: props.modelValue?.workFlowTask.failNum ?? 0,
        click: () => routerPushByKey('workflow_batch', { state: { taskBatchStatus: 4 } })
      },
      {
        label: $t('common.stop'),
        value: props.modelValue?.workFlowTask.stopNum ?? 0,
        click: () => routerPushByKey('workflow_batch', { state: { taskBatchStatus: 5 } })
      },
      {
        label: $t('common.cancel'),
        value: props.modelValue?.workFlowTask.cancelNum ?? 0,
        click: () => routerPushByKey('workflow_batch', { state: { taskBatchStatus: 6 } })
      }
    ]
  },
  {
    key: 'pods',
    title: $t('page.home.onlineServiceCount'),
    tip: $t('page.home.onlineServiceTip'),
    value: props.modelValue?.onLineService.total ?? 0,
    click: () => routerPushByKey('pods'),
    unit: '',
    color: {
      start: '#b686d4',
      end: '#c5a5d8'
    },
    icon: 'ant-design:database-outlined',
    bottom: [
      {
        label: $t('page.home.machine.type.client'),
        value: props.modelValue?.onLineService.clientTotal ?? 0,
        click: () => routerPushByKey('pods')
      },
      {
        label: $t('page.home.machine.type.server'),
        value: props.modelValue?.onLineService.serverTotal ?? 0,
        click: () => routerPushByKey('pods')
      }
    ]
  }
]);

interface GradientBgProps {
  gradientColor: string;
}

const [DefineGradientBg, GradientBg] = createReusableTemplate<GradientBgProps>();

function getGradientColor(color: CardData['color']) {
  const start = themeStore.darkMode ? getPaletteColorByNumber(color.start, 700) : color.start;
  const end = themeStore.darkMode ? getPaletteColorByNumber(color.end, 700) : color.end;
  return `linear-gradient(to bottom right, ${start}, ${end})`;
}
</script>

<template>
  <NCard :bordered="false" size="small" class="card-wrapper">
    <!-- define component start: GradientBg -->
    <DefineGradientBg v-slot="{ $slots, gradientColor }">
      <div class="rd-8px px-16px pb-4px pt-8px text-white" :style="{ backgroundImage: gradientColor }">
        <component :is="$slots.default" />
      </div>
    </DefineGradientBg>
    <!-- define component end: GradientBg -->

    <NGrid :cols="gridCol" responsive="screen" :x-gap="16" :y-gap="16">
      <NGi v-for="item in cardData" :key="item.key" class="home-card">
        <NSpin :show="false">
          <GradientBg :gradient-color="getGradientColor(item.color)" class="h-165px flex-1">
            <div :class="item.click ? 'cursor-pointer' : null" @click="item.click">
              <div class="flex justify-between">
                <div class="align-center flex">
                  <SvgIcon :icon="item.icon" class="text-26px" />
                  <h3 class="ml-2 text-18px">{{ item.title }}</h3>
                </div>
                <NPopover trigger="hover">
                  <template #trigger>
                    <NButton text>
                      <SvgIcon icon="ant-design:info-circle-outlined" class="text-20px color-white" />
                    </NButton>
                  </template>
                  {{ item.tip }}
                </NPopover>
              </div>
              <div class="flex">
                <CountTo :start-value="0" :end-value="item.value" class="text-30px text-white" />
              </div>
            </div>
            <NProgress
              v-if="item.key === 'job_task'"
              class="mb-24px h-20px pt-18px"
              type="line"
              color="#728bf9"
              rail-color="#ebebeb"
              :percentage="props.modelValue?.jobTask.successRate ?? 0"
              indicator-text-color="#fff"
            />
            <NProgress
              v-else-if="item.key === 'workflow_task'"
              class="mb-24px h-20px pt-18px"
              type="line"
              color="#728bf9"
              rail-color="#ebebeb"
              :percentage="props.modelValue?.workFlowTask.successRate ?? 0"
              indicator-text-color="#fff"
            />
            <DardRetryChart v-else-if="item.key === 'retry_task'" :model-value="props.modelValue?.retryTaskBarList" />
            <div v-else class="mb-12px h-32px"></div>
            <NDivider />
            <template v-for="(bottomItem, bottomIndex) in item.bottom" :key="bottomIndex">
              <NDivider v-if="bottomIndex !== 0" vertical />
              <span :class="bottomItem.click ? 'cursor-pointer home-card-footer' : null" @click="bottomItem.click">
                {{ bottomItem.label }}
                <CountTo :start-value="0" :end-value="bottomItem.value" />
              </span>
            </template>
          </GradientBg>
        </NSpin>
      </NGi>
    </NGrid>
  </NCard>
</template>

<style scoped>
.n-divider {
  margin: 0px 0 6px;
}

.n-divider--vertical {
  margin: 0 5px 0 5px;
}

:deep(.n-progress-icon--as-text) {
  width: 60px !important;
}

.home-card {
  transition: all 0.25s ease-in;
}

.home-card:hover {
  transform: translateY(-8px);
  box-shadow: var(--n-box-shadow);
  border-radius: 8px;
}

.home-card-footer:hover {
  color: #fff;
  font-size: 14px;
  transition: all 0.25s ease-in;
}

.home-card-footer:hover {
  color: #1366ff !important;
  font-size: 15px;
}
</style>
