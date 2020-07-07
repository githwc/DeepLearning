package com.yc.mini.mall.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mini.entity.MallFloor;
import com.yc.core.mini.mapper.MallFloorMapper;
import com.yc.mini.mall.service.MallFloorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-06-26
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MallFloorServiceImpl extends ServiceImpl<MallFloorMapper, MallFloor> implements MallFloorService {

    @Override
    public List<Tree<String>> tree() {
        List<MallFloor> list = baseMapper.selectList(Wrappers.<MallFloor>lambdaQuery()
                .orderByAsc(MallFloor::getSort)
        );

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        return TreeUtil.build(list, "0", treeNodeConfig, (floor, treeNode) -> {
            treeNode.setId(floor.getMallFloorId());
            treeNode.setParentId(floor.getMallFloorPid());
            treeNode.setName(floor.getName());
            treeNode.putExtra("image", floor.getImage());
            treeNode.putExtra("imageWidth", floor.getImageWidth());
            treeNode.putExtra("openType", floor.getOpenType());
            treeNode.putExtra("navigatorUrl", floor.getNavigatorUrl());
            treeNode.putExtra("sort", floor.getSort());
        });
    }
}
