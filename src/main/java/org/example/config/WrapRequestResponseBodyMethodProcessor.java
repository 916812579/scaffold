package org.example.config;

import org.example.annotation.UnWrap;
import org.example.response.Result;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.lang.reflect.Method;

public class WrapRequestResponseBodyMethodProcessor implements HandlerMethodReturnValueHandler {

    private RequestResponseBodyMethodProcessor proxy;

    public WrapRequestResponseBodyMethodProcessor(RequestResponseBodyMethodProcessor proxy) {
        this.proxy = proxy;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Method method = returnType.getMethod();
        UnWrap annotation = method.getAnnotation(UnWrap.class);
        if (annotation == null) {
            annotation = method.getDeclaringClass().getAnnotation(UnWrap.class);
        }
        return proxy.supportsReturnType(returnType) || annotation != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Method method = returnType.getMethod();
        UnWrap annotation = method.getAnnotation(UnWrap.class);
        if (annotation == null) {
            annotation = method.getDeclaringClass().getAnnotation(UnWrap.class);
        }
        if (annotation == null) {
            if (returnValue != null || !(returnValue instanceof Result)) {
                returnValue = new Result(returnValue);
            }
        }
        proxy.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
