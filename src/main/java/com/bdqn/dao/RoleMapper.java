package com.bdqn.dao;

import com.bdqn.pojo.Role;

import java.util.List;

/**
 * ClassName: {@link RoleMapper}
 * Description: 角色数据访问接口
 * Author: xyf
 * Date 2019/8/28 12:03
 */
public interface RoleMapper {

    /**
     * Description: 查询角色的数量
     * param: []
     * return: int
     * Date: 2019/8/28 12:04
     */
    int selectCount();

    /**
     * description: TODO 查询角色的全部信息
     * create time: 2019/9/7 9:24
     * []
     * @return java.util.List<com.bdqn.pojo.Role>
     */
    List<Role> selectRoles();
}
