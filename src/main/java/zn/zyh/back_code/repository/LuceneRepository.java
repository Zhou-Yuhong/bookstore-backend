package zn.zyh.back_code.repository;

import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.search.BookSearch;
import java.util.List;
public interface LuceneRepository {
    void createBookIndex(List<BookSearch> bookSearches);
    List<BookSearch> getBookByWord(String word);
    void deleteBookIndex(Integer bookId);
    void updateBookIndex(List<Book> books);
}
