package zn.zyh.back_code.dao;

import zn.zyh.back_code.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> getBooks();
}
