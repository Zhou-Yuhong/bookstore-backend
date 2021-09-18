package zn.zyh.back_code.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="order_product")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Order_product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_product_id",unique = true,nullable = false)
    private int id;
    private int num;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)// optional为false，表示Order_info不能为空。删除Order_Info,不影响products
    @JoinColumn(name="order_id",referencedColumnName ="id")
    @JsonBackReference
    private Order order;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    private Book book;
    public Order_product(int num, Order order, Book book){
        this.order =order;
        this.book=book;
        this.num=num;
    }
    public Order_product(){

    }
}
