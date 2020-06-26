package com.yc.practice.mall.controller;

import com.yc.practice.mall.service.MallFloorService;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-06-26
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/mall/mall-floor")
@Slf4j
public class MallFloorController {

    @Autowired
    public MallFloorService iMallFloorService;

}
