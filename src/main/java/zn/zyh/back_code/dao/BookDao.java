package zn.zyh.back_code.dao;

import org.springframework.data.relational.core.sql.In;
import zn.zyh.back_code.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> getBooks();
    boolean reduceStocks(Integer id, Integer num);
    void updateInventory(int inventory,int id);

    void deleteBooks(List<Book> books);

    void addBooks(List<Book> books);

    void updateBooks(List<Book> books);
}
