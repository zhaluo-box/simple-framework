package org.simpleframework.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClassUtil {

    private final static String FILE_PROTOCOL = "file";

    private final static String CLASS_FILE_SUFFIX = ".class";

    public static Set<Class<?>> extractPackageClass(String packageName) {

        var classes = new HashSet<Class<?>>();
        // 1. 获取类的加载器
        var classLoader = getClassLoader();
        // 2. 通过类加载器获取加载的资源
        var resource = classLoader.getResource(packageName.replace(".", "/"));
        if (Objects.isNull(resource)) {
            return Collections.unmodifiableSet(classes);
        }
        // 3. 根据不同的资源类型,采用不同的方式获取资源的集合
        if (resource.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            var packageDirectory = new File(resource.getPath());
            extractClassFile(classes, packageDirectory, packageName);
        }
        return Collections.unmodifiableSet(classes);
    }

    public static <T> T newInstance(Class<?> clazz, boolean accessible) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T) constructor.newInstance();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            log.error("获取实例失败, 异常信息 : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 抽取CLassFile
     *
     * @param classes     Classes
     * @param fileSource  源文件
     * @param packageName 包名称
     */
    private static void extractClassFile(Set<Class<?>> classes, File fileSource, String packageName) {
        // 如果是class文件则直接退出
        if (!fileSource.isDirectory()) {
            return;
        }
        var files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    // 获取文件绝对路径
                    var absolutePath = file.getAbsolutePath();
                    // 如果是class 文件则直接加载
                    if (absolutePath.endsWith(CLASS_FILE_SUFFIX)) {
                        addToClassSet(absolutePath);
                    }
                }
                return false;
            }

            private void addToClassSet(String absolutePath) {
                var fullPath = absolutePath.replace(File.separator, ".");
                fullPath = fullPath.substring(fullPath.indexOf(packageName));
                var classPath = fullPath.substring(0, fullPath.indexOf(CLASS_FILE_SUFFIX));
                classes.add(loadClass(classPath));
            }
        });

        // 如果是文件夹, 过滤, 将class文件加入 classes
        if (Objects.nonNull(files)) {
            for (File file : files) {
                extractClassFile(classes, file, packageName);
            }
        }

    }

    private static Class<?> loadClass(String classPath) {
        try {
            return Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            log.error("load class error ! ", e);
            throw new RuntimeException(e);
        }
    }

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static void setField(Field field, Object obj, Object target, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(obj, target);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            throw new RuntimeException(e);
        }

    }
}
