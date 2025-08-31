import { request } from '../request';

/** get retry task list */
export function fetchRetryTaskPageList(params?: Api.RetryTask.RetryTaskSearchParams) {
  return request<Api.RetryTask.RetryTaskList>({
    url: '/retry-task/list',
    method: 'get',
    params
  });
}

/** get retry task list */
export function fetchRetryTaskById(id: string) {
  return request<Api.RetryTask.RetryTask>({
    url: `/retry-task/${id}`,
    method: 'get'
  });
}

/** delete retry task */
export function fetchDeleteRetryTask(id: number) {
  return request<boolean>({
    url: `/retry-task/${id}`,
    method: 'delete'
  });
}

/** delete retry task */
export function fetchBatchDeleteRetryTask(ids: number[]) {
  return request<boolean>({
    url: `/retry-task/ids`,
    method: 'delete',
    data: ids
  });
}

/** delete retry task */
export function fetchStopRetryTask(id: number) {
  return request<boolean>({
    url: `/retry-task/stop/${id}`,
    method: 'post'
  });
}
