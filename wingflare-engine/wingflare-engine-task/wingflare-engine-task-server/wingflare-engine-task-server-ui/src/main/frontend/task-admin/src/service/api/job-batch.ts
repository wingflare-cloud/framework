import { request } from '../request';

/** get Job page */
export function fetchGetJobBatchList(params?: Api.JobBatch.JobBatchSearchParams) {
  return request<Api.JobBatch.JobBatchList>({
    url: '/job/batch/list',
    method: 'get',
    params
  });
}

export function fetchGetJobBatchDetail(id: string) {
  return request<Api.JobBatch.JobBatch>({
    url: `/job/batch/${id}`,
    method: 'get'
  });
}

/** stop job */
export function fetchJobBatchStop(jobId: string) {
  return request<boolean>({
    url: `/job/batch/stop/${jobId}`,
    method: 'post'
  });
}

/** retry job */
export function fetchJobBatchRetry(jobId: string) {
  return request<boolean>({
    url: `/job/batch/retry/${jobId}`,
    method: 'post'
  });
}

/** delete job */
export function fetchDeleteJobBatch(id: string) {
  return request<boolean>({
    url: `/job/batch/ids`,
    method: 'delete',
    data: [id]
  });
}

/** delete job */
export function fetchBatchDeleteJobBatch(data: string[]) {
  return request<boolean>({
    url: '/job/batch/ids',
    method: 'delete',
    data
  });
}
