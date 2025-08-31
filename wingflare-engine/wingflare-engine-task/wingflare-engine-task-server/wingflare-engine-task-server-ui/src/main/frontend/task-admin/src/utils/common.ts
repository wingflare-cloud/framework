import { Md5 } from 'ts-md5';
import dayjs from 'dayjs';
import { $t } from '@/locales';

/**
 * Transform record to option
 *
 * @example
 *   ```ts
 *   const record = {
 *     key1: 'label1',
 *     key2: 'label2'
 *   };
 *   const options = transformRecordToOption(record);
 *   // [
 *   //   { value: 'key1', label: 'label1' },
 *   //   { value: 'key2', label: 'label2' }
 *   // ]
 *   ```;
 *
 * @param record
 */
export function transformRecordToOption<T extends Record<string, string>>(record: T) {
  return Object.entries(record).map(([value, label]) => ({
    value,
    label
  })) as CommonType.Option<keyof T>[];
}

/**
 * Transform record to option with keys of number
 *
 * @param record
 * @param reverse
 */
export function transformRecordToNumberOption<T extends Record<number, string>>(record: T, reverse: boolean = false) {
  const options = Object.entries(record).map(([value, label]) => ({
    value: Number(value),
    label
  })) as CommonType.Option<keyof T>[];

  return reverse ? options.sort((a: any, b: any) => b.value - a.value) : options;
}

/**
 * Translate options
 *
 * @param options
 */
export function translateOptions(options: CommonType.Option<string | number>[]) {
  return options.map(option => ({
    ...option,
    label: $t(option.label as App.I18n.I18nKey)
  }));
}

/**
 * Translate options
 *
 * @param options
 */
export function translateOptions2(options: string[]) {
  return options.map(option => ({
    value: option,
    label: option
  }));
}

/**
 * tag Color
 *
 * @param index
 */
export function tagColor(index: number) {
  const tagMap: Record<number, NaiveUI.ThemeColor> = {
    0: 'error',
    1: 'info',
    2: 'success',
    3: 'warning',
    4: 'primary'
  };

  if (index === null || index < 0) {
    return tagMap[1];
  }

  return tagMap[index % 5];
}

/**
 * MD-5 哈希
 *
 * @param text 明文
 * @returns md5哈希
 */
export function md5(text: string): string {
  const md5Digest = new Md5();
  md5Digest.appendAsciiStr(text);
  return md5Digest.end() as string;
}

/**
 * Toggle html class
 *
 * @param className
 */
export function toggleHtmlClass(className: string) {
  function add() {
    document.documentElement.classList.add(className);
  }

  function remove() {
    document.documentElement.classList.remove(className);
  }

  return {
    add,
    remove
  };
}

/**
 * 创建 `最近n个自然月` timestamp 时间区间
 *
 * @param months 月数
 * @param startOf 时间的开始类型
 * @returns timestamp时间区间
 */
export function monthRange(months: number = 1, startOf: dayjs.OpUnitType = 'day') {
  return [dayjs().subtract(months, 'month').startOf(startOf).valueOf(), dayjs().endOf('day').valueOf()] as [
    number,
    number
  ];
}

/**
 * 创建 `最近n个自然月` timestamp 时间区间
 *
 * @param days 日数
 * @param startOf 时间的开始类型
 * @returns timestamp时间区间
 */
export function dayRange(days: number = 1) {
  return [
    dayjs()
      .subtract(days - 1, 'day')
      .startOf('day')
      .valueOf(),
    dayjs().endOf('day').valueOf()
  ] as [number, number];
}

/**
 * 创建 `最近n个自然月` 字符串时间区间
 *
 * @param months 月数
 * @param startOf 时间的开始类型
 * @returns 字符串时间区间
 */
export function monthRangeISO8601(months: number = 1, startOf: dayjs.OpUnitType = 'day') {
  return [
    dayjs().subtract(months, 'month').startOf(startOf).format('YYYY-MM-DDTHH:mm:ss'),
    dayjs().endOf('day').format('YYYY-MM-DDTHH:mm:ss')
  ] as [string, string];
}

export function weekRangeISO8601(weeks: number = 1, startOf: dayjs.OpUnitType = 'day') {
  return [
    dayjs().subtract(weeks, 'week').startOf(startOf).format('YYYY-MM-DDTHH:mm:ss'),
    dayjs().endOf('day').format('YYYY-MM-DDTHH:mm:ss')
  ] as [string, string];
}

export function isNotNull(value: any) {
  return value !== undefined && value !== null && value !== '' && value !== 'undefined';
}

export function isNull(value: any) {
  return value === undefined || value === null || value === '' || value === 'undefined' || value === 'null';
}

export function parseArgsJson(value: string) {
  let argsJson;

  try {
    argsJson = JSON.stringify(JSON.parse(value), null, 4);
  } catch {}

  return argsJson;
}

export function parseContent(value?: { key: string; value: string | number | boolean; type: string }[]) {
  if (!value) return undefined;
  return value.reduce<{ [key: string]: string | number | boolean }>((obj, item) => {
    if (item.type === 'string') {
      obj[item.key] = String(item.value);
    }

    if (item.type === 'boolean') {
      obj[item.key] = item.value === 1;
    }

    if (item.type === 'number') {
      obj[item.key] = Number(item.value);
    }

    return obj;
  }, {});
}

export function stringToContent(
  jsonString?: string
): { key: string; value: string | number | boolean; type: string }[] {
  if (!jsonString) return [];
  // 尝试解析JSON字符串
  let parsedObj = jsonString;

  if (typeof jsonString === 'string') {
    try {
      parsedObj = JSON.parse(jsonString);
    } catch {
      return [];
    }
  }

  // 创建结果数组
  const result = [];

  // 遍历对象的键值对
  for (const [key, value] of Object.entries(parsedObj)) {
    // 根据值的类型确定type字段
    let type = 'string';
    if (typeof value === 'number') {
      type = 'number';
    } else if (typeof value === 'boolean') {
      type = 'boolean';
    } else {
      type = 'string';
    }

    // 将转换后的对象添加到结果数组中
    result.push({
      key,
      value,
      type
    });
  }

  return result as any;
}

/**
 * 函数防抖
 *
 * @param func 函数
 * @param wait 延迟毫秒数
 * @param immediate true/false (是/否)即执行
 */
export function debounce(func: () => any, wait: number, immediate?: boolean) {
  let timeout: any;

  return () => {
    /* eslint-disable */
    // @ts-ignore
    const context = this
    const args: any = arguments
    if (timeout) clearTimeout(timeout);
    if (immediate) {
      const callNow = !timeout;
      timeout = setTimeout(() => {
        timeout = null;
      }, wait);
      if (callNow) func.apply(context, args);
    } else {
      timeout = setTimeout(() => {
        func.apply(context, args);
      }, wait);
    }
  };
}

/**
 * 生成 随机数
 *
 * @param length - 长度
 * @returns 随机数
 */
export function generateRandomString(length: number) {
  const chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  let randomString = '';
  for (let i = 0; i < length; i += 1) {
    const randomNumber = Math.floor(Math.random() * chars.length);
    randomString += chars.substring(randomNumber, randomNumber + 1);
  }
  return randomString;
}
