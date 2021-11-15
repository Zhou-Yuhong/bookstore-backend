package zn.zyh.back_code.repository;

import org.springframework.data.relational.core.sql.In;
import zn.zyh.back_code.entity.Book;

import java.util.List;

public interface BookRepository {
    Book findOne(Integer id);

    List<Book> getBooks();
    List<Book> getPageBooks(Integer page);
    boolean reduceStocks(Integer id, Integer num);
    void updateInventory(int inventory,int id);

    void deleteBooks(List<Book> books);

    void addBooks(List<Book> books);

    void updateBooks(List<Book> books);
    void updateBook(Book book);
    List<Book> getBooksByType(String type);
}
