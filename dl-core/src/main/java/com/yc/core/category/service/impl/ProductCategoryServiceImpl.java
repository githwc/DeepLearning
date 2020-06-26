package com.yc.core.category.service.impl;

import com.yc.core.category.entity.ProductCategory;
import com.yc.core.category.mapper.ProductCategoryMapper;
import com.yc.core.category.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2020-06-18
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

}
