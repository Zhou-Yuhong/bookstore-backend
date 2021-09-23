package zn.zyh.back_code.consumer;
import static zn.zyh.back_code.constant.RabbitmqConstant.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zn.zyh.back_code.dto.Order_info;
import zn.zyh.back_code.dto.Order_product_wrap;
import zn.zyh.back_code.dto.Order_wrap;
import zn.zyh.back_code.service.BookService;
import zn.zyh.back_code.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RabbitListener(queues = QUEUE_ADD_ORDER_NAME)
public class OrderConsumer {
    @Autowired
    BookService bookService;

    @Autowired
    OrderService orderService;

    @RabbitHandler
    public void handleDemand(JSONObject param){
        try {
            JSONObject order_info=param.getJSONObject("order_info");
            int userid=order_info.getInteger("userid");
            String order_time=order_info.getString("order_time");
            int num=order_info.getInteger("num");
            String S_value= order_info.getString("value");
            BigDecimal value=new BigDecimal(S_value);
            int state=order_info.getInteger("state");
            //这里的id也不重要
            Order_info orderInfo=new Order_info(userid,order_time,num,value,state);
            List<Order_product_wrap> order_products=new ArrayList<>();
            JSONArray products=param.getJSONArray("order_products");
            for(Object it:products){
                JSONObject tmp= JSON.parseObject(JSONObject.toJSONString(it));
                //这里的order_id不重要，
                Order_product_wrap new_order=new Order_product_wrap(tmp.getInteger("product_id"),0,tmp.getInteger("num"));
                order_products.add(new_order);
                //TODO 添加下订单未成功的处理
                bookService.reduceStocks(tmp.getInteger("product_id"),tmp.getInteger("num"));
            }
            Order_wrap order_wrap=new Order_wrap(orderInfo,order_products);

            this.orderService.addOrder_wrap(order_wrap);
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
