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
   public List<Book> getPageBooks(Integer page){
        List<Book> all=bookRepository.getBooks();
        int begin=(page-1)*9;
        int end=page*9;
        List<Book> result=new ArrayList<>();
        if(begin>=all.size()) return result;
        end= end<all.size() ? end:all.size();
        for(int i=begin;i<end;i++){
            result.add(all.get(i));
        }
        return result;
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

    @Override
    public void deleteBooks(List<Book> books){
        for(int i=0;i<books.size();i++){
            bookRepository.deleteById(books.get(i).getId());
            bookRepository.flush();
        }
    }
    @Override
    public void addBooks(List<Book> books){
        for(int i=0;i<books.size();i++){
            bookRepository.saveAndFlush(books.get(i));
        }
    }

    @Override
    public void updateBooks(List<Book> books){
        for(int i=0;i<books.size();i++){
            Book book=bookRepository.getOne(books.get(i).getId());
            book.setType(books.get(i).getType());
            book.setAuthor(books.get(i).getAuthor());
            book.setName(books.get(i).getName());
            book.setDescription(books.get(i).getDescription());
            book.setPrice(books.get(i).getPrice());
            book.setInventory(books.get(i).getInventory());
            book.setIsbn(books.get(i).getIsbn());
            book.setImage(books.get(i).getImage());
            bookRepository.save(book);
            bookRepository.flush();
        }
    }
}
