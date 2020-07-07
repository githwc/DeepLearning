package com.yc.practice.config.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
 * swagger相关配置
 * <p>
 * 安全框架中放行Swagger2,
 * 通过 "项目路径/swagger-ui.html" 访问
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2019-07-07
 * @Version: 1.0.0
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * 指定接口基础信息
     * apiInfo:定义项目描述信息
     * apis:   指定接口层中的位置
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("业务接口")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yc.practice.mall"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 指定接口基础信息
     * apiInfo:定义项目描述信息
     * apis:   指定接口层中的位置
     * <p>
     * [将多个controller拼装到一个分组
     * .apis(Predicates.or(selector1,selector2))   ]
     * [只监控user相关接口
     * .paths(PathSelectors.regex("/user.*"))  ]
     * [为有@Api注解的Controller生成API文档
     * .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))   ]
     * [为有@ApiOperation注解的方法生成API文档
     * .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  ]
     *
     * @return Docket
     */
    @Bean
    public Docket createSystemRestApi() {
        //分组展示
        Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.yc.practice.system.controller");
        // Predicate<RequestHandler> selector2 = RequestHandlerSelectors.basePackage("com.lh.modules");
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统接口")
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.or(selector1))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 定义项目描述信息
     *
     * @return 定义项目描述信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("Restful风格接口文档")
                .version("1.0")
                .build();
    }

}

