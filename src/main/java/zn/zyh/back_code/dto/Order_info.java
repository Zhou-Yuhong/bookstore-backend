package zn.zyh.back_code.dto;

import lombok.Data;
import zn.zyh.back_code.entity.Order;

import java.math.BigDecimal;

@Data
public class Order_info {
    private int id;
    private int userid;
    private String order_time;
    //订单包含的商品种类数目
    private int num;
    //订单总价值
    private BigDecimal value;
    //顶单状态
    private int state;
    public Order_info(){}
    public Order_info(Order order_info){
        this.id=order_info.getId();
        this.userid=order_info.getUserAuth().getUserId();
        this.order_time=order_info.getOrder_time();
        this.num=order_info.getNum();
        this.value=order_info.getValue();
        this.state=order_info.getState();
    }
    public Order_info(int userid, String order_time, int num, BigDecimal value, int state){
        this.userid=userid;
        this.order_time=order_time;
        this.num=num;
        this.value=value;
        this.state=state;
    }
}
