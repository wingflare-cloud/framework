<script setup lang="ts">
import { watch } from 'vue';
import { getPaletteColorByNumber } from '@sa/color';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useEcharts } from '@/hooks/common/echarts';
import { useThemeStore } from '@/store/modules/theme';

defineOptions({
  name: 'TaskPieChart'
});

interface Props {
  type?: Api.Dashboard.TaskType;
  modelValue: Api.Dashboard.CardCount;
}

const props = withDefaults(defineProps<Props>(), {
  type: 'JOB'
});

const appStore = useAppStore();
const themeStore = useThemeStore();

const { domRef, updateOptions } = useEcharts(() => ({
  tooltip: {
    trigger: 'item',
    textStyle: {
      color: themeStore.darkMode ? '#dededf' : '#333639'
    },
    backgroundColor: themeStore.darkMode ? '#48484e' : '#fff',
    formatter: '{a} <br/>{b}: {d}%'
  },
  legend: {
    bottom: '1%',
    left: 'center',
    itemStyle: {
      borderWidth: 0
    }
  },
  series: [
    {
      color: [getColor('#5da8ff'), getColor('#8e9dff'), getColor('#fedc69'), getColor('#26deca')],
      name: $t('page.home.retryTab.pie.title'),
      type: 'pie',
      radius: ['45%', '75%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 10,
        borderColor: themeStore.darkMode ? '#18181c' : '#fff',
        borderWidth: 1
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '12'
        }
      },
      labelLine: {
        show: false
      },
      data: [] as { name: string; value: number }[]
    }
  ]
}));

function getColor(color: string) {
  return themeStore.darkMode ? getPaletteColorByNumber(color, 700) : color;
}

const getData = async () => {
  await new Promise(resolve => {
    setTimeout(resolve, 1);
  });

  if (!props.modelValue) {
    await getData();
    return;
  }

  updateLocale();
};

function updateLocale() {
  updateOptions((opts, factory) => {
    const originOpts = factory();
    opts.series[0].name = originOpts.series[0].name;
    opts.series[0].color = originOpts.series[0].color;
    opts.series[0].itemStyle.borderColor = originOpts.series[0].itemStyle.borderColor;
    opts.tooltip.textStyle.color = originOpts.tooltip.textStyle.color;
    opts.tooltip.backgroundColor = originOpts.tooltip.backgroundColor;

    if (props.type === 'JOB') {
      const jobTask = props.modelValue.jobTask;
      opts.series[0].data = [
        { name: $t('common.success'), value: jobTask.successNum / jobTask.totalNum },
        { name: $t('common.fail'), value: jobTask.failNum / jobTask.totalNum },
        { name: $t('common.stop'), value: jobTask.stopNum / jobTask.totalNum },
        { name: $t('common.cancel'), value: jobTask.cancelNum / jobTask.totalNum }
      ];
    }

    if (props.type === 'RETRY') {
      const retryTask = props.modelValue.retryTask;
      opts.series[0].data = [
        { name: $t('common.success'), value: retryTask.finishNum / retryTask.totalNum },
        { name: $t('common.running'), value: retryTask.runningNum / retryTask.totalNum },
        { name: $t('page.home.retryTask.status.maxRetryTimes'), value: retryTask.maxCountNum / retryTask.totalNum },
        { name: $t('page.home.retryTask.status.pauseRetry'), value: retryTask.suspendNum / retryTask.totalNum }
      ];
    }

    if (props.type === 'WORKFLOW') {
      const workFlowTask = props.modelValue.workFlowTask;
      opts.series[0].data = [
        { name: $t('common.success'), value: workFlowTask.successNum / workFlowTask.totalNum },
        { name: $t('common.fail'), value: workFlowTask.failNum / workFlowTask.totalNum },
        { name: $t('common.stop'), value: workFlowTask.stopNum / workFlowTask.totalNum },
        { name: $t('common.cancel'), value: workFlowTask.cancelNum / workFlowTask.totalNum }
      ];
    }
    return opts;
  });
}

watch(
  () => appStore.locale,
  () => {
    updateLocale();
  }
);

watch(
  () => themeStore.darkMode,
  () => {
    updateLocale();
  }
);

watch(
  () => props.type,
  () => {
    getData();
  },
  { immediate: true }
);

watch(
  () => props.modelValue,
  () => {
    getData();
  }
);
</script>

<template>
  <NCard :bordered="false" class="card-wrapper">
    <div ref="domRef" class="h-360px overflow-hidden"></div>
  </NCard>
</template>

<style scoped></style>
