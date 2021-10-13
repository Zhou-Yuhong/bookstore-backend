package zn.zyh.back_code.serviceimpl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zn.zyh.back_code.repository.BookRepository;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.repository.LuceneRepository;
import zn.zyh.back_code.search.BookSearch;
import zn.zyh.back_code.service.BookService;
import zn.zyh.back_code.service.RedisService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Scope("singleton")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private LuceneRepository luceneRepository;
    @Override
    public Book findBookById(Integer id){
        //先在缓存里找书本
        String str= (String)(redisService.get(id.toString()));
        Book book=JSON.parseObject(str,Book.class);
        if(book==null){
            //在缓存里没找到，从数据库里找并写入缓存
            book=bookRepository.findOne(id);
            redisService.set(id.toString(),JSON.toJSONString(book));
            return book;
        }
        else {
            return book;
        }
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
        //清缓存
        for(Book it:books){
            Integer key=it.getId();
            redisService.remove(key.toString());
        }
        bookRepository.deleteBooks(books);
    }

    @Override
    public void addBooks(List<Book> books){
       bookRepository.addBooks(books);
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRED)
    public void updateBooks(List<Book> books){
       //删除数据库的缓存
        for(Book it:books){
            Integer key=it.getId();
            redisService.remove(key.toString());
        }
        //更新书籍信息
        bookRepository.updateBooks(books);
    }
    @Override
    public List<Book> searchBook(String word){
        List<Book> bookList=new ArrayList<>();
        List<BookSearch> bookSearches=luceneRepository.getBookByWord(word);
        for(BookSearch it:bookSearches){
            bookList.add(this.findBookById(it.getId()));
        }
        return bookList;
    }
}
