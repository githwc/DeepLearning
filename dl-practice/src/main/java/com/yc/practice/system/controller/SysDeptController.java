package com.yc.practice.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.constant.CommonConstant;
import com.yc.core.system.entity.SysDept;
import com.yc.core.system.model.query.DeptQuery;
import com.yc.core.tree.TreeNode;
import com.yc.practice.common.log.WriteLog;
import com.yc.practice.system.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 功能描述：部门前端控制器
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
@RequestMapping("/sysDept")
@Slf4j
@Api(tags="部门管理")
public class SysDeptController {

    private final SysDeptService service;

    @Autowired
    public SysDeptController(SysDeptService service) {
        this.service = service;
    }

    @GetMapping(value = "/departTree")
    @ApiOperation(value = "加载部门树",notes = "加载所有部门树")
    @WriteLog(opPosition = "加载部门树")
    public List<TreeNode> departTree(@RequestParam(value = "departName",required = false)String departName){
        return service.departTree(departName);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "部门添加",notes = "部门添加")
    @WriteLog(opPosition = "部门添加" ,optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody SysDept sysDept) {
        service.create(sysDept);
    }

    @GetMapping("/childrenDept")
    @ApiOperation(value = "查询子级部门",notes = "查询子级部门")
    @WriteLog(opPosition = "查询子级部门")
    public Page<SysDept> childrenDept(Page<SysDept> page, DeptQuery deptQuery){
        return service.childrenDept(page,deptQuery);
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "部门修改",notes = "部门修改")
    @WriteLog(opPosition = "部门修改" ,optype = CommonConstant.OPTYPE_UPDATE)
    public void edit(@RequestBody SysDept sysDept) {
        service.editByDeptId(sysDept);
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "部门删除",notes = "部门删除")
    @WriteLog(opPosition = "部门删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void delete(@RequestParam("sysDeptId") String sysDeptId) {
        service.deleteAlone(sysDeptId);
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "部门批量删除",notes = "部门批量删除")
    @WriteLog(opPosition = "部门批量删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(@RequestParam("ids") String ids) {
        service.deleteBatch(ids);
    }

}
