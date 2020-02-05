package com.yc.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.system.entity.SysRole;
import com.yc.core.system.model.query.RoleQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色
     * @param loginName
     * @return
     */
    List<SysRole> getUserRoles(@Param("loginName") String loginName);

    /**
     * 角色查询
     * @param page
     * @param roleQuery
     * @return
     */
    Page<SysRole> roleList(@Param("page") Page<SysRole> page, @Param("query") RoleQuery roleQuery);
}
