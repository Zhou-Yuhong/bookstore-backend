package zn.zyh.back_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zn.zyh.back_code.entity.Order_info;

import java.util.List;

public interface Order_infoRepository extends JpaRepository<Order_info,Integer> {
    @Query("select o from Order_info o where o.userid = ?1")
    List<Order_info> findByUserid(int userid);
}
