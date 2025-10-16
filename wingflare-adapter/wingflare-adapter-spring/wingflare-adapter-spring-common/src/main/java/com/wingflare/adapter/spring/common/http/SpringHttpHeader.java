package com.wingflare.adapter.spring.common.http;


import com.wingflare.api.http.HttpHeader;
import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Set;


public class SpringHttpHeader implements HttpHeader {

    private final HttpHeaders headers = new HttpHeaders();

    @Override
    public HttpHeader addHeader(String name, String value) {
        headers.add(name, value);
        return this;
    }

    @Override
    public HttpHeader setHeader(String name, String value) {
        headers.set(name, value);
        return this;
    }

    @Override
    public HttpHeader setHeader(String name, List<String> values) {
        headers.addAll(name, values);
        return this;
    }

    @Override
    public String getFirstHeader(String name) {
        return headers.getFirst(name);
    }

    @Override
    public List<String> getHeaders(String name) {
        return headers.get(name);
    }

    @Override
    public String getHeader(String name) {
        List<String> values = headers.get(name);
        return values != null && !values.isEmpty() ? String.join(",", values) : null;
    }

    @Override
    public HttpHeader removeHeader(String name) {
        headers.remove(name);
        return this;
    }

    @Override
    public boolean containsHeader(String name) {
        return headers.containsKey(name);
    }

    @Override
    public Set<String> getHeaderNames() {
        return headers.keySet();
    }

    @Override
    public int size() {
        return headers.size();
    }

    @Override
    public HttpHeader clear() {
        headers.clear();
        return this;
    }

    // 提供获取底层Spring HttpHeaders的方法
    public HttpHeaders getSpringHeaders() {
        return headers;
    }
}