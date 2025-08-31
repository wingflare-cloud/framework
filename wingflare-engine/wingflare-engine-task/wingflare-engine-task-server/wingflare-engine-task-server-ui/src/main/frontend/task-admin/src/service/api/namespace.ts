import { request } from '../request';

/** get namespace list */
export function fetchGetNamespaceList(params?: Api.Namespace.NamespaceSearchParams) {
  return request<Api.Namespace.NamespaceList>({
    url: '/namespace/list',
    method: 'get',
    params
  });
}

/** add namespace */
export function fetchAddNamespace(data: Api.Namespace.Namespace) {
  return request<boolean>({
    url: '/namespace',
    method: 'post',
    data
  });
}

/** edit namespace */
export function fetchEditNamespace(data: Api.Namespace.Namespace) {
  return request<boolean>({
    url: '/namespace',
    method: 'put',
    data
  });
}

/** delete namespace by id */
export function fetchDeleteNamespace(uniqueId: string) {
  return request<boolean>({
    url: `/namespace/${uniqueId}`,
    method: 'delete'
  });
}
