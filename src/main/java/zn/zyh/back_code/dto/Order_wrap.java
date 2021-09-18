package zn.zyh.back_code.dto;

import lombok.Data;
import zn.zyh.back_code.entity.Order;
import zn.zyh.back_code.entity.Order_product;

import java.util.List;
@Data
public class Order_wrap {
    public Order_info order_info;
    public List<Order_product_wrap>  order_products;
    public Order_wrap(Order order_info){
        this.order_info=new Order_info(order_info);
        List<Order_product> order_products=order_info.getOrder_products();
        this.order_products=Order_product_wrap.transform(order_products);
    }
    public Order_wrap(Order_info order_info, List<Order_product_wrap> order_products){
        this.order_info=order_info;
        this.order_products=order_products;
    }
    public String getTime(){
        return this.order_info.getOrder_time();
    }
}
