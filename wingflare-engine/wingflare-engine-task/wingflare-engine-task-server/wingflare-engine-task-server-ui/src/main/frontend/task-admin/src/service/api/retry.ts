import { request } from '../request';

/** get retryTask list */
export function fetchGetRetryList(params?: Api.Retry.RetrySearchParams) {
  return request<Api.Retry.RetryList>({
    url: '/retry/list',
    method: 'get',
    params
  });
}

/** get retryTask */
export function fetchGetRetryById(id: string, groupName: string) {
  return request<Api.Retry.Retry>({
    url: `/retry/${id}?groupName=${groupName}`,
    method: 'get'
  });
}

/** add retryTask */
export function fetchAddRetry(data: CommonType.RecordNullable<Api.Retry.Retry>) {
  return request<boolean>({
    url: '/retry',
    method: 'post',
    data
  });
}

/** batch add retryTask */
export function fetchBatchAddRetry(data: Api.Retry.RetryBatchAdd) {
  return request<boolean>({
    url: '/retry/batch',
    method: 'post',
    data
  });
}

/** edit retryTask */
export function fetchEditRetryTask(data: Api.Retry.Retry) {
  return request<boolean>({
    url: '/retry',
    method: 'put',
    data
  });
}

/** update retryTask status */
export function fetchUpdateRetryStatus(data: Api.Retry.RetryUpdateStatusRequest) {
  return request<boolean>({
    url: '/retry/status',
    method: 'put',
    data
  });
}

/** manual execute retryTask */
export function fetchExecuteRetry(data: Api.Retry.ManualTriggerRequestVO) {
  return request<boolean>({
    url: '/retry/manual/trigger/retry/task',
    method: 'post',
    data
  });
}

/** batch delete retryTask */
export function fetchBatchDeleteRetry(data: Api.Retry.BatchDeleteRetryVO) {
  return request<number>({
    url: '/retry/batch',
    method: 'delete',
    data
  });
}

/** generate retryTask idempotent id */
export function fetchIdempotentIdGenerate(data: CommonType.RecordNullable<Api.Retry.GenerateRetryIdempotentIdVO>) {
  return request<string>({
    url: '/retry/generate/idempotent-id',
    method: 'post',
    data
  });
}
