package com.yc.mini.mall.controller;

import cn.hutool.core.lang.tree.Tree;
import com.yc.mini.mall.service.MallProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述:商品类目控制层
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-05-08
 * @Version: 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/mallProductCategory")
public class MallProductCategoryController {

    private final MallProductCategoryService iMallGoodClassService;

    @Autowired
    public MallProductCategoryController(MallProductCategoryService iMallGoodClassService) {
        this.iMallGoodClassService = iMallGoodClassService;
    }

    /**
     * 类目树
     *
     * @return 树
     */
    @GetMapping(value = "/tree")
    public List<Tree<String>> classTree() {
        return iMallGoodClassService.mallProductTree();
    }


}
