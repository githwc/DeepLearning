package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallShipping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-05-08
 * @Version: 1.0.0
 *
 */
public interface MallShippingService extends IService<MallShipping> {

    /**
     * 我的收货地址
     * @param page 分页信息
     * @return page
     */
    Page<MallShipping> shippingPage(Page<MallShipping> page);

    /**
     * 添加收货地址
     * @param mallShipping 地址信息
     */
    void add(MallShipping mallShipping);

    /**
     * 修改商品信息
     * @param mallShipping 地址信息
     */
    void updateShipping(MallShipping mallShipping);

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
