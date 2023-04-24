package com.wingflare.lib.spring;


import com.wingflare.lib.spring.utils.ApiHelperUtil;
import com.wingflare.lib.standard.Ordered;
import com.wingflare.lib.standard.R;

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
