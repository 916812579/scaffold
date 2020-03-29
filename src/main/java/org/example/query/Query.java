package org.example.query;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.Data;

import java.time.LocalDate;

/**
 * 普通查询条件
 */
@Data
public class Query<T> {

    private final static String ASC = "asc";

    private final static String DESC = "desc";


    /**
     * 对象ID
     */
    private Long id;

    /**
     * 开始时间
     */
    private LocalDate beginDate;

    /**
     * 结束时间
     */
    private LocalDate endDate;

    /**
     * 排序字段
     */
    private String orderByColumn;

    /**
     * 排序方法
     */
    private SFunction<T, ?> orderByColumnFunc;

    /**
     * 是否升序
     */
    private String isAsc;

    public void setAsc() {
        isAsc = ASC;
    }

    public void setDesc() {
        isAsc = DESC;
    }

    public boolean isOrderByDesc() {
        return isAsc != null && isAsc.equalsIgnoreCase(ASC);
    }

}
