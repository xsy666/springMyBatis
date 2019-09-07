package com.bdqn.exception;
/**
 * ClassName: {@link CommonError}
 * create by:  xyf
 * description: TODO 全局错误接口
 * create time: 2019/9/7 10:04
 */
public interface CommonError {

    /**
     * description: TODO 获取错误的错误代码
     * create time: 2019/9/7 10:04
     * []
     * @return int
     */
    int getErrorCode();


    /**
     * description: TODO 获取错误的错误信息
     * create time: 2019/9/7 10:05
     * []
     * @return java.lang.String
     */
    String getErrMsg();


    /**
     * description: TODO  手动设置错误的业务信息(通过定制化的方式处理一些通用的错误类型)
     * create time: 2019/9/7 10:05
     * [errMsg]
     * @return com.bdqn.exception.CommonError
     */
    CommonError setErrMsg(String errMsg);
}
