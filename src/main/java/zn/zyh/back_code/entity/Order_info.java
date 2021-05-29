package zn.zyh.back_code.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="order_info")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Order_info {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id",unique = true,nullable = false)
    private int id;
    //下订单的用户名
    private int userid;
    private String order_time;
    //订单包含的商品种类数目
    private int num;
    //订单总价值
    private double value;
    //顶单状态
    private int state;


//    public int getId(){
//        return id;
//    }
//    public String getUsername(){
//        return username;
//    }
//    public String getOrder_time(){
//        return order_time;
//    }
//    public int getNum(){
//        return num;
//    }
//    public double getValue(){
//        return value;
//    }
//    public int getState(){
//        return state;
//    }
    public Order_info( int  userid, String order_time,int num,double value,int state){
        this.userid=userid;
        this.order_time=order_time;
        this.num=num;
        this.value=value;
        this.state=state;
    }
    public Order_info(){}
//    public Order_info(){}
}
