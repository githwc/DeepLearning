package com.yc.core.mall.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallShipping;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-05-08
 * @Version: 1.0.0
 */
@Repository
public interface MallShippingMapper extends BaseMapper<MallShipping> {

    /**
     * 我的收货地址
     * @param page 分页信息
     * @return page
     */
    Page<MallShipping> shippingPage(@Param("page") Page<MallShipping> page,@Param("sysUserId")String sysUserId);

}
