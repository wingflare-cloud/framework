package com.wingflare.adapter.spring.http;


import com.wingflare.api.core.Charset;
import com.wingflare.api.core.MimeType;
import com.wingflare.api.http.HttpCookie;
import com.wingflare.api.http.HttpHeader;
import com.wingflare.api.http.HttpResponse;
import com.wingflare.api.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;


public class SpringHttpResponse implements HttpResponse {

    private final ResponseEntity<String> responseEntity;
    private final HttpHeader header;
    private final HttpCookie cookie;

    public SpringHttpResponse(ResponseEntity<String> responseEntity) {
        this.responseEntity = responseEntity;
        this.header = new SpringHttpHeader();
        responseEntity.getHeaders().forEach((k, v) ->
                v.forEach(val -> this.header.addHeader(k, val)));
        this.cookie = new SpringHttpCookie("");
    }

    @Override
    public boolean isStream() {
        return false;
    }

    @Override
    public boolean isText() {
        if (responseEntity.getHeaders().getContentType() == null) {
            return false;
        }

        String contentType = responseEntity.getHeaders().getContentType().toString();

        return contentType.contains("text/") || responseEntity.getHeaders().getContentType().getSubtype().contains("json")
                || responseEntity.getHeaders().getContentType().getSubtype().contains("xml");
    }

    @Override
    public boolean isOk() {
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

    @Override
    public Charset getCharset() {
        if (responseEntity.getHeaders().getContentType() == null) {
            return null;
        }

        if (responseEntity.getHeaders().getContentType().getCharset() == null) {
            return null;
        }

        return Charset.fromCharsetName(responseEntity.getHeaders().getContentType().getCharset().name());
    }

    @Override
    public MimeType getContentType() {
        if (responseEntity.getHeaders().getContentType() == null) {
            return null;
        }

        return MimeType.getByMimeType(String.format("%s/%s", responseEntity.getHeaders().getContentType().getType(),
                responseEntity.getHeaders().getContentType().getSubtype()));
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.getByCode(responseEntity.getStatusCode().value());
    }

    @Override
    public HttpHeader getHeader() {
        return header;
    }

    @Override
    public String getBody() {
        return responseEntity.getBody();
    }

    @Override
    public <T> T getBody(Class<T> clz) {
        // 实际实现需要添加JSON转换逻辑
        return null;
    }

    @Override
    public HttpCookie getCookie() {
        return cookie;
    }

    @Override
    public OutputStream getStream() {
        return new ByteArrayOutputStream();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isStreamingSupported() {
        return false;
    }

}
