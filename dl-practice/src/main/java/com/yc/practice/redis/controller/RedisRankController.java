package com.yc.practice.redis.controller;

import com.yc.practice.redis.service.RedisRankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 *
 * 功能描述：排行榜 暴露接口 API
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-02-01
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/redisPractice/redisRank")
@Slf4j
public class RedisRankController {

    private final RedisRankService iRedisRankService;

    @Autowired
    public RedisRankController(RedisRankService iRedisRankService){
        this.iRedisRankService = iRedisRankService;
    }

    /**
     * 初始化数据
     */
    @PostMapping("/initRankData")
    public void initRankData(){
        iRedisRankService.initRankData();
    }

    /**
     * 获取数据
     * @return set
     */
    @GetMapping("/getData")
    public Set getData(){
        return iRedisRankService.getData();
    }

    /**
     * 清除数据
     */
    @PostMapping("/clearData")
    public void clearData(){
        iRedisRankService.clearData();
    }

    /**
     * 获取总成绩排行榜top10
     * @return set
     */
    @GetMapping("/scoreTop10")
    public Set top10(@RequestParam("type")String type){
        return iRedisRankService.top10(type);
    }

    /**
     * 新增一名学生的成绩到排行榜中
     */
    @PostMapping("/add")
    public void add(){
        iRedisRankService.add();
    }

    /**
     * 查询指定人的排名和分数
     * @return
     */
    @GetMapping("/userInfo")
    public Map userInfo(){
       return iRedisRankService.userInfo();
    }

    /**
     * .统计分数区间人数
     * @return
     */
    @GetMapping("/scopeCount")
    public Long scopeCount(){
        return iRedisRankService.scopeCount();
    }

    /**
     * 使用加法操作分数
     *  直接在原有的score上使用加法;
     *  如果没有这个元素，则会创建，并且score初始为0.再使用加法
     * @return
     */
    @PostMapping("/addScore")
    public void addScore(){
        iRedisRankService.addScore();
    }

    @Scheduled(cron="0 0/1 * * * ?")
    private void cancelTimeOutOrder() {
        log.debug("123Nih你好");
        log.info("123Nih你好");
        log.warn("123Nih你好");
        log.trace("123Nih你好");
        System.out.println(LocalDateTime.now());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        log.info("取消订单，并根据sku编号释放锁定库存");
    }

}
