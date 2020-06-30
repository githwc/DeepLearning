package com.yc.practice.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallShipping;
import com.yc.practice.mall.service.MallShippingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 功能描述:收货地址控制层
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-05-08
 * @Version: 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/mallShipping")
public class MallShippingController {

    private final MallShippingService iMallShippingService;

    @Autowired
    public MallShippingController (MallShippingService iMallShippingService){
        this.iMallShippingService = iMallShippingService;
    }

    /**
     * 我的收货地址
     * @param page 分页信息
     * @return page
     */
    @GetMapping("/page")
    public Page<MallShipping> shippingPage(Page<MallShipping> page){
        return iMallShippingService.shippingPage(page);
    }

    /**
     * 添加/更新收货地址
     * @param mallShipping 地址信息
     */
    @PostMapping
    public void saveShipping(@RequestBody MallShipping mallShipping){
        iMallShippingService.saveShipping(mallShipping);
    }

    /**
     * 删除指定收货地址
     * @param mallShippingId 收货地址ID
     */
    @DeleteMapping
    public void delete(String mallShippingId) {
        iMallShippingService.deleteAlone(mallShippingId);
    }

    /**
     * 查询我的所有收货地址
     * @return 我的所有收货地址
     */
    @GetMapping("/shipingList")
    public List<MallShipping> shipingList(){
        return iMallShippingService.shipingList();
    }
}
