package com.yc.practice.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.model.form.SysUserForm;
import com.yc.core.system.model.query.UserQuery;
import com.yc.core.system.model.vo.SysUserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 登出
     *
     * @param request  请求
     * @param response 响应
     */
    void logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 加载用户数据
     *
     * @param page      分页信息
     * @param userQuery 查询条件
     * @return 分页数据
     */
    Page<SysUserVO> userList(Page<SysUser> page, UserQuery userQuery);

    /**
     * 添加用户
     *
     * @param jsonObject 用户信息
     */
    void add(JSONObject jsonObject);

    /**
     * 修改用户
     *
     * @param sysUserForm 用户信息
     */
    void edit(SysUserForm sysUserForm);

    /**
     * 登录账号唯一性检测
     *
     * @param loginName 登录名
     */
    void checkIsOnly(String loginName);

    /**
     * 单个删除
     *
     * @param id id
     */
    void deleteUser(String id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    void deleteBatch(String ids);

    /**
     * 查询用户拥有角色
     *
     * @param userId 用户id
     * @return 角色s
     */
    List<String> queryUserRole(String userId);

    /**
     * 重置密码
     *
     * @param sysUserId 用户ID
     */
    void resetPassword(String sysUserId);

    /**
     * 修改密码
     *
     * @param sysUser 用户信息
     * @return success || error
     */
    String updatePassword(SysUser sysUser);

    /**
     * 获取聊天对象
     *
     * @param page 分页信息
     * @return list
     */
    Page<SysUserVO> chatPage(Page<SysUser> page);

}
