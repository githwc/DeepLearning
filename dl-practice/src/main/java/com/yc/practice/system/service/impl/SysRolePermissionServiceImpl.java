package com.yc.practice.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.system.entity.SysRolePermission;
import com.yc.core.system.mapper.SysRolePermissionMapper;
import com.yc.practice.system.service.SysRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2019-09-20
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

}
