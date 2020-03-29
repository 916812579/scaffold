package org.example.exception;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.response.Result;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Order
public class GlobalExceptionHandler {

    private Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result badArgumentHandler(IllegalArgumentException e) {
        printError(e);
        return new Result(ExceptionEnum.ARGUMENT_ERROR);
    }

    private void printError(Exception e) {
        logger.error(e.getMessage(), e);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Result badArgumentHandler(MethodArgumentTypeMismatchException e) {
        printError(e);
        return new Result(ExceptionEnum.ARGUMENT_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result badArgumentHandler(MissingServletRequestParameterException e) {
        printError(e);
        return new Result(ExceptionEnum.ARGUMENT_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result badArgumentHandler(HttpMessageNotReadableException e) {
        printError(e);
        return new Result(ExceptionEnum.ARGUMENT_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Result badArgumentHandler(ValidationException e) {
        printError(e);
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                String message = ((PathImpl) item.getPropertyPath()).getLeafNode().getName() + item.getMessage();
                return new Result(402, message);
            }
        }
        return new Result(ExceptionEnum.ARGUMENT_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessException(Exception e) {
        printError(e);
        return new Result(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindException(Exception e) {
        printError(e);
        BindException bindException = (BindException) e;
        List<String> errors = new ArrayList<>();
        for (ObjectError allError : bindException.getBindingResult().getAllErrors()) {
            errors.add(allError.getDefaultMessage());
        }
        return new Result(StringUtils.join(errors, ", "));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result seriousHandler(Exception e) {
        printError(e);
        return new Result(e.getMessage());
    }

}
