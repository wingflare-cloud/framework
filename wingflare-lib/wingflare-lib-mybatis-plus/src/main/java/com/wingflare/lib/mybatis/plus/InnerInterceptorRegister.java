package com.wingflare.lib.mybatis.plus;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;

import java.util.List;

public interface InnerInterceptorRegister {

    public List<InnerInterceptor> getInnerInterceptors();

}
