package zn.zyh.back_code.service;

import zn.zyh.back_code.entity.*;

import java.util.List;

public interface OrderService {
    //返回useid的所有订单
    List<Order_wrap> getOrder_wrapsByUserid(int userid);

    //增加订单
    void addOrder_wrap(Order_wrap order_wrap);
    //返回所有订单
    List<Order_wrap> getAllOrders();
    List<Order_wrap> getOrdersByTime(String begin,String end);
    //返回订单id对应的所有商品
    List<Order_product_wrap> getProductsByOrderid(int orderid);
    //根据时间范围返回所有的订单
    List<Order_info> getOrderidsBytime(String begin, String end);
    //根据用户id以及时间范围返回所有商品
    List<Order_product_wrap>getproductsByUseridandTime(int userid,String begin,String end);
    //根据时间范围返回所有商品
    List<Order_product_wrap>getProductsByTime(String begin,String end);
    //根据时间返回用户消费信息
    List<User_Comsumption> getUser_ComByTime(String begin,String end);
}
