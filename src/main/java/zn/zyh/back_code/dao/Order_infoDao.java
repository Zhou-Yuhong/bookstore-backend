package zn.zyh.back_code.dao;

import zn.zyh.back_code.entity.Order_info;

import java.util.List;

public interface Order_infoDao {
    //通过用户名获得订单,一个用户可能有多个订单
    List<Order_info> findByUserid(int user_id);
    //添加一个订单，返回自动生成的订单id
    int addOrder_info(Order_info order_info);
    //根据时间返回所有订单
    List<Order_info> getOrdersByTime(String begin, String end);
    //根据时间以及用户id返回订单
    List<Order_info> getOrdersByUseridAndTime(int userid,String begin,String end);
}
