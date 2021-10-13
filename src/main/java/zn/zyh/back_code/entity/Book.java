package zn.zyh.back_code.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="book")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",unique = true,nullable = false)
    private int id;

    private String isbn;
    private String name;
    private String type;
    private String author;
    private BigDecimal price;
    private String description;
    private Integer inventory;
    private String image;
    public Book(){
    };
    public Book( int id, String isbn, String name, String type, String author, BigDecimal price, String description,Integer inventory, String image){
        this.id=id;
        this.isbn=isbn;
        this.name=name;
        this.type=type;
        this.author=author;
        this.price=price;
        this.description=description;
        this.inventory=inventory;
        this.image=image;
    }
    public void updateInfo(Book book){
        this.isbn=book.isbn;
        this.name=book.name;
        this.type=book.type;
        this.author=book.author;
        this.price=book.price;
        this.description=book.description;
        this.inventory=book.inventory;
        this.image=book.image;
    }
}
