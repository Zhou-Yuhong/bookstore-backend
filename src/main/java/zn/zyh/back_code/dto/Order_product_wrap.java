package zn.zyh.back_code.dto;

import lombok.Data;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.entity.Order_product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
public class Order_product_wrap {
    private int id;
    private int product_id;
    private int order_id;
    private String name;
    private int num;
    private BigDecimal price;
    private String image;
    private String author;
    public Order_product_wrap(Order_product order_product, String name, BigDecimal price, String image, String author){
        this.id=order_product.getId();
        this.product_id=order_product.getBook().getId();
        this.order_id=order_product.getOrder().getId();
        this.num=order_product.getNum();
        this.name=name;
        this.price=price;
        this.image=image;
        this.author=author;
    }
    public Order_product_wrap(Order_product order_product){
        this.id=order_product.getId();
        this.order_id=order_product.getOrder().getId();
        this.num=order_product.getNum();
        Book book=order_product.getBook();
        this.product_id=book.getId();
        this.name=book.getName();
        this.image=book.getImage();
        this.author=book.getAuthor();
        this.price=book.getPrice();
    }
    public Order_product_wrap(int product_id,int order_id,int num){
        this.product_id=product_id;
        this.order_id=order_id;
        this.num=num;
        this.name="";
        this.image="";
        this.author="";
    }
    public static List<Order_product_wrap> transform(List<Order_product> order_products){
        List<Order_product_wrap> order_product_wraps=new ArrayList<>();
        for(Order_product it:order_products){
            order_product_wraps.add(new Order_product_wrap(it));
        }
        return order_product_wraps;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
}
