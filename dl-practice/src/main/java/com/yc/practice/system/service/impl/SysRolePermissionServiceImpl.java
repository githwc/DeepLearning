package com.yc.practice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.system.entity.SysRolePermission;
import com.yc.core.system.mapper.SysRolePermissionMapper;
import com.yc.practice.system.service.SysRolePermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: LionHerding
* @Author xieyc && 紫色年华
* @Date 2019-09-20
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public void saveRolePermission(String roleId, String permissionIds, String lastPermissionIds) {
        List<String> add = getDiff(lastPermissionIds,permissionIds);
        if(add!=null && add.size()>0) {
            List<SysRolePermission> list = new ArrayList<SysRolePermission>();
            for (String p : add) {
                if(StringUtils.isNotEmpty(p)) {
                    SysRolePermission rolepms = new SysRolePermission(roleId, p);
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }
        List<String> delete = getDiff(permissionIds,lastPermissionIds);
        if(delete!=null && delete.size()>0) {
            for (String permissionId : delete) {
                this.remove(new LambdaQueryWrapper<SysRolePermission>()
                        .eq(SysRolePermission::getRoleId, roleId)
                        .eq(SysRolePermission::getPermissionId, permissionId));
            }
        }
    }


    /**
     * 从diff中找出main中没有的元素
     * @param main
     * @param diff
     * @return
     */
    private List<String> getDiff(String main,String diff){
        if(StringUtils.isEmpty(diff)) {
            return null;
        }
        if(StringUtils.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }
        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<String>();
        for (String key : diffArr) {
            if(StringUtils.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }

}
