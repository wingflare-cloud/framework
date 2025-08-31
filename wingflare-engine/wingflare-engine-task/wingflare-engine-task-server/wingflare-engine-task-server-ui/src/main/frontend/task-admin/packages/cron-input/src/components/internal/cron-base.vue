<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import { DATE, DEFAULT_LOCALE, LOCALE_EN, TYPE, WEEK, YEAR } from '../../shared/constants';
import { generateSpecifies, weekLetterToNumber } from '../../shared/utils';
import Locales from '../../i18n';
import InputNumber from './input-number.vue';

defineOptions({ name: 'CronBase' });

interface Props {
  modelValue: string;
  field: {
    value: I18n.FieldType;
    label: string;
    min: number;
    max: number;
  };
  locale?: I18n.LocaleType;
}

const props = withDefaults(defineProps<Props>(), {
  locale: DEFAULT_LOCALE
});

interface Emits {
  (e: 'update:modelValue', value: string): void;
}

const emit = defineEmits<Emits>();

const labels = props.field.value === 'week' ? Object.values(Locales[props.locale].week) : null;

const { min, max, value: fieldValue } = props.field;

const type = ref(TYPE.EVERY);
const range = ref([min, min + 1]);
const step = ref([min, 1]);
const well = ref([min, 1]);
const specify = ref<number[]>([]);
const weekday = ref(1);
const lastDayOfWeek = ref(1);
const rangeStart = ref<[number, number]>([min, max - 1]);
const stepLeft = ref<[number, number]>([min, max]);
const stepRight = ref<[number, number]>([1, max]);
const wellLeft = ref<[number, number]>([0, 0]);
const wellRight = ref<[number, number]>([0, 0]);
const specifies = ref(generateSpecifies(min, max, labels));

if (fieldValue === WEEK) {
  wellLeft.value = [1, 5];
  wellRight.value = [min, max];
}

const label = computed(() => {
  const i18n = Locales[props.locale];
  const { type: typeLabel, fieldAlias: fieldAliasLabel } = i18n;
  return {
    empty: typeLabel.empty,
    every: `${typeLabel.every}${fieldAliasLabel[props.field.value]}`,
    unspecific: typeLabel.unspecific,
    range: [
      typeLabel.range[0],
      (props.field.value === WEEK || props.locale === LOCALE_EN ? '' : props.field.label) + typeLabel.range[1],
      props.field.value === WEEK || props.locale === LOCALE_EN ? '' : props.field.label
    ],
    step: [
      typeLabel.step[0],
      props.field.label + typeLabel.step[1],
      fieldAliasLabel[props.field.value] + typeLabel.step[2]
    ],
    well: typeLabel.well,
    weekday: typeLabel.weekday,
    lastWeekday: typeLabel.lastWeekday,
    lastDayOfDate: typeLabel.lastDayOfDate,
    lastDayOfWeek: typeLabel.lastDayOfWeek,
    specify: typeLabel.specify
  };
});

const isEnWeek = computed(() => props.field.value === WEEK && props.locale === LOCALE_EN);
const rangeEnd = computed<[number, number]>(() => [range.value[0] + 1, props.field.max]);
const isEmpty = computed(() => props.field.value === YEAR);
const isUnspecific = computed(() => [DATE, WEEK].includes(props.field.value));
const isStep = computed(() => props.field.value !== WEEK);
const isWell = computed(() => props.field.value === WEEK);
const isLastDayOfDate = computed(() => props.field.value === DATE);
const isLastDayOfWeek = computed(() => props.field.value === WEEK);
const isWeekday = computed(() => props.field.value === DATE);
const isLastWeekday = computed(() => props.field.value === DATE);
const value = computed(() => {
  switch (type.value) {
    case TYPE.EMPTY:
    case TYPE.UNSPECIFIC:
    case TYPE.LAST_WEEKDAY:
    case TYPE.EVERY:
      return type.value;
    case TYPE.RANGE:
      return range.value.join(type.value);
    case TYPE.STEP:
      return step.value.join(type.value);
    case TYPE.WELL:
      return well.value.join(type.value);
    case TYPE.WEEKDAY:
      return `${weekday.value}${type.value}`;
    case TYPE.LAST_DAY:
      return props.field.value === DATE ? type.value : `${lastDayOfWeek.value}${type.value}`;
    case TYPE.SPECIFY: {
      const specifyValue = specify.value;
      return specifyValue.length
        ? specifyValue.sort((a, b) => a - b).join(type.value)
        : `${specifyValue[0] || specifies.value[0].value}`;
    }
    default:
      return '';
  }
});

