import { defineStore } from 'pinia';
import { ref } from 'vue';
import { SetupStoreId } from '@/enum';

export type SearchParamsMap = { [key: string]: Record<string, any> | null };

export const useSearchStore = defineStore(SetupStoreId.Search, () => {
  const searchParamsMap = ref<SearchParamsMap>({});

  function get(key: string) {
    return searchParamsMap.value[key];
  }

  function set(key: string, params: Record<string, any>) {
    searchParamsMap.value[key] = params;
  }

  function remove(key: string) {
    searchParamsMap.value[key] = null;
  }

  function clear() {
    searchParamsMap.value = {};
  }

  return {
    get,
    set,
    remove,
    clear
  };
});
