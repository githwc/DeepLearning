package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.ProductCategory;
import com.yc.core.mall.mapper.ProductCategoryMapper;
import com.yc.practice.mall.service.ProductCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品类目impl
 *
 * @author xieyc && 紫色年华
 * @date 2020/6/29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {


}
