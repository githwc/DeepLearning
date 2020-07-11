package com.yc.mini.user.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yc.common.constant.CommonConstant;
import com.yc.common.global.response.RestResult;
import com.yc.core.mini.entity.MiniUser;
import com.yc.core.mini.mapper.MiniUserMapper;
import com.yc.mini.security.service.TokenService;
import com.yc.mini.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-07-09
 * @Version: 1.0.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    private final WxMaService wxMaService;
    private final MiniUserMapper miniUserMapper;
    private final TokenService tokenService;

    @Autowired
    public LoginServiceImpl(WxMaService wxMaService, MiniUserMapper miniUserMapper, TokenService tokenService) {
        this.wxMaService = wxMaService;
        this.miniUserMapper = miniUserMapper;
        this.tokenService = tokenService;
    }

    @Override
    public synchronized void wxLogin(JSONObject param, HttpServletResponse response) {
        try {
            WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(param.getString("code"));
            log.debug("wx miniapp openid : {}", result.getOpenid(), "wx miniapp sessionkey : {}", result.getSessionKey());
            MiniUser user = miniUserMapper.selectOne(Wrappers.<MiniUser>lambdaQuery().eq(MiniUser::getWxOpenId,
                    result.getOpenid()));
            LocalDateTime localDateTime = LocalDateTime.now();
            // 当前系统不存在用户信息，自动创建用户信息
            if (user == null) {
                user = new MiniUser();
                user.setWxOpenId(result.getOpenid());
                user.setSessionKey(result.getSessionKey());
                user.setLastLoginTime(LocalDateTime.now());
                miniUserMapper.insert(user);
            } else {
                localDateTime = user.getLastLoginTime();
                user.setLastLoginTime(LocalDateTime.now());
                user.setSessionKey(result.getSessionKey());
                miniUserMapper.updateById(user);
            }
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            Date date = Date.from(zdt.toInstant());
            String jwtToken = tokenService.create(user.getMiniUserId());
            response.addHeader(CommonConstant.HEADER_STRING, jwtToken);
            response.setHeader("Access-Control-Allow-Headers", "Authorization");
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            JSONObject jsonObject = new JSONObject().fluentPut("userId", user.getMiniUserId())
                    .fluentPut("headerImg", user.getHeaderImg())
                    .fluentPut("phone", user.getPhone());
            String successMsg = RestResult.success().data(jsonObject).toJSONString();
            ServletUtil.write(response, successMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                    Charset.forName(CommonConstant.CHARSET_UTF_8)));

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
