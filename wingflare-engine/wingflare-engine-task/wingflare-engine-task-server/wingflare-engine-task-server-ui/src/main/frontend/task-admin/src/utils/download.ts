import { localStg } from '@/utils/storage';
import { getServiceBaseURL } from '@/utils/service';
import { $t } from '@/locales';

const isHttpProxy = import.meta.env.DEV && import.meta.env.VITE_HTTP_PROXY === 'Y';
const { baseURL } = getServiceBaseURL(import.meta.env, isHttpProxy);

export function downloadJson(data: Blob, fileName: string, withRandomName = true) {
  try {
    let realFileName = fileName;
    if (withRandomName) {
      realFileName = `${fileName}-${new Date().getTime()}.json`;
    }
    downloadByData(data, realFileName);
  } catch {
    window.$message?.error($t('common.downloadFail'));
  } finally {
    window.$loading?.endLoading();
  }
}

export function downloadByData(data: BlobPart, filename: string) {
  const blobData = [data];
  const blob = new Blob(blobData, { type: 'application/octet-stream' });

  const blobURL = window.URL.createObjectURL(blob);
  const tempLink = document.createElement('a');
  tempLink.style.display = 'none';
  tempLink.href = blobURL;
  tempLink.setAttribute('download', filename);
  if (typeof tempLink.download === 'undefined') {
    tempLink.setAttribute('target', '_blank');
  }
  document.body.appendChild(tempLink);
  tempLink.click();
  document.body.removeChild(tempLink);
  window.URL.revokeObjectURL(blobURL);
}

export const downloadFetch = (url: string, body: any, fileName: string) => {
  window.$loading?.startLoading();
  const token = localStg.get('token')!;
  const namespaceId = localStg.get('namespaceId')!;
  fetch(`${baseURL}${url}?t=${new Date().getTime()}`, {
    method: 'post',
    body: JSON.stringify(body),
    headers: {
      'SNAIL-JOB-AUTH': token,
      'SNAIL-JOB-NAMESPACE-ID': namespaceId,
      'Content-Type': 'application/json;charset=utf-8;'
    }
  })
    .then(async response => response.blob())
    .then(data => downloadJson(data, fileName))
    .catch(() => window.$message?.error($t('common.downloadFail')))
    .finally(() => window.$loading?.endLoading());
};
