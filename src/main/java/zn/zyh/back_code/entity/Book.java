package zn.zyh.back_code.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="id")
    private int id;

    private String isbn;
    private String name;
    private String type;
    private String author;
    private Double price;
    private String description;
    private Integer inventory;
    private String image;
    public Book( int id, String isbn, String name, String type, String author, Double price, String description,Integer inventory, String image){
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
    public void setInventory(Integer inventory){
        this.inventory=inventory;
    }
    public String getName() {
        return name;
    }
    public int getId(){
        return this.id;
    }
    public String getType(){
        return this.type;
    }

    public String getAuthor() {
        return author;
    }
    public Double getPrice(){
        return price;
    }
    public String getDescription(){
        return description;
    }
    public Integer getInventory(){
        return this.inventory;
    }
    public String getIsbn(){
        return this.isbn;
    }
    public String getImage(){
        return this.image;
    }
}
