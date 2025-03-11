package com.wingflare.lib.captcha.service;


import com.wingflare.abstraction.lib.captcha.CaptchaGenerateServer;
import com.wingflare.abstraction.lib.captcha.CaptchaServerFactory;
import com.wingflare.abstraction.lib.captcha.bo.ClientInfo;
import com.wingflare.abstraction.lib.captcha.dto.CaptchaDto;
import com.wingflare.lib.captcha.CaptchaConfig;
import com.wingflare.lib.captcha.properties.CaptchaProperties;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.exceptions.ServerInternalException;
import com.wingflare.lib.core.utils.DateUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.standard.CacheService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import jakarta.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码服务
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Component
public class CaptchaServer {

    @Resource
    private CacheService cacheService;

    @Resource
    private CaptchaProperties captchaConfigure;

    private final AntPathMatcher matcher = new AntPathMatcher();

    private Boolean isInit = false;

    private final Map<String, Integer> captchaUrlPatternsCache = new HashMap<>();

    private final Map<String, Integer> captchaNameCache = new HashMap<>();

    private final Map<Integer, CaptchaServerFactory> captchaFactoryMap = new HashMap<>();

    private final Map<Integer, CaptchaConfig> captchaConfigMap = new HashMap<>();

    private final Map<Integer, CaptchaGenerateServer> captchaServerMap = new HashMap<>();

    /**
     * 生成并保存验证码值
     *
     * @param index
     * @param info
     * @return
     */
    public CaptchaDto<?> generateAndSave(Integer index, ClientInfo info) {
        init();
        CaptchaConfig config = getCaptchaConfig(index);
        info.setCaptchaType(config.getCaptchaType());
        String markString = getMark(info);
        String key = getKey(info);
        CaptchaDto<?> captchaDto = generate(index);

        cacheService.setCacheObject(key, String.format("sha1=%s&md5=%s&value=%s",
                        DigestUtils.sha1Hex(markString), DigestUtils.md5Hex(markString), captchaDto.getValue()),
                config.getTimeout(), config.getTimeUnit());

        return captchaDto;
    }

    /**
     * 验证验证码值是否正确
     *
     * @param index
     * @param info
     * @param val
     * @param isDelete
     * @return
     */
    public boolean check(Integer index, ClientInfo info, String val, boolean isDelete) {
        init();
        return getCaptchaServer(index).compare(get(info, isDelete), val);
    }


    /**
     * 获取验证码类型
     *
     * @param index
     * @return
     */
    public String getType(Integer index) {
        init();

        CaptchaConfig config = getCaptchaConfig(index);

        if (config != null) {
            return config.getCaptchaType();
        }

        return null;
    }

    /**
     * 获取验证码索引
     *
     * @param path
     * @return
     */
    public Integer captchaIndexOf(String path) {
        init();
        for (Map.Entry<String, Integer> urlIndex : captchaUrlPatternsCache.entrySet()) {
            if (matcher.match(urlIndex.getKey(), path)) {
                return urlIndex.getValue();
            }
        }

        return null;
    }

    /**
     * 获取验证码索引
     *
     * @param name
     * @return
     */
    public Integer captchaNameOf(String name, String captchaId, String dateTime, String hash) {
        init();

        if (!DigestUtils.md5Hex(String.format("%s_%s_%s", name, captchaId, dateTime)).equals(hash)) {
            throw new BusinessLogicException("captcha.hash.abnormal");
        }

        Date genDate = DateUtil.parse(dateTime);
        Date now = new Date();

        if (!now.before(genDate)) {
            throw new BusinessLogicException("captcha.gen.timeOut");
        }

        return captchaNameCache.get(name);
    }


    private CaptchaDto generate(Integer index) {
        CaptchaGenerateServer captchaGenerateServer = getCaptchaServer(index);

        if (captchaGenerateServer == null) {
            throw new ServerInternalException("undefined captcha {}", index);
        }

        return captchaGenerateServer.generate();
    }

    private CaptchaGenerateServer getCaptchaServer(Integer index) {
        if (captchaServerMap.containsKey(index)) {
            return captchaServerMap.get(index);
        }

        synchronized (captchaServerMap) {
            if (!captchaServerMap.containsKey(index)) {
                if (captchaConfigMap.containsKey(index) && captchaFactoryMap.containsKey(index)) {
                    captchaServerMap.put(index, captchaFactoryMap.get(index)
                            .create(captchaConfigMap.get(index).getProperties()));
                } else {
                    captchaServerMap.put(index, null);
                }
            }
        }

        return captchaServerMap.get(index);
    }

    private CaptchaConfig getCaptchaConfig(Integer index) {
        return captchaConfigMap.get(index);
    }

    private void init() {
        if (!isInit) {
            synchronized (this) {
                if (!isInit) {
                    matcher.setCachePatterns(captchaConfigure.isEnablePatternsCache());
                    isInit = true;
                    Integer i = 0;

                    for (CaptchaConfig config : captchaConfigure.getConfigures()) {
                        for (String urlPattern : config.getUrlPatterns()) {
                            captchaUrlPatternsCache.put(urlPattern, i);
                        }

                        if (StringUtil.isNotEmpty(config.getName())) {
                            captchaNameCache.put(config.getName(), i);
                        }

                        captchaConfigMap.put(i, config);
                        captchaFactoryMap.put(i, CaptchaServiceFactoryFactory.getFactory(config.getCaptchaType()));
                        i++;
                    }
                }
            }
        }
    }

    private String get(ClientInfo info, boolean isDelete) {
        String markString = getMark(info);
        String val;

        if (isDelete) {
            val = cacheService.getDeleteCacheObject(getKey(info));
        } else {
            val = cacheService.getCacheObject(getKey(info));
        }

        try {
            String sha1 = StringUtil.parseQueryStrFirst(val).get("sha1");
            String md5 = StringUtil.parseQueryStrFirst(val).get("md5");

            if (!DigestUtils.sha1Hex(markString).equals(sha1) || !DigestUtils.md5Hex(markString).equals(md5)) {
                return "";
            }

            return StringUtil.parseQueryStrFirst(val).get("value");
        } catch (UnsupportedEncodingException e) {
            throw new ServerInternalException("captcha decode fail.", e);
        }
    }

    private String getMark(ClientInfo info) {
        return String.format("ip=%s&sys=%s&ua=%s&type=%s", info.getIpaddr(), info.getSystemCode(),
                info.getUserAgent(), info.getCaptchaType());
    }

    private String getKey(ClientInfo info) {
        return String.format("%s%s", captchaConfigure.getStoragePrefix(), info.getCaptchaId());
    }

}
