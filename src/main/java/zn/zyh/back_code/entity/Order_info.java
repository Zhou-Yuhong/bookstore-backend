package zn.zyh.back_code.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_info")
public class Order_info {
    @Id
    private int id;
    //下订单的用户名
    private String username;
    private String order_time;
    //订单包含的商品种类数目
    private int num;
    //订单总价值
    private int value;
    //顶单状态
    private int state;


    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getOrder_time(){
        return order_time;
    }
    public int getNum(){
        return num;
    }
    public int getValue(){
        return value;
    }
    public int getState(){
        return state;
    }
    public Order_info(int id, String username, String order_time,int num,int value,int state){
        this.id=id;
        this.username=username;
        this.order_time=order_time;
        this.num=num;
        this.value=value;
        this.state=state;
    }
}
