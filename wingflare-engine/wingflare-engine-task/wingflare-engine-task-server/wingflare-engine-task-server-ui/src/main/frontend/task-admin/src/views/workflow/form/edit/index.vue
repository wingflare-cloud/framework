<script setup lang="ts">
import { onMounted, ref, watch } from 'vue';
import { onBeforeRouteLeave, useRoute, useRouter } from 'vue-router';
import { jsonClone } from '@sa/utils';
import Workflow from '@/components/workflow';
import { $t } from '@/locales';
import { useWorkflowStore } from '@/store/modules/workflow';
import { fetchUpdateWorkflow, fetchWorkflowInfo } from '@/service/api';

const store = useWorkflowStore();
const route = useRoute();
const router = useRouter();

const spinning = ref(false);

const id: string = String(route.query.id);

let workflowInfo: Workflow.NodeDataType = {};
const node = ref<Workflow.NodeDataType>({});

const getDetail = async () => {
  spinning.value = true;
  const { data, error } = await fetchWorkflowInfo(id);
  if (!error) {
    node.value = data;
    workflowInfo = jsonClone(data);
  }
  spinning.value = false;
};

const updated = ref(false);

onMounted(() => {
  store.clear();
  store.setType(0);
  store.setId(id);
  getDetail();
});

watch(
  () => node.value,
  val => {
    updated.value = isObjectChanged(val, workflowInfo);
  },
  { deep: true }
);

const update = async () => {
  const { error } = await fetchUpdateWorkflow(node.value);
  if (!error) {
    window.$message?.info($t('common.updateSuccess'));
    Object.assign(workflowInfo, node.value);
    updated.value = false;
    router.push({ path: '/workflow/task' });
  }
};

function isObjectChanged(source: any, comparison: any) {
  const sourceJson = JSON.stringify(source);
  const comparisonJson = JSON.stringify({ ...source, ...comparison });
  return sourceJson !== comparisonJson;
}

const cancel = () => {
  router.push('/workflow/task');
};

onBeforeRouteLeave((_to, _from, next) => {
  if (updated.value) {
    window.$dialog?.warning({
      title: $t('common.warning'),
      content: '有未保存的数据，是否确认离开？',
      positiveText: $t('common.confirm'),
      negativeText: $t('common.cancel'),
      onPositiveClick: () => {
        next();
      }
    });
  } else {
    next();
  }
});
</script>

<template>
  <Workflow v-model="node" :spinning="spinning" :updated="updated" @save="update" @cancel="cancel" />
</template>

<style scoped></style>
