package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
 * 性质类似于Spring的order 接口用于 Spring Bean的加载顺序
 * Created  on 2022/6/9 20:20:52
 *
 * @author zl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Documented
public @interface Order {

    int value();
}
