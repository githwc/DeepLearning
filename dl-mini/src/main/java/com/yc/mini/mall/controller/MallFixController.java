package com.yc.mini.mall.controller;

import com.yc.core.mini.entity.MallFix;
import com.yc.mini.mall.service.MallFixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-06-26
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/mallFix")
@Slf4j
public class MallFixController {

    private final MallFixService service;

    @Autowired
    public MallFixController(MallFixService iMallFixService) {
        this.service = iMallFixService;
    }

    /**
     * 获取轮播图或导航栏
     *
     * @return list
     */
    @GetMapping("/list")
    public List<MallFix> listFix() {
        return service.listFix();
    }

}
