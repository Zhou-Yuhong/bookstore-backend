package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.dao.BookDao;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }
}
