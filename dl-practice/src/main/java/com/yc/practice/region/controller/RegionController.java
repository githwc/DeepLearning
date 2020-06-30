package com.yc.practice.region.controller;

import cn.hutool.core.lang.tree.Tree;
import com.yc.practice.region.service.RegionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述:行政区划 控制层
 *
 * @Author: xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/region")
@Slf4j
@Api(tags = "行政区划")
public class RegionController {

    private final RegionService service;

    @Autowired
    public RegionController(RegionService service) {
        this.service = service;
    }

    /**
     * 区域级联信息
     *
     * @return case list
     */
    @GetMapping("/list")
    public List<Tree<String>> list() {
        return service.listRegion();
    }

}
