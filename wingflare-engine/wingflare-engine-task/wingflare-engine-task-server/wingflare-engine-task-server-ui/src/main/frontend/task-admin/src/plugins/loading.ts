// @unocss-include
import { getRgb } from '@sa/color';
import { $t } from '@/locales';
import { localStg } from '@/utils/storage';
import systemLogo from '@/assets/svg-icon/logo.svg?raw';
import { DARK_CLASS } from '@/constants/app';
import { toggleHtmlClass } from '@/utils/common';

export function setupLoading() {
  const themeColor = localStg.get('themeColor') || '#1366FF';
  const darkMode = localStg.get('darkMode') || false;
  const { r, g, b } = getRgb(themeColor);

  const primaryColor = `--primary-color: ${r} ${g} ${b}`;

  if (darkMode) {
    toggleHtmlClass(DARK_CLASS).add();
  }

  const loadingClasses = [
    'left-0 top-0',
    'left-0 bottom-0 animate-delay-500',
    'right-0 top-0 animate-delay-1000',
    'right-0 bottom-0 animate-delay-1500'
  ];

  const logoWithClass = systemLogo.replace('<svg', `<svg class="size-158px fill-primary"`);

  const dot = loadingClasses
    .map(item => {
      return `<div class="absolute w-16px h-16px bg-primary rounded-8px animate-pulse ${item}"></div>`;
    })
    .join('\n');

  const loading = `
<div class="fixed-center flex-col bg-layout" style="${primaryColor}">
  ${logoWithClass}
  <div class="w-56px h-56px my-36px">
    <div class="relative h-full animate-spin">
      ${dot}
    </div>
  </div>
  <h2 class="text-28px font-500 pt-32px w-80% text-center text-#646464 dark:#d6d6d6">${$t('system.title')}</h2>
  <h2 class="text-18px font-500 pt-32px w-80% text-center text-#646464 dark:#d6d6d6">${$t('system.desc')}</h2>
</div>`;

  const app = document.getElementById('app');

  if (app) {
    app.innerHTML = loading;
  }
}
