import { request } from '../request';

/** get notify list */
export function fetchGetNotifyConfigList(params?: Api.NotifyConfig.NotifySearchParams) {
  return request<Api.NotifyConfig.NotifyConfigList>({
    url: '/notify-config/list',
    method: 'get',
    params
  });
}

/** get notify systemTaskType list */
export function fetchGetNotifyConfigSystemTaskTypeList(systemTaskType: number) {
  return request<CommonType.Option<number>[]>({
    url: `/notify-config/all/${systemTaskType}`,
    method: 'get'
  });
}

/** add notify */
export function fetchAddNotify(data: Api.NotifyConfig.NotifyConfig) {
  return request<boolean>({
    url: '/notify-config',
    method: 'post',
    data
  });
}

/** edit notify */
export function fetchEditNotify(data: Api.NotifyConfig.NotifyConfig) {
  return request<boolean>({
    url: '/notify-config',
    method: 'put',
    data
  });
}

/** delete notify */
export function fetchBatchDeleteNotify(data: string[]) {
  return request<boolean>({
    url: '/notify-config/ids',
    method: 'delete',
    data
  });
}

/** edit notify status */
export function fetchUpdateNotifyStatus(id: string, status: Api.Common.EnableStatusNumber) {
  return request<boolean>({
    url: `/notify-config/${id}/status/${status}`,
    method: 'put'
  });
}

/** get notify recipient list */
export function fetchGetNotifyRecipientPageList(params?: Api.NotifyRecipient.NotifyRecipientParams) {
  return request<Api.NotifyRecipient.NotifyRecipientList>({
    url: '/notify-recipient/page/list',
    method: 'get',
    params
  });
}

/** get notify recipient list */
export function fetchGetNotifyRecipientList() {
  return request<CommonType.Option<number>[]>({
    url: '/notify-recipient/list',
    method: 'get'
  });
}

/** add notify recipient */
export function fetchAddNotifyRecipient(data: Api.NotifyRecipient.NotifyRecipient) {
  return request<boolean>({
    url: '/notify-recipient',
    method: 'post',
    data
  });
}

/** edit notify recipient */
export function fetchEditNotifyRecipient(data: Api.NotifyRecipient.NotifyRecipient) {
  return request<boolean>({
    url: '/notify-recipient',
    method: 'put',
    data
  });
}

/** delete notify recipient */
export function fetchDeleteNotifyRecipient(data: string[]) {
  return request<boolean>({
    url: '/notify-recipient/ids',
    method: 'delete',
    data
  });
}
