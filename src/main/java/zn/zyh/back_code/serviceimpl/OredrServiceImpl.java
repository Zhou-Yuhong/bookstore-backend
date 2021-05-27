package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.dao.BookDao;
import zn.zyh.back_code.dao.Order_infoDao;
import zn.zyh.back_code.dao.Order_productDao;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.entity.Order_info;
import zn.zyh.back_code.entity.Order_product;
import zn.zyh.back_code.entity.Order_wrap;
import zn.zyh.back_code.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OredrServiceImpl implements OrderService {
    @Autowired
    Order_infoDao orderInfoDao;
    @Autowired
    Order_productDao orderProductDao;
    @Autowired
    BookDao bookDao;
    @Override
    public List<Order_wrap> getOrder_wrapsByUsername(String username){
       List<Order_info> orders=orderInfoDao.findByUsername(username);
       List<Order_wrap> order_wraps=new ArrayList<>();
       for(int i=0;i<orders.size();i++){
           Order_info order=orders.get(i);
           //order_wraps.add(new Order_wrap(order,orderProductDao.getProducts(order.getId())));
           List<Order_product> products=orderProductDao.getProducts(order.getId());
           for(int j=0;j<products.size();j++){
              Book book=bookDao.findOne(products.get(j).getProduct_id());
              products.get(j).set_info(book.getName(),book.getPrice(),book.getImage(),book.getAuthor());
           }
           order_wraps.add(new Order_wrap(order,products));
       }
       return order_wraps;
    }
    @Override
    public void addOrder_wrap(Order_wrap order_wrap){
       int order_id=orderInfoDao.addOrder_info(order_wrap.order_info);
       orderProductDao.addProducts(order_wrap.order_products,order_id);

    }
}
