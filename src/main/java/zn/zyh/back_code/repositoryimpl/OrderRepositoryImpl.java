package zn.zyh.back_code.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zn.zyh.back_code.dao.OrderDao;
import zn.zyh.back_code.dao.UserAuthDao;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.repository.OrderRepository;
import zn.zyh.back_code.entity.Order;
import zn.zyh.back_code.utils.analysisutils.*;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserAuthDao userAuthDao;
    @Override
    public List<Order> findByUserid(int userid){
        UserAuth userAuth=userAuthDao.getUserAuthByUserId(userid);
        return orderDao.getOrdersByUserAuth(userAuth);
    }
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public int addOrder(Order order_info){
        Order order= orderDao.save(order_info);
      return order.getId();
    }
    @Override//根据时间返回所有订单
    public List<Order> getOrdersByTime(String begin, String end){
        List<Order> orders= orderDao.findAll();
        //List<user> filterAges = users.stream().filter(user->ages.contains(user.getAge())).collect(Collectors.toList());
        List<Order> filterOrders=orders.stream().filter(order_info -> AnaUtil.InRange(order_info.getOrder_time(),begin,end)).collect(Collectors.toList());
        return filterOrders;
    }
    @Override
    public List<Order> getOrdersByUseridAndTime(int userid, String begin, String end){
        UserAuth userAuth=userAuthDao.getUserAuthByUserId(userid);
        List<Order> orders= orderDao.getOrdersByUserAuth(userAuth);
        List<Order> filterOrders=orders.stream().filter(order_info -> AnaUtil.InRange(order_info.getOrder_time(),begin,end)).collect(Collectors.toList());
        return filterOrders;
    }
    @Override
    public List<Order> getAllOrders(){
        return orderDao.findAll();
    }
    @Override
    public Order getOrder(int id){
        return orderDao.getOne(id);
    }
}
