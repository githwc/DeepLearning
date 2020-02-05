package com.yc.practice.config.shiro;

import com.yc.practice.config.filter.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 功能描述：shiro配置类
 *
 *      调用登录接口，通过后执行自定义Filter的方法，进行shiro信息认证授权
 *      并且在每个请求前进行自定义Filter 和 toekn刷新
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-17 16:25
 * @Version: 1.0.0
 */
@Configuration
public class ShiroConfig {

    /**
     *  @DESC:创建ShiroFilterFactoryBean
     *
     * 定义Filter Chain(Shiro 内置过滤器),实现权限相关的拦截
     *  注: LinkedHashMap 顺序拦截
     *
     *      anon: 无需认证即可访问
     *      authc:必须认证才可以访问
     *      user: 需要认证或通过记住我认证才能访问
     *      perms:必须得到资源权限才可以访问
     *      roles:必须得到角色权限才可以访问
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        /*============  配置不会被拦截的链接 顺序判断 START ======*/
        filterChainDefinitionMap.put("/sysUser/login", "anon"); /*登录接口*/
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger**/**", "anon");

        filterChainDefinitionMap.put("/test","anon");   //Temp APi
        filterChainDefinitionMap.put("/test2","anon");   //Temp APi
        filterChainDefinitionMap.put("/login","anon");  //Temp APi
        filterChainDefinitionMap.put("/login2","anon");  //Temp APi
        filterChainDefinitionMap.put("/sysDept/queryTreeList2","anon");  //Temp APi

        /* ======== 权限访问 START =============*/
        filterChainDefinitionMap.put("/add","perms[hr:BaseSettings]");

        /*========== 认证后可访问 START ==========*/
        filterChainDefinitionMap.put("/*","authc");
        filterChainDefinitionMap.put("/add","authc");   // Temp API
        filterChainDefinitionMap.put("/update","authc");    //Temp API

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // 自定义过滤器拦截所有请求(不包括放行请求)
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setLoginUrl("/toLogin"); /*设置默认登录页面*/
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth"); /*设置默认未授权页面*/
        return shiroFilterFactoryBean;
    }

    /**
     * @DESC:创建DefaultWebSecurityManager
     *
     * @param shiroRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);

        /*
         * 关闭shiro自带的session，详情见文档
         *http://shiro.apache.org/session-management.html#SessionManagement-
         * StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * @DESC: 创建Realm
     *
     * @return
     */
    @Bean(name="shiroRealm")
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    // @Bean
    // public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    //     return new LifecycleBeanPostProcessor();
    // }
    //
    // @Bean
    // public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
    //     AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    //     advisor.setSecurityManager(securityManager);
    //     return advisor;
    // }
}
