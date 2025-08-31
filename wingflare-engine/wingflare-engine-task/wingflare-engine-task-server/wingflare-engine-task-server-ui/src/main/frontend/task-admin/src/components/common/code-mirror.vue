<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import CodeMirror from 'vue-codemirror6';
import { oneDark } from '@codemirror/theme-one-dark';
import { json } from '@codemirror/lang-json';
import { javascript } from '@codemirror/lang-javascript';
import { useBoolean } from '@sa/hooks';
import { useThemeStore } from '@/store/modules/theme';

defineOptions({
  name: 'CodeMirror'
});

interface Props {
  modelValue?: string;
  lang?: NaiveUI.CodeMirrorLang;
  height?: string;
  fontSize?: string;
  readonly?: boolean;
  disabled?: boolean;
  placeholder: string;
}

const props = withDefaults(defineProps<Props>(), {
  lang: 'js',
  readonly: false,
  disabled: false,
  height: 'auto',
  fontSize: '13px',
  modelValue: ''
});

interface Emits {
  (e: 'update:modelValue', modelValue: string): void;
}

const emit = defineEmits<Emits>();

const themeStore = useThemeStore();

const nodeExpression = ref<string>(props.modelValue);

const { bool: visible, setTrue: openModal } = useBoolean();

const codeLang = computed(() => {
  switch (props.lang) {
    case 'json':
      return json();
    case 'js':
      return javascript();
    default:
      return json();
  }
});

const theme = computed(() => {
  return {
    '&': {
      border: `1px solid ${themeStore.darkMode ? '#0000' : 'rgb(224, 224, 230)'}`,
      borderRadius: '6px',
      fontFamily: 'system-ui',
      backgroundColor: 'rgba(255, 255, 255, 0.1)'
    },
    '.cm-line': {
      height: '21px',
      fontSize: props.fontSize,
      fontFamily: 'monospace'
    },
    '.cm-lineNumbers': {
      fontFamily: 'system-ui',
      marginLeft: '5px',
      borderColor: '#0000',
      color: 'rgba(255, 255, 255, 0.82)'
    },
    '.cm-activeLineGutter': {
      backgroundColor: themeStore.darkMode ? '#262626' : '#a1a1a1'
    },
    '.cm-activeLine ': {
      backgroundColor: '#0000'
    },
    '.cm-gutters': {
      borderTopLeftRadius: '6px',
      borderBottomLeftRadius: '6px',
      backgroundColor: themeStore.darkMode ? '#262626' : '#a1a1a1'
    },
    '.cm-content': {
      padding: '5px 5px 5px 0'
    },
    '.cm-scroller': {
      height: props.height,
      overflowY: 'auto',
      overflowX: 'hidden'
    },
    '.cm-placeholder': {
      color: 'rgba(255, 255, 255, 0.38)'
    },
    '&.cm-focused': {
      outline: 'none',
      borderRadius: '6px',
      border: '1px solid #3388ff',
      boxShadow: '0 0 0 2px rgba(19, 102, 255, 0.2)',
      zIndex: '100'
    }
  };
});

watch(
  () => nodeExpression.value,
  () => {
    if (props.lang === 'json') {
      try {
        const obj = JSON.parse(nodeExpression.value);
        if (typeof obj === 'object') {
          nodeExpression.value = JSON.stringify(obj);
        }
      } catch {}
    }
    emit('update:modelValue', nodeExpression.value);
  }
);
</script>

<template>
  <div class="w-100% flex gap-8px">
    <CodeMirror
      v-model="nodeExpression"
      class="snial-code-mirror w-100%"
      :readonly="readonly"
      :disabled="disabled"
      :theme="theme"
      :placeholder="placeholder"
      basic
      :line-number="false"
      :lang="codeLang"
      :extensions="[oneDark]"
    />
    <NButton @click="openModal">
      <icon-flowbite:expand-outline class="text-18px" />
    </NButton>
    <NModal v-model:show="visible" class="max-w-90% w-600px" preset="card" title="编辑器" :bordered="false">
      <CodeMirror
        v-model="nodeExpression"
        class="w-100%"
        :readonly="readonly"
        :disabled="disabled"
        :theme="{
          ...theme,
          '.cm-scroller': {
            height: '300px',
            overflowY: 'auto',
            overflowX: 'hidden'
          }
        }"
        :placeholder="placeholder"
        basic
        :line-number="false"
        :lang="codeLang"
        :extensions="[oneDark]"
      />
    </NModal>
  </div>
</template>

<style scoped>
:deep(.snial-code-mirror) {
  max-width: calc(100% - 58px);
}
</style>
