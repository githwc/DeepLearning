package com.yc.practice.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.constant.CacheConstant;
import com.yc.common.constant.CommonConstant;
import com.yc.common.utils.YouBianCodeUtil;
import com.yc.core.system.entity.SysDept;
import com.yc.core.system.mapper.SysDeptMapper;
import com.yc.core.system.model.query.DeptQuery;
import com.yc.core.tree.Tree;
import com.yc.core.tree.TreeNode;
import com.yc.practice.common.dao.DaoApi;
import com.yc.practice.system.service.SysDeptService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* 功能描述：
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
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private DaoApi daoApi;

    @Autowired
    public SysDeptServiceImpl(DaoApi daoApi){
        this.daoApi = daoApi;
    }


    @Override
    public List<TreeNode> departTree(String departName) {
        List<TreeNode> list = this.baseMapper.departTree(departName);
       return Tree.getTreeList("#", list);
    }

    @Override
    public Page<SysDept> childrenDept(Page<SysDept> page, DeptQuery deptQuery) {
        return this.baseMapper.childrenDept(page,deptQuery);
    }

    @Override
    @CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
    public void editByDeptId(SysDept sysDept) {
        sysDept.setUpdateUserId(daoApi.getCurrUserId());
        this.updateById(sysDept);
    }

    @Override
    @CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
    public void deleteAlone(String id) {
        List<String> idList = new ArrayList<String>();
        SysDept sysDept = this.getById(id);
        if(sysDept == null) {
            throw new ErrorException(Error.DeptNotFound);
        }else {
            //逻辑删除子部门
            idList.add(id);
            this.checkChildrenExists(id, idList);
            idList.forEach(currId->{
                SysDept sysDept1 = new SysDept();
                sysDept1.setDelFlag(CommonConstant.DEL_FLAG_1);
                this.update(sysDept1,new LambdaQueryWrapper<SysDept>()
                        .eq(SysDept::getSysDeptId,currId)
                );
            });
        }
    }

    @Override
    public void deleteBatch(String ids) {
        if (ids == null || "".equals(ids.trim())) {
            throw new ErrorException(Error.ParameterNotFound);
        } else {
            List<String> list = Arrays.asList(ids.split(","));
            list.forEach(this::deleteAlone);
        }
    }

    @Override
    @CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
    public void create(SysDept sysDept) {
        if (sysDept != null ) {
            String[] codeAndLevel = this.generateOrgCode(sysDept.getParentId());
            sysDept.setUniqueCoding(codeAndLevel[0]);
            sysDept.setAdminLevel(Integer.parseInt(codeAndLevel[1]));
            sysDept.setCreateUserId(daoApi.getCurrUserId());
            if (StringUtils.isBlank(sysDept.getParentId())) {
                sysDept.setParentId("#");
            }
            this.save(sysDept);
        }
    }

    /**
     *  查询子级部门  ====== deleteAlone 子方法 =======
     * @param id 部门ID
     * @param idList idList
     */
    private void checkChildrenExists(String id, List<String> idList) {
        List<SysDept> departList = this.list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getParentId,id)
                .eq(SysDept::getDelFlag,CommonConstant.DEL_FLAG_0)
        );
        if(departList != null && departList.size() > 0) {
            for(SysDept depart : departList) {
                idList.add(depart.getSysDeptId());
                this.checkChildrenExists(depart.getSysDeptId(), idList);
            }
        }
    }

    /**
     * 生成部门编码及部门级别 ==== create 子方法 =======
     * @param parentId 父级编码
     * @return [部门编码,级别]
     */
    private String[] generateOrgCode(String parentId) {
        String[] strArray = new String[2];
        // 创建一个List集合,存储查询返回的所有SysDepart对象
        List<SysDept> departList;
        // 如果是最高级,则查询出同级的org_code, 调用工具类生成编码并返回
        if (StringUtil.isNullOrEmpty(parentId)) {
            strArray[1] = "1";
            // 先判断数据库中的表是否为空,空则直接返回初始编码
            departList = this.list(new LambdaQueryWrapper<SysDept>()
                    .eq(SysDept::getParentId, "#")
                    .or()
                    .isNull(SysDept::getParentId)
            );
            if(ObjectUtil.isNull(departList)) {
                strArray[0] = YouBianCodeUtil.getNextYouBianCode(null);
                return strArray;
            }else {
                SysDept depart = departList.get(0);
                strArray[0] = YouBianCodeUtil.getNextYouBianCode(depart.getUniqueCoding());
            }
        } else { // 反之则查询出所有同级的部门,获取结果后有两种情况,有同级和没有同级
            List<SysDept> parentList = this.list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getParentId,parentId)
                    .orderByDesc(SysDept::getUniqueCoding)
            );
            // 父级部门
            SysDept depart = this.getById(parentId);
            strArray[1] = String.valueOf(depart.getAdminLevel() + 1);
            // 处理同级部门为null的情况
            if (parentList == null || parentList.size() == 0) {
                // 直接生成当前的部门编码并返回
                strArray[0] = YouBianCodeUtil.getSubYouBianCode(depart.getUniqueCoding(), null);
            } else { //处理有同级部门的情况
                // 返回生成的当前部门编码
                strArray[0] = YouBianCodeUtil.getSubYouBianCode(depart.getUniqueCoding(), parentList.get(0).getUniqueCoding());
            }
        }
        return strArray;
    }
}
