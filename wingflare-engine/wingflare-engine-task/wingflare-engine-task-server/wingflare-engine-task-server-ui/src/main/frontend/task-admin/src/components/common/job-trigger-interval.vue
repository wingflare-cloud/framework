<script setup lang="ts">
import { nextTick, ref, watch } from 'vue';
import CronInput from '@sa/cron-input';
import dayjs from 'dayjs';
import { useAppStore } from '@/store/modules/app';
import { isNotNull } from '@/utils/common';

defineOptions({
  name: 'JobTriggerInterval'
});

interface Props {
  triggerType: Api.Common.TriggerType;
}

const model = defineModel<string>();
const props = defineProps<Props>();

const app = useAppStore();

/** 保存 `固定时间` 类型的 时间间隔 */
const interval = ref<number>(props.triggerType === 2 ? Number(model.value) : 60);

/** 保存 `CRON表达式` 类型的 表达式 */
const cron = ref<string>(props.triggerType === 3 ? model.value! : '* * * * * ?');

/** 安全解析 JSON 字符串为日期数组 */
const parseJsonToDateList = (jsonStr: string): Array<string | null> => {
  try {
    const parsed = JSON.parse(jsonStr);
    if (Array.isArray(parsed)) {
      return parsed;
    }
    return [null];
  } catch {
    return [null];
  }
};

/** 保存 `指定时间点` 类型的 时间 */
const dateList = ref<Array<string | null>>(
  props.triggerType === 5 && model.value ? parseJsonToDateList(model.value) : [null]
);

const specifiedDate = ref<number>(dayjs().add(15, 'second').valueOf());
const specifiedUnit = ref<string>('second');
const specifiedStep = ref<number>(10);
const specifiedCount = ref<number>(1);

// dayjs 支持的常用单位
const timeUnits = [
  { label: '秒', value: 'second' },
  { label: '分钟', value: 'minute' },
  { label: '小时', value: 'hour' },
  { label: '天', value: 'day' },
  { label: '月', value: 'month' },
  { label: '年', value: 'year' }
];

/** 监视 触发间隔 变化 */
watch(
  interval,
  val => {
    if (props.triggerType === 2) {
      model.value = `${val}`;
    }
  },
  { immediate: true }
);

/** 监视 cron 表达式变化 */
watch(
  cron,
  val => {
    if (props.triggerType === 3) {
      model.value = val;
    }
  },
  { immediate: true }
);

/** 监视 model.value 变化，同步 dateList */
watch(
  () => model.value,
  val => {
    if (props.triggerType === 5 && val) {
      const parsedDateList = parseJsonToDateList(val);
      // 只有当解析结果与当前 dateList 不同时才更新，避免循环触发
      if (JSON.stringify(parsedDateList) !== JSON.stringify(dateList.value)) {
        dateList.value = parsedDateList;
      }
    }
  }
);

/** 根据不同 triggerType, 为model赋值 */
watch(
  () => props.triggerType,
  triggerType => {
    if (triggerType === 2) {
      model.value = `${interval.value}`;
    } else if (triggerType === 3) {
      model.value = cron.value;
    } else if (triggerType === 5) {
      // 如果已有 model.value，解析后赋值给 dateList
      specifiedDate.value = dayjs().add(60, 'second').valueOf();
      if (model.value) {
        dateList.value = parseJsonToDateList(model.value);
      }
      model.value = JSON.stringify(dateList.value);
    } else {
      model.value = '*';
    }
  },
  { immediate: true }
);

watch(
  dateList,
  val => {
    if (props.triggerType === 5) {
      model.value = JSON.stringify(val);
    }
  },
  { deep: true }
);

const handleAddDate = () => {
  if (
    isNotNull(specifiedUnit.value) &&
    isNotNull(specifiedStep.value) &&
    isNotNull(specifiedCount.value) &&
    specifiedCount.value > 0
  ) {
    dateList.value = dateList.value.filter(item => isNotNull(item));
    const date = new Date(specifiedDate.value);
    for (let i = 0; i < specifiedCount.value; i += 1) {
      const newDate = dayjs(date)
        .add(i * specifiedStep.value, specifiedUnit.value as dayjs.ManipulateType)
        .format('YYYY-MM-DD HH:mm:ss');
      // 检查是否已存在相同的日期，如果不存在才添加
      if (!dateList.value.includes(newDate)) {
        dateList.value.push(newDate);
      }
    }
    return;
  }
  dateList.value.push(null);
};

const handleAddBlank = () => {
  dateList.value.push(null);
};

const handleRemoveDate = (index: number) => {
  dateList.value.splice(index, 1);
  if (dateList.value.length === 0) {
    dateList.value.push(null);
  }
};

const handleFormattedValueUpdate = (value: string, index: number) => {
  if (!value) return true;
  const duplicateIndex = dateList.value.findIndex((item, idx) => idx !== index && item === value);

  if (duplicateIndex !== -1) {
    window.$message?.warning(`该时间点 ${value} 已存在，请选择其他时间`);
    nextTick(() => {
      dateList.value[index] = null;
    });
    return false;
  }

  return true;
};

