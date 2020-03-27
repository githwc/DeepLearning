package com.yc.core.system.model.form;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 功能描述：表单提交数据
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-27
 * @Version: 1.0.0
 */
@Data
public class SysUserForm {

    private String sysUserId;
    /**
     * 真实姓名
     */
    private String userName;
    /**
     * 职务
     */
    private String jobs;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 手机
     */
    private String phone;
    /**
     * 住址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 序号
     */
    private Integer sort;
    /**
     * 状态(0正常 1 冻结)
     */
    private Integer state;
    /**
     * 备注
     */
    private String remark;

    /**
     * 角色集合
     */
    private String selectedroles;
}
