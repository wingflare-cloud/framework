/** socket 通信 */
import { getServiceBaseURL } from '@/utils/service';
import { localStg } from './storage';
import { generateRandomString } from './common';

/**
 * 初始化 websocket
 *
 * @param scene - 场景
 * @param sid - 会话 id
 * @returns
 */
export function initWebSocketUrl(scene: string, sid?: string) {
  const protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
  const isHttpProxy = import.meta.env.DEV && import.meta.env.VITE_HTTP_PROXY === 'Y';
  const { baseURL } = getServiceBaseURL(import.meta.env, isHttpProxy);
  const url = isHttpProxy ? import.meta.env.VITE_SERVICE_BASE_URL : protocol + window.location.host + baseURL;
  const token = localStg.get('token');
  if (!token) {
    return null;
  }
  return `${url}/websocket?Snail-Job-Auth=${token}&sid=${sid ?? generateRandomString(32)}&scene=${scene}`;
}
