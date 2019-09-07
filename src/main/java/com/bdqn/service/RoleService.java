package com.bdqn.service;

import com.bdqn.pojo.Role;

import java.util.List;

/**
 * ClassName: RoleService
 * Description: 角色业务接口
 * Author: xyf
 * Date 2019/8/28 12:01
 */
public interface RoleService {

    /**
     * Description: 查找角色数量
     * param: []
     * return: int
     * Date: 2019/8/28 12:02
     */
    int findRoleCount();

    /**
     * description: TODO 获取角色全部数据
     * create time: 2019/9/7 9:22
     * []
     * @return java.util.List<com.bdqn.pojo.Role>
     */
    List<Role> findRoles();
}
