package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.dto.Order_info;
import zn.zyh.back_code.dto.Order_product_wrap;
import zn.zyh.back_code.dto.Order_wrap;
import zn.zyh.back_code.dto.User_Comsumption;
import zn.zyh.back_code.repository.BookRepository;
import zn.zyh.back_code.repository.OrderRepository;
import zn.zyh.back_code.repository.Order_productRepository;
import zn.zyh.back_code.repository.UserRepository;
import zn.zyh.back_code.entity.*;
import zn.zyh.back_code.service.OrderService;
import zn.zyh.back_code.utils.analysisutils.AnaUtil;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OredrServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderInfoRepository;
    @Autowired
    Order_productRepository orderProductRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Override
    public List<Order_wrap> getOrder_wrapsByUserid(int userid){
       List<Order> orders= orderInfoRepository.findByUserid(userid);
       List<Order_wrap> order_wraps=new ArrayList<>();
       for(int i=0;i<orders.size();i++){
           order_wraps.add(new Order_wrap(orders.get(i)));
       }
       return order_wraps;
    }
    @Override
    public  List<Order> getOrderByUser(int userid){
        return orderInfoRepository.findByUserid(userid);
    }
    //获得orderid对应的商品,包括商品数量以及各种信息
    @Override
    public List<Order_product_wrap> getProductsByOrderid(int orderid){
        List<Order_product_wrap> product_wraps=new ArrayList<>();
        Order order= orderInfoRepository.getOrder(orderid);
        List<Order_product> products=order.getOrder_products();
        for(int j=0;j<products.size();j++){
            Order_product_wrap tmp=new Order_product_wrap(products.get(j));
            product_wraps.add(tmp);
        }
        return product_wraps;
    }
    @Override
    public List<Order_product_wrap> getproductsByUseridandTime(int userid,String begin,String end){
        //先获得所有符合条件的Order_info
        List<Order> orders= orderInfoRepository.getOrdersByUseridAndTime(userid,begin,end);
        if(orders.size()==0){
            List<Order_product_wrap> empty=new ArrayList<>();
            return empty;
        }
        List<Order_product_wrap> result=getProductsByOrderid(orders.get(0).getId());
        for(int i=1;i<orders.size();i++){
            AnaUtil.compact(result,getProductsByOrderid(orders.get(i).getId()));
        }
        return result;
    }

    @Override
    public List<Order_product_wrap> getProductsByTime(String begin,String end){
        List<Order> orders= orderInfoRepository.getOrdersByTime(begin,end);
        if(orders.size()==0){
            List<Order_product_wrap> empty=new ArrayList<>();
            return empty;
        }
        List<Order_product_wrap> result=getProductsByOrderid(orders.get(0).getId());
        for(int i=1;i<orders.size();i++){
            AnaUtil.compact(result,getProductsByOrderid(orders.get(i).getId()));
        }
        return result;
    }
    //根据时间范围返回所有的订单
    @Override
    public List<Order> getOrderidsBytime(String begin, String end){
         return orderInfoRepository.getOrdersByTime(begin,end);
    }
    //根据时间范围返回所有用户消费信息
    @Override
    public List<User_Comsumption> getUser_ComByTime(String begin, String end){
        //先获得时间范围内的所有订单
        List<Order> orders= orderInfoRepository.getOrdersByTime(begin, end);
        List<User_Comsumption> result=new ArrayList<>();
        //遍历订单
        for(int i=0;i<orders.size();i++){
            Order order_info=orders.get(i);
            UserAuth user=order_info.getUserAuth();
            //根据订单得到用户信息
            //根据订单id得到所用商品信息
            List<Order_product_wrap> product_wraps=this.getProductsByOrderid(order_info.getId());
            //得到订单价值
            BigDecimal value=order_info.getValue();
            User_Comsumption user_comsumption=new User_Comsumption(user,product_wraps,value);
            AnaUtil.addUserComsumption(result,user_comsumption);
        }
        return result;
    }
    @Override
    public List<Order_wrap> getAllOrders(){
        List<Order> orders= orderInfoRepository.getAllOrders();
        List<Order_wrap> order_wraps=new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            order_wraps.add(new Order_wrap(orders.get(i)));
        }
        return order_wraps;
    }
    @Override
    public List<Order> GetAllOrders(){
        return orderInfoRepository.getAllOrders();
    }
    @Override
    public List<Order_wrap> getOrdersByTime(String begin,String end){
        //先取得所有订单
        List<Order_wrap> order_wraps=this.getAllOrders();
        List<Order_wrap> filters=order_wraps.stream().filter(order_wrap -> AnaUtil.InRange(order_wrap.getTime(),begin,end)).collect(Collectors.toList());
        return filters;
    }
    @Override
    public void addOrder_wrap(Order_wrap order_wrap){
        Order_info order_info =order_wrap.order_info;
        UserAuth userAuth=userRepository.getUser(order_info.getUserid());
        Order order=new Order(userAuth,order_info);
       int order_id= orderInfoRepository.addOrder(order);
       order.setId(order_id);
       List<Order_product_wrap> order_product_wraps=order_wrap.getOrder_products();
       orderProductRepository.addProducts(order_product_wraps,order);
    }
    @Override
    public List<Order> getOrdersByProductName(String productName){
        List<Order_product> order_products= orderProductRepository.getProducts();
        List<Order> sorter=new ArrayList<>();
        for(int i=0;i<order_products.size();i++){
            Order_product order_product=order_products.get(i);
            Book book=order_product.getBook();
            if(book.getName().contains(productName)){
                sorter.add(order_product.getOrder());
            }
        }
        return sorter;
//        List<Order_product> order_products=orderProductRepository.getOrderProductsByProductName(productName);
//        List<Order> orders=new ArrayList<>();
//        for(Order_product it:order_products){
//            orders.add(it.getOrder());
//        }
//        return orders;
    }
}
