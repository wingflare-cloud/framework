<script setup lang="ts">
import { computed, nextTick, onUnmounted, reactive, ref } from 'vue';
import { useAppStore } from '@/store/modules/app';

defineOptions({
  name: 'OperateDrawer'
});

interface Props {
  title: string;
  minSize?: number;
}
const props = withDefaults(defineProps<Props>(), { minSize: 360 });

interface Emits {
  (e: 'update:modelValue', modelValue: boolean): void;
}
const emit = defineEmits<Emits>();

const model = defineModel<boolean>({ default: false });

const slots = defineSlots();

const appStore = useAppStore();

const state = reactive({ width: 0 });
const isFullscreen = ref(false);
const drawerWidth = computed(() => {
  const maxMinWidth = props.minSize;
  const maxMaxWidth = Math.max(props.minSize, 600);
  if (appStore.isMobile) {
    return state.width * 0.9 >= maxMinWidth ? `${maxMinWidth}px` : '90%';
  }
  let minWidth = state.width * 0.3 >= maxMinWidth ? `${maxMinWidth}px` : '30%';
  minWidth = state.width <= 420 ? '90%' : minWidth;
  let maxWidth = state.width * 0.5 >= maxMaxWidth ? `${maxMaxWidth}px` : '50%';
  maxWidth = state.width <= 420 ? '90%' : maxWidth;
  return isFullscreen.value ? maxWidth : minWidth;
});

const getState = () => {
  state.width = document.documentElement.clientWidth;
};

nextTick(() => {
  getState();
  window.addEventListener('resize', getState);
});

onUnmounted(() => {
  // 移除监听事件
  window.removeEventListener('resize', getState);
});

const onUpdateShow = (value: boolean) => {
  emit('update:modelValue', value);
};
</script>

<template>
  <NDrawer v-model:show="model" display-directive="if" :width="drawerWidth" @update:show="onUpdateShow">
    <NDrawerContent :title="props.title" :native-scrollbar="false" closable header-class="operate-dawer-header">
      <template #header>
        {{ props.title }}
        <div
          v-if="!appStore.isMobile"
          quaternary
          class="fullscreen text-18px color-#6a6a6a"
          @click="isFullscreen = !isFullscreen"
        >
          <icon-material-symbols:close-fullscreen-rounded v-if="isFullscreen" />
          <icon-material-symbols:open-in-full-rounded v-else />
        </div>
      </template>
      <slot></slot>
      <template v-if="slots.footer" #footer>
        <slot name="footer"></slot>
      </template>
    </NDrawerContent>
  </NDrawer>
</template>

<style scoped>
.fullscreen {
  height: 22px;
  width: 22px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.fullscreen:hover {
  background-color: #e8e8e8;
  color: #696969;
  border-radius: 6px;
  cursor: pointer;
}
</style>
