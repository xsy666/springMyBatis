package com.bdqn.exception;

/**
 * ClassName: {@link BusinessException}
 * create by:  xyf
 * description: TODO 业务异常类(包装器业务异常类实现)
 * create time: 2019/9/7 10:06
 */
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;//这里的CommonError本质是CommonError的实现类EmBusinessError


    /**
     * description: TODO 直接接受EmBusinessError的传参用于构造业务异常
     * create time: 2019/9/7 10:07
     * [commonError]
     *
     * @return
     */
    public BusinessException(CommonError commonError) {
        super();//必须需要显示调用super()方法，因为这里没有写无参的构造方法
        this.commonError = commonError;
    }

    /**
     * description: TODO 接受自定义errMsg的方式构造业务异常
     * create time: 2019/9/7 10:08
     * [commonError, errMsg]
     *
     * @return
     */
    public BusinessException(CommonError commonError, String errMsg) throws BusinessException {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    /**
     * description: TODO 获取错误的错误代码
     * create time: 2019/9/7 10:08
     * []
     *
     * @return int
     */
    @Override
    public int getErrorCode() {
        return commonError.getErrorCode();
    }

    /**
     * description: TODO 获取错误的错误信息
     * create time: 2019/9/7 10:08
     * []
     *
     * @return java.lang.String
     */
    @Override
    public String getErrMsg() {

        return commonError.getErrMsg();
    }

    /**
     * description: TODO 手动设置错误的业务信息(通过定制化的方式处理一些通用的错误类型)
     * create time: 2019/9/7 10:09
     * [errMsg]
     *
     * @return com.bdqn.exception.CommonError
     */
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
