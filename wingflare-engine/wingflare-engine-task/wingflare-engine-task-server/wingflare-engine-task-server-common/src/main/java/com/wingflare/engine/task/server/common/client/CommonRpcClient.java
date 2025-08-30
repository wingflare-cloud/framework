package com.wingflare.engine.task.server.common.client;

import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.server.common.dto.PullRemoteNodeClientRegisterInfoDTO;
import com.wingflare.engine.task.server.common.dto.UpdateClientInfoDTO;
import com.wingflare.engine.task.server.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.server.common.rpc.client.annotation.Body;
import com.wingflare.engine.task.server.common.rpc.client.annotation.Mapping;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.*;

/**
 * 调用客户端接口
 *
 * @author: opensnail
 * @date : 2023-06-19 15:40
 * @since sj_1.0.0
 */
public interface CommonRpcClient {

    @Mapping(path = SYNC_CONFIG, method = RequestMethod.POST)
    Result syncConfig(@Body ConfigRequest configRequest);

    @Mapping(path = GET_REG_NODES_AND_REFRESH, method = RequestMethod.POST)
    Result<String> pullRemoteNodeClientRegisterInfo(@Body PullRemoteNodeClientRegisterInfoDTO registerInfo);

    @Mapping(path = UPDATE_CLIENT_INFO, method = RequestMethod.POST)
    Result<Boolean> updateClientInfo(@Body UpdateClientInfoDTO clientInfoDTO);
}
