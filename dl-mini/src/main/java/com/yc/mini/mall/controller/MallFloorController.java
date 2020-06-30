package com.yc.mini.mall.controller;

import com.yc.mini.mall.service.MallFloorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-06-26
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/mallFloor")
@Slf4j
public class MallFloorController {

    @Autowired
    public MallFloorService iMallFloorService;

}
