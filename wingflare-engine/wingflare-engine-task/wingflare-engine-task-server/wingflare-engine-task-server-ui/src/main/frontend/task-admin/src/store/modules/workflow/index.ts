import { defineStore } from 'pinia';
import { ref } from 'vue';
import { fetchGetJobList } from '@/service/api';
import { SetupStoreId } from '@/enum';

export const useWorkflowStore = defineStore(SetupStoreId.Workflow, () => {
  const id = ref<string>();
  const type = ref<number>();
  const groupName = ref<string>();
  const jobList = ref<Pick<Api.Job.Job, 'id' | 'jobName' | 'executorInfo' | 'taskType'>[]>([]);

  function setId(value: string) {
    id.value = value;
  }

  function setType(value: number) {
    type.value = value;
  }

  async function setJobList(value: string) {
    groupName.value = value;
    const { data, error } = await fetchGetJobList({ groupName: value });
    if (!error) {
      jobList.value = data;
    }
  }

  function clear() {
    id.value = undefined;
    type.value = undefined;
    groupName.value = undefined;
    jobList.value = [];
  }

  return {
    id,
    type,
    groupName,
    jobList,
    setJobList,
    setType,
    setId,
    clear
  };
});
