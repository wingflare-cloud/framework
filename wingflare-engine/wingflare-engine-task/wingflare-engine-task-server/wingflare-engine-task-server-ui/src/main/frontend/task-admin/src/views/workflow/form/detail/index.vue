<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import Workflow from '@/components/workflow';
import { fetchWorkflowInfo } from '@/service/api';
import { useWorkflowStore } from '@/store/modules/workflow';

const store = useWorkflowStore();
const route = useRoute();

const spinning = ref(false);

const id: string = String(route.query.id);

const node = ref<Workflow.NodeDataType>({});

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
  store.setType(1);
  store.setId(id);
  getDetail();
});
</script>

<template>
  <Workflow v-model="node" :spinning="spinning" disabled />
</template>

<style scoped></style>
