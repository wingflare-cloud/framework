<script setup lang="ts">
import { $t } from '@/locales';

defineOptions({
  name: 'GlobalLogo'
});

interface Props {
  /** Whether to show the title */
  showTitle?: boolean;
}

withDefaults(defineProps<Props>(), {
  showTitle: true
});
</script>

<template>
  <RouterLink to="/" class="w-full flex-center nowrap-hidden">
    <SystemLogo class="fill-primary text-36px" />
    <h2 v-show="showTitle" class="pl-8px text-27px text-primary font-bold transition duration-300 ease-in-out">
      {{ $t('system.title') }}
    </h2>
  </RouterLink>
</template>

<style scoped></style>
