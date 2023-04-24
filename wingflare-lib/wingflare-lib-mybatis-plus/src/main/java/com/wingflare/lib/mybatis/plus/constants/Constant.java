package com.wingflare.lib.mybatis.plus.constants;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import java.util.regex.Pattern;

/**
 * 常量类
 *
 * @author shaoyuyao
 * @date 2022/8/18 14:43
 */
public interface Constant extends Constants {
    /**
     * @Param Value {@link org.apache.ibatis.annotations.Param}
     */
    String ID = "id";
    String DATA_SCOPE = "dataScope";
    String CLAZZ = "clazz";

    /**
     * SQL关键字
     */
    String SELECT = "SELECT";
    String DISTINCT = "DISTINCT";
    String INNER_JOIN = "INNER JOIN";
    String LEFT_JOIN = "LEFT JOIN";
    String RIGHT_JOIN = "RIGHT JOIN";
    String AS = "AS";
    String ON = "ON";
    String AND = "AND";
    String OR = "OR";
    String LEFT_JOIN_AROUND_SPACE = SPACE + LEFT_JOIN + SPACE;
    String INNER_JOIN_AROUND_SPACE = SPACE + INNER_JOIN + SPACE;
    String RIGHT_JOIN_AROUND_SPACE = SPACE + RIGHT_JOIN + SPACE;
    String AS_AROUND_SPACE = SPACE + AS + SPACE;
    String ON_AROUND_SPACE = SPACE + ON + SPACE;
    String AND_AROUND_SPACE = SPACE + AND + SPACE;
    String OR_AROUND_SPACE = SPACE + OR + SPACE;

    String LIKE_SPLIT_EXP = "\"%\"?\"%\"";
    String LIKE_LEFT_SPLIT_EXP = "\"%\"?";
    String LIKE_RIGHT_SPLIT_EXP = "?\"%\"";
    String LIKE_CONCAT_EXP = "CONCAT('%',?,'%')";
    String LIKE_LEFT_CONCAT_EXP = "CONCAT('%',?)";
    String LIKE_RIGHT_CONCAT_EXP = "CONCAT(?,'%')";

    /**
     * Wrapper 属性名
     */
    String Q_WRAPPER_IS_DISTINCT = WRAPPER_DOT + "isDistinct";
    String Q_WRAPPER_MASTER_TABLE_ALIAS = WRAPPER_DOT + "masterTableAlias";
    String Q_WRAPPER_MASTER_JOIN_SQL = WRAPPER_DOT + "joinSql";

    /**
     * 表别名前缀
     */
    String TABLE_ALIAS_PREF = "t";
    String MASTER_TABLE_ALIAS_PREF = TABLE_ALIAS_PREF + "1";

    /**
     * 逻辑删除
     */
    String IS_DELETE = "is_delete";
    Integer LOGIC_DELETE_VALUE = 1;
    Integer LOGIC_NOT_DELETE_VALUE = 0;

    /**
     * 正则
     */
    Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    Pattern UNDERLINE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * 符号
     */
    String UNDERLINE = "_";
    char UNDERLINE_CHAR = '_';
    String EQUALS_AROUND_SPACE = SPACE + EQUALS + SPACE;
    String PLUS_AROUND_SPACE = SPACE + PLUS + SPACE;
    String SUB_AROUND_SPACE = SPACE + DASH + SPACE;

    /**
     * 条件比较类型：变量比较
     */
    String CONDITION_TYPE_VARIABLE = "VARIABLE";
    /**
     * 条件比较类型：常量比较
     */
    String CONDITION_TYPE_CONSTANT = "CONSTANT";

    /**
     * 自动填充相关字段名
     */
    String AUTO_UPDATE_TIME_FIELD = "autoUpdateTime";
    String AUTO_UPDATE_INFO_FIELD = "autoUpdateInfo";
    String CREATED_TIME_FIELD = "createdTime";
    String UPDATED_TIME_FIELD = "updatedTime";
    String CREATE_USER_FIELD = "createUser";
    String UPDATE_USER_FIELD = "updateUser";
    String CREATE_USER_ID_FIELD = "createUserId";
    String UPDATE_USER_ID_FIELD = "updateUserId";
    String IS_DELETE_FIELD = "isDelete";
    String VERSION_FIELD = "version";

    /**
     * 数据无需更新时不操作数据库直接返回-1
     */
    int NOT_SQL_UPDATE_RET = -1;

}
