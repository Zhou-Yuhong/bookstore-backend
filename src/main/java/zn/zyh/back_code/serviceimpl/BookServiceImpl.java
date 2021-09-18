package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.repository.BookRepository;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.service.BookService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book findBookById(Integer id){
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }
    @Override
    public List<Book> getPageBooks(Integer page){
        return bookRepository.getPageBooks(page);
    }
    @Override
    public boolean reduceStocks(Integer id,Integer num){
        return bookRepository.reduceStocks(id,num);
    }
    @Override
    public void updateInventory(int inventory,int id){
        bookRepository.updateInventory(inventory,id);
    }

    @Override
    public void deleteBooks(List<Book> books){
        bookRepository.deleteBooks(books);
    }

    @Override
    public void addBooks(List<Book> books){
       bookRepository.addBooks(books);
    }

    @Override
    public void updateBooks(List<Book> books){
       bookRepository.updateBooks(books);
    }
}
