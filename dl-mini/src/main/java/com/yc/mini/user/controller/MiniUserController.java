package com.yc.mini.user.controller;

import com.yc.mini.user.service.MiniUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @Author xieyc && 紫色年华
 * @Date 2020-07-09
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/mini/mini-user")
@Slf4j
public class MiniUserController {

    @Autowired
    public MiniUserService iMiniUserService;

}
