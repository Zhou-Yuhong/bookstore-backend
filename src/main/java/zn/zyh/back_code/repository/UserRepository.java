package zn.zyh.back_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zn.zyh.back_code.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
