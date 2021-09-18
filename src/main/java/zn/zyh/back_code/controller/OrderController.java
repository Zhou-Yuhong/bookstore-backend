package zn.zyh.back_code.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zn.zyh.back_code.dto.Order_info;
import zn.zyh.back_code.dto.Order_product_wrap;
import zn.zyh.back_code.dto.Order_wrap;
import zn.zyh.back_code.entity.*;
import zn.zyh.back_code.producer.OrderProducer;
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

    @Autowired
    private OrderProducer orderProducer;

    private Objectutils objectutils;
    //返回所有订单
    @RequestMapping("/getOrders")
    public List<Order> getOrders(@RequestBody JSONObject param){
        int userid=param.getInteger("userid");
        System.out.print("服务器接收到getorders请求\n");
        List<Order> result=orderService.getOrderByUser(userid);
        return result;
    }
    @RequestMapping("/setOrders")
    public void setOrders(@RequestBody JSONObject param){
       orderProducer.sendOrder(param);
    }
    @RequestMapping("/getAllOrders")
    public List<Order> getAllOrders(@RequestBody Map<String, String> params){
        List<Order> result=orderService.GetAllOrders();
        return result;
    }
    @RequestMapping("/getDateOrders")
    List<Order> getDateOrders(@RequestBody JSONObject param){
        JSONArray date=param.getJSONArray("date");
        String begin=(String)date.get(0);
        String end=(String)date.get(1);
        return orderService.getOrderidsBytime(begin,end);
    }
    @RequestMapping("/getOrderItems")
    List<Order_product_wrap> getOrderItems(@RequestBody JSONObject param){
        Integer order_id=param.getInteger("order_id");
        return orderService.getProductsByOrderid(order_id);
    }
    @RequestMapping("/getProductOrders")
    List<Order> getProductOrders(@RequestBody JSONObject param){
        String productName= param.getString("productName");
        return orderService.getOrdersByProductName(productName);
    }
}
