package com.yc.practice.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.demo.entity.Demo;
import com.yc.core.demo.model.DemoQuery;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-04-17
 * @Version: 1.0.0
 */
public interface DemoService extends IService<Demo> {

    /**
     * 数据分页查询
     *
     * @param page  分页信息
     * @param query 入参
     * @return page
     */
    Page<Demo> demoPage(Page<Demo> page, DemoQuery query);

    /**
     * 数据添加
     *
     * @param demo 添加信息
     */
    void saveDemo(Demo demo);

    /**
     * 删除
     *
     * @param demoId
     */
    void deleteAlone(String demoId);


}
