declare namespace I18n {
  type LocaleType = 'en-US' | 'zh-CN';

  type FieldType = 'second' | 'minute' | 'hour' | 'date' | 'month' | 'week' | 'year';

  interface LocaleFields {
    second: string;
    minute: string;
    hour: string;
    date: string;
    month: string;
    week: string;
    year: string;
  }

  interface LocaleFieldAlias {
    second: string;
    minute: string;
    hour: string;
    date: string;
    month: string;
    week: string;
    year: string;
  }

  interface LocaleTypes {
    empty: string;
    every: string;
    unspecific: string;
    range: string[];
    step: string[];
    well: string[];
    weekday: string[];
    lastWeekday: string;
    lastDayOfDate: string;
    lastDayOfWeek: string;
    specify: string;
  }

  interface LocaleWeek {
    [key: string]: string;
  }

  interface Translations {
    field: LocaleFields;
    fieldAlias: LocaleFieldAlias;
    type: LocaleTypes;
    week: LocaleWeek;
    expression: string;
    preview: string[];
    previewError: string;
  }
}
