package com.yc.core.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallGood;
import com.yc.core.mall.model.query.GoodQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-04-08
 * @Version: 1.0.0
 *
 */
@Repository
public interface MallGoodMapper extends BaseMapper<MallGood> {

    /**
     * 商品分页信息
     * @param page 分页
     * @param query 入参
     * @return page
     */
    Page<MallGood> goodPage(@Param("page")Page<MallGood> page, @Param("query") GoodQuery query);
}
