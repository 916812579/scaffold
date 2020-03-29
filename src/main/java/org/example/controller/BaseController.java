package org.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.query.PageQuery;
import org.example.query.Query;
import org.example.service.IService;
import org.example.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 通用Controller
 */
public class BaseController<M, T extends IService> {

    @Autowired
    private T service;


    /**
     * 分页查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    @ResponseBody
    public Page<M> getPageData(PageQuery<M> query) {
        return service.getPageData(query);
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @GetMapping("/data")
    @ResponseBody
    public List<M> getData(Query<M> query) {
        return service.getData(query);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public boolean remove(String ids) {
        return service.removeByIds(CommonUtils.stringToLong(ids));
    }
}
