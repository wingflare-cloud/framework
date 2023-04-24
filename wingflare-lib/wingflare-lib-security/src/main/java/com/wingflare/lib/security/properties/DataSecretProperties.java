package com.wingflare.lib.security.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 数据加密配置
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Configuration
@ConfigurationProperties(prefix = "wf.datasecret")
public class DataSecretProperties {

    private String aesKey = "0123456abcdefghi";

    private String desKey = "0123456abcdefghi";

    private String rsaPrivateKeyBase64 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALWco8XXxqFJDG/p\n" +
            "HU+9EW1XzBzGq3Ck8bsD2BKkMAA0dQsYC2tp1oPlXyh7dRDcWOEZsp5+STEAY8ln\n" +
            "iQl1C+08LGc5i8Yngo4B+OOAbCIZgsi7OdWu5NSiEbfwZf/3jHhtEWWA8d/HV6IL\n" +
            "kxhvdOdSvhYNDznhkvdjN54ODD39AgMBAAECgYEAr23x3UiDvIRdmTSrdb0CuoaW\n" +
            "yi02qgcK4kXiUI99NpV1ghPl5ApGhv3oH6ix+ml/CWHTYrpGZSklOwXiH9qKeyom\n" +
            "2GPI2FiaFhWD1epeH9M1xtwv4A7NFF0VGBHgF494PQtvmRKiBa71sIk7FtrGF0BS\n" +
            "JX7WFRjaqbV9ytVBfoECQQDwNnGdSnZutdWasMoM/y5Hs+h+Cq5dlb5bxMvNr39B\n" +
            "wlHrCKWS7IPHChW7uFYL11MSXyHfDBCWYEru60LhwWSNAkEAwYw+LcOYfTlTyTU1\n" +
            "d0JOISh8xL06r1cMdqzao1KngCDpx1nshkBVZ95T/EcaEYWEoecmxb1EhXA+N+KZ\n" +
            "nSm7MQJBAO7ndv/Uuw1I03TSN2HNuZ2elLAmt1xkPbrPc1LSJGKmfQqgwuvpRvaC\n" +
            "VTMqFooYUYqICwvm1+h3RI9ydEZLLVECQF07QgRncL/5amvEzsFo1hvQ1fErOeaW\n" +
            "ZvGPqC3+NaNZt2MI5Q9yU/GZvhkK4vrHtzEKGLotxqEEhWQEKnzzSjECQD3UoHxn\n" +
            "HQzWZNM61bDjx8ebRRWeoWEN3S2414nfno8y55aoIbM6jHzNJiPxaDbdDue8TH8I\n" +
            "UV7TZWvDva7imwg=";

    private String rsaPublicKeyBase64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1nKPF18ahSQxv6R1PvRFtV8wc\n" +
            "xqtwpPG7A9gSpDAANHULGAtradaD5V8oe3UQ3FjhGbKefkkxAGPJZ4kJdQvtPCxn\n" +
            "OYvGJ4KOAfjjgGwiGYLIuznVruTUohG38GX/94x4bRFlgPHfx1eiC5MYb3TnUr4W\n" +
            "DQ854ZL3YzeeDgw9/QIDAQAB";

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public String getRsaPrivateKeyBase64() {
        return rsaPrivateKeyBase64;
    }

    public void setRsaPrivateKeyBase64(String rsaPrivateKeyBase64) {
        this.rsaPrivateKeyBase64 = rsaPrivateKeyBase64;
    }

    public String getRsaPublicKeyBase64() {
        return rsaPublicKeyBase64;
    }

    public void setRsaPublicKeyBase64(String rsaPublicKeyBase64) {
        this.rsaPublicKeyBase64 = rsaPublicKeyBase64;
    }
}
