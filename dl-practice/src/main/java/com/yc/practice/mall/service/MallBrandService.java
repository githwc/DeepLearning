package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.common.constant.CommonConstant;
import com.yc.core.mall.entity.MallBrand;
import com.yc.core.mall.model.query.BrandQuery;
import com.yc.practice.common.log.WriteLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-03-17
 * @Version: 1.0.0
 *
 */
public interface MallBrandService extends IService<MallBrand> {

    /**
     * 商品品牌分页信息
     * @param page 分页信息
     * @param query 查询入参
     * @return page brand
     */
    Page<MallBrand> pageList(Page<MallBrand> page, BrandQuery query);

    /**
     * 获取商品品牌详情
     * @param mallBrankId 商品品牌主键
     * @return 商品品牌详情
     */
    MallBrand detail(String mallBrankId);

    /**
     * 修改商品品牌信息
     * @param mallBrand 品牌信息
     */
    void updateBrand(@RequestBody MallBrand mallBrand);

    /**
     * 添加商品品牌信息
     *
     * @param mallBrand 商品品牌信息
     */
    void addBrand(@RequestBody MallBrand mallBrand);

    /**
     * 删除商品品牌信息
     *
     * @param brandId 商品品牌ID
     */
    void delBrand(String brandId);
}
