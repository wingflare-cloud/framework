import { request } from '../request';

/** get user page list */
export function fetchGetUserPageList(params?: Api.UserManager.UserManagerSearchParams) {
  return request<Api.UserManager.UserManagerList>({
    url: '/user/page/list',
    method: 'get',
    params
  });
}

/** get user simple list */
export function fetchGetUserSimpleList() {
  return request<Api.UserManager.UserManagerList>({
    url: '/user/simple/list',
    method: 'get'
  });
}

/** add user */
export function fetchAddUser(data: Api.UserManager.UserManager) {
  return request<boolean>({
    url: '/user',
    method: 'post',
    data
  });
}

/** edit user */
export function fetchEditUser(data: Api.UserManager.UserManager) {
  return request<boolean>({
    url: '/user',
    method: 'put',
    data
  });
}

/** delete user */
export function fetchDelUser(id: number) {
  return request<boolean>({
    url: `/user/${id}`,
    method: 'delete'
  });
}

/** batch delete user */
export function fetchBatchDelteUser(data: string[]) {
  return request<boolean>({
    url: `/user/ids`,
    method: 'delete',
    data
  });
}

/** update user password */
export function fetchUpdateUserPassword(data: Api.UserManager.UpdateUserPassword) {
  return request<boolean>({
    url: '/update-user-password',
    method: 'put',
    data
  });
}
