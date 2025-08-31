<script setup lang="ts">
import { computed, ref } from 'vue';
import type { Component } from 'vue';
import { getPaletteColorByNumber, mixColor } from '@sa/color';
import { $t } from '@/locales';
import GlobalFooter from '@/layouts/modules/global-footer/index.vue';
import { useAppStore } from '@/store/modules/app';
import { useThemeStore } from '@/store/modules/theme';
import { loginModuleRecord } from '@/constants/app';
import { localStg } from '@/utils/storage';
import { fetchVersion } from '@/service/api';
import PwdLogin from './modules/pwd-login.vue';

interface Props {
  /** The login module */
  module?: UnionKey.LoginModule;
}

const props = defineProps<Props>();

const { VITE_APP_VERSION } = import.meta.env;
const version = ref<string>(`${localStg.get('version') || VITE_APP_VERSION}`);

const getVersion = async () => {
  const { data, error } = await fetchVersion();
  if (!error && data) {
    version.value = data;
    localStg.set('version', data);
    return;
  }
  localStg.remove('version');
};

getVersion();

const appStore = useAppStore();
const themeStore = useThemeStore();

interface LoginModule {
  label: string;
  component: Component;
}

const moduleMap: Record<UnionKey.LoginModule, LoginModule> = {
  'pwd-login': { label: loginModuleRecord['pwd-login'], component: PwdLogin }
};

const activeModule = computed(() => moduleMap[props.module || 'pwd-login']);

const bgThemeColor = computed(() =>
  themeStore.darkMode ? getPaletteColorByNumber(themeStore.themeColor, 600) : themeStore.themeColor
);

const bgColor = computed(() => {
  const COLOR_WHITE = '#ffffff';

  const ratio = themeStore.darkMode ? 0.5 : 0.2;

  return mixColor(COLOR_WHITE, themeStore.themeColor, ratio);
});

const href = (url: string) => {
  window.open(url, '_blank');
};
</script>

<template>
  <div class="relative size-full flex-center overflow-hidden" :style="{ backgroundColor: bgColor }">
    <WaveBg :theme-color="bgThemeColor" />
    <NCard :bordered="false" class="relative z-4 w-auto rd-12px">
      <div class="w-400px lt-sm:w-300px">
        <header class="flex-y-center justify-between">
          <SystemLogo class="fill-primary text-64px lt-sm:text-48px" />
          <h3 class="flex text-28px text-primary font-500 lt-sm:text-22px">
            {{ $t('system.title') }}
            <span class="mt-3px pl-12px text-16px color-#00000072 font-600">v{{ version }}</span>
          </h3>
          <div class="i-flex-col">
            <ThemeSchemaSwitch
              :theme-schema="themeStore.themeScheme"
              :show-tooltip="false"
              class="text-20px lt-sm:text-18px"
              @switch="themeStore.toggleThemeScheme"
            />
            <LangSwitch
              v-if="themeStore.header.multilingual.visible"
              :lang="appStore.locale"
              :lang-options="appStore.localeOptions"
              :show-tooltip="false"
              @change-lang="appStore.changeLocale"
            />
          </div>
        </header>
        <main class="pt-24px">
          <div class="pt-0px">
            <Transition :name="themeStore.page.animateMode" mode="out-in" appear>
              <component :is="activeModule.component" />
            </Transition>
          </div>
          <div class="pt-12px text-center">
            <ButtonIcon
              tooltip-content="Mail"
              class="color-#272636 dark:color-#f0f2f5"
              icon="simple-icons:maildotru"
              @click="href('mailto:598092184@qq.com')"
            />
            <ButtonIcon
              class="color-#c71d23"
              tooltip-content="Gitee"
              icon="simple-icons:gitee"
              @click="href('https://gitee.com/aizuda/snail-job')"
            />
            <ButtonIcon
              tooltip-content="Github"
              class="color-#010409 dark:color-#e6edf3"
              icon="simple-icons:github"
              @click="href('https://github.com/aizuda/snail-job')"
            />
          </div>
          <GlobalFooter />
        </main>
      </div>
    </NCard>
  </div>
</template>

<style scoped></style>
