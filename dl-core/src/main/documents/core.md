一、关于平台目录结构
    src
      -main
          –java java源代码文件
            -com.lh	业务代码
                - common 公共资源
                - modules 具体业务代码层
                - system 系统代码层
          –resources 资源库
      -test
          –java 单元测试java源代码文件
          –resources 测试需要用的资源库
      -documents (文档)
          - Data 数据库文件
          - core.md 核心文档
          - log.md 日志文档(记录项目进度节点)、
          - tech.md 技术点文档
          - FAQ.md 开发过程中常见问题
    target(存放项目构建后的文件和目录，jar包、war包、编译的class文件等,maven构建的时候生成的)
    README.txt
二、关于技术点说明
	2.1分层思想(采用阿里巴巴领域模型概念)
    	dao(持久层):mapper.xml/mapper
    	entity(数据模型层):实体类
    	    VO: 供前端展示
    	    Query: 查询入参类
    	    Form: 提交入参类
    	controller:前端控制器
    	service:(Service/impl) 业务层
三、关于内部核心工具类
	1.内部核心工具类经测试成型后应打成jar包,供后期统一使用;
	2.若出现经常使用且未整理到核心工具类的函数，可经审核后进入核心工具类。
