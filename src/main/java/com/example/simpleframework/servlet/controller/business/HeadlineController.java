package com.example.simpleframework.servlet.controller.business;

import com.example.simpleframework.servlet.common.entity.bo.HeadLine;
import com.example.simpleframework.servlet.common.service.HeadLineService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annontation.Controller;
import org.simpleframework.core.annontation.inject.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Slf4j
@Controller
public class HeadlineController {

    @Autowired(value = "DefaultHeadLineService")
    @Getter
    private HeadLineService headLineService;

    public void addHeadline(HttpServletRequest request, HttpServletResponse response) {
        log.info("头条业务处理!");
        headLineService.addHeadLine(new HeadLine());
    }
}
