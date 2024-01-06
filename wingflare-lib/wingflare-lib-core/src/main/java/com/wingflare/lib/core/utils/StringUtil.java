package com.wingflare.lib.core.utils;

import com.wingflare.lib.core.AntPathMatcher;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 字符串工具类
 */
public class StringUtil extends StringUtils {

    private final static String[] UP_LETTER = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    private static final String[] EMPTY_STRING_ARRAY = {};

    private static Pattern camelPattern = Pattern.compile("[A-Z]");

    /**
     * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return str == null || EMPTY.equals(str.trim());
    }

    /**
     * 解析一个查询字符串
     *
     * @return
     */
    public static Map<String, String[]> parseQueryStr(String queryString) throws UnsupportedEncodingException {
        Map<String, String[]> params = new HashMap<>();
        if (queryString == null || queryString.isEmpty()) {
            return params;
        }

        String decodedQueryString = URLDecoder.decode(queryString, "UTF-8");
        String[] keyValues = decodedQueryString.split("&");

        for (String keyValue : keyValues) {
            String[] pair = keyValue.split("=", 2);
            String key = pair[0];
            String value = pair.length > 1 ? pair[1] : "";
            String[] values = params.computeIfAbsent(key, k -> new String[0]);
            values = Arrays.copyOf(values, values.length + 1);
            values[values.length - 1] = value;
            params.put(key, values);
        }

        return params;
    }

    public static Map<String, String> parseQueryStrLast(String queryString) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        if (queryString == null || queryString.isEmpty()) {
            return params;
        }

        String decodedQueryString = URLDecoder.decode(queryString, "UTF-8");
        String[] keyValues = decodedQueryString.split("&");

        for (String keyValue : keyValues) {
            String[] pair = keyValue.split("=", 2);
            String key = pair[0];
            String value = pair.length > 1 ? pair[1] : "";
            params.put(key, value);
        }

