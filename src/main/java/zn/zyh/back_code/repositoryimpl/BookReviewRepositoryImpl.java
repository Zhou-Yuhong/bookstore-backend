package zn.zyh.back_code.repositoryimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.dao.BookReviewDao;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.entity.BookReview;
import zn.zyh.back_code.repository.BookReviewRepository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class BookReviewRepositoryImpl implements BookReviewRepository {
    @Autowired
    BookReviewDao bookReviewDao;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public boolean addBookReview(Integer bookId,String review){
        BookReview bookReview=bookReviewDao.getBookReviewByBookId(bookId);
        if(bookReview!=null){
            return false;
        }
        BookReview bookReview2=new BookReview(bookId,review);
        bookReviewDao.save(bookReview2);
        return true;
    }

    @Override
    public void deleteBookReview(Integer bookId){
        Query query=new Query(new Criteria("bookId").is(bookId));
        mongoTemplate.remove(query,BookReview.class);
    }

    @Override
    public boolean editBookReview(Integer bookId,String review){
        BookReview bookReview=bookReviewDao.getBookReviewByBookId(bookId);
        if(bookReview==null){
            return false;
        }
        Query query=new Query(new Criteria("bookId").is(bookId));
        Update update=new Update().set("review",review);
        mongoTemplate.updateMulti(query,update,BookReview.class);
        return true;
    }
    @Override
    public BookReview getBookReview(Integer bookId){
        return bookReviewDao.getBookReviewByBookId(bookId);
    }
    @Override
    public void updateBookReviews(List<BookReview> bookReviewList){
        for(BookReview it:bookReviewList){
            this.editBookReview(it.getBookId(),it.getReview());
        }
    }
}
