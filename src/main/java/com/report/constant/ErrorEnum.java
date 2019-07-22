package com.report.constant;


public enum ErrorEnum {

    LACKPARAMETER(50001, "参数缺失"),
    DATASTATUSERROR(50002, "数据已失效，刷新重试"),
    SERVER_ERROR(50000, "服务器异常"),
    MISS_REQUEST_PARAMTER(50003, "缺少参数"),
    PERMISSION_NOT_EMPTY(50004, "没有权限"),
    TEAM_NULL(50005, "工作组不存在"),
    PARAMS_ERROR(50006, "参数错误"),
    PASSWORD_ERROR(50007, "密码错误"),
    PASSWORD_SAME(50008, "新旧密码重复"),
    ACCOUNT_NOT_EXIST(50009, "用户不存在"),
    NOT_LOGIN(50010, "没有登录"),
    UPDATE_ERROR(50011, "更新失败"),
    NOT_FOUND(50012, "记录未找到"),
    USERNAME_EXIST(50013,"用户名已存在"),
    NO_WORKTEAM(50014,"工作组不存在"),
    ;

    private int code;

    private String msg;

    ErrorEnum(int code, String msg) {
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
