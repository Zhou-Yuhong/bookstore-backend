package zn.zyh.back_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zn.zyh.back_code.entity.Order_product;

import java.util.List;

public interface Order_productRepository extends JpaRepository<Order_product,Integer> {
}
