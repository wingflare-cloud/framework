<script setup lang="ts">
import { NTag } from 'naive-ui';
import { roleRecord } from '@/constants/business';
import { $t } from '@/locales';
import { tagColor } from '@/utils/common';

defineOptions({
  name: 'UserManagerDetailDrawer'
});

interface Props {
  /** row data */
  rowData?: Api.UserManager.UserManager | null;
}

defineProps<Props>();

const visible = defineModel<boolean>('visible', {
  default: false
});
</script>

<template>
  <OperateDrawer v-model="visible" :title="$t('page.groupConfig.detail')">
    <NDescriptions label-placement="top" bordered :column="2">
      <NDescriptionsItem :label="$t('page.userManager.username')" :span="2">
        {{ rowData?.username }}
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('page.userManager.role')" :span="2">
        <NTag :type="tagColor(rowData?.role!)">{{ $t(roleRecord[rowData?.role!]) }}</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem
        v-if="rowData?.permissions !== undefined"
        :label="$t('page.userManager.permissionList')"
        :span="2"
      >
        <NTag v-for="(item, index) in rowData?.permissions" :key="index" type="info">
          <span class="title">{{ item.groupName }}</span>
          ({{ item.namespaceName }})
        </NTag>
      </NDescriptionsItem>
      <NDescriptionsItem v-else :label="$t('page.userManager.permissionList')" :span="2">
        <NTag type="info">ALL</NTag>
      </NDescriptionsItem>
      <NDescriptionsItem :label="$t('common.updateDt')" :span="2">
        {{ rowData?.updateDt }}
      </NDescriptionsItem>
    </NDescriptions>
  </OperateDrawer>
</template>

<style scoped>
.title {
  font-weight: bolder !important;
}
</style>
