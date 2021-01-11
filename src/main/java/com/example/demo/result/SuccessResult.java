package com.example.demo.result;

/**
 * @description:
 * @author: handa
 * @time: 2019/11/11 10:46
 */
public class SuccessResult<T> extends Result<T> {
    public SuccessResult(){
        super(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());

    }

    public SuccessResult(T data) {
        super(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }
}
