package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.repository.BookRepository;
import zn.zyh.back_code.repository.LuceneRepository;
import zn.zyh.back_code.search.BookSearch;
import zn.zyh.back_code.service.LuceneService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LuceneRepository luceneRepository;
    @Override
    public void synProductCreatIndex(){
        // 获取所有的book
        List<Book> bookList=bookRepository.getBooks();
        List<BookSearch> bookSearches=new ArrayList<>();
        for(Book it:bookList){
            bookSearches.add(new BookSearch(it));
        }
        // 再插入productList
        luceneRepository.createBookIndex(bookSearches);
    }
}
