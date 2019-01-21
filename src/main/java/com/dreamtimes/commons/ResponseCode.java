package com.dreamtimes.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    PARAM_EMPTY(2,"参数为空"),
    USER_NOT_EXSIT(3,"用户不存在"),
    USERNAME_EXSIT(4,"用户已存在"),
    EMAIL_EXSIT(5,"邮箱已存在"),
    TYPE_ERROR(6,"类型输入错误"),
    INSERT_USER_ERROR(7,"添加用户失败")
    ;
    private Integer status;
    private String msg;
}
