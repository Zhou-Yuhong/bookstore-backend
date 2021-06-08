package zn.zyh.back_code.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class User_Comsumption {
    private int userid;
    private String username;
    //购书数
    private int num;
    //消费额
    private BigDecimal value;
    public User_Comsumption(UserAuth userAuth, List<Order_product_wrap> order_product_wraps,BigDecimal value){
        this.userid=userAuth.getUserId();
        this.username=userAuth.getUsername();
        this.num=0;
        this.value=value;
        for(int i=0;i<order_product_wraps.size();i++){
            this.num+=order_product_wraps.get(i).getNum();
        }
    }
}
