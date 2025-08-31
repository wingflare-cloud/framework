<script setup lang="tsx">
import { NButton, NDynamicTags, NInput, NPopover, NTag } from 'naive-ui';
import { computed, nextTick, ref, watch } from 'vue';
import type { OnCreate } from 'naive-ui/es/dynamic-tags/src/interface';
import { isNull } from '@/utils/common';
import { fetchUpdatePodsLabels } from '@/service/api/dashboard';
import { $t } from '@/locales';

interface Props {
  id?: string | number;
  labels?: string;
  editable?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  id: undefined,
  labels: '{}',
  editable: false
});
const emit = defineEmits<{ (e: 'submitted'): void }>();

const inputValue = ref<string | null>(null);
const inputRef = ref<InstanceType<typeof NInput>>();

const parsedLabels = computed<Record<string, string>>(() => {
  try {
    return JSON.parse(props.labels || '{}');
  } catch {
    return {};
  }
});

const entries = computed(() => Object.entries(parsedLabels.value).filter(([k]) => k !== 'state'));
const firstEntry = computed(() => entries.value[0]);
const restEntries = computed(() => entries.value.slice(1));
const restValues = computed(() => restEntries.value.map(([k, v]) => `${k}:${v}`));

const getType = (key: string, value: string): 'info' | 'error' | 'success' => {
  if (key === 'env') return value === 'prod' ? 'error' : 'info';
  if (key === 'name') return 'success';
  return 'info';
};

const updateLabels = async (newLabels: Record<string, string>) => {
  if (!props.id) return;
  const { error } = await fetchUpdatePodsLabels({
    id: String(props.id),
    labels: JSON.stringify(newLabels)
  });
  if (error) {
    window.$message?.error(error.message);
    return;
  }
  inputValue.value = null;
  emit('submitted');
};

const parseLabelStr = (label: string): [string, string] | null => {
  const [key, value] = label.split(':').map(s => s.trim());
  return key && value ? [key, value] : null;
};

const handleCreate: OnCreate = label => {
  if (!Array.isArray(label) || label.length !== 2 || isNull(label[0]) || isNull(label[1])) {
    window.$message?.error('请输入有效的标签，格式如 "key:value"');
    return label;
  }
  const [key, value] = label;
  const updated = { ...parsedLabels.value, [key]: value };
  updateLabels(updated);
  inputValue.value = null;
  return label;
};

const handleUpdateMain = () => {
  const newLabels: Record<string, string> = {};
  restEntries.value.forEach(([k, v]) => {
    newLabels[k] = v;
  });
  updateLabels(newLabels);
};

const handleUpdateRest = (values: string[]) => {
  const updated: Record<string, string> = {};
  if (firstEntry.value) {
    const [k, v] = firstEntry.value;
    updated[k] = v;
  }
  values.forEach(str => {
    const pair = parseLabelStr(str);
    if (pair) {
      const [k, v] = pair;
      updated[k] = v;
    }
  });
  updateLabels(updated);
};

const handleInputConfirm = () => {
  if (inputValue.value?.length === 2) {
    handleCreate(inputValue.value);
  }
};

const handleInputEvent = (deactivate?: () => void) => {
  deactivate?.();
  handleInputConfirm();
};

watch(inputRef, value => {
  if (value) nextTick(() => value.focus());
});
</script>

<template>
  <div class="flex items-center justify-center">
    <template v-if="entries.length">
      <!-- 只读模式 -->
      <template v-if="!editable">
        <NTag class="mr-2" :type="getType(firstEntry[0], firstEntry[1])">{{ firstEntry[0] }}:{{ firstEntry[1] }}</NTag>

        <NPopover v-if="restEntries.length" trigger="click">
          <template #trigger>
            <NTag>+{{ restEntries.length }}</NTag>
          </template>
          <div class="flex flex-wrap justify-center gap-2">
            <NTag v-for="[k, v] in restEntries" :key="k" type="info">{{ k }}:{{ v }}</NTag>
          </div>
        </NPopover>
      </template>

      <!-- 可编辑模式 -->
      <template v-else>
        <NDynamicTags
          class="mr-2 flex justify-center"
          :value="[`${firstEntry[0]}:${firstEntry[1]}`]"
          :type="getType(firstEntry[0], firstEntry[1])"
          @create="handleCreate"
          @update:value="handleUpdateMain"
        >
          <template #trigger="{ activate }">
            <NButton v-if="firstEntry && !restEntries.length" size="small" dashed @click="activate">+</NButton>
          </template>
          <template #input="{ deactivate }">
            <NInput
              ref="inputRef"
              v-model:value="inputValue"
              size="small"
              pair
              separator=":"
              :placeholder="['key', 'value']"
              @blur="() => handleInputEvent(deactivate)"
              @keydown.enter.prevent="handleInputEvent(deactivate)"
            />
          </template>
        </NDynamicTags>

        <NPopover v-if="restEntries.length" trigger="click">
          <template #trigger>
            <NTag>+{{ restEntries.length }}</NTag>
          </template>
          <NDynamicTags
            class="flex justify-center"
            :value="restValues"
            type="info"
            @create="handleCreate"
            @update:value="handleUpdateRest"
          >
            <template #input="{ deactivate }">
              <NInput
                ref="inputRef"
                v-model:value="inputValue"
                size="small"
                pair
                separator=":"
                :placeholder="['key', 'value']"
                @blur="() => handleInputEvent(deactivate)"
                @keydown.enter.prevent="handleInputEvent(deactivate)"
              />
            </template>
          </NDynamicTags>
        </NPopover>
      </template>
    </template>

    <!-- 无标签情况 -->
    <NDynamicTags
      v-else-if="editable"
      type="info"
      class="flex justify-center"
      @create="handleCreate"
      @update:value="handleUpdateRest"
    >
      <template #input="{ deactivate }">
        <NInput
          ref="inputRef"
          v-model:value="inputValue"
          size="small"
          pair
          separator=":"
          :placeholder="['key', 'value']"
          @blur="() => handleInputEvent(deactivate)"
          @keydown.enter.prevent="handleInputEvent(deactivate)"
        />
      </template>
    </NDynamicTags>
    <NTag v-else-if="!entries.length" type="info">{{ $t('common.none') }}</NTag>
  </div>
</template>

<style scoped>
.n-dynamic-tags {
  justify-content: center;
}
.n-input {
  width: 130px;
}
</style>
