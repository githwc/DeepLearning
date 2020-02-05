package com.yc.practice.config.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 功能描述:
 *    swagger相关配置
 *
 *    安全框架中放行Swagger2,
 *    通过 "项目路径/swagger-ui.html" 访问
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-07-07
 * @Version: 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * @Description:指定接口基础信息
     * @Date: 2019/7/20 14:42
     * @Param:  apiInfo:定义项目描述信息
     *          apis:   指定接口层中的位置
     *          paths(PathSelectors.any()):  对所有路径进行监控
     * @Return:
     * @throws:
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("业务接口")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lh.modules"))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    @Bean
    public Docket createSystemRestApi() {
        //分组展示
        Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.lh.system.controller");
        // Predicate<RequestHandler> selector2 = RequestHandlerSelectors.basePackage("com.lh.modules");
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统接口")
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.or(selector1))
                // .apis(Predicates.or(selector1,selector2))    // 将多个controller拼装到一个分组
                // .paths(PathSelectors.regex("/user.*"))   //只监控user相关接口
                .paths(PathSelectors.any())
                .build()
                ;
    }

    /**
     * @Description: 定义项目描述信息
     * @Date: 2019/7/20 14:39
     * @Param:
     * @Return:
     * @throws:
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台管理服务API")
                .description("后台管理服务Restful风格API文档")
                .version("1.0")
                .build();
    }

}

