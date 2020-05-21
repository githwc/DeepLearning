package com.yc.common.constant;

/**
 * 功能描述：公用枚举参数
 *  枚举充当常量
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
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

    enum State implements CommonEnum{
        /**
         * 冻结状态
         */
        Disabled(1,"冻结"),
        enabled(0,"正常")
        ;

        private Integer code;
        private String  name;

        State(Integer code,String name){
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

    enum Reports implements CommonEnum{
        /**
         * 报表信息
         */
        DEMO_REPORT("一级菜单","/demoExport.xlsx")
        ;
        private String name;
        private String path;

        Reports(String name,String path){
            this.path = path;
            this.name = name;
        }

        public String getPath(){
            return path;
        }
        public String getName(){
            return name;
        }
    }

    enum GoodState implements CommonEnum{
        /**
         * 商品状态
         */
        WAIT_CHECK("0","待审核"),
        PAST("1","已通过"),
        REFUSE("2","已拒绝"),
        GOOD_UP("3","已上架"),
        GOOD_DOWN("4","已下架"),
        DELETE("5","已删除")
        ;
        private String code;
        private String name;

        GoodState(String code,String name){
            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return code;
        }
        public String getName(){
            return name;
        }
    }

    enum OrderState implements CommonEnum{
        /**
         * 订单状态
         */
        ORDER_STATE_0(0,"已取消"),
        ORDER_STATE_10(10,"未付款"),
        ORDER_STATE_20(20,"已付款(待发货)"),
        ORDER_STATE_40(40,"已发货"),
        ORDER_STATE_50(50,"交易成功"),
        ORDER_STATE_60(60,"交易关闭"),
        ORDER_STATE_70(70,"无效订单")
        ;
        private Integer code;
        private String name;

        OrderState(Integer code,String name){
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

    enum OrderLogState implements CommonEnum{
        /**
         * 订单日志状态
         */
        WAIT_PAY(0,"待付款"),
        WAIT_SEND_GOOD(1,"待发货"),
        SEND_GOOD(2,"已发货"),
        FINISH(3,"已完成"),
        CLOSE(4,"已关闭"),
        INVALID(5,"已取消")
        ;
        private Integer code;
        private String name;

        OrderLogState(Integer code,String name){
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

    enum ReceiveType implements CommonEnum{
        /**
         * 消息接收类型
         */
        SINGLE_USER(0,"指定用户"),
        All_USER(1,"全体用户")
        ;
        private Integer code;
        private String name;

        ReceiveType(Integer code,String name){
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

    enum SendState implements CommonEnum{
        /**
         * 发布状态
         */
        NO_SEND(0,"未发布"),
        SEND(1,"已发布"),
        CANCEL_SEND(2,"已撤销")
        ;
        private Integer code;
        private String name;

        SendState(Integer code,String name){
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
