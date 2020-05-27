package com.yc.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long("1590412128000");
        Date date = new Date(lt);
        System.out.println(simpleDateFormat.format(date));
    }
}
