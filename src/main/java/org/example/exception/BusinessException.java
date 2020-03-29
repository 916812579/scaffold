package org.example.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class BusinessException extends RuntimeException {

    private int code;

    private String message;

    public BusinessException(IExceptionInfo exceptionInfo, Object... args) {
        if (exceptionInfo == null) {
            exceptionInfo = ExceptionEnum.UNKNOWN;
        }

        this.code = exceptionInfo.getCode();
        if (args != null) {
            this.message = String.format(exceptionInfo.getDesc(), args);
        } else {
            this.message = exceptionInfo.getDesc();
        }
    }

    public BusinessException(Exception exception) {
        code = ExceptionEnum.UNKNOWN.getCode();
        message = exception.getMessage();
    }

    public BusinessException(String message) {
        code = ExceptionEnum.UNKNOWN.getCode();
        this.message = message;
    }

}
