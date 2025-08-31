<script setup lang="ts">
import { nextTick, onMounted, ref } from 'vue';
import CodeMirror from 'vue-codemirror6';
import { oneDark } from '@codemirror/theme-one-dark';
import { javascript } from '@codemirror/lang-javascript';
import { expressionRecord } from '@/constants/business';

defineOptions({
  name: 'BranchDesc'
});

interface Props {
  modelValue?: Workflow.ConditionNodeType;
}

const props = withDefaults(defineProps<Props>(), {
  modelValue: () => ({})
});

const nodeExpression = ref('');

const theme = ref({
  '.cm-line': {
    fontSize: '18px'
  },
  '.cm-scroller': {
    minHeight: '180px',
    overflowY: 'auto',
    overflowX: 'hidden'
  }
});

const createDocument = () => {
  const desc = document.getElementById('branch-desc');
  const descriptions = desc?.querySelector('.n-descriptions-table');
  const body = descriptions?.querySelector('tbody');
  const tr = document.createElement('tr');
  tr.className = 'n-descriptions-table-row';
  const th = document.createElement('th');
  th.className = 'n-descriptions-table-header';
  th.innerHTML = '条件表达式';
  th.setAttribute('colspan', '4');
  tr.appendChild(th);
  body?.insertBefore(tr, body?.childNodes[4]);

  const rows: HTMLCollectionOf<Element> = body!.getElementsByClassName('n-descriptions-table-row')!;

  const element = rows[3] as HTMLElement;

  element.querySelector('.n-descriptions-table-header')?.remove();
  const content = element.querySelector('.n-descriptions-table-content') as HTMLElement;
  content.setAttribute('style', 'padding: 0');
  content?.setAttribute('colspan', '4');
};

onMounted(() => {
  nextTick(() => {
    createDocument();
  });
  if (props.modelValue.decision?.nodeExpression) {
    nodeExpression.value = props.modelValue.decision.nodeExpression;
  }
});
</script>

<template>
  <NDescriptions id="branch-desc" :column="2" label-placement="left" bordered :label-style="{ width: '120px' }">
    <NDescriptionsItem label="节点名称" :span="2">{{ modelValue.nodeName }}</NDescriptionsItem>
    <NDescriptionsItem label="表达式类型">
      {{ expressionRecord[modelValue.decision?.expressionType!] }}
    </NDescriptionsItem>
    <NDescriptionsItem label="条件表达式" :span="2" :content-style="{ padding: 0 }">
      <CodeMirror
        v-model="nodeExpression"
        readonly
        disabled
        :theme="theme"
        basic
        :lang="javascript()"
        :extensions="[oneDark]"
      />
    </NDescriptionsItem>
  </NDescriptions>
</template>
