package zn.zyh.back_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zn.zyh.back_code.entity.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth,Integer> {
    @Query(value = "from UserAuth where username = :username and password = :password")
    UserAuth checkUser(@Param("username") String username, @Param("password") String password);
}
