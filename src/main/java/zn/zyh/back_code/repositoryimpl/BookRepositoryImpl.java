package zn.zyh.back_code.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zn.zyh.back_code.dao.BookDao;
import zn.zyh.back_code.repository.BookRepository;
import zn.zyh.back_code.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Autowired
    private BookDao bookDao;

    @Override
    public Book findOne(Integer id){
        return bookDao.getOne(id);
    }
    @Override
    public List<Book> getBooks(){
        return bookDao.getBooks();
    }
    @Override
   public List<Book> getPageBooks(Integer page){
        PageRequest pageRequest = PageRequest.of(page,9);
        Page<Book> books=bookDao.findAll(pageRequest);
        return books.getContent();
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean reduceStocks(Integer id, Integer num){
         Book book= bookDao.getBookById(id);
         int inventory=book.getInventory();
         if(num>inventory) return false;
         else {
             book.setInventory(inventory-num);
             bookDao.saveAndFlush(book);
             return true;
         }
    }
    @Override
    public void updateInventory(int inventory,int id){
        bookDao.updateInventory(inventory,id);
    }

    @Override
    public void deleteBooks(List<Book> books){
        for(int i=0;i<books.size();i++){
            bookDao.deleteById(books.get(i).getId());
            bookDao.flush();
        }
    }
    @Override
    public void addBooks(List<Book> books){
        for(int i=0;i<books.size();i++){
            bookDao.saveAndFlush(books.get(i));
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void updateBooks(List<Book> books){
        for(int i=0;i<books.size();i++){
            Book book= bookDao.getBookById(books.get(i).getId());
            book.setType(books.get(i).getType());
            book.setAuthor(books.get(i).getAuthor());
            book.setName(books.get(i).getName());
            book.setDescription(books.get(i).getDescription());
            book.setPrice(books.get(i).getPrice());
            book.setInventory(books.get(i).getInventory());
            book.setIsbn(books.get(i).getIsbn());
            book.setImage(books.get(i).getImage());
            bookDao.save(book);
            bookDao.flush();
        }
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public   void updateBook(Book book){
        Book book1=bookDao.getBookById(book.getId());
        book1.updateInfo(book);
        bookDao.saveAndFlush(book1);
    }
}
