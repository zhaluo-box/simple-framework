package org.simpleframework.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import javax.swing.plaf.metal.MetalTheme;
import java.lang.reflect.Method;

/**
 * 解析Aspect表达式并且定位被织入的目标
 * locator 定位
 * Created  on 2022/6/9 21:21:00
 *
 * @author zl
 */
public class PointcutLocator {

    /**
     * aspectj weaver 提供的切点解析器
     * PointcutParser.getAllSupportedPointcutPrimitives()  获取支持的所有语法树
     */
    private PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
                    PointcutParser.getAllSupportedPointcutPrimitives());

    /**
     * 切面表达式 解析器
     */
    private PointcutExpression pointcutExpression;

    public PointcutLocator(String expression) {

        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);

    }

    /**
     * 判断传入的Class对象是否是Aspect的目标代理类，即匹配Pointcut 表达式（初筛）
     *
     * @param targetClass 目标类
     * @return 是否匹配
     */
    public boolean roughMatches(Class<?> targetClass) {
        // couldMatchJoinPointsInType 比较坑，之恩校验within
        // 不能校验（execution ，call,get,set) ,面对无法校验的表达式 直接返回true
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    /**
     * 判断传入的Method对象是否是Aspect的目标代理方法，即匹配Pointcut 表达式（精确筛选）
     *
     * @param method 被代理的方法 进行精确匹配
     * @return
     */
    public boolean accurateMatches(Method method) {
        var shadowMatch = pointcutExpression.matchesMethodExecution(method);
        // 如果总是匹配 返回true 反之false
        return shadowMatch.alwaysMatches();
    }
}
