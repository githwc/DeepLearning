package com.yc.common.config.webMvcConfig;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：Spring Boot 2.0 跨域相关
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-27
 * @Version: 1.0.0
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }

	// @Value("${lionherding.path.upload}")
	// private String upLoadPath;
	// @Value("${lionherding.path.webapp}")
	// private String webAppPath;
	// @Value("${spring.resource.static-locations}")
	// private String staticLocations;
    //
	// @Bean
	// public CorsFilter corsFilter() {
	// 	final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	// 	final CorsConfiguration corsConfiguration = new CorsConfiguration();
	// 	/* 是否允许请求带有验证信息 */
	// 	corsConfiguration.setAllowCredentials(true);
	// 	/* 允许访问的客户端域名 */
	// 	corsConfiguration.addAllowedOrigin("*");
	// 	/* 允许服务端访问的客户端请求头 */
	// 	corsConfiguration.addAllowedHeader("*");
	// 	/* 允许访问的方法名,GET POST等 */
	// 	corsConfiguration.addAllowedMethod("*");
	// 	/* 添加映射路径*/
	// 	urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	// 	return new CorsFilter(urlBasedCorsConfigurationSource);
	// }
    //
	// /**
	//  * 静态资源的配置 - 使得可以从磁盘中读取 Html、图片、视频、音频等
	//  */
	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// 	registry.addResourceHandler("/**")
	// 	.addResourceLocations("file:" + upLoadPath + "//", "file:" + webAppPath + "//")
	// 	.addResourceLocations(staticLocations.split(","));
	// }
    //
	// /**
	//  * 访问根路径默认跳转 index.html页面
    //  *  （简化部署方案： 可以把前端打包直接放到项目的 webapp，上面的配置）
	//  */
	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// 	registry.addViewController("/").setViewName("index.html");
	// }
}
