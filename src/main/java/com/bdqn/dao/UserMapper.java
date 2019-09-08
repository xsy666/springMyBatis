package com.bdqn.dao;

import com.bdqn.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: {@link UserMapper}
 * Description: 用户数据访问层接口
 * Author: xyf
 * Date 2019/8/28 10:59
 */
public interface UserMapper {
    /**
     * Description: 查询用户数量
     * param: []
     * return: int
     * Date: 2019/8/28 11:03
     */
    int selectCount();

    List<User> selectUserByUserRoleArray(Integer[] userRoles);

    /**
     * Description: 添加用户
     * param: [user]
     * return: int
     * Date: 2019/8/29 9:11
     */
    int insertUser(@Param("user") User user);

    int delUser(@Param("uId") Integer id);

    User selectUserByCodeAndPwd(@Param("userCode")String userCode, @Param("userPassword")String userPassword);

    /**
     * Description:查询用户数据
     * param: []
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/8/20 16:29
     */
    List<User> selectUsers();

    /**
     * description: TODO 查询全部用户信息（包含名称和角色过滤）
     * create time: 2019/9/7 9:37
     * [queryUserRole, queryname]
     * @return java.util.List<com.bdqn.pojo.User>
     */
    List<User> selectUsersByRoleAndName(@Param("queryUserRole") Integer queryUserRole, @Param("queryname") String queryname);

    /**
     * description: TODO 通过userCode查询用户信息
     * create time: 2019/9/7 10:40
     * [userCode]
     * @return com.bdqn.pojo.User
     */
    User selectUserByCode(@Param("userCode") String userCode);

    /**
     * description: TODO 添加用户（包含新增的上传信息的字段）
     * create time: 2019/9/7 14:12
     * [user]
     * @return int
     */
    int insertSelective(User user);

    /**
     * description: TODO 根据id查找用户信息
     * create time: 2019/9/8 20:36
     * [userId]
     * @return com.bdqn.pojo.User
     */
    User selectUserById(@Param("userId") Integer userId);
}
