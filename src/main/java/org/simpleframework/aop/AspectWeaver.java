package org.simpleframework.aop;

import org.apache.commons.collections4.CollectionUtils;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.core.container.BeanContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created  on 2022/6/9 21:21:01
 *
 * @author zl
 */
public class AspectWeaver {

    private BeanContainer beanContainer;

    public void doAop() {
        // 1.获取所有切面类
        Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect.class);
        if (CollectionUtils.isEmpty(aspectSet)) {
            return;
        }
        // 2.拼装AspectInfoList
        List<AspectInfo> aspectInfoList = packAspectInfoList(aspectSet);
        // 3.遍历容器里的类
        var classSet = beanContainer.getClassSet();

        for (Class<?> targetClass : classSet) {
            // 过滤AspectClass 本身
            if (targetClass.isAnnotationPresent(Aspect.class)) {
                continue;
            }
            // 4.粗筛符合条件的Aspect
            List<AspectInfo> roughMatchedAspectList = collectRoughMatchedAspectListForspecificClass(aspectInfoList, targetClass);

            // 5.尝试进行Aspect的织入
            wrapIfNecessary(roughMatchedAspectList, targetClass);
        }

    }

    private List<AspectInfo> collectRoughMatchedAspectListForspecificClass(List<AspectInfo> aspectInfoList, Class<?> targetClass) {
        return null;
    }

    private void wrapIfNecessary(List<AspectInfo> roughMatchedAspectList, Class<?> targetClass) {
    }

    /**
     * 将切面类转换为 AspectInfo信息
     *
     * @param aspectSet
     * @return
     */
    private List<AspectInfo> packAspectInfoList(Set<Class<?>> aspectSet) {
        var aspectInfoList = new ArrayList<AspectInfo>();

        return aspectInfoList;
    }

}
