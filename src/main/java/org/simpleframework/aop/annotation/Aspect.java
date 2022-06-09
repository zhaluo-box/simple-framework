package org.simpleframework.aop.annotation;

import java.lang.annotation.Annotation;

/**
 * Created  on 2022/6/9 20:20:51
 *
 * @author zl
 */
public @interface Aspect {

    /**
     * 需要被织入的横切逻辑的注解标签
     * 自己实现AOP 1.0  基于注解Class 实现
     */
    Class<? extends Annotation> value();

    /**
     * 自己实现AOP 2.0
     * 基于AspectJ pointcutParse 和 pointcutExpression 改造
     *
     * @return aspectj point  expression 表达式
     */
    String pointcut();

}
