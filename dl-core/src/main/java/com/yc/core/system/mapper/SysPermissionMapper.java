package com.yc.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.core.cascade.CaseTopLevel;
import com.yc.core.system.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户名称查询用户权限
     * @param loginName 登录名称
     * @return 权限
     */
    List<SysPermission> queryPermissionByUser(@Param("loginName") String loginName);

    @Update("update sys_permission set is_leaf=#{leaf} where sys_permission_id = #{id}")
    int setMenuLeaf(@Param("id") String id,@Param("leaf") int leaf);

    /**
     * 权限菜单级联list
     * @return list
     */
    List<CaseTopLevel> caseList();
}
