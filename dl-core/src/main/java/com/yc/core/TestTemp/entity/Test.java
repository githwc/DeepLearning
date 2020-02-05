package com.yc.core.TestTemp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-09-29
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "test_id", type = IdType.UUID)
    private String testId;

    private String name;

    private Integer age;
    private String address;
    private Integer sort;
    private String remark;
    private String sex;
    private Integer arg2;
    private boolean arg1;


    /////////////////////////////// 非表字段 ///////////////////////////////

}
