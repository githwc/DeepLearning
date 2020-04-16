package com.yc.common.constant;

/**
 * 功能描述：公用参数
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-16
 * @Version: 1.0.0
 */
public interface CommonEnum {

    enum DelFlag implements CommonEnum{
        /**
         * 删除状态
         */
        DEL(1,"已删除"),
        NO_DEL(0,"未删除")
        ;

        private Integer code;
        private String  name;

        DelFlag(Integer code,String name){
            this.code = code;
            this.name = name;
        }

        public Integer getCode(){
            return code;
        }

        public String getName(){
            return name;
        }
    }

    enum MenuType implements CommonEnum{
        /**
         * 菜单类型
         */
        TOP_MENU_TYPE(0,"一级菜单"),

        SECOND_MENU_TYPE(1,"子菜单"),

        LAST_MENU_TYPE(2,"按钮")
        ;
        private Integer code;
        private String name;

        MenuType(Integer code,String name){
            this.code = code;
            this.name = name;
        }

        public Integer getCode(){
            return code;
        }

        public String getName(){
            return name;
        }
    }


}
