package org.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 代理创建者
 * 基于Cglib 生成的代理工具类
 * Created  on 2022/6/9 21:21:24
 *
 * @author zl
 */
public final class ProxyCreator {

    /**
     * 创建代理类
     *
     * @param targetClass       目标对象的Class
     * @param methodInterceptor 方法拦截器
     * @return 返回代理对象
     */
    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor) {
        return Enhancer.create(targetClass, methodInterceptor);
    }
}
