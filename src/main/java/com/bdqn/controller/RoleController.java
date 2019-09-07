package com.bdqn.controller;

import com.bdqn.exception.BusinessException;
import com.bdqn.exception.EmBusinessError;
import com.bdqn.pojo.Role;
import com.bdqn.response.CommonReturnType;
import com.bdqn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName: RoleController
 * create by:  xyf
 * description: TODO 角色控制器
 * create time: 2019/9/7 12:21
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * description: TODO  获取角色信息（JSON数据供前端调用）
     * create time: 2019/9/7 12:54
     * []
     * @return java.lang.Object
     */
    @GetMapping("/roleList")
    @ResponseBody
    public Object getRoleNames() throws Exception {
        List<Role> roleList =roleService.findRoles();
        if (roleList.size()==0){
            throw  new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        return CommonReturnType.create(roleList);
    }
}
