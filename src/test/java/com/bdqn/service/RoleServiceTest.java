package com.bdqn.service;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/applicationContext.xml")
public class RoleServiceTest {
    private ApplicationContext context;
    private Logger logger = Logger.getLogger(UserServiceTest.class);
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findRoleCount() throws Exception {
        RoleService roleService= (RoleService) context.getBean("roleService");
        int result=roleService.findRoleCount();
        logger.info(result);
    }

}