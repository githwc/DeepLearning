package com.yc.practice.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallGoodClass;
import com.yc.core.tree.TreeNode;
import com.yc.practice.mall.service.MallGoodClassService;
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
@RequestMapping("/mallGoodClass")
public class MallGoodClassController {


    public final MallGoodClassService iMallGoodClassService;

    @Autowired
    public MallGoodClassController(MallGoodClassService iMallGoodClassService){
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
     * 添加类目
     * @param mallGoodClass 类目信息
     */
    @PostMapping(value = "/add")
    public void add(@RequestBody MallGoodClass mallGoodClass) {
        iMallGoodClassService.add(mallGoodClass);
    }

    /**
     * 查询子级类目
     * @param page 分页信息
     * @param parentId 父级类别ID
     * @return page
     */
    @GetMapping("/childrenDept")
    public Page<MallGoodClass> childrenClass(Page<MallGoodClass> page, String parentId){
        return iMallGoodClassService.childrenClass(page,parentId);
    }

    /**
     * 编辑商品类目
     * @param mallGoodClass 类目信息
     */
    @PutMapping
    public void edit(@RequestBody MallGoodClass mallGoodClass) {
        iMallGoodClassService.editById(mallGoodClass);
    }

    /**
     * 删除类目
     * @param mallGoodClassId 类目ID
     */
    @DeleteMapping
    public void delete(@RequestParam("mallGoodClassId") String mallGoodClassId) {
        iMallGoodClassService.deleteAlone(mallGoodClassId);
    }

}
