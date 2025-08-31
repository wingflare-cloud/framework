<script setup lang="ts">
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useNaiveForm } from '@/hooks/common/form';
import { useSearchStore } from '@/store/modules/search';

defineOptions({
  name: 'SearchForm'
});

interface Props {
  model: Record<string, any>;
  btnSpan?: string;
}

const props = defineProps<Props>();

interface Emits {
  (e: 'reset'): void;
  (e: 'search'): void;
}

const emit = defineEmits<Emits>();

const route = useRoute();
const appStore = useAppStore();
const searchStore = useSearchStore();

const title = ref(appStore.isMobile ? $t('common.search') : undefined);

const { formRef, validate, restoreValidation } = useNaiveForm();

async function reset() {
  await restoreValidation();
  Object.assign(props.model, { ...props.model, page: 1 });
  searchStore.remove(String(route.path));
  emit('reset');
}

async function search() {
  await validate();
  Object.assign(props.model, { ...props.model, page: 1 });
  searchStore.set(String(route.path), props.model);
  emit('search');
}

const btnSpan = computed(() => {
  const keyNum = Object.keys(props.model).length - 2;
  return props.btnSpan || (keyNum % 4 !== 0 ? `24 m:12 m:${(4 - (keyNum % 4)) * 6}` : '24');
});

onMounted(() => {
  const searchParams = searchStore.get(String(route.path));
  if (!searchParams) return;
  Object.assign(props.model, searchParams);
});
</script>

<template>
  <NCard :title="title" :bordered="false" size="small" class="card-wrapper">
    <NCollapse :default-expanded-names="String(route.name)">
      <NCollapseItem :title="$t('common.search')" :name="String(route.name)">
        <NForm ref="formRef" :model="model" label-placement="left" :label-width="80" :show-feedback="appStore.isMobile">
          <NGrid responsive="screen" cols="24" item-responsive :y-gap="12">
            <slot></slot>
            <NFormItemGi :y-gap="8" :span="btnSpan" class="pr-24px lg:p-t-0 md:p-t-16px">
              <NSpace class="min-w-172px w-full" justify="end">
                <NButton @click="reset">
                  <template #icon>
                    <icon-ic-round-refresh class="text-icon" />
                  </template>
                  {{ $t('common.reset') }}
                </NButton>
                <NButton type="primary" ghost @click="search">
                  <template #icon>
                    <icon-ic-round-search class="text-icon" />
                  </template>
                  {{ $t('common.search') }}
                </NButton>
              </NSpace>
            </NFormItemGi>
          </NGrid>
        </NForm>
      </NCollapseItem>
    </NCollapse>
  </NCard>
</template>

<style scoped>
:deep(.n-collapse-item__content-inner) {
  padding-top: 12px !important;
}
</style>
