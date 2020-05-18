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
 * 功能描述：收货地址控制层
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
@Slf4j
@RestController
@RequestMapping("/mallShipping")
public class MallShippingController {

    public MallShippingService iMallShippingService;

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
     * 添加收货地址
     * @param mallShipping 地址信息
     */
    @PostMapping
    public void add(@RequestBody MallShipping mallShipping){
        iMallShippingService.add(mallShipping);
    }

    /**
     * 修改收货地址
     * @param mallShipping 收货地址信息
     */
    @PutMapping
    public void updateShipping(@RequestBody MallShipping mallShipping){
        iMallShippingService.updateShipping(mallShipping);
    }

    /**
     * 删除指定收货地址
     * @param mallShippingId 收货地址ID
     */
    @DeleteMapping
    public void delete(@RequestParam("mallShippingId") String mallShippingId) {
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
