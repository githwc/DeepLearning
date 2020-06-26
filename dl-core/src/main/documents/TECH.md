一、采用阿里巴巴领域模型概念
    entity(数据模型层):实体类
    ~VO: 供前端展示
    ~Query: 查询入参类
    ~Form: 提交入参类
    dao(持久层):mapper.xml/mapper
    ~controller:前端控制器
    service:(Service/impl) 业务层
三、注解
    @Component: 指这个类需要被组件扫描器扫描到并实例化对象到IOC容器
    @Configuration: 指这个类是一个类似XML文件的配置类,里面用bean标签标记的方法需要被实例化到IOC容器中。
                    在SpringBoot中取消了XML文件并大量使用@Configuration注解的类来实现配置。
    @EnableCaching: 开启缓存
四、mall
    购买->选择商品->填写收货信息、确认金额->
    去结算(创建订单)->去支付,支付完成->跳转到我的订单页
五、日志输出
    1、application.properties同时配置Path和File,file胜;
       logging.path为D:/tmp/spring-boot.log/会发生很奇怪的事情,它会在D盘下，创建tmp目录(若不存在),
       在tmp目录下会创建”spring-boot.log#”目录,是以#替换/的;
    2、日志输出文件的加载顺序:logback.xml > application.properties > logback-spring.xml,最后加载的，会覆盖前者
    
