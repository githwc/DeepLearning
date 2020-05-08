package com.yc.core;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：MyBatis-plus 代码生成器
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-06-28 17:17
 * @Version: 1.0.0
 */
public class MyBatisGenerator {

    /**
     * 生成文件所在项目路径
     */
    private static final String BASE_PATH = "D:\\workSpace\\DeepLearning\\dl-core";
    /**
     * 基本包名
     */
    private static final String BASE_PACKAGE = "com.yc.core";
    /**
     * 文件夹名
     */
    private static final String MODEL_NAME = "mall";
    private static final String AUTHOR = "xieyc";
    /**
     * 要生成的表名
     */
    private static final String[] TABLES = {"mall_good_class","mall_order_item","mall_pay","mall_shipping"};

    /**
     * 数据库配置四要素
     */
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/deeplearning?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "xieyc@mysql";

    public static void main(String[] args) {
        //代码生成器
        AutoGenerator gen = new AutoGenerator();
        // 全局配置
        gen.setGlobalConfig(new GlobalConfig()
                // 主键自增
                .setIdType(IdType.UUID)
                // 是否覆盖文件
                .setFileOverride(true)
                // 输出目录
                .setOutputDir( BASE_PATH + "/src/main/java")
                // 作者
                .setAuthor(AUTHOR)
                // 生成后打开文件夹
                .setOpen(false)
                // 开启 activeRecord 模式
                // .setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML 生成基本的resultMap
                .setBaseResultMap(false)
                // XML 生成基本的sql片段
                .setBaseColumnList(false)
                // 自定义文件命名,%s会自动填充表实体属性！默认IXXXService
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
        );

         // 数据库配置
        gen.setDataSource(new DataSourceConfig()
                .setUrl(URL)
                .setDriverName(DRIVER_NAME)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setDbType(DbType.MYSQL)
        );

        // 包配置
        gen.setPackageInfo(new PackageConfig()
                .setModuleName(MODEL_NAME)
                // 自定义包路径
                .setParent(BASE_PACKAGE)
                // 这里是控制器包名，默认 web
                .setController("controller")
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("resource")
        );

        // 注入自定义配置 可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig abc = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        //自定义文件输出位置（非必须）
        List<FileOutConfig> fileOutList = new ArrayList<>();
        fileOutList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return BASE_PATH + "/src/main/resources/mapper/"+ MODEL_NAME +"/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        abc.setFileOutConfigList(fileOutList);
        gen.setCfg(abc);
        // 指定模板引擎 默认是VelocityTemplateEngine ，需要引入相关引擎依赖
        gen.setTemplateEngine(new FreemarkerTemplateEngine());
        // 模板配置 关闭默认 xml 生成，调整生成 至 根目录
        gen.setTemplate(new TemplateConfig()
                .setXml(null)
                .setService("/mybatis/service.java")
                .setServiceImpl("/mybatis/serviceImpl.java")
                .setController("/mybatis/controller.java")
                .setMapper("/mybatis/mapper.java")
                .setEntity("/mybatis/model.java")
        );

        // 策略配置
        gen.setStrategy(new StrategyConfig()
                        // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                        // 需要生成的表
                .setInclude(TABLES)
                        // 自定义实体，公共字段
                .setSuperEntityColumns("id")
                .setControllerMappingHyphenStyle(true)
        );
        // 执行生成
        gen.execute();
    }
}
