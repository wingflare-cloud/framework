<script setup lang="ts">
import { ref } from 'vue';
import { fetchCardCount } from '@/service/api';
import CardData from './modules/card-data.vue';
import TaskTab from './modules/task-tab.vue';

const cardCount = ref<Api.Dashboard.CardCount>();

const getCardData = async () => {
  const { data: cardData, error } = await fetchCardCount();
  if (!error) {
    cardCount.value = cardData;
  }
};

getCardData();
</script>

<template>
  <NSpace vertical :size="16" class="home-main">
    <CardData v-model="cardCount!" />
    <NCard :bordered="false" class="card-wrapper p-t-136px 2xl:p-t-0 lg:p-t-36px md:p-t-90px">
      <TaskTab v-model="cardCount!" />
    </NCard>
  </NSpace>
</template>

<style scoped>
.home-main {
  max-height: calc(100vh - 155px);
}
</style>
