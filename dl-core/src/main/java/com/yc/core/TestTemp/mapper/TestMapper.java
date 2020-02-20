package com.yc.core.TestTemp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.core.TestTemp.entity.Test;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-29
 * @Version: 1.0.0
 *
 */
@Repository
public interface TestMapper extends BaseMapper<Test> {

    HashMap testHashMap(String name);
}
