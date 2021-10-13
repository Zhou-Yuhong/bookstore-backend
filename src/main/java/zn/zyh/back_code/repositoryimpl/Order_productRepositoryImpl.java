package zn.zyh.back_code.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zn.zyh.back_code.dao.BookDao;
import zn.zyh.back_code.dao.Order_productDao;
import zn.zyh.back_code.dto.Order_product_wrap;
import zn.zyh.back_code.entity.Order;
import zn.zyh.back_code.repository.Order_productRepository;
import zn.zyh.back_code.entity.Order_product;

import java.util.List;

@Repository
public class Order_productRepositoryImpl implements Order_productRepository {
    @Autowired
    Order_productDao orderProductDao;
    @Autowired
    BookDao bookDao;
    @Override
    public List<Order_product> getProducts(){
        return orderProductDao.findAll();
    }
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addProducts(List<Order_product_wrap> order_product_wraps, Order order){
        for(Order_product_wrap it:order_product_wraps){
             Order_product order_product=new Order_product(it.getNum(),order,bookDao.getOne(it.getProduct_id()));
             orderProductDao.saveAndFlush(order_product);
        }
    }
    @Override
    public List<Order_product> getOrderProductsByProductName(String name){
        return orderProductDao.getOrder_productsByBook_Name(name);
    }
}
