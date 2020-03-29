package org.example.service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.query.PageQuery;
import org.example.query.Query;

import java.util.List;

public interface IService<T> extends com.baomidou.mybatisplus.extension.service.IService<T> {

    /**
     * 根据通用条件进行查询
     *
     * @param query
     * @return
     */
    LambdaQueryChainWrapper<T> getQueryCondition(Query<T> query);

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    Page<T> getPageData(PageQuery<T> pageQuery);

    /**
     * 查询数据
     * @return
     */
    List<T> getData(Query<T> query);

}
