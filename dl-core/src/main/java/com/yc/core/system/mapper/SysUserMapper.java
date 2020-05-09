package com.yc.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.model.query.UserQuery;
import com.yc.core.system.model.vo.SysUserVO;
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
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 用户查询
     * @param page 分页信息
     * @param userQuery 入参
     * @return 返回
     */
    Page<SysUserVO> userList(@Param("page") Page<SysUser> page, @Param("query") UserQuery userQuery);

    /**
     * 根据登录账号获取用户信息
     *
     * @param loginName 登陆账号
     * @return 用户信息
     */
    SysUser loginByName(@Param("loginName") String loginName);

    /**
     * 用户查询
     * @param page 分页信息
     * @return 用户page
     */
    Page<SysUserVO> chatPage(@Param("page") Page<SysUser> page);


}
