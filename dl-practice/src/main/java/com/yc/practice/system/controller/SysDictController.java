package com.yc.practice.system.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.constant.CommonConstant;
import com.yc.core.system.entity.SysDict;
import com.yc.core.system.model.query.DictQuery;
import com.yc.practice.common.log.WriteLog;
import com.yc.practice.system.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述:字典前端控制器
 *
 * @Author: xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/sysDict")
@Slf4j
@Api(tags = "系统字典")
public class SysDictController {

    private final SysDictService service;

    @Autowired
    public SysDictController(SysDictService service) {
        this.service = service;
    }

    @GetMapping(value = "/dictTree")
    @ApiOperation(value = "加载字典树", notes = "加载所有字典")
    @WriteLog(opPosition = "加载字典树")
    public List<Tree<String>> dictTree(@RequestParam(value = "name", required = false) String name) {
        return service.dictTree(name);
    }

    @GetMapping("/childrenDict")
    @ApiOperation(value = "查询子级字典", notes = "查询子级字典")
    @WriteLog(opPosition = "查询子级字典")
    public Page<SysDict> childrenDict(Page<SysDict> page, DictQuery dictQuery) {
        return service.childrenDict(page, dictQuery);
    }

    @PostMapping
    @ApiOperation(value = "字典添加/修改", notes = "字典添加/修改")
    @WriteLog(opPosition = "字典添加/修改", optype = CommonConstant.OPTYPE_CREATE)
    public void saveDict(@RequestBody SysDict sysDict) {
        service.saveDict(sysDict);
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "字典删除", notes = "字典删除")
    @WriteLog(opPosition = "字典删除", optype = CommonConstant.OPTYPE_DELETE)
    public void delete(String sysDictId) {
        service.deleteAlone(sysDictId);
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "字典批量删除", notes = "字典批量删除")
    @WriteLog(opPosition = "字典批量删除", optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(String ids) {
        service.deleteBatch(ids);
    }

    @GetMapping("/getDict")
    @ApiOperation(value = "查询指定字典", notes = "查询指定字典")
    @WriteLog(opPosition = "查询指定字典")
    public List<SysDict> getDict(String skey) {
        return service.getDict(skey);
    }
}
