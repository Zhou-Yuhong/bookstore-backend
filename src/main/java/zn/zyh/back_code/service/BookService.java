package zn.zyh.back_code.service;

import zn.zyh.back_code.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(Integer id);
    List<Book> getBooks();
}
