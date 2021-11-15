package zn.zyh.back_code.entity;

import lombok.Data;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "BookReview")
public class BookReview {
    @Field("bookId")
    private Integer bookId;

    @Field("review")
    private String review;

    public BookReview(){};

    public BookReview(Integer bookId,String review){
        this.bookId=bookId;
        this.review=review;
    }
}
