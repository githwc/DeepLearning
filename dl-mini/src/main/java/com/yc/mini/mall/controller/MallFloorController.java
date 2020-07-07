package com.yc.mini.mall.controller;

import cn.hutool.core.lang.tree.Tree;
import com.yc.mini.mall.service.MallFloorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * 功能描述: 商品楼层控制层
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-06-26
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/mallFloor")
@Slf4j
public class MallFloorController {

    private final MallFloorService service;

    @Autowired
    public MallFloorController(MallFloorService service){
        this.service = service;
    };

    /**
     * 楼层树
     * @return tree
     */
    @GetMapping("/tree")
    public List<Tree<String>> tree(){
        return service.tree();
    };


}
