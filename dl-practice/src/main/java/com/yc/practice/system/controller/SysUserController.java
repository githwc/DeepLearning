package com.yc.practice.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.constant.CommonConstant;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.model.query.UserQuery;
import com.yc.core.system.model.vo.SysUserVO;
import com.yc.practice.common.log.WriteLog;
import com.yc.practice.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 功能描述：系统用户前端控制器
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/sysUser")
@Slf4j
@Api(tags = "系统用户")
public class SysUserController {

    private final SysUserService service;

    @Autowired
    public SysUserController(SysUserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public JSONObject login(@RequestBody SysUser sysUser) {
        return service.login(sysUser);
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "用户登出", notes = "用户登出")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        service.logout(request, response);
    }

    @GetMapping(value = "/userList")
    @ApiOperation(value = "查询用户", notes = "查询某个部门下的有效用户")
    @WriteLog(opPosition = "查询用户")
    public Page<SysUserVO> userList(Page<SysUserVO> page, UserQuery userQuery) {
        return service.userList(page, userQuery);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "用户添加", notes = "用户添加")
    @WriteLog(opPosition = "用户添加", optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody JSONObject jsonObject) {
        service.add(jsonObject);
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "用户修改", notes = "用户修改")
    @WriteLog(opPosition = "用户修改", optype = CommonConstant.OPTYPE_UPDATE)
    public void edit(@RequestBody JSONObject jsonObject) {
        service.edit(jsonObject);
    }

    @GetMapping("/checkIsOnly")
    @ApiOperation(value = "账号唯一性检测", notes = "登录账号唯一性检测")
    @WriteLog(opPosition = "账号唯一性检测")
    public void checkIsOnly(String loginName) {
        service.checkIsOnly(loginName);
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @WriteLog(opPosition = "删除用户", optype = CommonConstant.OPTYPE_DELETE)
    public void delete(@RequestParam("sysUserId") String sysUserId) {
        service.deleteUser(sysUserId);
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除用户", notes = "批量删除用户")
    @WriteLog(opPosition = "批量删除用户", optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(@RequestParam("sysUserIds") String ids) {
        service.deleteBatch(ids);
    }

    @GetMapping(value = "/queryUserRole")
    @ApiOperation(value = "查询用户拥有角色", notes = "查询用户拥有角色")
    @WriteLog(opPosition = "查询用户拥有角色")
    public List<String> queryUserRole(@RequestParam("sysUserId") String userId) {
        return service.queryUserRole(userId);
    }


    @PostMapping(value = "/resetPassword")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @WriteLog(opPosition = "重置密码", optype = CommonConstant.OPTYPE_UPDATE)
    public void resetPassword(@RequestBody SysUser sysUser) {
        service.resetPassword(sysUser.getSysUserId());
    }

}
