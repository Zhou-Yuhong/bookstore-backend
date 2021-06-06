package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.dao.BookDao;
import zn.zyh.back_code.dao.Order_infoDao;
import zn.zyh.back_code.dao.Order_productDao;
import zn.zyh.back_code.dao.UserDao;
import zn.zyh.back_code.entity.*;
import zn.zyh.back_code.service.OrderService;

import javax.transaction.Transactional;
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
