package com.bdqn.controller;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * ClassName: {@link UploadController}
 * create by:  xyf
 * description: TODO 上传文件控制器
 * create time: 2019/9/7 14:36
 */
@Controller
@RequestMapping("/course")
public class UploadController {
    private static Logger log = Logger.getLogger(UploadController.class);

    /**
     * description: TODO 显示上传文件页面
     * create time: 2019/9/7 14:37
     * [multi]
     * @return java.lang.String
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadPage(@RequestParam(value = "multi", required = false) Boolean multi) {
        if (multi != null && multi) {
            return "course_admin/multifile";
        }
        return "course_admin/file";
    }

    /**
     * @Description:处理单文件上传
     * @param: [file]
     * @return: java.lang.String
     * @Date: 2019/07/12 9:29
     */
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            log.debug(file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\temp\\test\\", System.currentTimeMillis() + file.getOriginalFilename()));
        }
        return "course_admin/success";
    }

    /**
     * @Description: 处理多文件上传
     * @param: [multiRequest]
     * @return: java.lang.String
     * @Date: 2019/07/12 9:30
     */
    @RequestMapping(value = "/doUpload2", method = RequestMethod.POST)
    public String doUploadFile2(MultipartHttpServletRequest multiRequest) throws IOException {
        Iterator<String> filesNames = multiRequest.getFileNames();
        while (filesNames.hasNext()) {
            String fileName = filesNames.next();
            MultipartFile file = multiRequest.getFile(fileName);
            if (!file.isEmpty()) {
                log.debug(file.getOriginalFilename());
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\temp\\test\\", System.currentTimeMillis() + file.getOriginalFilename()));
            }

        }

        return "course_admin/success";
    }
}
