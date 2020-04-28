package com.yc.common.webMvcConfig;

import com.yc.common.propertie.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
public class WebMvcConfigurerSelf implements WebMvcConfigurer {

    private final UploadProperties uploadProperties;

    @Autowired
    public WebMvcConfigurerSelf (UploadProperties uploadProperties){
        this.uploadProperties = uploadProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 页面相关的静态目录
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // 映射本地存储路径 "file:D:/upload/"
        registry.addResourceHandler(uploadProperties.getImgAccessPath()).addResourceLocations("file:"+uploadProperties.getImgFilePath());
    }

    /**
     * 跨域设置
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }

    // TODO: 2020/4/27 待删除
    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     super.configureMessageConverters(converters);
    //
    //     converters.add(0, new MappingJackson2HttpMessageConverter());
    //
    //     FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    //
    //     List<MediaType> supportedMediaTypes = new ArrayList<>();
    //     supportedMediaTypes.add(MediaType.APPLICATION_JSON);
    //     supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    //     supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
    //     supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
    //     supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
    //     supportedMediaTypes.add(MediaType.APPLICATION_PDF);
    //     supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
    //     supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
    //     supportedMediaTypes.add(MediaType.APPLICATION_XML);
    //     supportedMediaTypes.add(MediaType.IMAGE_GIF);
    //     supportedMediaTypes.add(MediaType.IMAGE_JPEG);
    //     supportedMediaTypes.add(MediaType.IMAGE_PNG);
    //     supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
    //     supportedMediaTypes.add(MediaType.TEXT_HTML);
    //     supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
    //     supportedMediaTypes.add(MediaType.TEXT_PLAIN);
    //     supportedMediaTypes.add(MediaType.TEXT_XML);
    //     fastConverter.setSupportedMediaTypes(supportedMediaTypes);
    //
    //     FastJsonConfig fastJsonConfig = new FastJsonConfig();
    //     fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    //     fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
    //     fastConverter.setFastJsonConfig(fastJsonConfig);
    //     converters.add(fastConverter);
    // }



}
