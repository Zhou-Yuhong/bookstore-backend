package zn.zyh.back_code.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_product")
public class Order_product {
    @Id
    private int product_id;
    private int order_id;
    private String name;
    private int num;
    private int price;
    private String image;
    private String author;
    public int getProduct_id(){
        return product_id;
    }
    public int getOrder_id(){
        return order_id;
    }
    public String getName(){
        return name;
    }
    public int getNum(){
        return num;
    }
    public int getPrice(){
        return price;
    }
    public String getImage(){
        return image;
    }
    public String getAuthor(){return author;}
    public Order_product(int product_id,int order_id,String name,int num,int price,String image,String author){
        this.product_id=product_id;
        this.order_id=order_id;
        this.name=name;
        this.num=num;
        this.price=price;
        this.image=image;
        this.author=author;
    }
}
