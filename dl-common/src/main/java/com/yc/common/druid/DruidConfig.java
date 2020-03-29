package com.yc.common.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 *    配置Druid的监控
 *    安全框架中放行Druid,通过访问 "项目路径/druid" 进入
 *
 *    [去除监控页面的广告]
 *      druid监控页面加载以后,footer页是有阿里的广告,要去除这个广告需要修改druid.jar的源码文件，
 *      具体方法是，用winRAR打开jar包，在druid-1.1.6.jar\support\http\resources\js\common.js路径下找到文件，
 *      修改common.js中的代码，删除buildFooter函数中的代码即可
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-06-06
 * @Version: 1.0.0
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }


    /**
     * 配置管理后台的Servlet
     * @return bean
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<String, String>();
        initParams.put("loginUsername","xieyc");
        initParams.put("loginPassword","xieyc");
        //默认就是允许所有访问:可设置为""
        initParams.put("allow","127.0.0.1");
        //拒绝访问
        initParams.put("deny","192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置web监控的filter
     * @return bean
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<String, String>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
