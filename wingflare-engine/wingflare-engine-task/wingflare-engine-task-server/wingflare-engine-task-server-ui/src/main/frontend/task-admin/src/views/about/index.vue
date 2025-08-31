<script setup lang="ts">
import { computed, ref } from 'vue';
import { $t } from '@/locales';
import { useAppStore } from '@/store/modules/app';
import pkg from '~/package.json';
import { localStg } from '@/utils/storage';

const appStore = useAppStore();

const column = computed(() => (appStore.isMobile ? 1 : 2));

// interface PkgJson {
//   name: string;
//   dependencies: PkgVersionInfo[];
//   devDependencies: PkgVersionInfo[];
// }

// interface PkgVersionInfo {
//   name: string;
//   version: string;
// }

const { VITE_APP_VERSION } = import.meta.env;

// const { name, dependencies, devDependencies } = pkg;
//
// function transformVersionData(tuple: [string, string]): PkgVersionInfo {
//   const [$name, $version] = tuple;
//   return {
//     name: $name,
//     version: $version
//   };
// }

const version = ref<string>(`${localStg.get('version') || VITE_APP_VERSION}`);

// const pkgJson: PkgJson = {
//   name,
//   dependencies: Object.entries(dependencies).map(item => transformVersionData(item)),
//   devDependencies: Object.entries(devDependencies).map(item => transformVersionData(item))
// };

const latestBuildTime = BUILD_TIME;
</script>

<template>
  <NSpace vertical :size="16">
    <NCard :title="$t('page.about.title')" :bordered="false" size="small" segmented class="card-wrapper">
      <p v-html="$t('page.about.introduction')" />
    </NCard>
    <NCard :title="$t('page.about.projectInfo.title')" :bordered="false" size="small" segmented class="card-wrapper">
      <NDescriptions label-placement="left" bordered size="small" :column="column">
        <NDescriptionsItem :label="$t('page.about.projectInfo.officialWebsite')">
          <a class="text-primary" :href="pkg.officialWebsite" target="_blank" rel="noopener noreferrer">
            {{ $t('page.about.projectInfo.officialWebsite') }}
          </a>
        </NDescriptionsItem>
        <NDescriptionsItem :label="$t('page.about.projectInfo.version')">
          <NTag type="primary">{{ version }}</NTag>
        </NDescriptionsItem>
        <NDescriptionsItem :label="$t('page.about.projectInfo.githubLink')">
          <a class="text-primary" :href="pkg.repository.githubUrl" target="_blank" rel="noopener noreferrer">
            {{ $t('page.about.projectInfo.githubLink') }}
          </a>
        </NDescriptionsItem>
        <NDescriptionsItem :label="$t('page.about.projectInfo.giteeLink')">
          <a class="text-primary" :href="pkg.repository.giteeUrl" target="_blank" rel="noopener noreferrer">
            {{ $t('page.about.projectInfo.giteeLink') }}
          </a>
        </NDescriptionsItem>
        <NDescriptionsItem :label="$t('page.about.projectInfo.previewLink')">
          <a class="text-primary" :href="pkg.website" target="_blank" rel="noopener noreferrer">
            {{ $t('page.about.projectInfo.previewLink') }}
          </a>
        </NDescriptionsItem>
        <NDescriptionsItem :label="$t('page.about.projectInfo.latestBuildTime')">
          <NTag type="primary">{{ latestBuildTime }}</NTag>
        </NDescriptionsItem>
      </NDescriptions>
    </NCard>
    <!--    <NCard-->
    <!--      :title="$t('page.about.projectInfo.videoTutorial')"-->
    <!--      :bordered="false"-->
    <!--      size="small"-->
    <!--      segmented-->
    <!--      class="card-wrapper"-->
    <!--    >-->
    <!--      <NCarousel :slides-per-view="3" :space-between="20" show-arrow :mousewheel="true">-->
    <!--        <iframe-->
    <!--          src="//player.bilibili.com/player.html?bvid=BV1Yu4y1k7WU&page=1"-->
    <!--          scrolling="no"-->
    <!--          border="0"-->
    <!--          width="100%"-->
    <!--          height="298px"-->
    <!--          autoplay="no"-->
    <!--          frameborder="no"-->
    <!--          framespacing="0"-->
    <!--          allowfullscreen="false"-->
    <!--        ></iframe>-->
    <!--        <iframe-->
    <!--          src="//player.bilibili.com/player.html?bvid=BV1Lu4y1C74h&page=1"-->
    <!--          scrolling="no"-->
    <!--          border="0"-->
    <!--          width="100%"-->
    <!--          height="298px"-->
    <!--          frameborder="no"-->
    <!--          framespacing="0"-->
    <!--          autoplay="no"-->
    <!--          allowfullscreen="false"-->
    <!--        ></iframe>-->
    <!--        <iframe-->
    <!--          src="//player.bilibili.com/player.html?bvid=BV1Km4y1N7s9&page=1"-->
    <!--          scrolling="no"-->
    <!--          border="0"-->
    <!--          width="100%"-->
    <!--          height="298px"-->
    <!--          autoplay="no"-->
    <!--          frameborder="no"-->
    <!--          framespacing="0"-->
    <!--          allowfullscreen="true"-->
    <!--        ></iframe>-->
    <!--        <iframe-->
    <!--          src="//player.bilibili.com/player.html?bvid=BV1xk4y1F7tD&page=1"-->
    <!--          scrolling="no"-->
    <!--          border="0"-->
    <!--          width="100%"-->
    <!--          height="298px"-->
    <!--          frameborder="no"-->
    <!--          framespacing="0"-->
    <!--          allowfullscreen="true"-->
    <!--        ></iframe>-->
    <!--      </NCarousel>-->
    <!--      <p></p>-->
    <!--      <p></p>-->
    <!--    </NCard>-->
  </NSpace>
</template>

<style scoped></style>
