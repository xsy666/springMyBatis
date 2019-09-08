package com.bdqn.service.impl;

import com.bdqn.dao.UserMapper;
import com.bdqn.exception.BusinessException;
import com.bdqn.exception.EmBusinessError;
import com.bdqn.pojo.User;
import com.bdqn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: {@link UserServiceImpl}
 * Description:  用户业务接口实现类
 * Author: xyf
 * Date 2019/8/28 10:57
 */
//@Service("userService")
@Service
//@Scope("singleton") //直接采用注解的
//@Scope("prototype") //创建多个实例对象
@Transactional //开启事务注解
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * Description: 查找用户的数量
     * param: []
     * return: int
     * Date: 2019/8/28 10:56
     */
    @Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true) //开启事务注解
    public int findUserCount() {
        return userMapper.selectCount();
    }

    @Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true) //开启事务注解
    public List<User> findUserByUserRoleArray(Integer[] userRoles) {
        return userMapper.selectUserByUserRoleArray(userRoles);
    }

    /**
     * Description: 添加用户
     * param: [user]
     * return: int
     * Date: 2019/8/29 9:07
     *
     * @param user
     */

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT) //开启事务注解
    public int addUser(User user) {
        int result =0;
        try {
            if (user==null){
                return 0;//这里是要抛出业务异常
            }else {
                result =userMapper.insertUser(user);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    /**
     * description: TODO 添加用户（包含用户的上传信息的路径）
     * create time: 2019/9/7 14:10
     * [user]
     *
     * @param user
     * @return boolean
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public boolean addUser1(User user) throws BusinessException {
        boolean flag = false;
        if (userMapper.insertSelective(user)==1){
            flag = true;
        }
        return flag;
    }

    /**
     * Description: 删除某个用户
     * param: [id]
     * return: int
     * Date: 2019/8/29 10:16
     *
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT) //开启事务注解
    public int delUserById(Integer id) {
        int result = 0;
        try {
            if (id!=null){
                result=userMapper.delUser(id);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    /**
     * Description: 登录
     * param: [userCode, userPassword]
     * return: com.bdqn.pojo.User
     * Date: 2019/9/3 15:30
     *
     * @param userCode
     * @param userPassword
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User login(String userCode, String userPassword) {
        User user=userMapper.selectUserByCodeAndPwd(userCode,userPassword);
        if (user!=null){
            return user;
        }
        return null;
    }

    /**
     * Description: 查找所有用户
     * param: []
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/9/4 16:39
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> findUsers() throws BusinessException {
        return userMapper.selectUsers();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> findUsersByRoleAndName(Integer queryUserRole, String queryname) {
        List<User> userList =  userMapper.selectUsersByRoleAndName(queryUserRole,queryname);
        if (userList==null){
            return null;
        }
        return userList;
    }

    /**
     * description: TODO 通过userCode查看是否存在该userCode的用户
     * create time: 2019/9/7 10:38
     * [userCode]
     *
     * @param userCode
     * @return com.bdqn.pojo.User
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User findUsersByCode(String userCode) throws BusinessException {
        return userMapper.selectUserByCode(userCode);
    }

    /**
     * description: TODO 根据ID查找到用户信息
     * create time: 2019/9/8 20:35
     * [userId]
     *
     * @param userId
     * @return com.bdqn.pojo.User
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User findUserById(Integer userId) throws BusinessException {
        return userMapper.selectUserById(userId);
    }

    /**
     * description: TODO 修改用户信息（不包含修改用户图片）
     * create time: 2019/9/8 22:04
     * [user]
     *
     * @param user
     * @return java.lang.Integer
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT) //开启事务注解
    public Integer modifyUser(User user) throws BusinessException {
        return  userMapper.updateUserByUser(user);
    }


}
