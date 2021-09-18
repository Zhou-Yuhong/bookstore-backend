package zn.zyh.back_code.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zn.zyh.back_code.entity.User;

public interface UserDao extends JpaRepository<User,Integer> {
}
