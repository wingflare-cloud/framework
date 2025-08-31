<script setup lang="ts">
import { $t } from '@/locales';
import SelectGroup from '@/components/common/select-group.vue';
import { translateOptions } from '@/utils/common';
import { enableStatusNumberOptions } from '@/constants/business';

defineOptions({
  name: 'SceneSearch'
});

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const model = defineModel<Api.RetryScene.SceneSearchParams>('model', { required: true });

function reset() {
  emit('reset');
}

function search() {
  emit('search');
}
</script>

<template>
  <SearchForm :model="model" @search="search" @reset="reset">
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryScene.groupName')" path="groupName" class="pr-24px">
      <SelectGroup v-model:value="model.groupName" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryScene.sceneName')" path="sceneName" class="pr-24px">
      <SelectScene v-model:value="model.sceneName" :group-name="model.groupName as string" clearable />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryScene.sceneStatus')" path="sceneStatus" class="pr-24px">
      <NSelect
        v-model:value="model.sceneStatus"
        :placeholder="$t('page.jobTask.form.jobStatus')"
        :options="translateOptions(enableStatusNumberOptions)"
        clearable
      />
    </NFormItemGi>
    <NFormItemGi span="24 s:12 m:6" :label="$t('page.retryScene.ownerName')" path="ownerId" class="pr-24px">
      <SystemUser v-model:value="model.ownerId" clearable />
    </NFormItemGi>
  </SearchForm>
</template>

<style scoped></style>
