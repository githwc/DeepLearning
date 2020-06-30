package com.yc.core.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallProduct;
import com.yc.core.mall.model.query.GoodQuery;
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
public interface MallProductMapper extends BaseMapper<MallProduct> {

    /**
     * 商品分页信息
     * @param page 分页
     * @param query 入参
     * @return page
     */
    Page<MallProduct> goodPage(@Param("page")Page<MallProduct> page, @Param("query") GoodQuery query);

}
