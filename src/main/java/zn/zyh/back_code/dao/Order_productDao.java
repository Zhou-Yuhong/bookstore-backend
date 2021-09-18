package zn.zyh.back_code.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zn.zyh.back_code.entity.Order_product;

import java.util.List;

public interface Order_productDao extends JpaRepository<Order_product,Integer> {
    List<Order_product> getOrder_productsByBook_Name(String name);
}
