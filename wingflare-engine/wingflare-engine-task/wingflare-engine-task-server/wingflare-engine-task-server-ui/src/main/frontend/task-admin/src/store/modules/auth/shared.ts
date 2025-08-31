import { localStg } from '@/utils/storage';

/** Get token */
export function getToken() {
  return localStg.get('token') || '';
}

/** Get user info */
export function getUserInfo() {
  const emptyInfo: Api.Auth.UserInfo = {
    id: '',
    userId: '',
    username: '',
    userName: '',
    mode: '',
    role: 1,
    roles: [],
    buttons: [],
    namespaceIds: [],
    deleteAlert: {
      'job-task': true,
      'retry-scene': true,
      'workflow-task': true
    }
  };
  const userInfo = localStg.get('userInfo') || emptyInfo;

  // fix new property: buttons, this will be removed in the next version `1.1.0`
  if (!userInfo.buttons) {
    userInfo.buttons = [];
  }

  return userInfo;
}

/** Clear auth storage */
export function clearAuthStorage() {
  localStg.remove('token');
  // localStg.remove('refreshToken');
  localStg.remove('namespaceId');
  localStg.remove('userInfo');
  localStg.remove('deleteAlert');
}
