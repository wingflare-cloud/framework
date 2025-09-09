package com.wingflare.adapter.spring.common.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @ClassName StringOrMappingJackson2HttpMessageConverter
 * @Author naizui_ycx
 * @Date 2023/02/02 02
 * @Description string返回结果 json兼容
 */
public class StringOrMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final StringHttpMessageConverter converter = new StringHttpMessageConverter();

    public StringOrMappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        boolean canWrite = super.canWrite(clazz, mediaType);

        if (!canWrite) {
            canWrite = clazz.isAssignableFrom(String.class);
        }

        return canWrite;
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        if (object instanceof String) {
            outputMessage.getHeaders().setContentType(MediaType.TEXT_PLAIN);
            converter.write((String)object, MediaType.TEXT_PLAIN, outputMessage);
            return;
        }

        outputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        super.writeInternal(object, object != null ? object.getClass() : null, outputMessage);
    }

}