package com.bdqn.service;

import com.bdqn.pojo.User;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/applicationContext.xml")
public class UserServiceTest extends Object{


    private ApplicationContext context;
    private Logger logger = Logger.getLogger(UserServiceTest.class);
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("spring/applicationContext*.xml");
//        context = new ClassPathXmlApplicationContext("spring/applicationContext.xml","spring/applicationContext-dao.xml");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserCount() throws Exception {
        UserService userService= (UserService) context.getBean("userService");
        UserService userService1= (UserService) context.getBean("userService");
        int result=userService.findUserCount();
        logger.info(result);
//        默认单例模式
//        logger.info(userService);
//        logger.info(userService1);
    }

    @Test
    public void findUserByUserRoleArray() throws Exception {
        UserService userService= (UserService) context.getBean("userService");
        Integer userRoles[] = {2,3};
        List<User> userList = userService.findUserByUserRoleArray(userRoles);
        for (User user1 :
                userList) {
            logger.info("——" + user1.getUserName() + "——" + user1.getUserCode()
                    + "——" + user1.getGender() + "——" + user1.getPhone()+"——" +
                    user1.getRole().getRoleName() + "——" +
                    user1.getRole().getRoleCode() + "——" +
                    user1.getRole().getId() );
        }
    }

    @Test
    public void addUser() throws Exception {
        int count = 0;
        User user = new User();
        user.setUserCode("test005");
        user.setUserName("测试用户005");
        user.setUserPassword("55555");
        Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1989-12-12");
        user.setBirthday(birthday);
        user.setCreationDate(new Date());
        user.setAddress("地址5测试");
        user.setGender(1);
        user.setPhone("13655553697");
        user.setUserRole(1);
        user.setCreatedBy(1);
        user.setCreationDate(new Date());
        UserService userService= (UserService) context.getBean("userService");
        count = userService.addUser(user);
        logger.info(count);
    }

    @Test
    public void delUserById() throws Exception {
        UserService userService= (UserService) context.getBean("userService");
        int result=userService.delUserById(38);
        logger.info(result);
    }

}