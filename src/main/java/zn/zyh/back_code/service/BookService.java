package zn.zyh.back_code.service;

import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.entity.BookReview;

import java.util.List;

public interface BookService {
    Book findBookById(Integer id);
    List<Book> getBooks();
    //减少库存
    boolean reduceStocks(Integer id,Integer num);
    void updateInventory(int inventory,int id);

    //删除书籍
    void deleteBooks(List<Book> books);
    List<Book> getPageBooks(Integer page);
    List<Book> searchBook(String word);
    //增加书籍
    void addBooks(List<Book> books);

    //更新书籍
    void updateBooks(List<Book> books);
    boolean addBookReview(Integer bookId,String review);
    void deleteBookReview(Integer bookId);
    boolean editBookReview(Integer bookId,String BookReview);
    BookReview getBookReview(Integer bookId);

    List<Book> searchByTag(String tag);
}
