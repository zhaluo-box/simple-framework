package org.simpleframework.core.container;

import com.example.simpleframework.servlet.common.service.HeadLineService;
import com.example.simpleframework.servlet.controller.DispatcherServlet;
import com.example.simpleframework.servlet.controller.business.HeadlineController;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annontation.Service;

/**
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @Order(1)
    @DisplayName("Bean　加载测试!")
    @Test
    public void loadBeanTest() {
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("com.example.simpleframework.servlet");
        Assertions.assertEquals(2, beanContainer.getBeanMapSize());
        Assertions.assertEquals(true, beanContainer.isLoaded());
    }

    @Test
    @Order(2)
    @DisplayName("getBean test")
    public void getBeanTest() {

        var controller = (HeadlineController) beanContainer.getBean(HeadlineController.class);
        Assertions.assertEquals(true, controller instanceof HeadlineController);

        var dispatchServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertNull(dispatchServlet, "没有注解的Bean 无法获取");

    }

    @Test
    public void getClassBySuperTest() {
        beanContainer.loadBeans("com.example.simpleframework.servlet");
        var classesBySuper = beanContainer.getClassesBySuper(HeadLineService.class);
        System.out.println(classesBySuper);
    }

    @Test
    public void getClassByAnnotationTest() {
        beanContainer.loadBeans("com.example.simpleframework.servlet");
        System.out.println(beanContainer.getClassesByAnnotation(Service.class));
    }

    @Test
    public void test() {
    }

}