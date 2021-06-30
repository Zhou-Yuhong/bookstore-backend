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

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @RequestMapping("/getPageBooks")
    public List<Book> getPageBooks(@RequestBody JSONObject param){
        Integer page=param.getInt("page");
        return bookService.getPageBooks(page) ;

    }
    @RequestMapping("/getBook")
    public Book getBook(@RequestParam("id") Integer id){
        Book book= bookService.findBookById(id);
        return book;
    }

    @RequestMapping("/deleteBooks")
    public boolean deleteBooks(@RequestBody JSONObject param){
        List<Book> debooks=new ArrayList<>();
       JSONArray books=param.getJSONArray("deletebooks");
       for(int i=0;i<books.size();i++){
           JSONObject tmp=(JSONObject)books.get(i);
           //public Book( int id, String isbn, String name, String type, String author, BigDecimal price, String description,Integer inventory, String image)
           BigDecimal value=new BigDecimal(tmp.getString("price"));
           Book book=new Book(tmp.getInt("key"),tmp.getString("isbn"),tmp.getString("name"),tmp.getString("type"),tmp.getString("author"),value,tmp.getString("description"),tmp.getInt("inventory"),tmp.getString("image"));
           debooks.add(book);
       }
       bookService.deleteBooks(debooks);
        return true;
    }

    @RequestMapping("/updateBooks")
    public boolean updateBooks(@RequestBody JSONObject param){
        List<Book> upbooks=new ArrayList<>();
        JSONArray books=param.getJSONArray("updatebooks");
        for(int i=0;i<books.size();i++){
            JSONObject tmp=(JSONObject)books.get(i);
            //public Book( int id, String isbn, String name, String type, String author, BigDecimal price, String description,Integer inventory, String image)
            BigDecimal value=new BigDecimal(tmp.getString("price"));
            Book book=new Book(tmp.getInt("key"),tmp.getString("isbn"),tmp.getString("name"),tmp.getString("type"),tmp.getString("author"),value,tmp.getString("description"),tmp.getInt("inventory"),tmp.getString("image"));
            upbooks.add(book);
        }
        bookService.updateBooks(upbooks);
        return true;
    }
    @RequestMapping("/addBooks")
    public boolean addBooks(@RequestBody JSONObject param){
        List<Book> adbooks=new ArrayList<>();
        JSONArray books=param.getJSONArray("addbooks");
        for(int i=0;i<books.size();i++){
            JSONObject tmp=(JSONObject)books.get(i);
            //public Book( int id, String isbn, String name, String type, String author, BigDecimal price, String description,Integer inventory, String image)
            BigDecimal value=new BigDecimal(tmp.getString("price"));
            Book book=new Book(0,tmp.getString("isbn"),tmp.getString("name"),tmp.getString("type"),tmp.getString("author"),value,tmp.getString("description"),tmp.getInt("inventory"),tmp.getString("image"));
            adbooks.add(book);
        }
        bookService.addBooks(adbooks);
        return true;
    }
}
