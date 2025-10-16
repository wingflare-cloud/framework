package com.wingflare.lib.http.client.okhttp;


import com.wingflare.api.http.HttpHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class OkHttpHeader implements HttpHeader {

    // 存储「规范化头部名称 → 值列表」的核心映射
    private final Map<String, List<String>> headerMap = new HashMap<>();

    @Override
    public OkHttpHeader addHeader(String name, String value) {
        // 校验参数非空
        Objects.requireNonNull(name, "Header name must not be null");
        Objects.requireNonNull(value, "Header value must not be null");

        // 规范化名称并更新映射表
        String canonicalName = canonicalizeHeaderName(name);

        // 追加值到列表（不存在则创建新列表）
        headerMap.computeIfAbsent(canonicalName, k -> new ArrayList<>())
                .add(value);

        return this;
    }

    @Override
    public OkHttpHeader setHeader(String name, String value) {
        Objects.requireNonNull(name, "Header name must not be null");
        Objects.requireNonNull(value, "Header value must not be null");

        String canonicalName = canonicalizeHeaderName(name);
        // 覆盖原有值，创建新列表存储单个值
        List<String> values = new ArrayList<>();
        values.add(value);
        headerMap.put(canonicalName, values);

        return this;
    }

    @Override
    public OkHttpHeader setHeader(String name, List<String> values) {
        Objects.requireNonNull(name, "Header name must not be null");
        Objects.requireNonNull(values, "Header values must not be null");

        String canonicalName = canonicalizeHeaderName(name);

        // 存储值列表的副本，防止外部修改影响内部状态
        List<String> valueCopy = new ArrayList<>(values);
        headerMap.put(canonicalName, valueCopy);

        return this;
    }

    @Override
    public String getFirstHeader(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        String canonicalName = canonicalizeHeaderName(name);
        List<String> values = headerMap.get(canonicalName);
        return values.isEmpty() ? null : values.getFirst();
    }

    @Override
    public List<String> getHeaders(String name) {
        if (name == null || name.isBlank()) {
            return Collections.emptyList();
        }
        String canonicalName = canonicalizeHeaderName(name);
        return Collections.unmodifiableList(headerMap.get(canonicalName));
    }

    @Override
    public String getHeader(String name) {
        List<String> values = getHeaders(name);
        return values.isEmpty() ? null : String.join(",", values);
    }

    @Override
    public OkHttpHeader removeHeader(String name) {
        if (name == null || name.isBlank()) {
            return this;
        }
        String canonicalName = canonicalizeHeaderName(name);
        headerMap.remove(canonicalName);
        return this;
    }

    @Override
    public boolean containsHeader(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }

        String canonicalName = canonicalizeHeaderName(name);
        return headerMap.containsKey(canonicalName);
    }

    @Override
    public Set<String> getHeaderNames() {
        // 返回不可修改集合，避免外部删除名称
        return Collections.unmodifiableSet(headerMap.keySet());
    }

    @Override
    public int size() {
        return headerMap.size();
    }

    @Override
    public OkHttpHeader clear() {
        headerMap.clear();
        return this;
    }

    @Override
    public Iterator<HeaderEntry> iterator() {
        return new HeaderEntryIterator(headerMap.entrySet().iterator());
    }

    /**
     * 头部迭代器具体实现
     * 支持遍历HeaderEntry、安全删除（同步更新小写映射表）
     */
    private static class HeaderEntryIterator implements Iterator<HeaderEntry> {

        private final Iterator<Map.Entry<String, List<String>>> entryIterator;
        private Map.Entry<String, List<String>> currentEntry;

        public HeaderEntryIterator(Iterator<Map.Entry<String, List<String>>> entryIterator) {
            this.entryIterator = entryIterator;
        }

        @Override
        public boolean hasNext() {
            return entryIterator.hasNext();
        }

        @Override
        public HeaderEntry next() {
            currentEntry = entryIterator.next();
            // 返回匿名实现的HeaderEntry，确保数据不可修改
            return new HeaderEntry() {
                @Override
                public String getName() {
                    return currentEntry.getKey(); // 直接返回规范化名称
                }

                @Override
                public List<String> getValues() {
                    return Collections.unmodifiableList(currentEntry.getValue());
                }
            };
        }

        @Override
        public void remove() {
            if (currentEntry == null) {
                throw new IllegalStateException("next() must be called before remove()");
            }

            entryIterator.remove();
            currentEntry = null;
        }
    }

    /**
     * 将任意格式的头部名称规范化为「首字母大写、连字符分隔」格式
     * 示例：content-type → Content-Type；USER-AGENT → User-Agent；accept → Accept
     * @param name 原始头部名称
     * @return 规范化后的头部名称
     */
    private String canonicalizeHeaderName(String name) {
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            return trimmedName;
        }

        // 按连字符分割单词，每个单词首字母大写、其余小写，再拼接
        return Arrays.stream(trimmedName.split("-"))
                .map(this::capitalizeSingleWord)
                .collect(Collectors.joining("-"));
    }

    /**
     * 将单个单词规范化为「首字母大写、其余小写」格式
     * 示例：type → Type；AGENT → Agent；empty → empty
     * @param word 原始单词
     * @return 规范化后的单词
     */
    private String capitalizeSingleWord(String word) {
        if (word.isEmpty()) {
            return word;
        }
        if (word.length() == 1) {
            return word.toUpperCase();
        }

        return Character.toUpperCase(word.charAt(0))
                + word.substring(1).toLowerCase();
    }

}