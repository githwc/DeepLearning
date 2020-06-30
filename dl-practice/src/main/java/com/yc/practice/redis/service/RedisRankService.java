package com.yc.practice.redis.service;


import java.util.Map;
import java.util.Set;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-02-01
 * @Version: 1.0.0
 */
public interface RedisRankService {

    /**
     * 初始化数据
     */
    void initRankData();

    /**
     * 获取数据
     * @return set
     */
    Set getData();

    /**
     * 初始化数据
     */
    void clearData();


    /**
     * 获取排行榜top10
     * @return
     */
    Set top10(String type);

    /**
     * 新增学生成绩
     */
    void add();

    /**
     * 查询指定人的排名和分数
     * @return
     */
    Map userInfo();

    /**
     * .统计分数区间人数
     * @return
     */
    Long scopeCount();

    /**
     * 使用加法操作分数
     *  直接在原有的score上使用加法;
     *  如果没有这个元素，则会创建，并且score初始为0.再使用加法
     * @return
     */
    void addScore();

}
