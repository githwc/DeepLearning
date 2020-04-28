package com.yc.practice.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.demo.entity.Demo;
import com.yc.core.demo.model.DemoQuery;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-04-17
 * @Version: 1.0.0
 *
 */
public interface DemoService extends IService<Demo> {

    /**
     * 数据分页查询
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    Page<Demo> demoPage(Page<Demo> page, DemoQuery query);

    /**
     * 数据添加
     * @param demo 添加信息
     */
    void add(Demo demo);

    /**
     * 修改
     * @param demo 修改信息
     */
    void editById(Demo demo);

    /**
     * 删除
     * @param demoId
     */
    void deleteAlone(String demoId);


}
