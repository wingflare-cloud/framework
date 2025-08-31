<script setup lang="ts">
import { ref, watch } from 'vue';
import { alarmTypeRecord } from '@/constants/business';
import { $t } from '@/locales';
import { tagColor } from '@/utils/common';
import DingDingNotify = Api.NotifyRecipient.DingDingNotify;
import EmailNotify = Api.NotifyRecipient.EmailNotify;

defineOptions({
  name: 'NotifyRecipientDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.NotifyRecipient.NotifyRecipient | null;
}

const props = defineProps<Props>();
const notifyAttribute = ref<DingDingNotify | EmailNotify>();

const visible = defineModel<boolean>('visible', {
  default: false
});

watch(
  () => props.rowData,
  () => {
    const rowData = props.rowData?.notifyAttribute || null;
    notifyAttribute.value = JSON.parse(rowData!) || {};
  },
  { immediate: true }
);
</script>

<template>
  <OperateDrawer v-model="visible" :title="$t('page.notifyRecipient.detail')">
    <NDescriptions label-placement="top" bordered :column="2">
      <NDescriptionsItem :label="$t('page.notifyRecipient.recipientName')" :span="2">
        {{ rowData?.recipientName }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.notifyRecipient.notifyType')" :span="2">
        <NTag :type="tagColor(rowData?.notifyType!)">{{ $t(alarmTypeRecord[rowData?.notifyType!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem v-if="rowData?.notifyType !== 2" :label="$t('page.notifyRecipient.notifyType')" :span="2">
        {{ (notifyAttribute as DingDingNotify)?.webhookUrl }}
      </NDescriptionsItem>
      <NDescriptionsItem v-if="rowData?.notifyType !== 2" :label="$t('page.notifyRecipient.ats')" :span="2">
        <NTag
          v-for="(item, index) in (notifyAttribute as DingDingNotify)?.ats"
          :key="index"
          :type="tagColor(index)"
          class="!mr-10px"
        >
          {{ item }}
        </NTag>
      </NDescriptionsItem>
      <NDescriptionsItem v-if="rowData?.notifyType == 2" :label="$t('page.notifyRecipient.tos')" :span="2">
        <NTag
          v-for="(item, index) in (notifyAttribute as EmailNotify)?.tos"
          :key="index"
          :type="tagColor(index)"
          class="!mr-10px"
        >
          {{ item }}
        </NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.notifyRecipient.description')" :span="2">
        {{ rowData?.description }}
      </NDescriptionsItem>
    </NDescriptions>
  </OperateDrawer>
</template>

<style scoped></style>
