package com.bdqn.exception;

/**
 * ClassName: {@link EmBusinessError}
 * create by:  xyf
 * description: TODO  业务错误的枚举（需要实现CommonError中的方法，即CommonError的实现枚举）
 * create time: 2019/9/7 10:10
 */
public enum EmBusinessError implements CommonError {
    //通用的错误类型10000开头(有些情况用户名没有传入、邮箱为填写都可以使用)
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),

    //   20000开头为用户信息相关的错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_NOT_EXIST1(20002, "用户列表不存在"),
    USER_NAMEORPASSWORD(20003, "用户名或密码错误"),

    //   30000开头为供应商信息相关的错误定义
    PROVIDER_NOT_EXIST(30001, "供应商信息不存在"),

    //   40000开头为角色信息相关的错误定义
    ROLES_NOT_EXIST(40001, "角色信息不存在"),
    ;

    private int errCode;//错误代码
    private String errMsg;//错误描述

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * description: TODO 获取错误的错误代码
     * create time: 2019/9/7 10:11
     * []
     *
     * @return int
     */
    @Override
    public int getErrorCode() {

        return this.errCode;
    }

    /**
     * description: TODO 获取错误的错误信息
     * create time: 2019/9/7 10:11
     * []
     *
     * @return java.lang.String
     */
    @Override
    public String getErrMsg() {

        return this.errMsg;
    }

    /**
     * description: TODO 手动设置错误的业务信息(通过定制化的方式处理一些通用的错误类型)
     * create time: 2019/9/7 10:11
     * [errMsg]
     *
     * @return com.bdqn.exception.CommonError
     */
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        //        return this就是返回当前对象的引用(就是实际调用这个方法的实例化对象)
        return this;
    }
}
