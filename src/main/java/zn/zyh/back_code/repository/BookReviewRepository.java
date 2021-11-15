package zn.zyh.back_code.repository;

import zn.zyh.back_code.entity.BookReview;
import java.util.List;
public interface BookReviewRepository {
    //增删改查
    boolean addBookReview(Integer bookId,String review);
    void deleteBookReview(Integer bookId);
    boolean editBookReview(Integer bookId,String BookReview);
    BookReview getBookReview(Integer bookId);
    void updateBookReviews(List<BookReview> bookReviewList);
}
