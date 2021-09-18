package zn.zyh.back_code.repository;

import zn.zyh.back_code.dto.Order_product_wrap;
import zn.zyh.back_code.entity.Order;
import zn.zyh.back_code.entity.Order_product;

import java.util.List;

public interface Order_productRepository {
    //取得全部商品
    List<Order_product> getProducts();
    void addProducts(List<Order_product_wrap> order_product_wraps, Order order);
    List<Order_product> getOrderProductsByProductName(String name);
}
