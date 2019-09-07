package com.bdqn.controller;

import org.apache.log4j.Logger;
import org.springframework.lang.Nullable;
        import org.springframework.web.servlet.ModelAndView;
        import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController extends AbstractController{

    private Logger logger = Logger.getLogger(HelloController.class);

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("hello springMVC");
        return new ModelAndView("index1");
    }
}
