package com.wingflare.adapter.spring.common;


import com.wingflare.adapter.spring.common.utils.ApiHelperUtil;
import com.wingflare.api.core.Ordered;
import com.wingflare.api.core.R;

public interface ResponseConverter {

    Object convert(Object o);

    class Default implements ResponseConverter, Ordered {

        @Override
        public Object convert(Object o) {
            try {
                if (ApiHelperUtil.checkIsOriginalResp() || o instanceof R || ApiHelperUtil.checkInternalSource()) {
                    return o;
                }
                return R.ok(o);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int getOrder() {
            return MAX;
        }
    }

}