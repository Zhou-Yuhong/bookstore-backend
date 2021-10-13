package zn.zyh.back_code.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zn.zyh.back_code.entity.Book;

import javax.transaction.Transactional;
import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer> {
    @Query("select b from Book b")
    List<Book> getBooks();
    @Modifying
    @Query(value = "update Book set inventory = :inventory where id = :id")
    void updateInventory(@Param("inventory") int inventory,@Param("id") int id);
    Book getBookById(Integer id);
}
