package org.example.service.impl;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.example.query.PageQuery;
import org.example.query.Query;
import org.example.service.IService;

import java.time.LocalDate;
import java.util.List;

import static com.baomidou.mybatisplus.core.enums.SqlKeyword.ASC;
import static com.baomidou.mybatisplus.core.enums.SqlKeyword.DESC;


public class ServiceImpl<M extends BaseMapper<T>, T> extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<M, T> implements IService<T> {


    @Override
    public LambdaQueryChainWrapper<T> getQueryCondition(Query<T> query) {
        LambdaQueryChainWrapper<T> lambdaQuery = lambdaQuery();
        LocalDate beginDate = query.getBeginDate();
        LocalDate endDate = query.getEndDate();
        if (beginDate != null) {
            lambdaQuery.apply("create_time > {0}", beginDate);
        }
        if (endDate != null) {
            endDate = endDate.plusDays(1);
            lambdaQuery.apply("create_time < {0}", endDate);
        }
        String orderByColumn = query.getOrderByColumn();
        boolean orderByDesc = query.isOrderByDesc();
        SFunction orderByColumnFunc = query.getOrderByColumnFunc();

        // 默认排序
        if (StringUtils.isBlank(orderByColumn) && orderByColumnFunc == null) {
            lambdaQuery.getWrapper().getExpression().add(SqlKeyword.ORDER_BY, () -> "id", DESC);
        } else if (StringUtils.isNotBlank(orderByColumn)) {

            String[] camelCase = StringUtils.splitByCharacterTypeCamelCase(orderByColumn);
            for (int i = 0; i < camelCase.length; i++) {
                camelCase[i] = camelCase[i].toLowerCase();
            }
            orderByColumn = StringUtils.join(camelCase, "_");
            String _orderByColumn = orderByColumn;
            if (orderByDesc) {
                lambdaQuery.getWrapper().getExpression().add(SqlKeyword.ORDER_BY, () -> _orderByColumn, DESC);
            } else {
                lambdaQuery.getWrapper().getExpression().add(SqlKeyword.ORDER_BY, () -> _orderByColumn, ASC);
            }
        } else {
            if (orderByDesc) {
                lambdaQuery.orderByDesc(orderByColumnFunc);
            } else {
                lambdaQuery.orderByAsc(orderByColumnFunc);
            }
        }

        Long id = query.getId();
        if (id != null) {
            lambdaQuery.getWrapper().getExpression().add(() -> "id", SqlKeyword.EQ, () -> String.valueOf(id));
        }
        return lambdaQuery;
    }

    @Override
    public Page<T> getPageData(PageQuery<T> pageQuery) {
        LambdaQueryChainWrapper<T> condition = getQueryCondition(pageQuery);
        return condition.page(pageQuery.getPage());
    }

    @Override
    public List<T> getData(Query<T> query) {
        return getQueryCondition(query).list();
    }
}
