package org.example.vo;

import lombok.Data;
import org.example.annotation.JsonEnum;
import org.example.constant.TestEnum;

@Data
public class TestVO {

    @JsonEnum(value = TestEnum.class)
    private Integer code = 1;
}
