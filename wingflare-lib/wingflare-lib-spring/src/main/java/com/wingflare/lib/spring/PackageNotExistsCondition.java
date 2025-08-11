package com.wingflare.lib.spring;

import com.wingflare.lib.spring.annotation.ConditionalOnPackageNotExists;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

// 条件判断类：检查指定包是否不存在
public class PackageNotExistsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取注解的属性值（即需要检查的包名）
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnPackageNotExists.class.getName());
        String packageName = null;

        if (attributes != null) {
            packageName = (String) attributes.get("value");
        }

        if (packageName == null || packageName.isEmpty()) {
            return false; // 包名为空时不满足条件
        }

        // 检查包是否存在（通过尝试加载包下的标志性类实现）
        // 这里使用包名拼接一个不可能存在的类名，若加载失败则说明包不存在
        String testClassName = packageName + ".WingFlarePackageMarkerTest";

        try {
            // 尝试加载类，若抛出ClassNotFoundException则说明包不存在
            ClassUtils.forName(testClassName, context.getClassLoader());
            // 若未抛异常，说明包存在（或至少该路径可被类加载器识别）
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}