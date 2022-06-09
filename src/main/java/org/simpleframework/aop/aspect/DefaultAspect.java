package org.simpleframework.aop.aspect;

import java.lang.reflect.Method;

/**
 * Created  on 2022/6/9 20:20:55
 *
 * @author zl
 */
public abstract class DefaultAspect {

    /**
     * 事前拦截
     *
     * @param targetClass 被代理的目标类
     * @param method      被代理的目标方法
     * @param args        被代理方法的目标方法对应的参数列表
     * @throws Throwable 所有异常
     */
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {

    }

    /**
     * @param targetClass 被代理的目标类
     * @param method      被代理的目标方法
     * @param args        被代理方法的目标方法对应的参数列表
     * @param returnValue 被代理的目标方法执行后的返回值
     * @throws Throwable 所有异常
     */
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        return returnValue;
    }

    /**
     * @param targetClass 被代理的目标类
     * @param method      被代理的目标方法
     * @param args        被代理方法的目标方法对应的参数列表
     * @param e           执行的异常信息
     */
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) {

    }

}
