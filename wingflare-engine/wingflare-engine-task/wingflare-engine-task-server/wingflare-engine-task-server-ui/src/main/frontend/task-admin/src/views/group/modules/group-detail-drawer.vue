<script setup lang="ts">
import { useClipboard } from '@vueuse/core';
import { groupConfigStatusRecord, yesOrNoRecord } from '@/constants/business';
import { $t } from '@/locales';
import { tagColor } from '@/utils/common';
defineOptions({
  name: 'GroupDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.GroupConfig.GroupConfig | null;
}

defineProps<Props>();

const visible = defineModel<boolean>('visible', {
  default: false
});

const { copy, isSupported } = useClipboard();

async function handleCopy(source?: string) {
  if (!isSupported) {
    window.$message?.error('您的浏览器不支持 Clipboard API');
    return;
  }

  if (!source) {
    return;
  }

  if (navigator.clipboard && window.isSecureContext) {
    await copy(source);
  } else {
    const range = document.createRange();
    range.selectNode(document.getElementById('tokenDetailInput')!);
    const selection = window.getSelection();
    if (selection?.rangeCount) selection.removeAllRanges();
    selection?.addRange(range);
    document.execCommand('copy');
  }
  window.$message?.success('复制成功');
}
</script>

<template>
  <OperateDrawer v-model="visible" :title="$t('page.groupConfig.detail')">
    <NDescriptions label-placement="top" bordered :column="1">
      <NDescriptionsItem :label="$t('page.groupConfig.groupName')" :span="1">
        {{ rowData?.groupName }}
      </NDescriptionsItem>

      <NDescriptionsItem :span="1">
        <template #label>
          <div class="flex items-center gap-2">
            <span>{{ $t('page.groupConfig.token') }}</span>
            <NButton type="default" text @click="handleCopy(rowData?.token)">
              <icon-ic:round-content-copy class="text-icon" />
            </NButton>
          </div>
        </template>
        <span id="tokenDetailInput">{{ rowData?.token }}</span>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.groupConfig.groupStatus')" :span="1">
        <NTag :type="tagColor(rowData?.groupStatus!)">{{ $t(groupConfigStatusRecord[rowData?.groupStatus!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.groupConfig.initScene')" :span="1">
        <NTag :type="tagColor(rowData?.initScene!)">{{ $t(yesOrNoRecord[rowData?.initScene!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.groupConfig.description')" :span="1">
        {{ rowData?.description }}
      </NDescriptionsItem>
    </NDescriptions>
  </OperateDrawer>
</template>

<style scoped></style>
