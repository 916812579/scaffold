package org.example.query;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 分页查询条件
 */
@Data
public class PageQuery<T> extends Query<T> {


    // 分页参数

    /**
     * 查询页
     */
    private Integer pageNum = 1;

    /**
     * 查询页大小
     */
    private Integer pageSize = 10;

    /**
     * 返回mybatis-plus分页对象
     * @return
     */
    public Page<T> getPage() {
        Page<T> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return page;
    }

}
