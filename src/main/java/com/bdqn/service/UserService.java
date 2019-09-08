package com.bdqn.service;

import com.bdqn.exception.BusinessException;
import com.bdqn.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: {@link UserService}
 * Description: 用户业务接口
 * Author: xyf
 * Date 2019/8/26 10:34
 */

public interface UserService {

    /**
     * Description: 查找用户的数量
     * param: []
     * return: int
     * Date: 2019/8/28 10:56
     */
    int findUserCount();

    List<User> findUserByUserRoleArray(Integer[] userRoles);

    /**
     * Description: 添加用户
     * param: [user]
     * return: int
     * Date: 2019/8/29 9:07
     */
    int addUser(User user);

    /**
     * description: TODO 添加用户（包含用户的上传信息的路径）
     * create time: 2019/9/7 14:10
     * [user]
     * @return boolean
     */
    boolean addUser1(User user) throws BusinessException;

    /**
     * Description: 删除某个用户
     * param: [id]
     * return: int
     * Date: 2019/8/29 10:16
     */
    int delUserById(Integer id);

    /**
     * Description: 登录
     * param: [userCode, userPassword]
     * return: com.bdqn.pojo.User
     * Date: 2019/9/3 15:30
     */
    User login(String userCode, String userPassword);
    /**
     * Description: 查找所有用户
     * param: []
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/9/4 16:39
     */
    List<User> findUsers() throws BusinessException;

    /**
     * description: TODO 查询全部用户信息（包含名称和角色过滤）
     * create time: 2019/9/7 9:35
     * [queryUserRole, queryname]
     * @return java.util.List<com.bdqn.pojo.User>
     */
    List<User> findUsersByRoleAndName(Integer queryUserRole, String queryname);


    /**
     * description: TODO 通过userCode查看是否存在该userCode的用户
     * create time: 2019/9/7 10:38
     * [userCode]
     * @return com.bdqn.pojo.User
     */
    User findUsersByCode(String userCode)  throws BusinessException;

    /**
     * description: TODO 根据ID查找到用户信息
     * create time: 2019/9/8 20:35
     * [userId]
     * @return com.bdqn.pojo.User
     */
    User findUserById(Integer userId) throws BusinessException;
}
