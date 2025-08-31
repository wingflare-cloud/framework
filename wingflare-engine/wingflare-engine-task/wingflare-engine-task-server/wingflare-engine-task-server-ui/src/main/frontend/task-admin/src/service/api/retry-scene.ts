import { request } from '../request';

/** get retry scene list */
export function fetchGetRetryScenePageList(params?: Api.RetryScene.SceneSearchParams) {
  return request<Api.RetryScene.SceneList>({
    url: '/scene-config/page/list',
    method: 'get',
    params
  });
}

/** get retry scene list */
export function fetchGetRetrySceneList(params?: Api.RetryScene.SceneSearchParams) {
  return request<Api.RetryScene.Scene[]>({
    url: '/scene-config/list',
    method: 'get',
    params
  });
}

/** add retry scene */
export function fetchAddRetryScene(data: Api.RetryScene.Scene) {
  return request<boolean>({
    url: '/scene-config',
    method: 'post',
    data
  });
}

/** edit retry scene */
export function fetchEditRetryScene(data: Api.RetryScene.Scene) {
  return request<boolean>({
    url: '/scene-config',
    method: 'put',
    data
  });
}

/** update retry scene status */
export function fetchUpdateSceneStatus(id: string, status: Api.Common.EnableStatusNumber) {
  return request<boolean>({
    url: `/scene-config/${id}/status/${status}`,
    method: 'put'
  });
}

/** delete retry scene status */
export function fetchDeleteRetryScene(id: string) {
  return request({
    url: '/scene-config/ids',
    method: 'delete',
    data: [id]
  });
}

/** batch delete retry scene status */
export function fetchBatchDeleteRetryScene(data: string[]) {
  return request({
    url: '/scene-config/ids',
    method: 'delete',
    data
  });
}
