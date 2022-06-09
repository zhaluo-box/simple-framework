package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.simpleframework.aop.PointcutLocator;

/**
 * Created  on 2022/6/9 20:20:54
 *
 * @author zl
 */
@Getter
@AllArgsConstructor
public class AspectInfo {

    /**
     * 排序
     */
    private final int orderIndex;

    /**
     * 默认的切面拦截实现
     */
    private final DefaultAspect aspectObject;

    /**
     * 切面定位器
     */
    private final PointcutLocator pointcutLocator;
}
