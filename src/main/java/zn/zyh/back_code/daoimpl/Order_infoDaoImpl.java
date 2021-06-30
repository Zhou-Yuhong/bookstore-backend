package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.dao.Order_infoDao;
import zn.zyh.back_code.entity.Order_info;
import zn.zyh.back_code.repository.Order_infoRepository;
import zn.zyh.back_code.utils.analysisutils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Repository
public class Order_infoDaoImpl implements Order_infoDao {
    @Autowired
    private Order_infoRepository order_infoRepository;
    @Override
    public List<Order_info> findByUserid(int userid){
        return order_infoRepository.findByUserid(userid);
    }
    @Override
    public int addOrder_info(Order_info order_info){
        Order_info order=order_infoRepository.save(order_info);
      return order.getId();
    }
    @Override//根据时间返回所有订单
    public List<Order_info> getOrdersByTime(String begin, String end){
        List<Order_info> orders=order_infoRepository.findAll();
        //List<user> filterAges = users.stream().filter(user->ages.contains(user.getAge())).collect(Collectors.toList());
        List<Order_info> filterOrders=orders.stream().filter(order_info -> AnaUtil.InRange(order_info.getOrder_time(),begin,end)).collect(Collectors.toList());
        return filterOrders;
    }
    @Override
    public List<Order_info> getOrdersByUseridAndTime(int userid,String begin,String end){
        List<Order_info> orders=order_infoRepository.findByUserid(userid);
        List<Order_info> filterOrders=orders.stream().filter(order_info -> AnaUtil.InRange(order_info.getOrder_time(),begin,end)).collect(Collectors.toList());
        return filterOrders;
    }
    @Override
    public List<Order_info> getAllOrders(){
        return order_infoRepository.findAll();
    }
    @Override
    public Order_info getOrder(int id){
        return order_infoRepository.getOne(id);
    }
}
