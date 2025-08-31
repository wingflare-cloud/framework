<script setup lang="ts">
import { $t } from '@/locales';
import SearchForm from '@/components/common/search-form.vue';
import { alarmTypeRecordOptions } from '@/constants/business';
import { translateOptions } from '@/utils/common';

defineOptions({
  name: 'NotifyRecipientSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.NotifyRecipient.NotifyRecipientParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm :model="model" @search="search" @reset="reset">
    <NFormItemGi
      span="24 s:12 m:6"
      :label-width="100"
      :label="$t('page.notifyRecipient.recipientName')"
      path="recipientName"
      class="pr-24px"
    >
      <NInput
        v-model:value="model.recipientName"
        :placeholder="$t('page.notifyRecipient.form.recipientName')"
        clearable
      />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.notifyRecipient.notifyType')" path="notifyType" class="pr-24px">
      <NSelect
        v-model:value="model.notifyType"
        :options="translateOptions(alarmTypeRecordOptions)"
        :placeholder="$t('page.notifyRecipient.notifyType')"
        clearable
      />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
