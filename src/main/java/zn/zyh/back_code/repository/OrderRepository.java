package zn.zyh.back_code.repository;

import zn.zyh.back_code.entity.Order;

import java.util.List;

public interface OrderRepository {
    //通过用户名获得订单,一个用户可能有多个订单
    List<Order> findByUserid(int user_id);
    //添加一个订单，返回自动生成的订单id
    int addOrder(Order order_info);
    //根据时间返回所有订单
    List<Order> getOrdersByTime(String begin, String end);
    //根据时间以及用户id返回订单
    List<Order> getOrdersByUseridAndTime(int userid, String begin, String end);
    List<Order> getAllOrders();
    //通过id查找订单
    Order getOrder(int id);
}
