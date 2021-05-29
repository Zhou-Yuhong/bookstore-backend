package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import zn.zyh.back_code.dao.BookDao;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Integer id){
        return bookRepository.getOne(id);
    }
    @Override
    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }
    @Override
    public boolean reduceStocks(Integer id, Integer num){
         Book book=bookRepository.getOne(id);
         int inventory=book.getInventory();
         if(num>inventory) return false;
         else {
             bookRepository.updateInventory(inventory-num,id);
             return true;
         }
    }
    @Override
    public void updateInventory(int inventory,int id){
        bookRepository.updateInventory(inventory,id);
    }

}
