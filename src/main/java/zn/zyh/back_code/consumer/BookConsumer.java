package zn.zyh.back_code.consumer;
import static zn.zyh.back_code.constant.RabbitmqConstant.*;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.service.BookService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RabbitListener(queues = QUEUE_ADD_BOOK_NAME)
public class BookConsumer {
    @Autowired
    BookService bookService;

    @RabbitHandler
    public void handle(JSONObject param){
        List<Book> debooks=new ArrayList<>();
        JSONArray books=param.getJSONArray("deletebooks");
        for(Object it:books){
            JSONObject tmp=(JSONObject)it;
            //public Book( int id, String isbn, String name, String type, String author, BigDecimal price, String description,Integer inventory, String image)
            BigDecimal value=new BigDecimal(tmp.getString("price"));
            Book book=new Book(tmp.getInteger("key"),tmp.getString("isbn"),tmp.getString("name"),tmp.getString("type"),tmp.getString("author"),value,tmp.getString("description"),tmp.getInteger("inventory"),tmp.getString("image"));
            debooks.add(book);
        }
        bookService.deleteBooks(debooks);
    }
}
