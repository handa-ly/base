package com.example.demo.result;

/**
 * @description:
 * @author: handa
 * @time: 2019/11/11 10:50
 */
public class MustException extends RuntimeException {

    private int errCode = ResultCode.Unknown_Exception.getCode();

    public MustException() {
        super(ResultCode.Unknown_Exception.getMsg());
    }

    public MustException(int code, String msg) {
        super(msg);
        this.errCode = code;
    }

    public MustException(String msg) {
        super(msg);
    }

    public MustException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.errCode = resultCode.getCode();
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }


}
