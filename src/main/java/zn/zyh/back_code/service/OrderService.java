package zn.zyh.back_code.service;

import zn.zyh.back_code.entity.Order_wrap;

import java.util.List;

public interface OrderService {
    //返回所有订单
    List<Order_wrap> getOrder_wrapsByUsername(String username);
    void addOrder_wrap(Order_wrap order_wrap);
}