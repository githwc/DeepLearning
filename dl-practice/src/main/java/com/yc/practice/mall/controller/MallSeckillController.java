package com.yc.practice.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallSeckill;
import com.yc.core.mall.model.form.SeckillForm;
import com.yc.core.mall.model.vo.SeckillVO;
import com.yc.practice.mall.service.MallSeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 功能描述：秒杀商品控制层
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
     * 秒杀商品分页查询
     * @param page 分页信息
     * @return page
     */
    @GetMapping("/page")
    public Page<MallSeckill> mallSeckillPage(Page<MallSeckill> page){
        return iMallSeckillService.mallSeckillPage(page);
    }

    /**
     * 增加秒杀商品
     * @param mallSeckill 商品信息
     */
    @PostMapping
    public void add(@RequestBody MallSeckill mallSeckill){
        iMallSeckillService.add(mallSeckill);
    }

    /**
     * 修改秒杀商品信息
     * @param mallSeckill 商品信息
     */
    @PutMapping
    public void updateSeckill(@RequestBody MallSeckill mallSeckill){
        iMallSeckillService.updateSeckill(mallSeckill);
    }

    /**
     * 删除指定秒杀商品
     * @param mallSeckillId 秒杀商品ID
     */
    @DeleteMapping
    public void delete(@RequestParam("mallSeckillId") String mallSeckillId) {
        iMallSeckillService.deleteAlone(mallSeckillId);
    }

    /**
     * 秒杀商品详情
     * @param mallSeckillId 主键
     * @return (系统时间,加密串,秒杀ID)
     */
    @GetMapping("/mallSeckill")
    public SeckillVO mallSeckill(@RequestParam("mallSeckillId")String mallSeckillId){
        return this.iMallSeckillService.mallSeckill(mallSeckillId);
    }

    /**
     * 执行秒杀
     * @param seckillForm 秒杀提交页
     */
    @PutMapping("/execSeckill")
    public void execSeckill(@RequestBody SeckillForm seckillForm){
        this.iMallSeckillService.execSeckill(seckillForm);
    }

}
