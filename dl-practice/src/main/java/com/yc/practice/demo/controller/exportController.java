package com.yc.practice.demo.controller;

import com.yc.practice.demo.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：导入导出 控制层
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-02-16
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/demo/export")
@Slf4j
public class exportController {

    private final ExportService service;

    @Autowired
    public exportController(ExportService service) {
        this.service = service;
    }

    /**
     * 导出Word
     * @param response 响应
     */
    @PostMapping(value = "/exportWord")
    public void export(HttpServletResponse response) {
        service.export(response);
    }
}
