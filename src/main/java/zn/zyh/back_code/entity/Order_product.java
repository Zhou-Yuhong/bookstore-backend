package zn.zyh.back_code.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="order_product")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Order_product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id",unique = true,nullable = false)
    private int id;
    private int product_id;
    private int order_id;
    private int num;
    public Order_product(int product_id,int order_id,int num){
        this.product_id=product_id;
        this.order_id=order_id;
        this.num=num;
    }
    public Order_product(){

    }
//    private String name;
//    private int num;
//    private double price;
//    private String image;
//    private String author;
//    public int getProduct_id(){
//        return product_id;
//    }
//    public int getOrder_id(){
//        return order_id;
//    }
//    public String getName(){
//        return name;
//    }
//    public int getNum(){
//        return num;
//    }
//    public double getPrice(){
//        return price;
//    }
//    public String getImage(){
//        return image;
//    }
//    public String getAuthor(){return author;}
//    public Order_product(int product_id,int order_id,String name,int num,double price,String image,String author){
//        this.product_id=product_id;
//        this.order_id=order_id;
//        this.name=name;
//        this.num=num;
//        this.price=price;
//        this.image=image;
//        this.author=author;
//    }
//
//    public Order_product(){}
//    public Order_product(int product_id,int order_id,int num){
//        this.product_id=product_id;
//        this.order_id=order_id;
//        this.num=num;
//        this.name="";
//        this.image="";
//        this.price=0;
//        this.author="";
//    }
//    public void set_info(String name,double price,String image,String author){
//        this.name=name;
//        this.price=price;
//        this.image=image;
//        this.author=author;
//    }
}
