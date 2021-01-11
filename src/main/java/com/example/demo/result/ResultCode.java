package com.example.demo.result;

public enum ResultCode {
//    SUCCESS(200, "请求成功"),
    SUCCESS(200, "請求成功"),
//    Unknown_Exception(-1, "未知异常"),
    Unknown_Exception(-1, "未知異常"),


//    ROLE_ID_NULL(10001,"角色id为空！"),
    ROLE_ID_NULL(10001,"角色id為空！"),
//    PARAMTER_IS_NULL(10002,"%s不能为空!"),
    PARAMTER_IS_NULL(10002,"%s不能為空!"),
    LOGIN_ERROR(10003,"用戶名或密碼不正確!"),

//    USER_ACCOUNT_EMPTY(20001,"用户账号为空！"),
    USER_ACCOUNT_EMPTY(20001,"用戶賬號為空！"),
//    USER_ACCOUNT_REGISTRYED(20002,"用户账号已注册！"),
    USER_ACCOUNT_REGISTRYED(20002,"用戶賬號已註冊！"),
//    NAME_REPEAT(20003,"%s重复！"),
    NAME_REPEAT(20003,"%s重複！"),
//    NOT_All_EXIST(20004,"%s不能同时存在！"),
    NOT_All_EXIST(20004,"%s不能同時为空！"),
//    NOT_LEGAL(20005,"%s输人不合法！"),
    NOT_LEGAL(20005,"%s輸入不合法！"),
    NOT_EXIST(20006,"%s不存在"),
//    DATASOURC_REPORT(20007,"数据已存在，请不要重复添加！"),
    DATASOURC_REPORT(20007,"數據已存在，請不要重複添加！"),
    DATA_COLUMN_TOO_LONG(20008,"%s數據長度過長！"),
    MUST_HAVEING(20009,"必須%s"),
    UPLOAD_FAIL(20010,"文件上傳失敗"),
    DATAFORMAT_FAIL(20011,"时间格式不对"),
	DATA_CHANGE_ERROR(20012,"%s数据类型转换错误"),
	FIEL_CREATE_FAIL(20013,"创建文件目录失败,%s"),
	SQL_ERROR(20015,"sql不合法！"),
	IS_NULL(20014,"無可導出數據！");



    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
