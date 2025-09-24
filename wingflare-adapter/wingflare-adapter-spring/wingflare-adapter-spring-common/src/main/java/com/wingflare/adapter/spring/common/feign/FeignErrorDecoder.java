package com.wingflare.adapter.spring.common.feign;


import com.alibaba.fastjson2.JSON;
import com.wingflare.adapter.spring.common.utils.ApiHelperUtil;
import com.wingflare.api.core.R;
import com.wingflare.api.core.Std;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.StringUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * @ClassName FeignErrorDecoder
 * @Author naizui_ycx
 * @Date 2023/02/23 12
 * @Description feign错误解码器
 */
public class FeignErrorDecoder extends ErrorDecoder.Default {

    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String s, Response response) {
        if (Std.ERR_STATUS_CODE == response.status() || Std.PARAM_ERR_STATUS_CODE == response.status()
                || Std.FORBIDDEN_STATUS_CODE == response.status()) {
            if (ApiHelperUtil.checkRequestAutoThrowErr()) {
                try {
                    if (response.body() != null) {
                        String body = Util.toString(response.body().asReader(Charset.defaultCharset()));

                        if (StringUtil.isNotBlank(body)) {
                            R<?> r = JSON.parseObject(body, R.class);
                            if (r.getRet() != null && r.getRet() != R.RET_NO_ERR) {
                                throw new BusinessLogicException(r.getMsg(), r.getData());
                            }
                        } else {
                            throw new BusinessLogicException(response.reason());
                        }
                    }
                } catch (IOException throwable) {
                    logger.warn("读取response异常", e(Map.of(
                            "message", throwable.getMessage(),
                            "exception", throwable.getClass().getName()
                    )));
                }
            }
        }

        return super.decode(s, response);
    }

}
