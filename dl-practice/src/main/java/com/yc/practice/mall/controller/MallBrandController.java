package com.yc.practice.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.constant.CommonConstant;
import com.yc.core.mall.entity.MallBrand;
import com.yc.core.mall.model.query.BrandQuery;
import com.yc.practice.common.log.WriteLog;
import com.yc.practice.mall.service.MallBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述： 商品品牌 控制层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-17
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/mallBrand")
public class MallBrandController {

    private final MallBrandService mallBrandService;

    @Autowired
    public MallBrandController(MallBrandService mallBrandService){
        this.mallBrandService = mallBrandService;
    }

    /**
     * 商品品牌分页信息
     * @param page 分页信息
     * @param query 查询入参
     * @return page brand
     */
    @GetMapping("/page")
    @WriteLog(opPosition = "商品品牌分页信息")
    public Page<MallBrand> pageList(Page<MallBrand> page, BrandQuery query){
        return mallBrandService.pageList(page,query);
    }

    /**
     * 获取商品品牌详情
     * @param mallBrandId 商品品牌主键
     * @return 商品品牌详情
     */
    @GetMapping("/detail")
    @WriteLog(opPosition = "商品品牌详情")
    public MallBrand detail(String mallBrandId){
        return mallBrandService.detail(mallBrandId);
    }

    /**
     * 添加商品品牌信息
     *
     * @param mallBrand 商品品牌信息
     */
    @PostMapping("/add")
    @WriteLog(opPosition = "添加商品品牌" ,optype = CommonConstant.OPTYPE_CREATE)
    public void addBrand(@RequestBody MallBrand mallBrand) {
        mallBrandService.addBrand(mallBrand);
    }

    /**
     * 删除商品品牌信息
     *
     * @param brandId 商品品牌ID
     */
    @DeleteMapping("/delete")
    @WriteLog(opPosition = "删除商品品牌" ,optype = CommonConstant.OPTYPE_DELETE)
    public void delBrand(String brandId) {
        mallBrandService.delBrand(brandId);
    }

    /**
     * 修改商品品牌信息
     * @param mallBrand 品牌信息
     */
    @PutMapping("/update")
    @WriteLog(opPosition = "修改商品品牌信息" ,optype = CommonConstant.OPTYPE_UPDATE)
    public void updateBrand(@RequestBody MallBrand mallBrand) {
        mallBrandService.updateBrand(mallBrand);
    }

}
