package com.yc.practice;

import com.yc.core.mall.model.form.CartForm;
import com.yc.practice.mall.service.MallCartService;
import com.yc.practice.mall.service.impl.MallCartServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-09
 * @Version: 1.0.0
 */
// @RunWith(SpringRunner.class)
@SpringBootTest
public class TestTemp {

    @Test
    public void b (){
        System.out.println("aaaa");
    }

    @Autowired
    private MallCartService mallCartService;

    @Test
    public void dd(){
        CartForm form = new CartForm();
        form.setNum(1);
        form.setMallGoodId("1");
        mallCartService.add(form);
    }

}
