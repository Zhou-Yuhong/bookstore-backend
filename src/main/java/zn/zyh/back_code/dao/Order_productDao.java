package zn.zyh.back_code.dao;

import zn.zyh.back_code.entity.Order_product;
import zn.zyh.back_code.entity.Order_product_wrap;

import java.util.List;

public interface Order_productDao {
    //通过订单id获得所有商品
    List<Order_product> getProducts(int orderid);
    //添加物品
    void addProducts(List<Order_product> order_products, int orderid);
}
