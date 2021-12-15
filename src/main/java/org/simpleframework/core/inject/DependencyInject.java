package org.simpleframework.core.inject;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.core.annontation.inject.Autowired;
import org.simpleframework.core.container.BeanContainer;
import org.simpleframework.utils.ClassUtil;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 模拟依赖注入  DI
 */
@Slf4j
public class DependencyInject {

    private BeanContainer beanContainer;

    public DependencyInject() {
        beanContainer = BeanContainer.getInstance();
    }

    public void doIoc() {

        // 1. 获取所有class
        var classSet = beanContainer.getClassSet();
        if (classSet.isEmpty()) {
            log.warn("no beans from beanContainer!");
            return;
        }
        // 2. 获取Class 的所有成员变量
        classSet.forEach(clazz -> {
            // 遍历成员变量
            var fields = clazz.getDeclaredFields();
            if (fields.length != 0) {
                for (Field field : fields) {
                    // 找到AutoWired 标准的成员变量
                    if (field.isAnnotationPresent(Autowired.class)) {
                        // 获取类型
                        Class<?> fieldClass = field.getType();
                        // 获取在容器中的对应实例
                        var autowiredAnnotation = field.getAnnotation(Autowired.class);
                        Object target = getFieldInstance(fieldClass, autowiredAnnotation);
                        if (Objects.isNull(target)) {
                            var message = "unable to inject relevant fieldClass, target fieldClass is " + fieldClass.getSimpleName();
                            throw new RuntimeException(message);
                        }
                        // 通过反射将值注入给成员变量
                        ClassUtil.setField(field, beanContainer.getBean(clazz), target, true);
                    }
                }
            }
        });
    }

    private Object getFieldInstance(Class<?> fieldClass, Autowired autowiredAnnotation) {
        // 如果当前类型,在beanMap中存在直接返回,否则查找实现类
        var bean = beanContainer.getBean(fieldClass);
        if (Objects.nonNull(bean)) {
            return bean;
        }
        Class<?> implementedClass = getImplementClass(fieldClass, autowiredAnnotation.value());
        return Objects.isNull(implementedClass) ? null : beanContainer.getBean(implementedClass);
    }

    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        var classesBySuper = beanContainer.getClassesBySuper(fieldClass);
        if (CollectionUtils.isEmpty(classesBySuper)) {
            return null;
        }
        // 如果实现类不为空,  如果只有一个则直接返回,
        if (classesBySuper.size() == 1) {
            return classesBySuper.iterator().next();
        }
        // 如果是多个
        if (StringUtils.isBlank(autowiredValue)) {
            log.error("成员变量类型 {}, 成员变量个数 {}", fieldClass, classesBySuper.size());
            throw new RuntimeException(" 实现类有多个,请配置autowired value 属性" + classesBySuper);
        }
        for (Class<?> clazz : classesBySuper) {
            // 查找类名相同的返回
            if (clazz.getSimpleName().equalsIgnoreCase(autowiredValue)) {
                return clazz;
            }
        }
        return null;
    }
}
