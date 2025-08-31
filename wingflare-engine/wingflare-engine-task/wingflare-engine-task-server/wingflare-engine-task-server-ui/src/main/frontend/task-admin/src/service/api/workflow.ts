import { parseContent } from '@/utils/common';
import { request } from '../request';

/** get workflow page list */
export function fetchGetWorkflowPageList(params?: Api.Workflow.WorkflowSearchParams) {
  return request<Api.Workflow.WorkflowList>({
    url: '/workflow/page/list',
    method: 'get',
    params
  });
}

/** trigger workflow */
export function fetchTriggerWorkflow(id: string) {
  return request({
    url: `/workflow/trigger/${id}`,
    method: 'post'
  });
}

/** trigger workflow */
export function fetchTriggerWorkflowParams(data: Api.Workflow.WorkflowTriggerParams) {
  return request({
    url: '/workflow/trigger',
    method: 'post',
    data
  });
}

/** get namespace list */
export function fetchGetWorkflowNameList(params?: Api.Workflow.WorkflowNameSearchParams) {
  return request<Api.Workflow.Workflow[]>({
    url: '/workflow/workflow-name/list',
    method: 'get',
    params
  });
}

/** get workflow batch list */
export function fetchGetWorkflowBatchList(params?: Api.WorkflowBatch.WorkflowBatchSearchParams) {
  return request<Api.WorkflowBatch.WorkflowBatchList>({
    url: '/workflow/batch/page/list',
    method: 'get',
    params
  });
}

export function fetchUpdateWorkflowStatus(data: Api.Workflow.WorkflowdUpdateJobStatusRequestVO) {
  return request({
    url: `/workflow/update/status`,
    method: 'put',
    data
  });
}

export function fetchBatchDeleteWorkflow(data: string[]) {
  return request({
    url: '/workflow/ids',
    method: 'delete',
    data
  });
}

export function fetchStopWorkflowBatch(id: string) {
  return request({
    url: `/workflow/batch/stop/${id}`,
    method: 'post'
  });
}

export function fetchWorkflowNodeRetry(id: string, workflowNodeId: string) {
  return request<null>({
    url: `/workflow/node/retry/${workflowNodeId}/${id}`,
    method: 'post'
  });
}

export function fetchCheckNodeExpression(expression: Workflow.BrachNodeType) {
  return request<{ key: number; value: string }>({
    url: '/workflow/check-node-expression',
    method: 'post',
    data: {
      ...expression,
      checkContent: JSON.stringify(parseContent(expression.checkContents))
    }
  });
}

export function fetchAddWorkflow(data: Workflow.NodeDataType) {
  return request<null>({
    url: `/workflow`,
    method: 'post',
    data
  });
}

export function fetchUpdateWorkflow(data: Workflow.NodeDataType) {
  return request<null>({
    url: `/workflow`,
    method: 'put',
    data
  });
}

export function fetchWorkflowInfo(id: string) {
  return request<Workflow.NodeDataType>({
    url: `/workflow/${id}`,
    method: 'get'
  });
}

export function fetchWorkflowBatchInfo(id: string) {
  return request<Workflow.NodeDataType>({
    url: `/workflow/batch/${id}`,
    method: 'get'
  });
}

export function fetchNodeRetry(nodeId: string, taskBatchId: string) {
  return request<null>({
    url: `/workflow/node/retry/${nodeId}/${taskBatchId}`,
    method: 'post'
  });
}

export function fetchNodeStop(nodeId: string, taskBatchId: string) {
  return request<null>({
    url: `/workflow/node/stop/${nodeId}/${taskBatchId}`,
    method: 'post'
  });
}

export function fetchDeleteWorkflowBatch(id: string) {
  return request({
    url: '/workflow/batch/ids',
    method: 'delete',
    data: [id]
  });
}

export function fetchBatchDeleteWorkflowBatch(data: string[]) {
  return request({
    url: '/workflow/batch/ids',
    method: 'delete',
    data
  });
}