const disablePreviousDate = (ts: number) => {
  const now = new Date();
  const targetDate = new Date(ts);
  // 禁用今天之前的所有日期
  return (
    targetDate.getFullYear() < now.getFullYear() ||
    (targetDate.getFullYear() === now.getFullYear() && targetDate.getMonth() < now.getMonth()) ||
    (targetDate.getFullYear() === now.getFullYear() &&
      targetDate.getMonth() === now.getMonth() &&
      targetDate.getDate() < now.getDate())
  );
};

const disablePreviousTime = (ts: number) => {
  const now = new Date();
  const selectedDate = new Date(ts);

  // 只有选择的是今天时，才需要禁用过去的时间
  const isToday =
    selectedDate.getFullYear() === now.getFullYear() &&
    selectedDate.getMonth() === now.getMonth() &&
    selectedDate.getDate() === now.getDate();

  if (!isToday) {
    // 如果不是今天，不禁用任何时间
    return {
      isHourDisabled: () => false,
      isMinuteDisabled: () => false,
      isSecondDisabled: () => false
    };
  }

  return {
    isHourDisabled: (hour: number) => {
      return hour < now.getHours();
    },
    isMinuteDisabled: (minute: number, hour?: number | null) => {
      const currentHour = hour ?? now.getHours();
      if (currentHour < now.getHours()) return true;
      if (currentHour > now.getHours()) return false;
      return minute < now.getMinutes();
    },
    isSecondDisabled: (second: number, minute?: number | null, hour?: number | null) => {
      const currentHour = hour ?? now.getHours();
      const currentMinute = minute ?? now.getMinutes();
      if (currentHour < now.getHours()) return true;
      if (currentHour > now.getHours()) return false;
      if (currentMinute < now.getMinutes()) return true;
      if (currentMinute > now.getMinutes()) return false;
      return second < now.getSeconds();
    }
  };
};
</script>

<template>
  <div>
    <NInputGroup v-if="triggerType === 2">
      <NInputNumber v-model:value="interval" :placeholder="$t('page.jobTask.form.triggerInterval')" />
      <NInputGroupLabel>{{ $t('common.second') }}</NInputGroupLabel>
    </NInputGroup>
    <CronInput
      v-else-if="triggerType === 3"
      v-model="cron"
      :placeholder="$t('page.jobTask.form.triggerInterval_CRON')"
      :lang="app.locale"
    />
    <template v-else-if="triggerType === 5">
      <NCard title="批量生成指定时间点" size="small" class="mb-4">
        <NGrid cols="24" x-gap="12" y-gap="12">
          <NGi :span="24">
            <div class="flex items-center gap-6px">
              <span class="w-85px">开始时间:</span>
              <NDatePicker
                v-model:value="specifiedDate"
                class="w-full"
                clearable
                type="datetime"
                format="yyyy-MM-dd HH:mm:ss"
                :is-date-disabled="disablePreviousDate"
                :is-time-disabled="disablePreviousTime"
              />
            </div>
          </NGi>
          <NGi :span="12">
            <div class="flex items-center gap-6px">
              <span class="w-50px">步长:</span>
              <NInputNumber v-model:value="specifiedStep" :min="10" class="w-full" placeholder="步长" />
            </div>
          </NGi>
          <NGi :span="12">
            <div class="flex items-center gap-6px">
              <span class="w-50px">单位:</span>
              <NSelect v-model:value="specifiedUnit" :options="timeUnits" class="w-full" />
            </div>
          </NGi>
          <NGi :span="12">
            <div class="flex items-center gap-6px">
              <span class="w-50px">个数:</span>
              <NInputNumber v-model:value="specifiedCount" :min="1" class="w-full" placeholder="个数" />
            </div>
          </NGi>
          <NGi :span="24">
            <NButton class="w-full" dashed @click="handleAddDate">
              <template #icon>
                <icon-ic-round-plus />
              </template>
              批量生成
            </NButton>
          </NGi>
        </NGrid>
      </NCard>
      <NCard title="指定时间点列表" size="small">
        <NGrid cols="24" x-gap="6" y-gap="6">
          <NGi v-for="(_, index) in dateList" :key="index" :span="24">
            <div class="flex items-center gap-12px">
              <NDatePicker
                v-model:formatted-value="dateList[index]"
                class="w-full"
                clearable
                type="datetime"
                format="yyyy-MM-dd HH:mm:ss"
                :is-date-disabled="disablePreviousDate"
                :is-time-disabled="disablePreviousTime"
                @update:formatted-value="value => handleFormattedValueUpdate(value, index)"
              />
              <NButton type="error" ghost @click="handleRemoveDate(index)">
                <template #icon>
                  <icon-ep-delete />
                </template>
                删除
              </NButton>
            </div>
          </NGi>
          <NGi :span="24">
            <NButton class="mt-6px w-full" dashed @click="handleAddBlank">
              <template #icon>
                <icon-ic-round-plus />
              </template>
              新增空白选择器
            </NButton>
          </NGi>
        </NGrid>
      </NCard>
    </template>
    <NInput v-else-if="triggerType === 99" disabled />
  </div>
</template>

<style scoped></style>
