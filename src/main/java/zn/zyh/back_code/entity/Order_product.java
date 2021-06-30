package zn.zyh.back_code.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_product_id",unique = true,nullable = false)
    private int id;
    private int product_id;
    private int order_id;
    private int num;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)// optional为false，表示Order_info不能为空。删除Order_Info,不影响products
    @JoinColumn(name="order_id",insertable = false,updatable = false)
    @JsonBackReference
    private Order_info order_info;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="product_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Book book;
    public Order_product(int product_id,int order_id,int num){
        this.product_id=product_id;
        this.order_id=order_id;
        this.num=num;
    }
    public Order_product(){

    }
}
