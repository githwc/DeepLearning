package com.yc.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.core.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 功能描述:
 *
 *

 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色
     * @param loginName
     * @return
     */
    List<SysRole> getUserRoles(@Param("loginName") String loginName);

}
