import { request } from '../request';

/** get retry dead letter list */
export function fetchGetRetryDeadLetterPageList(params?: Api.RetryDeadLetter.RetryDeadLetterSearchParams) {
  return request<Api.RetryDeadLetter.RetryDeadLetterList>({
    url: '/retry-dead-letter/list',
    method: 'get',
    params
  });
}

export function fetchGetRetryDeadLetterById(id: string, groupName: string) {
  return request({
    url: `/retry-dead-letter/${id}?groupName=${groupName}`,
    method: 'get'
  });
}

/** add retry scene */
export function fetchRollbackRetryDeadLetter(data: Api.RetryDeadLetter.BatchDeadLetter) {
  return request<boolean>({
    url: '/retry-dead-letter/batch/rollback',
    method: 'post',
    data
  });
}

/** edit retry scene */
export function fetchDeleteRetryDeadLetter(data: Api.RetryDeadLetter.BatchDeadLetter) {
  return request<boolean>({
    url: '/retry-dead-letter/batch',
    method: 'delete',
    data
  });
}
