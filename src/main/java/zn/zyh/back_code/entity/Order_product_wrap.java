package zn.zyh.back_code.entity;

import lombok.Data;

import java.math.BigDecimal;

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
    public Order_product_wrap(Order_product order_product,String name,BigDecimal price,String image,String author){
        this.id=order_product.getId();
        this.product_id=order_product.getProduct_id();
        this.order_id=order_product.getOrder_id();
        this.num=order_product.getNum();
        this.name=name;
        this.price=price;
        this.image=image;
        this.author=author;
    }
    public Order_product_wrap(Order_product order_product){
        this.id=order_product.getId();
        this.product_id=order_product.getProduct_id();
        this.order_id=order_product.getOrder_id();
        this.num=order_product.getNum();
        Book book=order_product.getBook();
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
    public BigDecimal getPrice(){
        return this.price;
    }
}
