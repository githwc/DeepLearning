package com.yc.practice.region.controller;

import com.yc.practice.region.service.RegionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/region")
@Slf4j
@Api(tags="行政区划")
public class RegionController {

    private final RegionService service;

    @Autowired
    public RegionController(RegionService service) {
        this.service = service;
    }
}
