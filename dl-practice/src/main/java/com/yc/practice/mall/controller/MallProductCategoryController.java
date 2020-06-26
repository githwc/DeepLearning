package com.yc.practice.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallProductCategory;
import com.yc.core.tree.TreeNode;
import com.yc.practice.mall.service.MallProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 功能描述：商品类目控制层
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-05-08
 * @Version: 1.0.0
 *
 */
@Slf4j
@RestController
@RequestMapping("/mallProductCategory")
public class MallProductCategoryController {

    private final MallProductCategoryService iMallGoodClassService;

    @Autowired
    public MallProductCategoryController(MallProductCategoryService iMallGoodClassService){
        this.iMallGoodClassService = iMallGoodClassService;
    }

    /**
     * 类目树
     * @return 树
     */
    @GetMapping(value = "/classTree")
    public List<TreeNode> classTree(){
        return iMallGoodClassService.classTree();
    }

    /**
     * 添加/更新类目
     * @param mallProductCategory 类目信息
     */
    @PostMapping
    public void add(@RequestBody MallProductCategory mallProductCategory) {
        iMallGoodClassService.add(mallProductCategory);
    }

    /**
     * 查询子级类目
     * @param page 分页信息
     * @param parentId 父级类别ID
     * @return page
     */
    @GetMapping("/childrenClass")
    public Page<MallProductCategory> childrenClass(Page<MallProductCategory> page, String parentId){
        return iMallGoodClassService.childrenClass(page,parentId);
    }

    /**
     * 删除类目
     * @param mallProductCategoryId 类目ID
     */
    @DeleteMapping
    public void delete(String mallProductCategoryId) {
        iMallGoodClassService.deleteAlone(mallProductCategoryId);
    }

    /**
     * 类目级联信息
     * @return case list
     */
    @GetMapping("/classList")
    public List<MallProductCategory> classList(){
        return iMallGoodClassService.classList();
    }


}
