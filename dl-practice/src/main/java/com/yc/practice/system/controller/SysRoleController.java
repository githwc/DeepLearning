package com.yc.practice.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.constant.CommonConstant;
import com.yc.common.constant.CommonEnum;
import com.yc.core.system.entity.SysRole;
import com.yc.core.system.model.query.RoleQuery;
import com.yc.practice.common.UserUtil;
import com.yc.practice.common.log.WriteLog;
import com.yc.practice.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 功能描述:角色前端控制器
 *
 * @Author: xieyc && 紫色年华
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/sysRole")
@Slf4j
@Api(tags = "系统角色")
public class SysRoleController {

    private final SysRoleService service;

    @Autowired
    public SysRoleController(SysRoleService service) {
        this.service = service;
    }

    @GetMapping(value = "/rolePage")
    @ApiOperation(value = "查询所有角色", notes = "加载所有角色(分页)")
    @WriteLog(opPosition = "查询所有角色")
    @PreAuthorize("hasAuthority('ISYSTEM:ROLE:QUERY')")
    public Page<SysRole> rolePage(Page<SysRole> page, RoleQuery roleQuery) {
        return service.rolePage(page, roleQuery);
    }

    @GetMapping(value = "/roleList")
    @ApiOperation(value = "查询所有角色", notes = "查询所有角色(新增用户时调用)")
    @WriteLog(opPosition = "查询所有角色")
    public List<SysRole> roleList() {
        return service.roleList();
    }

    @PostMapping
    @ApiOperation(value = "角色添加/更新", notes = "角色添加/更新")
    @WriteLog(opPosition = "角色添加/更新", optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody SysRole sysRole) {
        if (StringUtils.isNotBlank(sysRole.getSysRoleId())) {
            service.updateById(sysRole);
        } else {
            sysRole.setCreateUserId(UserUtil.getUserId());
            service.save(sysRole);
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "角色删除", notes = "角色删除")
    @WriteLog(opPosition = "角色删除", optype = CommonConstant.OPTYPE_DELETE)
    public void delete(String sysRoleId) {
        SysRole sysRole = new SysRole();
        sysRole.setSysRoleId(sysRoleId);
        sysRole.setDelFlag(CommonEnum.DelFlag.DEL.getCode());
        service.updateById(sysRole);
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "角色批量删除", notes = "角色批量删除")
    @WriteLog(opPosition = "角色批量删除", optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(String ids) {
        List<String> listIds = Arrays.asList(ids.split(","));
        listIds.forEach(curr -> {
            SysRole sysRole = new SysRole();
            sysRole.setSysRoleId(curr);
            sysRole.setDelFlag(CommonEnum.DelFlag.DEL.getCode());
            service.updateById(sysRole);
        });
    }

    @GetMapping("/duplicate")
    @ApiOperation(value = "重复校验", notes = "角色代码唯一性校验")
    @WriteLog(opPosition = "重复校验")
    public void duplicate(String roleCode) {
        service.duplicate(roleCode);
    }

    @GetMapping(value = "/rolePermission")
    @ApiOperation(value = "查询角色授权", notes = "查询角色拥有的权限")
    @WriteLog(opPosition = "查询角色授权")
    public List<String> queryRolePermission(String sysRoleId) {
        return service.queryRolePermission(sysRoleId);
    }

    @PostMapping(value = "/rolePermission")
    @ApiOperation(value = "保存角色授权", notes = "保存角色拥有的权限")
    @WriteLog(opPosition = "保存角色授权", optype = CommonConstant.OPTYPE_CREATE)
    public void saveRolePermission(@RequestBody JSONObject json) {
        service.saveRolePermission(json);
    }
}
