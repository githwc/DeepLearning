package com.yc.practice.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.demo.entity.Demo;
import com.yc.core.demo.model.DemoQuery;
import com.yc.practice.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 功能描述：Demo 控制层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-17
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    private final DemoService service;

    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }

    /**
     * 数据分页查询
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    @GetMapping("/page")
    public Page<Demo> demoPage(Page<Demo> page, DemoQuery query){
        return service.demoPage(page,query);
    }

    /**
     * 数据添加
     * @param demo 添加信息
     */
    @PostMapping
    public void add(@RequestBody Demo demo) {
        service.add(demo);
    }

    /**
     * 修改
     * @param demo 修改信息
     */
    @PutMapping
    public void edit(@RequestBody Demo demo) {
        service.editById(demo);
    }

    /**
     * 删除
     * @param demoId 主键
     */
    @DeleteMapping
    public void delete(@RequestParam("demoId") String demoId) {
        service.deleteAlone(demoId);
    }

}
