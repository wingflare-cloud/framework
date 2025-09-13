package com.wingflare.lib.core.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName SerializationUtil
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 序列化工具来源于spring
 */
public abstract class SerializationUtil {

    private static final Pattern pattern = Pattern.compile("@S\\[(.*?)]");

    /**
     * Serialize the given object to a byte array.
     *
     * @param object the object to serialize
     * @return an array of bytes representing the object in a portable fashion
     */
    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            oos.flush();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), ex);
        }
        return baos.toByteArray();
    }

    /**
     * Deserialize the byte array into an object.
     *
     * @param bytes a serialized object
     * @return the result of deserializing the bytes
     * @deprecated This utility uses Java Object Serialization, which allows
     * arbitrary code to be run and is known for being the source of many Remote
     * Code Execution (RCE) vulnerabilities.
     * <p>Prefer the use of an external tool (that serializes to JSON, XML, or
     * any other format) which is regularly checked and updated for not allowing RCE.
     */
    @Deprecated
    public static Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return ois.readObject();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Failed to deserialize object", ex);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Failed to deserialize object type", ex);
        }
    }

    /**
     * Clone the given object using Java Object Serialization.
     *
     * @param object the object to clone
     * @param <T>    the type of the object to clone
     * @return a clone (deep-copy) of the given object
     * @since 6.0
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T object) {
        return (T) SerializationUtil.deserialize(SerializationUtil.serialize(object));
    }

    /**
     * 带类型信息的数据编码
     *
     * @param value
     *
     * @return
     */
    public static String typeValueEncode(Object value) {
        return String.format(
                "@S[%s]",
                Base64.encodeBase64URLSafeString(
                        SerializationUtil.serialize(value)
                )
        );
    }

    /**
     * 匹配带类型的值
     *
     * @param s
     * @return
     */
    public static Matcher typeValueMatch(String s) {
        return pattern.matcher(s);
    }

    /**
     * 带类型值字符串解码
     *
     * @param s
     * @param <T>
     *
     * @return
     */
    public static <T> T typeValueDecode(String s) {
        return ObjectUtil.cast(SerializationUtil.deserialize(Base64.decodeBase64(s)));
    }

}
