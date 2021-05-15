package zn.zyh.back_code.entity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Order_wrap {
    public Order_info order_info;
    public List<Order_product>  order_products;
    public Order_wrap(Order_info order_info,List<Order_product> order_products){
        this.order_info=order_info;
        this.order_products=order_products;
    }
}
