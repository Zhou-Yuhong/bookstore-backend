package zn.zyh.back_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zn.zyh.back_code.entity.Book;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b")
    List<Book> getBooks();

//    @Query(value = "from UserAuth where username = :username and password = :password")
//    UserAuth checkUser(@Param("username") String username, @Param("password") String password);
//    @Modifying
//    @Query("update Book set inventory=?1 where id=?2")
//    void updateInventory(int inventory,int id);
    @Modifying
    @Query(value = "update Book set inventory = :inventory where id = :id")
    void updateInventory(@Param("inventory") int inventory,@Param("id") int id);
}
