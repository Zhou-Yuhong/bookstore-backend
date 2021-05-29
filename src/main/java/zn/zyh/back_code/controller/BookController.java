package zn.zyh.back_code.controller;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.service.BookService;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks(@RequestBody Map<String, String> params) {
        List<Book> result=bookService.getBooks();

        return result;
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam("id") Integer id){
        Book book= bookService.findBookById(id);
        return book;
    }
}
