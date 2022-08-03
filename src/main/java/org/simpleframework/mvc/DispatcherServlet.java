package org.simpleframework.mvc;

import com.example.simpleframework.servlet.controller.business.HeadlineController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Slf4j
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        log.info("初始化 servlet");
        // 常驻内存变量初始化
        // 1. 容器初始化，
        // 2. 初始化请求处理器
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
