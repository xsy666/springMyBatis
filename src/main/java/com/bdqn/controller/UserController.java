package com.bdqn.controller;

import com.bdqn.exception.BusinessException;
import com.bdqn.exception.EmBusinessError;
import com.bdqn.pojo.Role;
import com.bdqn.pojo.User;
import com.bdqn.pojo.vo.UserVO;
import com.bdqn.response.CommonReturnType;
import com.bdqn.service.RoleService;
import com.bdqn.service.UserService;
import com.bdqn.utils.constant.Constants;
import com.bdqn.utils.page.PageResultBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: {@link UserController}
 * Description: 用户控制器
 * Author: xyf
 * Date 2019/9/3 15:01
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * description: TODO 提供用户的数据
     * create time: 2019/9/7 11:28
     * []
     *
     * @return java.lang.Object
     */
    @GetMapping("/userListJson")
    @ResponseBody
    public Object getUserList() throws Exception {

        List<User> userList;
        userList = userService.findUsers();
        if (userList.size() == 0) {
//            throw new RuntimeException("数据获取失败");
//            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST1);
        }
        //        将业务层的用户数据User转换成转化成控制器层的UserVO(需要通过java8lambda表达式)
        List<UserVO> userVOList = userList.stream().map(user -> {
            UserVO userVO = this.convertFromModel(user);
            return userVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(userVOList);
    }

    /**
     * description: TODO 实现用户页面的跳转和用户页面的搜索（包含用户名称和角色的过滤）
     * create time: 2019/9/7 11:27
     * [pageNum, queryUserRole, queryname, model]
     *
     * @return java.lang.String
     */
    @GetMapping("/userList")
    public String userList(@RequestParam(value = "pageNum", required = false) String pageNum,
                           @RequestParam(value = "queryUserRole", required = false) Integer queryUserRole,
                           @RequestParam(value = "queryname", required = false) String queryname,
                           Model model) {
        if (pageNum == null) {
            pageNum = "1";
        }
        PageHelper.startPage(Integer.valueOf(pageNum), 5, "`u`.creationDate desc");
//        PageResultBean<User> userPageResultBean = new PageResultBean<>(userService.findUsers());
        PageResultBean<User> userPageResultBean = new PageResultBean<>(userService.findUsersByRoleAndName(queryUserRole, queryname));
        List<Role> roleList = roleService.findRoles();
        List<User> userList = userPageResultBean.getRows();
        model.addAttribute("userList", userList);//分页实体信息
        model.addAttribute("roleList", roleList);//分页实体信息
        model.addAttribute("page", userPageResultBean);//分页信息
        model.addAttribute("pageNum", pageNum);//当前页
        return "user/userlist";
    }


    /**
     * description: TODO 通过userCode获取用户信息
     * create time: 2019/9/7 10:42
     * [userCode]
     *
     * @return java.lang.Object
     */
    @PostMapping("/getUserByCode")
    @ResponseBody
    public Object getUserByCode(@RequestParam(value = "userCode") String userCode) throws Exception {
        User user = userService.findUsersByCode(userCode);

        if (user == null) {
//            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
            return CommonReturnType.create(20001, "fail");
        }
        //        需要将UserModel转换成UserVO（供用户来查看的信息）
        UserVO userVO = convertFromModel(user);
        return CommonReturnType.create(userVO);
    }


    /**
     * description: TODO  模型数据转成视图数据
     * create time: 2019/9/7 12:02
     * [user]
     *
     * @return com.bdqn.pojo.vo.UserVO
     */
    private UserVO convertFromModel(User user) {
//        处理空值的情况
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        //        copyProperties(Object source, Object target)；
        // 通过反射将一个对象的值赋值个另外一个对象（前提是对象中属性的名字相同）。
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    /**
     * description: TODO 跳转到用户添加视图
     * create time: 2019/9/7 12:16
     * []
     *
     * @return java.lang.String
     */
    @GetMapping("/useradd")
    public String useraddView() {
        return "user/useradd";
    }


    /**
     * description: TODO  处理新增用户（包含上传的证件照和工作照）
     * create time: 2019/9/8 0008下午 2:16
     *
     * @ param [user, session, request, attachs]
     * @ return java.lang.String
     */
    @PostMapping(value = "/doUseraddMulti")
    public String addUserSave( User user, HttpSession session, HttpServletRequest request,
                              @RequestParam(value = "attachs", required = false) MultipartFile[] attachs) throws BusinessException {
        String idPicPath = null;
        String workPicPath = null;
        String errorInfo = null;
        boolean flag = true;
//        String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        String path = "D:\\mybatisWork\\springMyBatis\\src\\main\\webapp\\uploadFile";
        logger.info("uploadFile path ============== > " + path);
        for (int i = 0; i < attachs.length; i++) {
            MultipartFile attach = attachs[i];
            if (!attach.isEmpty()) {
                if (i == 0) {
                    errorInfo = "uploadFileError";
                } else if (i == 1) {
                    errorInfo = "uploadWpError";
                }
                String oldFileName = attach.getOriginalFilename();//原文件名
                logger.info("uploadFile oldFileName ============== > " + oldFileName);
                String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
                logger.debug("uploadFile prefix============> " + prefix);
                int filesize = 500000;
                logger.debug("uploadFile size============> " + attach.getSize());
                if (attach.getSize() > filesize) {//上传大小不得超过 500k
                    request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
                    flag = false;
                } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                        || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {//上传图片格式不正确
                    String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
                    logger.debug("new fileName======== " + attach.getName());
                    File targetFile = new File(path, fileName);
                    if (!targetFile.exists()) {
                        targetFile.mkdirs();
                    }
                    //保存
                    try {
                        attach.transferTo(targetFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute(errorInfo, " * 上传失败！");
                        flag = false;
                    }
                    if (i == 0) {
                        idPicPath = path + File.separator + fileName;
                    } else if (i == 1) {
                        workPicPath = path + File.separator + fileName;
                    }
                    logger.debug("idPicPath: " + idPicPath);
                    logger.debug("workPicPath: " + workPicPath);

                } else {
                    request.setAttribute(errorInfo, " * 上传图片格式不正确");
                    flag = false;
                }
            }
        }
        if (flag) {
            user.setCreatedBy(((User) session.getAttribute(Constants.USERSESSION)).getId());
            user.setCreationDate(new Date());
            user.setIdPicPath(idPicPath);
            user.setWorkPicPath(workPicPath);
            if (userService.addUser1(user)) {
                return "user/userList";
            }
        }
        return "user/useradd";
    }
}

