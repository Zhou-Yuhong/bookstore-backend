package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import zn.zyh.back_code.dao.BookDao;
import zn.zyh.back_code.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
     @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Book findOne(Integer id){
        Book book;
        String sql="SELECT * FROM book WHERE id=?";
        book=jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Book(
                  rs.getInt("id"),
                  rs.getString("isbn"),
                  rs.getString("name"),
                  rs.getString("type"),
                  rs.getString("author"),
                  rs.getDouble("price"),
                  rs.getString("description"),
                  rs.getInt("inventory"),
                  rs.getString("image")
                ),
                new Object[]{id});
        return book;

    }

    @Override
    public List<Book> getBooks() {
        List<Book> books=new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM book",
                (rs,rowNum)->new Book(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("inventory"),
                        rs.getString("image")
                )
                ).forEach(book->books.add(book));
        return books;
    }
    @Override
    public boolean reduceStocks(Integer id,Integer num) {
        //先查询获得库存量
        Book book;
        String sql = "SELECT * FROM book WHERE id=?";
        book = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("inventory"),
                        rs.getString("image")
                ),
                new Object[]{id});
        Integer inventory=book.getInventory();
        if(num>inventory) return false;
        else {
           String sql2="update book set inventory = ? where id = ?";
           Object args[]=new Object[]{inventory-num,id};
           int result=jdbcTemplate.update(sql2,args);
           if(result>0) return true;
           else  return false;
        }
    }


}
