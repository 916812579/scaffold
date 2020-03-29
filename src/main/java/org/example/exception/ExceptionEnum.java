package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements IExceptionInfo {
    UNKNOWN(100, "未知错误"),
    ARGUMENT_ERROR(200, "参数错误"),
    USERNAME_OR_PASSWORD_ERROR(300, "用户名或密码错误"),
    VERIFICATION_CODE_ERROR(400, "验证码错误"),
    SYSTEM_DATA(500, "系统%s,禁止删除");



    private int code;

    private String desc;
}
