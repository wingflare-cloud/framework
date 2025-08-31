<script lang="tsx" setup>
import { computed, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { NEllipsis } from 'naive-ui';
import { $t } from '@/locales';
import { localStg } from '@/utils/storage';
import { useAppStore } from '@/store/modules/app';
import { useAuthStore } from '@/store/modules/auth';
import ButtonIcon from '@/components/custom/button-icon.vue';
import SvgIcon from '@/components/custom/svg-icon.vue';

defineOptions({
  name: 'NamespaceSelect'
});

const router = useRouter();
const appStore = useAppStore();
const authStore = useAuthStore();
const namespaceId = ref<string>(localStg.get('namespaceId')!);
const namespaceIds = ref(localStg.get('userInfo')?.namespaceIds || []);

watch(
  () => authStore.namespaceUniqueId,
  val => {
    namespaceId.value = val;
    authStore.setNamespaceId(val);
  }
);

watch(
  () => authStore.userInfo.namespaceIds,
  val => {
    namespaceIds.value = val;
  },
  { deep: true }
);

const dropOptions = computed(() =>
  namespaceIds.value.map(item => {
    return {
      label: () => {
        if (item.uniqueId === namespaceId.value) {
          return (
            <div class="max-w-130px flex items-center justify-between">
              <NEllipsis tooltip={{ placement: 'left' }}>{item.name}</NEllipsis>
              <SvgIcon class="ml-6px" icon="ant-design:check-outlined" />
            </div>
          );
        }
        return (
          <div class="max-w-130px flex items-center justify-between">
            <NEllipsis tooltip={{ placement: 'left' }}>{item.name}</NEllipsis>
          </div>
        );
      },
      key: item.uniqueId
    };
  })
);

const onChange = (value: string) => {
  namespaceId.value = value;
  authStore.setNamespaceId(value);
  router.go(0);
};

const namespaceName = computed(() => {
  return namespaceIds.value.filter(item => item.uniqueId === namespaceId.value)[0]?.name || 'Default';
});
</script>

<template>
  <NDropdown v-if="appStore.isMobile" :value="namespaceId" :options="dropOptions" trigger="hover" @select="onChange">
    <div>
      <ButtonIcon :tooltip-content="$t('icon.namespace')" tooltip-placement="left">
        <SvgIcon icon="eos-icons:namespace" />
      </ButtonIcon>
    </div>
  </NDropdown>

  <NDropdown v-else :value="namespaceId" :options="dropOptions" trigger="click" @select="onChange">
    <div class="namespace-select">
      <ButtonIcon class="w-full" :tooltip-content="$t('icon.namespace')" tooltip-placement="left">
        <SvgIcon icon="eos-icons:namespace" />
        <NEllipsis class="text-14px">{{ namespaceName }}</NEllipsis>
        <SvgIcon icon="material-symbols:expand-more-rounded" />
      </ButtonIcon>
    </div>
  </NDropdown>
</template>

<style lang="scss" scoped>
.namespace-select {
  width: 158px;
  border: 1px solid rgb(224, 224, 230);
  border-radius: 35px;

  :deep(.n-button):hover,
  :deep(.n-button):focus {
    background-color: var(--n-color);
  }

  :deep(.n-ellipsis) {
    width: 100%;
    text-align: left;
    max-width: 88px;
  }

  :deep(.n-button) {
    width: 100% !important;
    padding: 0 5px 0 10px !important;
  }

  :deep(.n-button__content) {
    width: 100% !important;
  }

  :deep(.gap-8px) {
    gap: 0 !important;
  }

  :deep(.flex-center) {
    width: 100% !important;
    justify-content: space-between !important;
  }
}

.namespace-select:hover {
  border-color: rgb(var(--nprogress-color));
}

.namespace-select-option {
  display: flex;
  justify-content: space-between;
  align-items: center;

  :deep(.n-ellipsis) {
    width: 100%;
    max-width: 113px;
  }
}
</style>
