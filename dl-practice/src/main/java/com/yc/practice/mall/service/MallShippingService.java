package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallShipping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-05-08
 * @Version: 1.0.0
 */
public interface MallShippingService extends IService<MallShipping> {

    /**
     * 我的收货地址
     * @param page 分页信息
     * @return page
     */
    Page<MallShipping> shippingPage(Page<MallShipping> page);

    /**
     * 添加/更新收货地址
     * @param mallShipping 地址信息
     */
    void saveShipping(MallShipping mallShipping);

    /**
     * 删除指定收货地址
     * @param mallShippingId 收货地址ID
     */
    void deleteAlone(String mallShippingId);

    /**
     * 查询我的所有收货地址
     * @return 我的所有收货地址
     */
    List<MallShipping> shipingList();

}
