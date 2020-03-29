package org.example.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestEnum implements IEnum {

    OK(1, "Ok")
    ;

    private int code;

    private String desc;
}
