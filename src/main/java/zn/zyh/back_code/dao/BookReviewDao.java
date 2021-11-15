package zn.zyh.back_code.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import zn.zyh.back_code.entity.BookReview;

public interface BookReviewDao extends MongoRepository<BookReview,Integer> {
    BookReview getBookReviewByBookId(Integer bookId);
}
