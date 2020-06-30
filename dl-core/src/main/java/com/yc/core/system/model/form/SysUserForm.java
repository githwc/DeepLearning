package com.yc.core.system.model.form;

import lombok.Data;

/**
 * 功能描述:表单提交数据
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-03-27
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
     * 头像
     */
    private String headImg;
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
