package com.bdqn.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: {@link TestController}
 * Description: 测试控制器（是Servlet分发的前端控制器）
 * Author: xyf
 * Date 2019/8/30 12:09
 */
//@Component
@Controller
@RequestMapping(value = "/test")
public class TestController {

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/test.html")
    public ModelAndView test(){
        logger.info("hello test");
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/test1.html")
    public String test2(){
        logger.info("hello test2");
        return "test";
    }
}
