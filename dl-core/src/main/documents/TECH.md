一、采用阿里巴巴领域模型概念
    entity(数据模型层):实体类
    ~VO: 供前端展示
    ~Query: 查询入参类
    ~Form: 提交入参类
    dao(持久层):mapper.xml/mapper
    ~controller:前端控制器
    service:(Service/impl) 业务层
二、快捷键
	1.Alt+Enter：创建该接口的实现类/实现未实现的方法
	2.【精准搜索】
		ctrl + f	页面内查找
		ctrl + shift + T	查找方法
		ctrl + shift + R	查找文件
		ctrl + alt + S	打开设置
		ctrl + G    查找方法的调用方
	3.【重构】
		F2：修改名称/重构变量
		Alt+Enter:指定函数修改参数后可使用此快捷键重构关联函数
	4.【其他】
		ctrl + O 重写父类方法       
		ctrl + shift + x 打开文件所在工作空间 
三、注解
    @Component: 指这个类需要被组件扫描器扫描到并实例化对象到IOC容器
    @Configuration: 指这个类是一个类似XML文件的配置类,里面用bean标签标记的方法需要被实例化到IOC容器中。
                    在SpringBoot中取消了XML文件并大量使用@Configuration注解的类来实现配置。
    @EnableCaching: 开启缓存
四、mall
    购买->选择商品->填写收货信息、确认金额->
    去结算(创建订单)->去支付,支付完成->跳转到我的订单页
