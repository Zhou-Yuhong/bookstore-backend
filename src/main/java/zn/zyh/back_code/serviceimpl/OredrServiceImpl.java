package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.dao.BookDao;
import zn.zyh.back_code.dao.Order_infoDao;
import zn.zyh.back_code.dao.Order_productDao;
import zn.zyh.back_code.dao.UserDao;
import zn.zyh.back_code.entity.*;
import zn.zyh.back_code.service.OrderService;
import zn.zyh.back_code.utils.analysisutils.AnaUtil;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OredrServiceImpl implements OrderService {
    @Autowired
    Order_infoDao orderInfoDao;
    @Autowired
    Order_productDao orderProductDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;
    @Override
    public List<Order_wrap> getOrder_wrapsByUserid(int userid){
       List<Order_info> orders=orderInfoDao.findByUserid(userid);
       List<Order_wrap> order_wraps=new ArrayList<>();
       for(int i=0;i<orders.size();i++){
           Order_info order=orders.get(i);
           //order_wraps.add(new Order_wrap(order,orderProductDao.getProducts(order.getId())));
           List<Order_product> products=orderProductDao.getProducts(order.getId());
           List<Order_product_wrap> product_wraps=new ArrayList<>();
           for(int j=0;j<products.size();j++){
              Book book=bookDao.findOne(products.get(j).getProduct_id());
               Order_product_wrap tmp=new Order_product_wrap(products.get(j),book.getName(),book.getPrice(),book.getImage(),book.getAuthor());
               product_wraps.add(tmp);
           }
           order_wraps.add(new Order_wrap(order,product_wraps));
       }
       return order_wraps;
    }
    //获得orderid对应的商品
    @Override
    public List<Order_product_wrap> getProductsByOrderid(int orderid){
        List<Order_product_wrap> product_wraps=new ArrayList<>();
        List<Order_product> products=orderProductDao.getProducts(orderid);
        for(int j=0;j<products.size();j++){
            Book book=bookDao.findOne(products.get(j).getProduct_id());
            Order_product_wrap tmp=new Order_product_wrap(products.get(j),book.getName(),book.getPrice(),book.getImage(),book.getAuthor());
            product_wraps.add(tmp);
        }
        return product_wraps;
    }
    @Override
    public List<Order_product_wrap> getproductsByUseridandTime(int userid,String begin,String end){
        //先获得所有符合条件的Order_info
        List<Order_info> orders=orderInfoDao.getOrdersByUseridAndTime(userid,begin,end);
        if(orders.size()==0){
            List<Order_product_wrap> empty=new ArrayList<>();
            return empty;
        }
        List<Order_product_wrap> result=getProductsByOrderid(orders.get(0).getId());
        for(int i=1;i<orders.size();i++){
            AnaUtil.compact(result,getProductsByOrderid(orders.get(i).getId()));
        }
        return result;
//        for(int i=0;i<orders.size()-1;i++){
//             List
//        }
    }

    @Override
    public List<Order_product_wrap> getProductsByTime(String begin,String end){
        List<Order_info> orders=orderInfoDao.getOrdersByTime(begin,end);
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
    public List<Order_info> getOrderidsBytime(String begin, String end){
         return orderInfoDao.getOrdersByTime(begin,end);
    }
    //根据时间范围返回所有用户消费信息
    @Override
    public List<User_Comsumption> getUser_ComByTime(String begin,String end){
        //先获得时间范围内的所有订单
        List<Order_info> orders=orderInfoDao.getOrdersByTime(begin, end);
        List<User_Comsumption> result=new ArrayList<>();
        //遍历订单
        for(int i=0;i<orders.size();i++){
            Order_info order_info=orders.get(i);
            //根据订单得到用户信息
            UserAuth user=userDao.getUser(order_info.getUserid());
            //根据订单id得到所用商品信息
            List<Order_product_wrap> product_wraps=getProductsByOrderid(order_info.getId());
            //得到订单价值
            BigDecimal value=order_info.getValue();
            User_Comsumption user_comsumption=new User_Comsumption(user,product_wraps,value);
            AnaUtil.addUserComsumption(result,user_comsumption);
        }
        return result;
    }
    @Override
    public List<Order_wrap> getAllOrders(){
        //先取得所有用户
        List<UserAuth> userAuths=userDao.getUsers();
        List<Order_wrap> order_wraps=new ArrayList<>();
        //遍历用户得到所有订单
        for(int k=0;k<userAuths.size();k++){
            List<Order_info> orders=orderInfoDao.findByUserid(userAuths.get(k).getUserId());
            for(int i=0;i<orders.size();i++){
                Order_info order=orders.get(i);
                //order_wraps.add(new Order_wrap(order,orderProductDao.getProducts(order.getId())));
                List<Order_product> products=orderProductDao.getProducts(order.getId());
                List<Order_product_wrap> product_wraps=new ArrayList<>();
                for(int j=0;j<products.size();j++){
                    Book book=bookDao.findOne(products.get(j).getProduct_id());
                    Order_product_wrap tmp=new Order_product_wrap(products.get(j),book.getName(),book.getPrice(),book.getImage(),book.getAuthor());
                    product_wraps.add(tmp);
                }
                order_wraps.add(new Order_wrap(order,product_wraps));
            }
        }
        return order_wraps;
    }
    @Override
    public void addOrder_wrap(Order_wrap order_wrap){
       int order_id=orderInfoDao.addOrder_info(order_wrap.order_info);
       List<Order_product>order_products=new ArrayList<>();
       //由wrap生成需要的
       for(int i=0;i<order_wrap.order_products.size();i++){
           order_products.add(new Order_product(order_wrap.order_products.get(i).getProduct_id(),order_wrap.order_products.get(i).getOrder_id(),order_wrap.order_products.get(i).getNum()));
       }
       orderProductDao.addProducts(order_products,order_id);
    }
}
