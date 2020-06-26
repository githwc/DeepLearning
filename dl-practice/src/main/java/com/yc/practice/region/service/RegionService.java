package com.yc.practice.region.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.cascadeList.CaseTopLevel;
import com.yc.core.region.entity.Region;
import org.springframework.web.bind.annotation.GetMapping;

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
public interface RegionService extends IService<Region> {

    /**
     * 区域级联信息
     * @return case list
     */
    List<CaseTopLevel> regionList();
}
