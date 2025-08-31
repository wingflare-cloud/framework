<script setup lang="ts">
import { DelayLevel, backOffRecord, enableStatusNumberRecord, routeKeyRecord } from '@/constants/business';
import { $t } from '@/locales';
import { tagColor } from '@/utils/common';

defineOptions({
  name: 'SceneDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.RetryScene.Scene | null;
}

const props = defineProps<Props>();

const visible = defineModel<boolean>('visible', {
  default: false
});

function maxRetryCountUpdate(maxRetryCount: number) {
  if (props.rowData?.backOff !== 1) {
    return '';
  }

  let desc = '';
  for (let i = 1; i <= maxRetryCount; i += 1) {
    desc += `,${DelayLevel[i as keyof typeof DelayLevel]}`;
  }
  return desc.substring(1, desc.length);
}
</script>

<template>
  <OperateDrawer v-model="visible" :title="$t('page.retryScene.detail')">
    <NDescriptions label-placement="top" bordered :column="2">
      <NDescriptionsItem :label="$t('page.retryScene.sceneName')" :span="2">{{ rowData?.sceneName }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retryScene.groupName')" :span="2">{{ rowData?.groupName }}</NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retryScene.sceneStatus')" :span="1">
        <NTag :type="tagColor(rowData?.sceneStatus!)">{{ $t(enableStatusNumberRecord[rowData?.sceneStatus!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('common.routeKey.routeLabel')" :span="1">
        <NTag :type="tagColor(rowData?.routeKey!)">{{ $t(routeKeyRecord[rowData?.routeKey!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retryScene.maxRetryCount')" :span="1">
        {{ rowData?.maxRetryCount }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retryScene.executorTimeout')" :span="1">
        {{ rowData?.executorTimeout }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retryScene.deadlineRequest')" :span="1">
        {{ rowData?.deadlineRequest }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retryScene.backOff')" :span="1">
        <NTag :type="tagColor(rowData?.backOff!)">
          {{ $t(backOffRecord[rowData?.backOff!]) }}
        </NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retryScene.triggerInterval')" :span="2">
        {{ rowData?.backOff === 1 ? maxRetryCountUpdate(rowData?.maxRetryCount) : rowData?.triggerInterval }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.retryScene.description')" :span="2">
        {{ rowData?.description }}
      </NDescriptionsItem>
    </NDescriptions>
  </OperateDrawer>
</template>

<style scoped></style>