        return params;
    }

    public static Map<String, String> parseQueryStrFirst(String queryString) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        if (queryString == null || queryString.isEmpty()) {
            return params;
        }

        String decodedQueryString = URLDecoder.decode(queryString, "UTF-8");
        String[] keyValues = decodedQueryString.split("&");

        for (String keyValue : keyValues) {
            String[] pair = keyValue.split("=", 2);
            String key = pair[0];

            if (!params.containsKey(key)) {
                String value = pair.length > 1 ? pair[1] : "";
                params.put(key, value);
            }
        }

        return params;
    }

    /**
     * 判断一个字符串是否为合法http(s)链接
     *
     * @param urlString
     * @return
     */
    public static boolean isUrl(String urlString) {

        URI uri = null;

        try {
            uri = new URI(urlString);
        } catch (URISyntaxException e) {
            return false;
        }

        if (uri.getHost() == null) {
            return false;
        }

        if (uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https")) {
            return true;
        }

        return false;
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 链接多个字符串
     *
     * @param ss
     * @return
     */
    public static String commaAll(String... ss) {
        return join(ss, ",");
    }

    /**
     * 连接多个字符串
     *
     * @param ss
     * @return
     */
    public static String commaAll(List<String> ss) {
        return join(ss, ",");
    }

    /**
     * 判断字符串列表中是否存在非空字符串
     *
     * @param str String
     * @return true：存在 false：不存在
     * @author shaoyuyao
     */
    public static boolean isExistNotEmpty(String... str) {
        for (String s : str) {
            if (isNotEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为空，并且不是空白字符
     *
     * @param str 要判断的value
     * @return 结果
     */
    public static boolean hasText(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 按长度分割字符串
     *
     * @param text
     * @param n
     * @return
     */
    public static List<String> splitForLen(String text, int n) {
        List<String> results = new ArrayList<>();
        int length = text.length();

        for (int i = 0; i < length; i += n) {
            results.add(text.substring(i, Math.min(length, i + n)));
        }

        return results;
    }

    /**
     * 驼峰转蛇形
     *
     * @param str
     * @return
     */
    public static String camelToSnakeCase(String str) {
        return camelToSnakeCase(str, SEPARATOR);
    }

    public static String camelToSnakeCase(String str, char c) {
        Matcher matcher = camelPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, c + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String convertToCamelCase(String str) {
        return convertToCamelCase(str, SEPARATOR);
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param str 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String str, char c) {
        StringBuilder result = new StringBuilder();
        String separator = ConvertUtil.toStr(c);

        // 快速检查
        if (str == null || str.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!str.contains(separator)) {
            // 不含下划线，仅将首字母大写
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = str.split(separator);
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }

        return result.toString();
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String s) {
        return toCamelCase(s, SEPARATOR);
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String s, char cs) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == cs) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Tokenize the given {@code String} into a {@code String} array via a
     * {@link StringTokenizer}.
     * <p>The given {@code delimiters} string can consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters.
     *
     * @param str               the {@code String} to tokenize (potentially {@code null} or empty)
     * @param delimiters        the delimiter characters, assembled as a {@code String}
     *                          (each of the characters is individually considered as a delimiter)
     * @param trimTokens        trim the tokens via {@link String#trim()}
     * @param ignoreEmptyTokens omit empty tokens from the result array
     *                          (only applies to tokens that are empty after trimming; StringTokenizer
     *                          will not consider subsequent delimiters as token in the first place).
     * @return an array of the tokens
     * @see StringTokenizer
     * @see String#trim()
     */
    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

        if (str == null) {
            return EMPTY_STRING_ARRAY;
        }

        StringTokenizer st = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }

        return toStringArray(tokens);
    }

    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    //---------------------------------------------------------------------
    // Convenience methods for working with String arrays
    //---------------------------------------------------------------------

    /**
     * Copy the given {@link Collection} into a {@code String} array.
     * <p>The {@code Collection} must contain {@code String} elements only.
     *
     * @param collection the {@code Collection} to copy
     *                   (potentially {@code null} or empty)
     * @return the resulting {@code String} array
     */
    public static String[] toStringArray(Collection<String> collection) {
        return (!CollectionUtil.isEmpty(collection) ? collection.toArray(EMPTY_STRING_ARRAY) : EMPTY_STRING_ARRAY);
    }

    public static boolean hasLength(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * Copy the given {@link Enumeration} into a {@code String} array.
     * <p>The {@code Enumeration} must contain {@code String} elements only.
     *
     * @param enumeration the {@code Enumeration} to copy
     *                    (potentially {@code null} or empty)
     * @return the resulting {@code String} array
     */
    public static String[] toStringArray(Enumeration<String> enumeration) {
        return (enumeration != null ? toStringArray(Collections.list(enumeration)) : EMPTY_STRING_ARRAY);
    }

    /**
     * 按长度分割字符串
     *
     * @param string
     * @param len
     * @return
     */
    public static List<String> lengthSplit(String string, int len) {
        String regex = "(?<=\\G.{" + len + "})";
        return Arrays.asList(string.split(regex));
    }

    /**
     * 补0流水号
     *
     * @param str       需要补0的字符串
     * @param strLength 总共几位数
     * @return String 补完之后的流水号
     */
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                // 左补0
                sb.append("0").append(str);
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * 根据下标获取大写字母
     *
     * @param index
     * @return String
     */
    public static String getUpLetter(int index) {
        if (index < 0) {
            return StringUtil.EMPTY;
        }
        return UP_LETTER[index];
    }

    /**
     * 获取大写字母的差集
     *
     * @param letter 大写字母数组
     * @return String[]
     */
    public static String[] getUpLetterMinus(String[] letter) {
        return minus(UP_LETTER, letter);
    }

    /**
     * 获取两个字符数组的差集
     *
     * @param arr1
     * @param arr2
     * @return String[]
     */
    public static String[] minus(String[] arr1, String[] arr2) {
        // 将较长的数组转换为set
        Set<String> set = new HashSet<>(Arrays.asList(arr1.length > arr2.length ? arr1 : arr2));

        // 遍历较短的数组，实现最少循环
        for (String i : arr1.length > arr2.length ? arr2 : arr1) {
            // 如果集合里有相同的就删掉，如果没有就将值添加到集合
            if (set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }

        String[] arr = {};
        return set.toArray(arr);
    }

    /**
     * List -> String，带单引号，例如[a,b,c] -> 'a','b','c'
     * 备注：要想实现[a,b,c] -> a,b,c，直接调用String.join(",",list)方法即可
     */
    public static String listToString(Iterable<? extends CharSequence> elements) {
        StringJoiner joiner = new StringJoiner(",");
        for (CharSequence cs : elements) {
            joiner.add("'" + cs + "'");
        }
        return joiner.toString();
    }

    /**
     * 内容编码
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return StringUtil.EMPTY;
        }
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return StringUtil.EMPTY;
        }
    }

    /**
     * url批量匹配
     *
     * @param str
     * @param strs
     * @return
     */
    public static boolean urlMatches(String str, List<String> strs) {
        if (isEmpty(str) || strs == null || strs.isEmpty()) {
            return false;
        }
        for (String pattern : strs) {
            if (isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    public static String httpBuildQuery(Map<String, Object> map, boolean sort) throws UnsupportedEncodingException {
        String reString = "";
        reString = paramBuild(map, "", true, sort);
        reString = URLEncoder.encode(reString, "utf-8");
        reString = reString.replace("%3D", "=")
                .replace("%26", "&");
        reString = removeEnd(reString, "&");
        return reString;
    }

    private static String paramBuild(Object object, String parentStr, boolean first, boolean sort) {
        StringBuilder sb = new StringBuilder();
        if (object instanceof Map) {
            List<Map.Entry<String, Object>> list = new ArrayList<>(((Map<String, Object>) object).entrySet());
            //按照map的key排序
            if (sort) {
                //升序排序
                list.sort(Map.Entry.comparingByKey());
            }

            for (Map.Entry<String, Object> mapping : list) {
                String key = mapping.getKey();
                Object value = mapping.getValue();
                if (first) {
                    sb.append(paramBuild(value, key, false, sort));
                } else {
                    sb.append(paramBuild(value, parentStr + "[" + key + "]", false, sort));
                }

            }

        } else if (object.getClass().isArray()) {
            int length = Array.getLength(object);

            for (int i = 0; i < length; i++) {
                sb.append(paramBuild(Array.get(object, i), parentStr + "[" + i + "]", false, sort));
            }
        } else if (object instanceof List) {
            for (int i = 0; i < ((List) object).size(); i++) {
                sb.append(paramBuild(((List) object).get(i), parentStr + "[" + i + "]", false, sort));
            }
        } else if (object instanceof String || object instanceof Number) {
            sb.append(parentStr)
                    .append("=")
                    .append(object)
                    .append("&");
        }

        return sb.toString();
    }

}
