<script setup lang="ts">
import { ref } from 'vue';
import { localStg } from '@/utils/storage';

defineOptions({
  name: 'GlobalFooter'
});

const { VITE_APP_VERSION } = import.meta.env;
const version = ref<string>(`${localStg.get('version') || VITE_APP_VERSION}`);
</script>

<template>
  <DarkModeContainer class="h-full flex-center">
    <a href="https://gitee.com/aizuda/easy-retry/blob/master/LICENSE" target="_blank" rel="noopener noreferrer">
      Copyright © 2024 Snail Job v{{ version }}
    </a>
  </DarkModeContainer>
</template>

<style scoped></style>
