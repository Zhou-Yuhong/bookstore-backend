package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.dao.Order_productDao;
import zn.zyh.back_code.entity.Order_product;
import zn.zyh.back_code.entity.Order_product_wrap;
import zn.zyh.back_code.repository.Order_productRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Order_productDaoImpl implements Order_productDao {
    @Autowired
    Order_productRepository order_productRepository;
    @Override
    public void addProducts(List<Order_product> order_products, int orderid){
        for(int i=0;i<order_products.size();i++){
            order_products.get(i).setOrder_id(orderid);
            order_productRepository.saveAndFlush(order_products.get(i));
        }
    }
}