watch(
  () => props.modelValue,
  val => {
    let data = val;

    if (props.field.value === WEEK) {
      data = weekLetterToNumber(val).replaceAll('8', '1');
    }

    if ([TYPE.EMPTY, TYPE.UNSPECIFIC, TYPE.LAST_DAY, TYPE.LAST_WEEKDAY, TYPE.EVERY].includes(data)) {
      type.value = data;
    } else if (data.includes(TYPE.RANGE)) {
      type.value = TYPE.RANGE;
      range.value = data.split(TYPE.RANGE).map(i => Number.parseInt(i, 10));
    } else if (data.includes(TYPE.STEP)) {
      type.value = TYPE.STEP;
      step.value = data.split(TYPE.STEP).map(i => Number.parseInt(i, 10));
    } else if (data.includes(TYPE.WELL)) {
      type.value = TYPE.WELL;
      well.value = data.split(TYPE.WELL).map(i => Number.parseInt(i, 10));
    } else if (data.includes(TYPE.WEEKDAY)) {
      type.value = TYPE.WEEKDAY;
      weekday.value = Number.parseInt(data, 10);
    } else if (data.includes(TYPE.LAST_DAY)) {
      type.value = TYPE.LAST_DAY;
      lastDayOfWeek.value = Number.parseInt(data, 10);
    } else {
      type.value = TYPE.SPECIFY;
      specify.value =
        data !== 'undefined' && data !== 'NaN' ? data.split(TYPE.SPECIFY).map(i => Number.parseInt(i, 10)) : [];
    }
  },
  { immediate: true }
);

watch(
  () => value.value,
  val => {
    emit('update:modelValue', val);
  }
);

const onRangeStartChange = (val: number) => {
  const [, ranEnd] = range.value;

  if (val >= ranEnd) {
    range.value[1] = val + 1;
  }
};

const onCheckboxGroupChange = () => {
  let checkType = TYPE.SPECIFY;

  if (specify.value.length === 0) {
    checkType = props.field.value === YEAR ? TYPE.EMPTY : TYPE.EVERY;
  }

  type.value = checkType;
};
</script>

<template>
  <NRadioGroup v-model:value="type" class="flex-col">
    <NRadio v-if="isEmpty && field.value !== YEAR" class="cron-radio" :value="TYPE.EMPTY">{{ label.empty }}</NRadio>
    <NRadio class="cron-radio" :value="TYPE.EVERY">{{ label.every }}</NRadio>
    <NRadio v-if="isEmpty && field.value === YEAR" class="cron-radio" :value="TYPE.EMPTY">{{ label.empty }}</NRadio>
    <NRadio v-if="isUnspecific" class="cron-radio" :value="TYPE.UNSPECIFIC">{{ label.unspecific }}</NRadio>
    <div class="cron-radio flex items-center justify-start gap-5px">
      <NRadio :value="TYPE.RANGE" />
      {{ label.range[0] }}
      <InputNumber
        v-model="range[0]"
        :range="rangeStart"
        :field-value="field.value"
        :locale="locale"
        @update:value="onRangeStartChange"
      />
      {{ label.range[1] }}
      <InputNumber v-model="range[1]" :range="rangeEnd" :field-value="field.value" :locale="locale" />
      {{ label.range[2] }}
    </div>
    <div v-if="isStep" class="cron-radio flex items-center justify-start gap-5px">
      <NRadio :value="TYPE.STEP" />
      <span>{{ label.step[0] }}</span>
      <InputNumber v-model="step[0]" :range="stepLeft" />
      <span>{{ label.step[1] }}</span>
      <InputNumber v-model="step[1]" :range="stepRight" />
      <span>{{ label.step[2] }}</span>
    </div>
    <div v-if="isWell" class="cron-radio flex items-center justify-start gap-5px">
      <NRadio :value="TYPE.WELL" />
      {{ label.well[0] }}
      <InputNumber v-model="well[1]" :range="[...wellLeft]" />
      {{ label.well[1] }}
      <InputNumber v-model="well[0]" :range="[...wellRight]" :field-value="field.value" :locale="locale" />
    </div>
    <div v-if="isWeekday" class="cron-radio flex items-center justify-start gap-5px">
      <NRadio :value="TYPE.WEEKDAY" />
      {{ label.weekday[0] }}
      <InputNumber v-model="weekday" :range="rangeStart" />
      {{ label.weekday[1] }}
    </div>
    <NRadio v-if="isLastWeekday" class="cron-radio" :value="TYPE.LAST_WEEKDAY">{{ label.lastWeekday }}</NRadio>
    <NRadio v-if="isLastDayOfDate" class="cron-radio" :value="TYPE.LAST_DAY">{{ label.lastDayOfDate }}</NRadio>
    <div v-if="isLastDayOfWeek" class="cron-radio flex items-center justify-start gap-5px">
      <NRadio v-if="isLastDayOfWeek" :value="TYPE.LAST_DAY" />
      {{ label.lastDayOfWeek }}
      <InputNumber v-model="lastDayOfWeek" :range="[1, 7]" :field-value="field.value" :locale="locale" />
    </div>
    <div class="cron-radio flex flex-wrap items-center justify-start gap-5px">
      <NRadio class="cron-radio" :value="TYPE.SPECIFY">{{ label.specify }}</NRadio>
      <NCheckboxGroup
        v-if="type === TYPE.SPECIFY"
        v-model:value="specify"
        class="p-l-22px"
        :class="{ 'checkbox-group-en-week': isEnWeek }"
        @update:value="onCheckboxGroupChange"
      >
        <NCheckbox
          v-for="option in specifies"
          :key="option.value"
          :label="option.label"
          :value="option.value"
          size="small"
          class="min-w-50px"
        />
      </NCheckboxGroup>
    </div>
  </NRadioGroup>
</template>

<style scoped lang="scss">
.cron-radio:not(:first-child) {
  margin-top: 12px;
}
</style>
