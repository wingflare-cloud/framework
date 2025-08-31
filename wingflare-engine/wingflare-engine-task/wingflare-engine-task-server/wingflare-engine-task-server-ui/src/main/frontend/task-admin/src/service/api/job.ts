import { request } from '../request';

/** get Job page */
export function fetchGetJobPage(params?: Api.Job.JobSearchParams) {
  return request<Api.Job.JobList>({
    url: '/job/page/list',
    method: 'get',
    params
  });
}

/** get Job list */
export function fetchGetJobList(params?: Api.Job.JobSearchParams) {
  return request<Api.Job.Job[]>({
    url: '/job/list',
    method: 'get',
    params
  });
}

/** get Job list */
export function fetchGetJobDetail(id: string) {
  return request<Workflow.JobTaskType>({
    url: `/job/${id}`,
    method: 'get'
  });
}

/** get Job Task list */
export function fetchGetJobTaskList(params?: Api.Job.jobTaskSearchParams) {
  return request<Api.Job.JobTaskList>({
    url: '/job/task/list',
    method: 'get',
    params
  });
}

/** get Job Task Tree */
export function fetchGetJobTaskTree(params?: Api.Job.jobTaskSearchParams) {
  return request<Api.Job.JobTaskTreeList>({
    url: '/job/task/tree/list',
    method: 'get',
    params
  });
}

/** add Job */
export function fetchAddJob(data: Api.Job.Job) {
  return request<boolean>({
    url: '/job',
    method: 'post',
    data
  });
}

/** edit Job */
export function fetchEditJob(data: Api.Job.Job) {
  return request<boolean>({
    url: '/job',
    method: 'put',
    data
  });
}

/** edit Job status */
export function fetchUpdateJobStatus(data: Api.Job.JobUpdateJobStatusRequestVO) {
  return request<boolean>({
    url: '/job/status',
    method: 'put',
    data
  });
}

/** batch delete Job by id */
export function fetchBatchDeleteJob(data: string[]) {
  return request<boolean>({
    url: '/job/ids',
    method: 'delete',
    data
  });
}

/** trigger Job by id */
export function fetchTriggerJob(jobId: string) {
  return request<boolean>({
    url: `/job/trigger/${jobId}`,
    method: 'post'
  });
}

/** trigger Job by id */
export function fetchTriggerJobParams(data: Api.Job.TriggerJobParams) {
  return request<boolean>({
    url: '/job/trigger',
    method: 'post',
    data
  });
}

/** job name list */
export function fetchGetJobNameList(params?: Api.Job.JobNameListSearchParams) {
  return request<Api.Job.Job[]>({
    url: '/job/job-name/list',
    method: 'get',
    params
  });
}

/** job executor page */
export function fetchGetExecutorList(params?: Api.Job.JobExecutorParams) {
  return request<Api.Job.JobExecutorList>({
    url: '/job/executor/page/list',
    method: 'get',
    params
  });
}

/** job executor list */
export function fetchGetExecutorAllList(params?: Api.Job.JobExecutorParams) {
  return request<string[]>({
    url: '/job/executor/list',
    method: 'get',
    params
  });
}

/** batch delete Job executor by ids */
export function fetchBatchDeleteJobExecutor(data: string[]) {
  return request<boolean>({
    url: '/job/executor/ids',
    method: 'delete',
    data
  });
}
