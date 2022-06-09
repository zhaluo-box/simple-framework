package org.simpleframework.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.simpleframework.aop.aspect.AspectInfo;

import javax.swing.plaf.metal.MetalTheme;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created  on 2022/6/9 20:20:56
 *
 * @author zl
 */
public class AspectListExecutor implements MethodInterceptor {

    /**
     * 被代理的目标类Class
     */
    private Class<?> targetClass;

    /**
     * 排序后的Aspect 列表
     */
    @Getter
    private List<AspectInfo> sortedAspectInfoList;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList) {
        this.targetClass = targetClass;
        this.sortedAspectInfoList = sortAspectInfoList(aspectInfoList);
    }

    /**
     * 对所有Aspect进行排序
     */
    private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectInfoList) {
        // TODO　待实现排序逻辑
        return null;
    }

    /**
     * 本质与模板模式一致，切面抽象类定义的钩子方法，在被代理类方法前后 执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // TODO　待实现切面方法织入逻辑 需要将  aspectInfoList 的所有方法 按照order顺序执行一遍

        Object returnValue = null;
        // 执行前置逻辑

        try {
            // 调用方法本身
            returnValue = methodProxy.invokeSuper(proxy, args);
            // 执行后置逻辑
        } catch (Exception e) {
            // 如果被代理方法抛出异常 执行所有的异常钩子方法 afterThrowing 方法
        }

        return returnValue;
    }
}
