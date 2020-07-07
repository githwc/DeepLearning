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
 * 功能描述:秒杀商品控制层
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-06-01
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/mallSeckill")
@Slf4j
public class MallSeckillController {

    private final MallSeckillService iMallSeckillService;

    @Autowired
    public MallSeckillController(MallSeckillService iMallSeckillService) {
        this.iMallSeckillService = iMallSeckillService;
    }

    /**
     * 高并发高发点:
     *  1.前端页面资源获取:cdn:内容分发网络,加速用户资源获取
     *  2.秒杀信息获取:放入redis缓存
     *  3.执行秒杀操作优化:由于Java执行sql,获取锁后再执行sql.由此产生线程等待(等待行锁).一次事务(包含网络延迟,GC(50ms))耗时大约2ms,则QPS为500;
     *                  所以应该把客户端逻辑放在Mysql服务端，避免网络延迟和GC影响
     *              3.1 如何放到Mysql服务端
     *                      两种解决方案：
     *                      1.定制Sql方案：update /+[auto_commit]/，需要修改mysql源码
     *                      2.使用存储过程：整个事务在Mysql端完成。
     *  具体执行:
     *      1.redis统一存储秒杀信息,防止频繁刷新引起线程堵塞
     *      2.使用存储过程,执行记录购买信息和减库存等业务逻辑，提高响应速度
     */

    /**
     * 秒杀商品详情
     *
     * @param mallSeckillId 主键
     * @return (系统时间, 加密串, 秒杀ID)
     */
    @GetMapping("/mallSeckill")
    public SeckillVO mallSeckill(String mallSeckillId) {
        return this.iMallSeckillService.mallSeckill(mallSeckillId);
    }

    /**
     * 执行秒杀
     *
     * @param seckillForm 秒杀提交页
     */
    @PutMapping("/execSeckill")
    public void execSeckill(@RequestBody SeckillForm seckillForm) {
        // this.iMallSeckillService.execSeckill(seckillForm);
        this.iMallSeckillService.execSeckillByProcedure(seckillForm);
    }

    // ============ CRUD =================

    /**
     * 秒杀商品分页查询
     *
     * @param page 分页信息
     * @return page
     */
    @GetMapping("/page")
    public Page<MallSeckill> pageMallSeckill(Page<MallSeckill> page) {
        return iMallSeckillService.pageMallSeckill(page);
    }

    /**
     * 增加/更新秒杀商品
     *
     * @param mallSeckill 商品信息
     */
    @PostMapping
    public void save(@RequestBody MallSeckill mallSeckill) {
        iMallSeckillService.saveMallSeckill(mallSeckill);
    }

    /**
     * 删除指定秒杀商品
     *
     * @param mallSeckillId 秒杀商品ID
     */
    @DeleteMapping
    public void delete(String mallSeckillId) {
        iMallSeckillService.deleteAlone(mallSeckillId);
    }
}
