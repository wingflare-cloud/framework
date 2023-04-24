package com.wingflare.lib.mybatis.plus.plugin.handler;

import com.wingflare.lib.mybatis.plus.enums.LogicOperator;
import net.sf.jsqlparser.expression.Expression;

import java.util.Map;

/**
 * 查询条件处理器
 *
 * @author ycx
 * @date 2023/01/12
 */
public interface SelectWhereHandlerInterface {

   Expression setWhere(Expression whereExp, String mappedStatementId, Map parameter);

   /**
    * 逻辑运算符
    *
    * @return
    */
   default public LogicOperator logic() {
      return LogicOperator.AND;
   }

}
