import { LOCALE_CN, LOCALE_EN } from '../shared/constants';
import zhCN from './langs/zh-cn';
import enUS from './langs/en-us';

const locales: Record<string, I18n.Translations> = {
  [LOCALE_CN]: zhCN,
  [LOCALE_EN]: enUS
};

export default locales;
