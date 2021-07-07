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
import zn.zyh.back_code.entity.*;
import zn.zyh.back_code.service.BookService;
import zn.zyh.back_code.service.OrderService;
import zn.zyh.back_code.utils.objectutils.*;

import java.math.BigDecimal;
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
    public List<Order_info> getOrders(@RequestBody JSONObject param){
        int userid=param.getInt("userid");
        System.out.print("服务器接收到getorders请求\n");
        List<Order_info> result=orderService.getOrderByUser(userid);
        return result;
    }
    @RequestMapping("/setOrders")
    public void setOrders(@RequestBody JSONObject param){
         System.out.print(param);
        JSONObject order_info=param.getJSONObject("order_info");
        int userid=order_info.getInt("userid");
        String order_time=order_info.getString("order_time");
        int num=order_info.getInt("num");
        String S_value= order_info.getString("value");
        BigDecimal value=new BigDecimal(S_value);
        int state=order_info.getInt("state");
        //这里的id也不重要
        Order_info orderInfo=new Order_info(userid,order_time,num,value,state);
        List<Order_product_wrap> order_products=new ArrayList<>();
        JSONArray products=param.getJSONArray("order_products");
        for(int i=0;i<products.size();i++){
            JSONObject tmp=(JSONObject) products.get(i);
            //这里的order_id不重要，
            Order_product_wrap new_order=new Order_product_wrap(tmp.getInt("product_id"),0,tmp.getInt("num"));
            order_products.add(new_order);
            //TODO 添加下订单未成功的处理
            bookService.reduceStocks(tmp.getInt("product_id"),tmp.getInt("num"));
        }
        Order_wrap order_wrap=new Order_wrap(orderInfo,order_products);

        this.orderService.addOrder_wrap(order_wrap);
        return;
    }
    @RequestMapping("/getAllOrders")
    public List<Order_info> getAllOrders(@RequestBody Map<String, String> params){
        List<Order_info> result=orderService.GetAllOrders();
        return result;
    }
    @RequestMapping("/getDateOrders")
    List<Order_info> getDateOrders(@RequestBody JSONObject param){
        JSONArray date=param.getJSONArray("date");
        String begin=(String)date.get(0);
        String end=(String)date.get(1);
        return orderService.getOrderidsBytime(begin,end);
    }
    @RequestMapping("/getOrderItems")
    List<Order_product_wrap> getOrderItems(@RequestBody JSONObject param){
        Integer order_id=param.getInt("order_id");
        return orderService.getProductsByOrderid(order_id);
    }
    @RequestMapping("/getProductOrders")
    List<Order_info> getProductOrders(@RequestBody JSONObject param){
        String productName= param.getString("productName");
        return orderService.getOrdersByProductName(productName);
    }
}
