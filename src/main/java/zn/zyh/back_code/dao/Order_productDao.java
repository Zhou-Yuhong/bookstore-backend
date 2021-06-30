package zn.zyh.back_code.dao;

import zn.zyh.back_code.entity.Order_product;
import zn.zyh.back_code.entity.Order_product_wrap;

import java.util.List;

public interface Order_productDao {
    //添加物品
    void addProducts(List<Order_product> order_products, int orderid);
}
