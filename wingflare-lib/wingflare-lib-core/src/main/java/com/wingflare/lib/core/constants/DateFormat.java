package com.wingflare.lib.core.constants;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 日期格式常量文件
 */
public interface DateFormat {

    String PATTERN_GRACE = "yyyy/MM/dd HH:mm:ss";
    String PATTERN_GRACE_NORMAL = "yyyy/MM/dd HH:mm";
    String PATTERN_GRACE_SIMPLE = "yyyy/MM/dd";
    String yyMMdd = "yyMMdd";
    String PATTERN_YEARS_MONTH = "yyyyMM";

    // Classical style
    String PATTERN_CLASSICAL = "yyyy-MM-dd HH:mm:ss";
    String PATTERN_CLASSICAL_NORMAL = "yyyy-MM-dd HH:mm";
    String PATTERN_CLASSICAL_SIMPLE = "yyyy-MM-dd";
    String PATTERN_CLASSICAL_TIME = "HH:mm:ss";

    //CH style
    String PATTERN_CH = "yyyy年MM月dd日 HH时mm分ss秒";
    String PATTERN_CH_NORMAL = "yyyy年MM月dd日 HH时mm分";
    String PATTERN_CH_SIMPLE = "yyyy年MM月dd日";

}
