package com.yc.practice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-26
 * @Version: 1.0.0
 */
@SpringBootTest
@Slf4j
public class TempTest {

    @Test
    public void tests(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        // long lt = new Long("1590412128000");
        // Date date = new Date(lt);
        // System.out.println(simpleDateFormat.format(date));
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTime(new Date());
        // calendar.add(Calendar.DAY_OF_YEAR,-1);
        // String dd = simpleDateFormat.format(calendar.getTime());
        // System.out.println(dd);
        // System.out.println(IdUtil.simpleUUID());
        System.out.println(RandomUtil.getRandom());
    }

}
