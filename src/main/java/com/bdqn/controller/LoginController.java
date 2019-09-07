package com.bdqn.controller;

import com.bdqn.exception.BusinessException;
import com.bdqn.exception.EmBusinessError;
import com.bdqn.pojo.User;
import com.bdqn.service.UserService;
import com.bdqn.utils.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ClassName: {@link LoginController}
 * Description: 登录控制器
 * Author: xyf
 * Date 2019/9/4 15:15
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Description: 跳转到登录页面
     * param: []
     * return: java.lang.String
     * Date: 2019/9/3 15:17
     */
    @GetMapping("")
    public String login() {
        return "login";
    }

    /**
     * Description: 跳转到后台主页面
     * param: []
     * return: java.lang.String
     * Date: 2019/9/3 15:29
     */
    @GetMapping("/frame")
    public String frame(HttpServletRequest request) {
        if (request.getSession().getAttribute("userSession") == null) {
            return Constants.REDIRECT+"login";
        }
        return "frame";
    }

    /**
     * Description: 处理登录
     * param: [userCode, userPassword]
     * return: java.lang.String
     * Date: 2019/9/3 15:28
     */
//    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @PostMapping("/doLogin")
    public String doLogin(@RequestParam(value = "userCode") String userCode,
                          @RequestParam("userPassword") String userPassword,
                          HttpServletRequest request) {
        User user = userService.login(userCode, userPassword);
        if (user != null) {
            request.getSession().setAttribute("userSession", user);
            //跳转到后台主页面
            return "redirect:/login/frame";
        } else {
//            转发到登录页面
            return "redirect:/login";
        }
    }

    @PostMapping("/doLogin1")
    public String doLogin1(@RequestParam(value = "userCode") String userCode,
                           @RequestParam("userPassword") String userPassword,
                           HttpServletRequest request) throws BusinessException {
        User user = userService.login(userCode, userPassword);
        if (user == null) {
//            throw new RuntimeException("用户名或密码错误");
            throw new BusinessException(EmBusinessError.USER_NAMEORPASSWORD);
        }
        request.getSession().setAttribute(Constants.USERSESSION, user);
        //跳转到后台主页面
        return "redirect:/login/frame";
    }

    /**
     * Description: 退出系统
     * param: [session]
     * return: java.lang.String
     * Date: 2019/9/4 14:55
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        //将session中的user移除
        if (session.getAttribute(Constants.USERSESSION)!=null){
            session.removeAttribute(Constants.USERSESSION);
        }
//        return "login";
        return Constants.REDIRECT+"login";//重定向
    }

    /**
     * Description: 局部异常处理
     * param: [e, request]
     * return: java.lang.String
     * Date: 2019/9/3 17:26
     */
   /* @ExceptionHandler(value = {RuntimeException.class})
    public String handlerException(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("e", e);
        return "/common/error";
    }*/

}
