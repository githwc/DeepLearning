package com.yc.practice.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.config.exception.RunException.RunningException;
import com.yc.common.constant.CommonConstant;
import com.yc.common.log.WriteLog;
import com.yc.core.system.entity.SysDept;
import com.yc.core.system.model.query.DeptQuery;
import com.yc.core.tree.TreeNode;
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
@Api(tags="系统部门")
public class SysDeptController {

    private final SysDeptService service;

    @Autowired
    public SysDeptController(SysDeptService service) {
        this.service = service;
    }

    @GetMapping(value = "/departTree")
    @ApiOperation(value = "加载部门树",notes = "加载所有部门树")
    @WriteLog(opPosition = "加载部门树" ,optype = CommonConstant.OPTYPE_READ)
    public List<TreeNode> departTree(@RequestParam(value = "departName",required = false)String departName){
        try {
            return service.departTree(departName);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping("/childrenDept")
    @ApiOperation(value = "查询子级部门",notes = "查询子级部门")
    @WriteLog(opPosition = "查询子级部门" ,optype = CommonConstant.OPTYPE_READ)
    public Page<SysDept> childrenDept(Page<SysDept> page, DeptQuery deptQuery){
        return service.childrenDept(page,deptQuery);
    }

    @ApiOperation(value = "部门添加",notes = "部门添加")
    @PostMapping(value = "/add")
    @WriteLog(opPosition = "部门添加" ,optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody SysDept sysDept) {
        try {
            service.create(sysDept);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "部门修改",notes = "部门修改")
    @WriteLog(opPosition = "部门修改" ,optype = CommonConstant.OPTYPE_UPDATE)
    public void edit(@RequestBody SysDept sysDept) {
        try{
            service.editByDeptId(sysDept);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "部门删除",notes = "部门删除")
    @WriteLog(opPosition = "部门删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void delete(@RequestParam("sysDeptId") String sysDeptId) {
        try{
            service.deleteAlone(sysDeptId);
        }catch(Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "部门批量删除",notes = "部门批量删除")
    @WriteLog(opPosition = "部门批量删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(@RequestParam("ids") String ids) {
        try{
            service.deleteBatch(ids);
        }catch(Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }



}
