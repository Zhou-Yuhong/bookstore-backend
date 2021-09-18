package zn.zyh.back_code.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zn.zyh.back_code.entity.Order;
import zn.zyh.back_code.entity.UserAuth;

import java.util.List;

public interface OrderDao extends JpaRepository<Order,Integer> {
//    @Query("select o from Order o where o.userid = ?1")
//    List<Order> findByUserid(int userid);
      List<Order> getOrdersByUserAuth(UserAuth userAuth);
}
