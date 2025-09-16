package com.wingflare.lib.datascope.aspect;


import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.datascope.annotation.DataPermission;
import com.wingflare.lib.datascope.annotation.DpBinding;
import com.wingflare.lib.datascope.entity.DpBindingData;
import com.wingflare.lib.datascope.utils.DataScopeUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限切面
 *
 * @author shaoyuyao
 * @date 2022/8/17 15:27
 */
@Component
@Aspect
@ConditionalOnBean({
        DataScopeUtil.class
})
public class DataPermissionAspect {


    private final DataScopeUtil dataScopeUtil;

    public DataPermissionAspect(DataScopeUtil dataScopeUtil) {
        this.dataScopeUtil = dataScopeUtil;
    }

    @Before("execution(* com.wingflare..*.controller..*(..)) || execution(* com.wingflare..*.service..*(..))")
    public void deBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 判断方法上是否包含 @DataPermission，只有包含该注解才做数据权限处理
        DataPermission dataPermission = AnnotationUtils.findAnnotation(signature.getMethod(), DataPermission.class);
        if (dataPermission == null) {
            return;
        }

        DpBinding[] dpBindings = dataPermission.dpBindings();

        if (CollectionUtil.isEmpty(dpBindings)) {
            return;
        }

        List<DpBindingData> dpBindingDataList = new ArrayList<>(dpBindings.length);

        for (DpBinding dpBinding : dpBindings) {
            dpBindingDataList.add(DpBindingData.of(dpBinding));
        }

        // 设置数据权限
        dataScopeUtil.setDataPermission(dpBindingDataList);
    }

}
