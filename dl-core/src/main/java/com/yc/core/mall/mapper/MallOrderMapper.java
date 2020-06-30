package com.yc.core.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.model.query.OrderQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
@Repository
public interface MallOrderMapper extends BaseMapper<MallOrder> {

    /**
     * 订单分页查询
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    Page<MallOrder> page(@Param("page")Page<MallOrder> page, @Param("query") OrderQuery query);

}
