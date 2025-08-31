<script setup lang="ts">
import { computed } from 'vue';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import { useAuthStore } from '@/store/modules/auth';

defineOptions({
  name: 'HeaderBanner'
});

const appStore = useAppStore();
const authStore = useAuthStore();

const gap = computed(() => (appStore.isMobile ? 0 : 16));

const timeFix = () => {
  const time = new Date();
  const hour = time.getHours();
  let text = '';
  if (hour > 5 && hour <= 8) {
    text = 'morning';
  } else if (hour > 8 && hour <= 11) {
    text = 'bth';
  } else if (hour > 11 && hour < 14) {
    text = 'noon';
  } else if (hour >= 14 && hour < 17) {
    text = 'ath';
  } else if (hour >= 17 && hour <= 19) {
    text = 'dusk';
  } else if (hour > 19 && hour < 21) {
    text = 'evening';
  } else if (hour >= 21 || hour <= 5) {
    text = 'earlyMorning';
  }
  return text;
};

const href = (url: string) => {
  window.open(url, '_blank');
};
</script>

<template>
  <NCard :bordered="false" class="card-wrapper">
    <NGrid :x-gap="gap" :y-gap="16" responsive="screen" item-responsive>
      <NGi class="flex" span="24 s:24 m:12">
        <div class="flex-y-center">
          <div class="pl-12px">
            <h3 class="text-22px font-semibold">
              {{ $t(`page.home.${timeFix()}Greeting`, { userName: authStore.userInfo.username }) }}
            </h3>
            <!-- <p class="text-#999 leading-30px">{{ $t('system.title') + ' - ' + $t('system.desc') }}</p> -->
          </div>
        </div>
      </NGi>
      <NGi class="flex-center" span="24 s:12 m:6">
        <div class="float-right max-w-250px w-full flex flex-col-reverse">
          <div class="order-9999 m-6px max-w-240px text-13px color-gray">
            <span>官号</span>
            <span class="float-right">
              ❤️
              <NButton text @click="href('https://snailjob.opensnail.com/docs/sponsor.html')">成为赞助商</NButton>
            </span>
          </div>
          <NButton quaternary class="h-full" @click="href('https://space.bilibili.com/3493144058399579')">
            <NThing class="max-w-full flex-center">
              <template #avatar>
                <SvgIcon class="text-66px color-#00aeec" local-icon="bilibili" />
              </template>
              <template #header>
                <div class="bilibili-title color-#00aeec font-semibold">Open Snail</div>
                <div class="flow-long-title ml-3px font-semibold">bilibili 官方账号</div>
              </template>
            </NThing>
          </NButton>
        </div>
      </NGi>
      <NGi class="flex-center" span="24 s:12 m:6">
        <div class="float-right max-w-250px w-full flex flex-col-reverse">
          <div class="order-9999 m-6px max-w-240px text-13px color-gray">
            <span>广告</span>
            <span class="float-right">
              ❤️
              <NButton text @click="href('https://snailjob.opensnail.com/docs/sponsor.html')">成为赞助商</NButton>
            </span>
          </div>
          <NButton quaternary class="h-full" @click="href('https://doc.flowlong.com/?from=snail-job')">
            <NThing class="max-w-full flex-center">
              <template #avatar>
                <SvgIcon class="text-66px" local-icon="flowlong" />
              </template>
              <template #header>
                <div class="flow-long-title-color font-semibold">FlowLong</div>
                <div class="flow-long-title font-semibold">飞龙工作流引擎</div>
              </template>
            </NThing>
          </NButton>
        </div>
      </NGi>
    </NGrid>
  </NCard>
</template>

<style scoped>
.flow-long-title-color {
  text-align: left;
  line-height: 36px;
  font-size: 28px;
  background: -webkit-linear-gradient(120deg, #bd34fe 30%, #41d1ff);
  background-clip: text;
  -webkit-text-fill-color: transparent;
}

.flow-long-title {
  text-align: left;
  line-height: 22px;
  font-size: 20px;
}

.bilibili-title {
  text-align: cneter;
  line-height: 36px;
  font-size: 26px;
}
</style>
