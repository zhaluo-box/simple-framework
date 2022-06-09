package org.simpleframework.core.container;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.core.annontation.Component;
import org.simpleframework.core.annontation.Controller;
import org.simpleframework.core.annontation.Repository;
import org.simpleframework.core.annontation.Service;
import org.simpleframework.utils.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 自己实现的bean 容器
 * todo 优化 将方法抽象为接口 类似于Spring 的一些顶级接口
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BeanContainer {

    /**
     * 用于存放所有的Bean 基于Class 为Key  Bean 为Value
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new ConcurrentHashMap<>();

    private static final Set<Class<? extends Annotation>> BEAN_ANNOTATION = Set.of(Component.class, Controller.class, Repository.class, Service.class,
                                                                                   Aspect.class);

    /**
     * 多线程可见，是否已经开始加载
     */
    private volatile boolean loaded;

    /**
     * 单例
     */
    enum ContainerHolder {
        HOLDER;

        private final BeanContainer instance;

        ContainerHolder() {
            log.info("Bean Container Instance! ");
            instance = new BeanContainer();
        }
    }

    /**
     * 是否已经开始加载
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * 获取单例
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    /**
     * 获取Bean的数量
     */
    public int getBeanMapSize() {
        return BEAN_MAP.size();
    }

    /**
     * 同步方法加载指定 packageName 下的所有添加了容器注解的类
     *
     * @param packageName 包的全路径名称
     */
    public synchronized void loadBeans(String packageName) {
        if (isLoaded()) {
            log.warn(" BeanContainer has been loaded !");
            return;
        }

        var classSet = ClassUtil.extractPackageClass(packageName);
        if (classSet.isEmpty()) {
            log.warn("extract nothing from packageName! {}", packageName);
            return;
        }

        classSet.forEach(clazz -> {
            BEAN_ANNOTATION.forEach(annotation -> {
                if (clazz.isAnnotationPresent(annotation)) {
                    BEAN_MAP.put(clazz, ClassUtil.newInstance(clazz, true));
                }
            });
        });
        loaded = true;
    }

    /**
     * 添加Bean
     */
    public Object addBean(Class<?> clazz) {
        return BEAN_MAP.put(clazz, ClassUtil.newInstance(clazz, true));
    }

    /**
     * 移除bean
     */
    public Object removeBean(Class<?> clazz) {
        return BEAN_MAP.remove(clazz);
    }

    /**
     * 获取bean
     */
    public Object getBean(Class<?> clazz) {
        return BEAN_MAP.get(clazz);
    }

    /**
     * 获取bean_map keySet
     * 获取容器里的所有的类
     */
    public Set<Class<?>> getClassSet() {
        return BEAN_MAP.keySet();
    }

    /**
     * 获取bean values
     */
    public Set<Object> getBeans() {
        return new HashSet<>(BEAN_MAP.values());
    }

    /**
     * TODO 是否需要判空, 是否返回NUll 或者 size > 0 hashSet();
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        return getClassesForCondition(clazz -> clazz.isAnnotationPresent(annotation));
    }

    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
        return getClassesForCondition(clazz -> interfaceOrClass.isAssignableFrom(clazz));
    }

    private Set<Class<?>> getClassesForCondition(Predicate<Class<?>> predicate) {
        return getClassSet().stream().filter(predicate).collect(Collectors.toUnmodifiableSet());
    }
}
