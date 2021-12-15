package org.simpleframework.core.inject;

import com.example.simpleframework.servlet.controller.business.HeadlineController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.container.BeanContainer;

/**
 *
 */
@DisplayName("DI 测试")
class DependencyInjectTest {

    @Test
    @DisplayName("doIoc 测试!")
    public void doIocTest() {

        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.example.simpleframework.servlet");

        // 验证bean 在没有自动注入的时候,成员变量为null
        var headLineController = (HeadlineController) beanContainer.getBean(HeadlineController.class);
        Assertions.assertNull(headLineController.getHeadLineService(), "没有依赖注入时, 成员变量为空!");

        // 调用doIoc 后成员变量有值
        new DependencyInject().doIoc();
        headLineController = (HeadlineController) beanContainer.getBean(HeadlineController.class);
        Assertions.assertNotNull(headLineController.getHeadLineService(), "没有依赖注入时, 成员变量为空!");

    }

}