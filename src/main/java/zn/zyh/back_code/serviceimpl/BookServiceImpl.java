package zn.zyh.back_code.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zn.zyh.back_code.entity.BookReview;
import zn.zyh.back_code.entity.TagNode;
import zn.zyh.back_code.repository.BookRepository;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.repository.BookReviewRepository;
import zn.zyh.back_code.repository.LuceneRepository;
import zn.zyh.back_code.repository.TagNodeRepository;
import zn.zyh.back_code.search.BookSearch;
import zn.zyh.back_code.service.BookService;
import zn.zyh.back_code.service.RedisService;

import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private BookReviewRepository bookReviewRepository;
    @Autowired
    private TagNodeRepository tagNodeRepository;
    @Override
    public Book findBookById(Integer id){
        //先在缓存里找书本
        String str= (String)(redisService.get(id.toString()));
        Book book=JSON.parseObject(str,Book.class);
        if(book==null){
            //在缓存里没找到，从数据库里找并写入缓存
            book=bookRepository.findOne(id);
            BookReview bookReview=bookReviewRepository.getBookReview(id);
            if(bookReview!=null){
                book.setReview(bookReview.getReview());
            }
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
        List<Book> result=bookRepository.getPageBooks(page);
        for(Book it:result){
            BookReview bookReview=bookReviewRepository.getBookReview(it.getId());
            if(bookReview!=null){
                it.setReview(bookReview.getReview());
            }
        }
        return result;
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
        //清缓存同时删除索引
        for(Book it:books){
            Integer key=it.getId();
            redisService.remove(key.toString());
            bookReviewRepository.deleteBookReview(key);
            luceneRepository.deleteBookIndex(key);
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
        //更新书评
        List<BookReview> bookReviews=new ArrayList<>();
        for(Book it:books){
            if(it.getReview()!=null){
                BookReview bookReview =new BookReview(it.getId(),it.getReview());
                bookReviews.add(bookReview);
            }
        }
        bookReviewRepository.updateBookReviews(bookReviews);
        //更新索引信息
        luceneRepository.updateBookIndex(books);
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
    @Override
    public boolean addBookReview(Integer bookId,String review){
        return bookReviewRepository.addBookReview(bookId,review);
    }
    @Override
    public void deleteBookReview(Integer bookId){
        bookReviewRepository.deleteBookReview(bookId);
    }
    @Override
    public boolean editBookReview(Integer bookId,String BookReview){
        return bookReviewRepository.editBookReview(bookId,BookReview);
    }
    @Override
    public BookReview getBookReview(Integer bookId){
        return bookReviewRepository.getBookReview(bookId);
    }
    @Override
    public List<Book> searchByTag(String tag){
        HashMap<Integer,Book> bookHashMap=new HashMap<>();
        List<Book> result = new ArrayList<>();
        TagNode tagNode = tagNodeRepository.findByTagName(tag);
        List<TagNode> tagNodes=tagNodeRepository.getTagNodes(tag);
        tagNodes.add(tagNode);
        for(TagNode it:tagNodes){
            List<Book> bookList=bookRepository.getBooksByType(it.getTagName());
            for(Book that:bookList){
                if(bookHashMap.containsKey(that.getId())) continue;
                bookHashMap.put(that.getId(),that);
            }
        }
        for(Integer key:bookHashMap.keySet()){
            result.add(bookHashMap.get(key));
        }
        return result;
    }
}
