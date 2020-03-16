package com.yc.practice.TestTemp.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.config.exception.RunException.RunningException;
import com.yc.common.constant.CacheConstant;
import com.yc.common.constant.CommonConstant;
import com.yc.practice.common.log.WriteLog;
import com.yc.common.utils.EncoderUtil;
import com.yc.core.system.entity.SysUser;
import com.yc.practice.TestTemp.service.TestService;
import com.yc.practice.config.filter.JwtUtil;
import com.yc.practice.system.service.SysUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:   xieyc
 * @Datetime: 2019-09-18 14:01
 * @Version: 1.0.0
 */
@RestController
@Api(tags="测试接口")
@Slf4j
public class TestShiroController {

    private final SysUserService iSysUserService;
    private final TestService testService;
    private RedisTemplate redisTemplate;

    @Autowired
    public TestShiroController(SysUserService iSysUserService, RedisTemplate redisTemplate,TestService testService) {
        this.iSysUserService = iSysUserService;
        this.testService = testService;
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping("/test2")
    @WriteLog(opPosition = "测试日志点" ,optype = CommonConstant.OPTYPE_READ)
    public String test2(){
        log.info("执行test方法");
        return "test";
    }

    @RequestMapping("/test")
    public void test(){
        log.info("执行test方法");
        testService.testHashMap();
        // return "test";
    }

    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    @RequestMapping("/login")
    @WriteLog(opPosition = "登录系统" ,optype = CommonConstant.OPTYPE_READ)
    public String login(String name, String password, Model model){
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        // 执行登录方法
        try{
            subject.login(token);   // 调用自定义的shiroRealm 执行登录认证
            return "test";
        }catch(UnknownAccountException e){
            model.addAttribute("msg","登录失败,用户名错误！");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","登录失败,密码错误！");
            return "login";
        }
    }

    @RequestMapping("/login2")
    @ResponseBody
    @WriteLog(opPosition = "登录系统" ,optype = CommonConstant.OPTYPE_READ)
    public JSONObject login2(String name, String password, Model model){
        SysUser sysUser = new SysUser();
        sysUser = iSysUserService.getUserByName(name);
        if(sysUser==null) {
            throw new RunningException("该用户不存在！");
        }else {
            // 密码验证
            String userpassword = EncoderUtil.encrypt(name, password, sysUser.getLoginName());
            String syspassword = sysUser.getPassword();
            // if(!syspassword.equals(userpassword)) {
            //     sysLogService.addLog("登录失败，用户:"+username+"密码输入错误！", CommonConstant.LOG_TYPE_1, 3);
            //     throw new UserPasswordException("密码错误！");
            // }
            JSONObject jsonObject = new JSONObject();
            //生成token
            String token = JwtUtil.sign(name, syspassword);
            ValueOperations operations = redisTemplate.opsForValue();
            // 放入缓存并设置超时时间
            operations.set(CacheConstant.LOGIN_USER_TOKEN_ + token, token,30, TimeUnit.MINUTES);
            jsonObject.put("token", token);
            jsonObject.put("userInfo", sysUser);
            return jsonObject;
        }
    }

}
