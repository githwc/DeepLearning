package com.yc.practice.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.config.exception.RunException.RunningException;
import com.yc.common.config.exception.parameterException.ParameterException;
import com.yc.common.constant.CommonConstant;
import com.yc.practice.common.dao.DaoApi;
import com.yc.core.system.entity.SysDict;
import com.yc.core.system.mapper.SysDictMapper;
import com.yc.core.system.model.query.DictQuery;
import com.yc.core.tree.Tree2;
import com.yc.core.tree.TreeNode2;
import com.yc.practice.system.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final static String MODE_KEY_VALUE = "0";
    private final static String MODE_VALUE_KEY = "1";

    @Autowired
    private DaoApi daoApi;

    @Override
    public List<TreeNode2> dictTree(String name) {
        List<TreeNode2> list = this.baseMapper.dictTree(name);
        Tree2 tree = new Tree2(list).setRoot("字典管理").build();
        return tree.getRootNodes();
    }

    @Override
    public Page<SysDict> childrenDict(Page<SysDict> page, DictQuery dictQuery) {
        return this.baseMapper.childrenDict(page, dictQuery);
    }

    @Override
    public void editByDictId(SysDict sysDict) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",sysDict.getParentId());
        queryWrapper.eq("del_flag",0);
        queryWrapper.ne("sys_dict_id",sysDict.getSysDictId());
        queryWrapper.and(wrapper-> wrapper.eq("name",sysDict.getName()).or().eq("value",sysDict.getValue()));
        List<SysDict> sysDicts = this.baseMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(sysDicts)){
            throw new RuntimeException("存在重复字典项,请重新填写！");
        }
        sysDict.setUpdateUserId(daoApi.getCurrentUserId());
        this.updateById(sysDict);
    }

    @Override
    public void deleteAlone(String id) {
        List<String> idList = new ArrayList<String>();
        SysDict sysDict = this.getById(id);
        if (sysDict == null) {
            throw new RunningException("未找到对应实体");
        } else {
            //逻辑删除子级字典
            idList.add(id);
            this.checkChildrenExists(id, idList);
            idList.forEach(currId -> {
                SysDict sysDict1 = new SysDict();
                sysDict1.setDelFlag(CommonConstant.DEL_FLAG_1);
                this.update(sysDict1, new LambdaQueryWrapper<SysDict>()
                        .eq(SysDict::getSysDictId, currId)
                );
            });
        }
    }

    @Override
    public void deleteBatch(String ids) {
        if (ids == null || "".equals(ids.trim())) {
            throw new ParameterException("参数不识别！");
        } else {
            List<String> list = Arrays.asList(ids.split(","));
            list.forEach(this::deleteAlone);
        }
    }

    @Override
    public void create(SysDict sysDict) {
        if (sysDict != null) {
            sysDict.setCreateUserId(daoApi.getCurrentUserId());
            if (StringUtils.isBlank(sysDict.getParentId())) {
                sysDict.setParentId("#");
            }else{
                QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("parent_id",sysDict.getParentId());
                queryWrapper.eq("del_flag",0);
                queryWrapper.and(wrapper-> wrapper.eq("name",sysDict.getName()).or().eq("value",sysDict.getValue()));
                List<SysDict> sysDicts = this.baseMapper.selectList(queryWrapper);
                if(ObjectUtil.isNotEmpty(sysDicts)){
                    throw new RunningException("存在重复字典项,请重新填写！");
                }
            }
            this.save(sysDict);
        }
    }

    /**
     * 查询子级字典  ====== deleteAlone 子方法 =======
     *
     * @param id     字典ID
     * @param idList idList
     */
    private void checkChildrenExists(String id, List<String> idList) {
        List<SysDict> dictList = this.list(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getParentId, id)
                .eq(SysDict::getDelFlag, CommonConstant.DEL_FLAG_0)
        );
        if (dictList != null && dictList.size() > 0) {
            for (SysDict dict : dictList) {
                idList.add(dict.getSysDictId());
                this.checkChildrenExists(dict.getSysDictId(), idList);
            }
        }
    }

    @Override
    public List<SysDict> getDict(String sKey) {
        // 字典路径检查
        Object[] keys = sKey.replaceAll(" ", "").split(">");
        if (keys.length <= 0) {
            throw new RunningException("字典路径不能为空，禁止读取根字典信息!");
        }
        if(keys.length != 2){
            throw new RunningException("字典路径格式有误！");
        }
        return this.baseMapper.getDictByRoute(keys[0],keys[1]);
    }

    //  =============== 核心方法 END ===============
}
