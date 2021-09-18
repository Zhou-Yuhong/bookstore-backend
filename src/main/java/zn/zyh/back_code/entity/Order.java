package zn.zyh.back_code.entity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import zn.zyh.back_code.dto.Order_info;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name="order_info")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",unique = true,nullable = false)
    private int id;
    private String order_time;
    //订单包含的商品种类数目
    private int num;
    //订单总价值
    private BigDecimal value;
    //顶单状态
    private int state;

    @JsonIgnore
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Order_product> order_products;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="userid",referencedColumnName = "user_id")
    private UserAuth userAuth;
    public Order(String order_time, int num, BigDecimal value, int state, UserAuth userAuth){
        this.userAuth=userAuth;
        this.order_time=order_time;
        this.num=num;
        this.value=value;
        this.state=state;
    }
    public Order(UserAuth userAuth, Order_info order_info){
        this.userAuth=userAuth;
        this.order_time=order_info.getOrder_time();
        this.num=order_info.getNum();
        this.value=order_info.getValue();
        this.state=order_info.getState();
    }
    public Order(){}


}
