package com.bdqn.controller;

import com.bdqn.pojo.User;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * ClassName: UserController
 * Description: 用户控制器
 * Author: xyf
 * Date 2019/9/2 15:37
 */
@Controller
@RequestMapping("/userTest")
public class UserTestController {

    private Logger logger = Logger.getLogger(this.getClass());


//    @RequestMapping(value = "/getUserById",method = RequestMethod.GET)
    @GetMapping(value = {"/getUserById","/getUser"})
    public ModelAndView getUserById(@RequestParam(value = "uId",required = false) Integer id){
        logger.info("接受的id:"+id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");//找到对应的视图
        modelAndView.addObject("uId",id);//存放模型数据(键值对)
//        modelAndView.addObject(id);//存放模型数据(值)
        //省略使用传过来的参数
        return modelAndView;
    }

    @GetMapping("/getUserById1")
    @ResponseBody
    public Object getUserById1(@RequestParam(value = "uId",required = false) Integer id){
        logger.info("接受的id:"+id);
        return id;
//        return JSON.toJSONString(id);
    }




    /**********************Model*************************/
    @GetMapping(value = "/getUserByName")
    public String getUserByName(@RequestParam(value = "username",required = false) String username,
                                Model model){
        logger.info("接受的username:"+username);
        model.addAttribute("userName",username);
        return "user";
    }
    /**********************ModelMap*************************/
    @GetMapping(value = "/getUserByUserCode")
    public String getUserByUserCode(@RequestParam(value = "usercode",required = false) String usercode,
                                ModelMap modelMap){
        logger.info("接受的username:"+usercode);
//        模拟数据库查询，得到User对象
        User user = new User();
        user.setUserName("科比");
        user.setGender(1);
        user.setUserCode(usercode);
        user.setCreationDate(new Date());
        modelMap.addAttribute("user",user);
        return "user";
    }
    /**********************Map*************************/
    @GetMapping(value = "/getUserByPhone")
    public String getUserByPhone(@RequestParam(value = "phone",required = false) String phone,
                                    Map<String,Object> map){
        logger.info("接受的username:"+phone);
//        模拟数据库查询，得到User对象
        User user = new User();
        user.setUserName("科比");
        user.setGender(1);
        user.setPhone(phone);
        user.setCreationDate(new Date());
        map.put("user",user);
        return "user";
    }


}
