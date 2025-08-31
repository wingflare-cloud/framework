<script setup lang="tsx">
import { ref, watch } from 'vue';
import type { SelectOption } from 'naive-ui';
import { NEllipsis } from 'naive-ui';
import { $t } from '@/locales';
import { translateOptions2 } from '@/utils/common';
import { fetchGetRetrySceneList } from '@/service/api';

defineOptions({
  name: 'SelectScene'
});

const emit = defineEmits<Emits>();

interface Props {
  groupName: string | null;
  clearable?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  clearable: false
});

/** 场景列表 */
const sceneNameList = ref<string[]>([]);
/** 场景列表 */
const sceneNameRef = ref<string>('');

interface Emits {
  (e: 'update:value', value: string): void;
}

async function getRetrySceneList(groupName: string) {
  if (props.groupName) {
    const res = await fetchGetRetrySceneList({ groupName });
    sceneNameList.value = res.data!.map((scene: Api.RetryScene.Scene) => scene.sceneName);
  } else {
    sceneNameRef.value = '';
    sceneNameList.value = [];
  }
}

// const renderOption = ({ node, option }: { node: VNode; option: SelectOption }) => {
//   h(
//     NTooltip,
//     {
//       trigger: 'hover'
//     },
//     {
//       trigger: () => node,
//       default: () => option.label
//     }
//   );
// };

watch(
  () => props.groupName,
  () => {
    getRetrySceneList(props.groupName!);
  }
);

watch(
  () => sceneNameRef.value,
  () => {
    emit('update:value', sceneNameRef.value);
  }
);

const renderLabel = (option: SelectOption) => <NEllipsis>{option.label}</NEllipsis>;
</script>

<template>
  <NSelect
    v-model:value="sceneNameRef"
    :placeholder="$t('page.retryTask.form.sceneName')"
    :options="translateOptions2(sceneNameList)"
    :clearable="props.clearable"
    :render-label="renderLabel"
    filterable
  />
</template>

<style scoped></style>
