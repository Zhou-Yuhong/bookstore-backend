package zn.zyh.back_code.service;

import zn.zyh.back_code.dto.Order_product_wrap;
import zn.zyh.back_code.dto.Order_wrap;
import zn.zyh.back_code.dto.User_Comsumption;
import zn.zyh.back_code.entity.*;

import java.util.List;

public interface OrderService {
    //返回useid的所有订单
    List<Order_wrap> getOrder_wrapsByUserid(int userid);
    //仅返回订单信息
    List<Order> getOrderByUser(int userid);
    //增加订单
    void addOrder_wrap(Order_wrap order_wrap);
    //返回所有订单，包含具体商品信息
    List<Order_wrap> getAllOrders();
    //返回所有订单，不包含具体商品信息
    List<Order> GetAllOrders();
    List<Order_wrap> getOrdersByTime(String begin,String end);
    //返回订单id对应的所有商品
    List<Order_product_wrap> getProductsByOrderid(int orderid);
    //根据时间范围返回所有的订单
    List<Order> getOrderidsBytime(String begin, String end);
    //根据用户id以及时间范围返回所有商品
    List<Order_product_wrap>getproductsByUseridandTime(int userid,String begin,String end);
    //根据时间范围返回所有商品
    List<Order_product_wrap>getProductsByTime(String begin,String end);
    //根据时间返回用户消费信息
    List<User_Comsumption> getUser_ComByTime(String begin, String end);
    List<Order> getOrdersByProductName(String productName);
}
