package com.yc.practice.system.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CommonEnum;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.utils.YouBianCodeUtil;
import com.yc.core.system.entity.SysDept;
import com.yc.core.system.mapper.SysDeptMapper;
import com.yc.core.system.model.query.DeptQuery;
import com.yc.practice.common.UserUtil;
import com.yc.practice.system.service.SysDeptService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<Tree<String>> departTree(String departName) {
        List<SysDept> list = baseMapper.selectList(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getDelFlag, 0)
                .eq(StringUtils.isNotBlank(departName), SysDept::getDepartName, departName)
                .orderByAsc(SysDept::getSort)
        );
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setNameKey("title");
        return TreeUtil.build(list, "#", treeNodeConfig, (dept, treeNode) -> {
            treeNode.setId(dept.getSysDeptId());
            treeNode.setParentId(dept.getParentId());
            treeNode.setName(dept.getDepartName());
            treeNode.putExtra("orderNum", dept.getSort());
        });
    }

    @Override
    public Page<SysDept> childrenDept(Page<SysDept> page, DeptQuery deptQuery) {
        return baseMapper.selectPage(page, new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getDelFlag, false)
                .eq(SysDept::getParentId, deptQuery.getParentId())
                .like(StringUtils.isNotBlank(deptQuery.getDepartName()), SysDept::getDepartName, deptQuery.getDepartName())
                .orderByAsc(SysDept::getSort)
                .orderByDesc(SysDept::getCreateTime)
        );
    }

    @Override
    public void deleteAlone(String id) {
        List<String> idList = new ArrayList<String>();
        SysDept sysDept = this.getById(id);
        if (sysDept == null) {
            throw new ErrorException(Error.DeptNotFound);
        } else {
            //逻辑删除子部门
            idList.add(id);
            this.checkChildrenExists(id, idList);
            idList.forEach(currId -> {
                SysDept sysDept1 = new SysDept();
                sysDept1.setDelFlag(CommonEnum.DelFlag.DEL.getCode());
                this.update(sysDept1, new LambdaQueryWrapper<SysDept>()
                        .eq(SysDept::getSysDeptId, currId)
                );
            });
        }
    }

    @Override
    public void deleteBatch(String ids) {
        if (StringUtils.isBlank(ids)) {
            throw new ErrorException(Error.ParameterNotFound);
        } else {
            List<String> list = Arrays.asList(ids.split(","));
            list.forEach(this::deleteAlone);
        }
    }

    @Override
    public void saveDept(SysDept sysDept) {
        if (StringUtils.isNotBlank(sysDept.getSysDeptId())) {
            sysDept.setUpdateUserId(UserUtil.getUserId());
            this.updateById(sysDept);
        } else {
            if (ObjectUtil.isNotNull(sysDept)) {
                String[] codeAndLevel = this.generateOrgCode(sysDept.getParentId());
                sysDept.setUniqueCoding(codeAndLevel[0]);
                sysDept.setAdminLevel(Integer.parseInt(codeAndLevel[1]));
                sysDept.setCreateUserId(UserUtil.getUserId());
                if (StringUtils.isBlank(sysDept.getParentId())) {
                    sysDept.setParentId("root");
                }
                this.save(sysDept);
            }
        }
    }

    /**
     * 查询子级部门  ====== deleteAlone 子方法 =======
     *
     * @param id     部门ID
     * @param idList idList
     */
    private void checkChildrenExists(String id, List<String> idList) {
        List<SysDept> departList = this.list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getParentId, id)
                .eq(SysDept::getDelFlag, CommonEnum.DelFlag.NO_DEL.getCode())
        );
        if (departList != null && departList.size() > 0) {
            for (SysDept depart : departList) {
                idList.add(depart.getSysDeptId());
                this.checkChildrenExists(depart.getSysDeptId(), idList);
            }
        }
    }

    /**
     * 生成部门编码及部门级别 ==== create 子方法 =======
     *
     * @param parentId 父级编码
     * @return [部门编码, 级别]
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
                    .eq(SysDept::getParentId, "root")
                    .or()
                    .isNull(SysDept::getParentId)
                    .orderByDesc(SysDept::getUniqueCoding)
            );
            if (ObjectUtil.isNull(departList)) {
                strArray[0] = YouBianCodeUtil.getNextYouBianCode(null);
                return strArray;
            } else {
                SysDept depart = departList.get(0);
                strArray[0] = YouBianCodeUtil.getNextYouBianCode(depart.getUniqueCoding());
            }
        } else { // 反之则查询出所有同级的部门,获取结果后有两种情况,有同级和没有同级
            List<SysDept> parentList = this.list(new LambdaQueryWrapper<SysDept>()
                    .eq(SysDept::getParentId, parentId)
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
