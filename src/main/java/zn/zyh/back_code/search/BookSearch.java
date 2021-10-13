package zn.zyh.back_code.search;

import lombok.Data;
import zn.zyh.back_code.entity.Book;

@Data
public class BookSearch {
    private int id;

    private String description;

    public BookSearch(){}

    public BookSearch(Book book){
        this.id=book.getId();
        this.description=book.getDescription();
    }
}
