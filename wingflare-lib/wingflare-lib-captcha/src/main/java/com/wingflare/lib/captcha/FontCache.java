package com.wingflare.lib.captcha;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字体缓存工具类，使用LRU策略和弱引用管理字体缓存
 */
public final class FontCache {
    // 内置字体名称
    private static final String[] FONT_NAMES = {
            "actionj.ttf", "epilog.ttf", "fresnel.ttf", "headache.ttf", "lexo.ttf",
            "prefix.ttf", "progbot.ttf", "ransom.ttf", "robot.ttf", "scandal.ttf"
    };

    // 最大缓存字体数量
    private static final int MAX_CACHE_SIZE = 20;

    // 缓存存储：字体名称 -> 基础字体
    private static final Map<String, Font> baseFontCache = new ConcurrentHashMap<>();

    // 缓存存储：基础字体+大小 -> 派生字体（使用弱引用，内存不足时可回收）
    private static final Map<FontKey, Font> derivedFontCache = new WeakHashMap<>();

    private FontCache() {}

    /**
     * 获取指定索引的内置字体
     */
    public static Font getBuiltInFont(int index, int style, float size) {
        if (index < 0 || index >= FONT_NAMES.length) {
            throw new IllegalArgumentException("Invalid font index: " + index);
        }
        return getFont(FONT_NAMES[index], style, size);
    }

    /**
     * 获取指定名称的字体
     */
    public static Font getFont(String fontName, int style, float size) {
        // 先尝试从缓存获取基础字体
        Font baseFont = baseFontCache.get(fontName);

        // 缓存未命中则加载字体
        if (baseFont == null) {
            try (InputStream is = FontCache.class.getResourceAsStream("/" + fontName)) {
                if (is == null) {
                    throw new IllegalArgumentException("Font not found: " + fontName);
                }
                baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
                baseFontCache.put(fontName, baseFont);

                // 控制缓存大小，超过时清理
                if (baseFontCache.size() > MAX_CACHE_SIZE) {
                    clearOldestEntries();
                }
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException("Failed to load font: " + fontName, e);
            }
        }

        // 尝试从缓存获取派生字体
        FontKey key = new FontKey(baseFont, style, size);
        Font derivedFont = derivedFontCache.get(key);

        if (derivedFont == null) {
            derivedFont = baseFont.deriveFont(style, size);
            derivedFontCache.put(key, derivedFont);
        }

        return derivedFont;
    }

    /**
     * 清理最旧的缓存条目
     */
    private static void clearOldestEntries() {
        if (FontCache.baseFontCache.size() <= 10) return;

        // 对于ConcurrentHashMap，我们简单清除一半
        if (FontCache.baseFontCache instanceof ConcurrentHashMap<?, ?>) {
            int removeCount = FontCache.baseFontCache.size() - 10;
            FontCache.baseFontCache.keySet().stream()
                    .limit(removeCount)
                    .toList()
                    .forEach(FontCache.baseFontCache::remove);
        }
    }

    /**
     * 清除所有缓存
     */
    public static void clearCache() {
        baseFontCache.clear();
        derivedFontCache.clear();
    }

    /**
     * 字体缓存键，包含基础字体、样式和大小
     */
    private record FontKey(Font baseFont, int style, float size) {}

}
