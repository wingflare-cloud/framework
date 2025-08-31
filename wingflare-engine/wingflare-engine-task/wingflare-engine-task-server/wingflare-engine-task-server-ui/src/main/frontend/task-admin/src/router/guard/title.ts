import { useTitle } from '@vueuse/core';
import type { Router } from 'vue-router';
import { $t } from '@/locales';

const { VITE_APP_TITLE } = import.meta.env;

export function createDocumentTitleGuard(router: Router) {
  router.afterEach(to => {
    const { i18nKey, title } = to.meta;

    const documentTitle = i18nKey ? $t(i18nKey) : title;

    useTitle(documentTitle, { titleTemplate: `%s | ${VITE_APP_TITLE}` });
  });
}
