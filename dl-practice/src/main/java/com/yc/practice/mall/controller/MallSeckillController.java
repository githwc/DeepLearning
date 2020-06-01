package com.yc.practice.mall.controller;

import com.yc.core.mall.entity.MallSeckill;
import com.yc.practice.mall.service.MallSeckillService;
import javafx.scene.AmbientLight;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-06-01
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/mallSeckill")
@Slf4j
public class MallSeckillController {

    private final MallSeckillService iMallSeckillService;

    @Autowired
    public MallSeckillController(MallSeckillService iMallSeckillService){
        this.iMallSeckillService = iMallSeckillService;
    }

    /**
     * 减库存
     * @param mallSeckillId 秒杀商品ID
     */
    @PutMapping("/cutSeckill")
    public void cutSeckill(@RequestParam("mallSeckillId") String mallSeckillId){
        this.iMallSeckillService.cutSeckill(mallSeckillId);
    }

    /**
     * 秒杀商品详情
     * @param mallSeckillId 主键
     * @return detail
     */
    @GetMapping("/mallSeckill")
    public MallSeckill mallSeckill(@RequestParam("mallSeckillId")String mallSeckillId){
        return this.iMallSeckillService.mallSeckill(mallSeckillId);
    }

}
