import { request } from '../request';

/** Version */
export function fetchVersion() {
  return request<string>({
    url: '/system/version',
    method: 'get'
  });
}
