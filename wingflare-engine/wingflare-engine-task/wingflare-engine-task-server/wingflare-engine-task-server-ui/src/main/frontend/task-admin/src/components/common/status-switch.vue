<script setup lang="ts">
import { useBoolean } from '@sa/hooks';
import { enableStatusNumberRecord } from '@/constants/business';
import { $t } from '@/locales';

defineOptions({
  name: 'StatusSwitch'
});

interface Props {
  disabled?: boolean;
  info?: string;
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
  info: ''
});

const value = defineModel<Api.Common.EnableStatusNumber>('value', { required: false, default: 0 });

interface Emits {
  (e: 'submitted', value: Api.Common.EnableStatusNumber, callback: (flag: boolean) => void): void;
}

const emit = defineEmits<Emits>();

/** 状态切换过程的 loading 状态 */
const { bool: loading, setTrue: startLoading, setFalse: endLoading } = useBoolean();

const handleUpdateValue = (val: Api.Common.EnableStatusNumber) => {
  value.value = val === 0 ? 1 : 0;
  window.$dialog?.warning({
    title: '系统提示',
    content: `确定要${$t(enableStatusNumberRecord[val])}${props.info}吗？`,
    positiveText: '确定',
    negativeText: '取消',
    onPositiveClick: () => {
      startLoading();
      emit('submitted', val, flag => {
        if (flag) value.value = val;
        endLoading();
      });
    },
    onNegativeClick: () => {}
  });
};
</script>

<template>
  <NSwitch
    v-model:value="value"
    :loading="loading"
    :rubber-band="false"
    :checked-value="1"
    :unchecked-value="0"
    :disabled="props.disabled"
    @update:value="handleUpdateValue"
  />
</template>

<style scoped></style>
