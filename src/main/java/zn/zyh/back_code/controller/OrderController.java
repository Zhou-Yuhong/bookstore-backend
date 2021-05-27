package zn.zyh.back_code.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zn.zyh.back_code.constant.Constant;
import zn.zyh.back_code.entity.Order_info;
import zn.zyh.back_code.entity.Order_product;
import zn.zyh.back_code.entity.Order_wrap;
import zn.zyh.back_code.service.BookService;
import zn.zyh.back_code.service.OrderService;
import zn.zyh.back_code.utils.objectutils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;
    private Objectutils objectutils;
    //返回所有订单
    @RequestMapping("/getOrders")
    public List<Order_wrap> getOrders(@RequestBody Map<String,String> params){
        String username=params.get(Constant.USERNAME);
        System.out.print("服务器接收到getorders请求\n");
        List<Order_wrap> result=orderService.getOrder_wrapsByUsername(username);
        return result;
    }
    @RequestMapping("/setOrders")
    public void setOrders(@RequestBody JSONObject param){
         System.out.print(param);
        JSONObject order_info=param.getJSONObject("order_info");
        String username=order_info.getString("username");
        String order_time=order_info.getString("order_time");
        int num=order_info.getInt("num");
        int value= order_info.getInt("value");
        int state=order_info.getInt("state");
        //这里的id也不重要
        Order_info orderInfo=new Order_info(0,username,order_time,num,value,state);
        List<Order_product> order_products=new ArrayList<>();
        JSONArray products=param.getJSONArray("order_products");
        for(int i=0;i<products.size();i++){
            JSONObject tmp=(JSONObject) products.get(i);
            //这里的order_id不重要，
            Order_product new_order=new Order_product(tmp.getInt("product_id"),0,tmp.getInt("num"));
            order_products.add(new_order);
            //TODO 添加下订单未成功的处理
            bookService.reduceStocks(tmp.getInt("product_id"),tmp.getInt("num"));
        }
        Order_wrap order_wrap=new Order_wrap(orderInfo,order_products);

        this.orderService.addOrder_wrap(order_wrap);
        return;
    }
}
