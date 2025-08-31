<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import Workflow from '@/components/workflow';
import { $t } from '@/locales';
import { useWorkflowStore } from '@/store/modules/workflow';
import { fetchAddWorkflow, fetchWorkflowInfo } from '@/service/api';

const store = useWorkflowStore();
const route = useRoute();
const router = useRouter();

const spinning = ref(false);

const id: string = String(route.query.id);

const node = ref<Workflow.NodeDataType>({
  workflowName: `Workflow ${new Date().getTime()}`,
  workflowStatus: 1,
  blockStrategy: 1,
  description: undefined,
  executorTimeout: 60
});

const getDetail = async () => {
  spinning.value = true;
  const { data, error } = await fetchWorkflowInfo(id);
  if (!error) {
    node.value = data;
  }
  spinning.value = false;
};

onMounted(() => {
  store.clear();
  store.setType(0);
  getDetail();
});

const save = async () => {
  const { error } = await fetchAddWorkflow(node.value);
  if (!error) {
    window.$message?.info($t('common.addSuccess'));
    router.push('/workflow/task');
  }
};

const cancel = () => {
  router.push('/workflow/task');
};
</script>

<template>
  <Workflow v-model="node" :spinning="spinning" @save="save" @cancel="cancel" />
</template>

<style scoped></style>
