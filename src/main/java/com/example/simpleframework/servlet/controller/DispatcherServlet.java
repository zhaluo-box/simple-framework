package com.example.simpleframework.servlet.controller;

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
        super.init();
        log.info("初始化 servlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        log.info("请求路径 : {}", req.getServletPath());
        log.info("请求方式 : {}", req.getMethod());
        final var HEAD_LINE_PATH = "/head-lines/";
        if (HEAD_LINE_PATH.equals(req.getServletPath())) {
            new HeadlineController().addHeadline(req, resp);
        }
    }
}
