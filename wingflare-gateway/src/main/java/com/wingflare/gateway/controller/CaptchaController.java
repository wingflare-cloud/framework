package com.wingflare.gateway.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.abstraction.lib.captcha.bo.ClientInfo;
import com.wingflare.abstraction.lib.captcha.dto.CaptchaDto;
import com.wingflare.gateway.dto.CaptchaBodyDto;
import com.wingflare.lib.captcha.service.CaptchaServer;
import com.wingflare.lib.captcha.support.CaptchaCtx;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.constants.HttpHeader;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.exceptions.ServerInternalException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.configure.properties.BusinessSystemProperties;
import com.wingflare.lib.spring.utils.SnowflakeUtil;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.R;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import jakarta.annotation.Resource;

/**
 * 验证码生成控制器
 */
@RestController
@RequestMapping("/captcha")
@ConditionalOnProperty(name = "security.captcha.enable", havingValue = "true")
public class CaptchaController {

    @Resource
    private CaptchaServer captchaServer;

    @Resource
    private SnowflakeUtil snowflakeUtil;

    @Resource
    private BusinessSystemProperties businessSystemProperties;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 获取验证码
     *
     * @return
     */
    @RequestMapping(value = {"/p/{path}", "/n/{name}"}, method = RequestMethod.GET)
    public Mono<ResponseEntity<String>> getCaptcha(@PathVariable("name") String name, @PathVariable("path") String path,
                                                   ServerWebExchange exchange)
            throws JsonProcessingException {
        Integer index = null;
        ServerHttpRequest request = exchange.getRequest();
        String systemCode = request.getHeaders().getFirst(Ctx.HEADER_KEY_BUSINESS_SYSTEM);
        String captchaId;

        if (!businessSystemProperties.getNames().contains(systemCode)) {
            throw new BusinessLogicException("unknown system.");
        }

        if (StringUtil.isNotEmpty(path)) {
            path = new String(Base64.decodeBase64(path));
            index = captchaServer.captchaIndexOf(path);
        }

        if (index == null && StringUtil.isNotEmpty(name)) {
            String dateTime = request.getHeaders().getFirst(CaptchaCtx.CAPTCHA_DATE_TIME_TRANSFER_KEY);
            String hash = request.getHeaders().getFirst(CaptchaCtx.CAPTCHA_HASH_TRANSFER_KEY);
            captchaId = request.getHeaders().getFirst(CaptchaCtx.CAPTCHA_ID_TRANSFER_KEY);
            index = captchaServer.captchaNameOf(name, captchaId, dateTime, hash);
        } else {
            captchaId = snowflakeUtil.nextStringId();
        }

        if (index == null) {
            throw new ServerInternalException("Illegal verification code request.");
        }

        ClientInfo info = Builder.of(ClientInfo::new)
                .with(ClientInfo::setIpaddr, request.getRemoteAddress().getAddress().toString())
                .with(ClientInfo::setUserAgent, request.getHeaders().getFirst(HttpHeader.REQUEST_USER_AGENT))
                .with(ClientInfo::setSystemCode, systemCode)
                .with(ClientInfo::setCaptchaId, captchaId)
                .build();

        CaptchaBodyDto captchaBodyDto = new CaptchaBodyDto();
        CaptchaDto captchaDto = captchaServer.generateAndSave(index, info);
        captchaBodyDto.setType(info.getCaptchaType());
        captchaBodyDto.setCaptchaId(info.getCaptchaId());
        captchaBodyDto.setBody(captchaDto.getBody());
        String response = objectMapper.writeValueAsString(R.ok(captchaBodyDto));

        return Mono.just(new ResponseEntity<>(response, HttpStatus.OK));
    }

}
