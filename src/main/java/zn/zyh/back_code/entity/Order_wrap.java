package zn.zyh.back_code.entity;

import org.aspectj.weaver.ast.Or;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

public class Order_wrap {
    public Order_info order_info;
    public List<Order_product_wrap>  order_products;
    public Order_wrap(Order_info order_info){
        this.order_info=order_info;
        List<Order_product> order_products=order_info.getOrder_products();
        this.order_products=new ArrayList<>();
        for(int i=0;i<order_products.size();i++){
            this.order_products.add(new Order_product_wrap(order_products.get(i)));
        }
    }
    public Order_wrap(Order_info order_info,List<Order_product_wrap> order_products){
        this.order_info=order_info;
        this.order_products=order_products;
    }
    public String getTime(){
        return this.order_info.getOrder_time();
    }
}
