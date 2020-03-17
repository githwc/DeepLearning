package com.yc.practice.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.config.exception.RunException.RunningException;
import com.yc.common.constant.CommonConstant;
import com.yc.practice.common.dao.DaoApi;
import com.yc.practice.common.log.WriteLog;
import com.yc.core.system.entity.SysRole;
import com.yc.core.system.model.query.RoleQuery;
import com.yc.practice.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 功能描述：角色前端控制器
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author   xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysRole")
@Slf4j
@Api(tags = "系统角色")
public class SysRoleController {

    private final SysRoleService service;
    private final DaoApi daoApi;

    @Autowired
    public SysRoleController(SysRoleService service,DaoApi daoApi) {
        this.service = service;
        this.daoApi = daoApi;
    }

    @GetMapping(value = "/rolePage")
    @ApiOperation(value = "查询所有角色",notes = "加载所有角色(分页)")
    @WriteLog(opPosition = "查询所有角色")
    public Page<SysRole> rolePage(Page<SysRole> page, RoleQuery roleQuery) {
        try{
            return service.rolePage(page,roleQuery);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping(value = "/roleList")
    @ApiOperation(value = "查询所有角色",notes = "查询所有角色(新增用户时调用)")
    @WriteLog(opPosition = "查询所有角色")
    public List<SysRole> roleList() {
        try{
            return service.roleList();
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "角色添加",notes = "角色添加")
    @WriteLog(opPosition = "角色添加" ,optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody SysRole sysRole) {
        try {
            sysRole.setCreateUserId(daoApi.getCurrentUserId());
            service.save(sysRole);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "角色修改",notes = "角色修改")
    @WriteLog(opPosition = "角色修改" ,optype = CommonConstant.OPTYPE_UPDATE)
    public void edit(@RequestBody SysRole role) {
        try {
            service.updateById(role);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "角色删除",notes = "角色删除")
    @WriteLog(opPosition = "角色删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void delete(@RequestParam("sysRoleId") String id) {
        try {
            SysRole sysRole = new SysRole();
            sysRole.setSysRoleId(id);
            sysRole.setDelFlag(CommonConstant.DEL_FLAG_1);
            service.updateById(sysRole);
        }catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "角色批量删除",notes = "角色批量删除")
    @WriteLog(opPosition = "角色批量删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(@RequestParam("ids") String ids) {
        try {
            List<String> listIds = Arrays.asList(ids.split(","));
            listIds.forEach(curr->{
                SysRole sysRole = new SysRole();
                sysRole.setSysRoleId(curr);
                sysRole.setDelFlag(CommonConstant.DEL_FLAG_1);
                service.updateById(sysRole);
            });
        }catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping("/duplicate")
    @ApiOperation(value = "重复校验",notes = "角色代码唯一性校验")
    @WriteLog(opPosition = "重复校验")
    public void duplicate(@RequestParam("roleCode") String roleCode){
        try {
            service.duplicate(roleCode);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping(value = "/rolePermission")
    @ApiOperation(value = "查询角色授权", notes = "查询角色拥有的权限")
    @WriteLog(opPosition = "查询角色授权")
    public List<String> queryRolePermission(@RequestParam("sysRoleId") String roleId) {
        try {
            return service.queryRolePermission(roleId);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @PostMapping(value = "/rolePermission")
    @ApiOperation(value = "保存角色授权", notes = "保存角色拥有的权限")
    @WriteLog(opPosition = "保存角色授权" ,optype = CommonConstant.OPTYPE_CREATE)
    public void saveRolePermission(@RequestBody JSONObject json) {
        try {
            service.saveRolePermission(json);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }
}
