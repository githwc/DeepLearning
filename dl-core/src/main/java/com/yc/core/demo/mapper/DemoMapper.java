package com.yc.core.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.demo.entity.Demo;
import com.yc.core.demo.model.DemoQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-04-17
 * @Version: 1.0.0
 *
 */
@Repository
public interface DemoMapper extends BaseMapper<Demo> {

    /**
     * 数据分页查询
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    Page<Demo> demoPage(@Param("page") Page<Demo> page, @Param("query") DemoQuery query);
}
