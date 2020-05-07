package com.yc.practice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Predicate;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-06
 * @Version: 1.0.0
 */
@Data
@AllArgsConstructor
public class Emp {

    private int id;

    private int age;

    private String name;

    /**
     * 自定义过滤<>年龄大于70岁</>
     */
    public static Predicate<Emp> ageGreaterThan70 = x -> x.getAge() > 70;

    /**
     * 自定义过滤<>名字包含b</>
     */
    public static Predicate<Emp> filterName = x -> x.getName().contains("b");

}
