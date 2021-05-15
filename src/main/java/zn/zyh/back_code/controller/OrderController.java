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
import zn.zyh.back_code.service.OrderService;
import zn.zyh.back_code.utils.objectutils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

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
        //Order_info order_info=new Order_info(0,params.get("username"),params.get("order_time"),params.get("num"),params.get("value"),params.get("state"));
        //JSONObject a=JSONObject.
//        Object order_info=params.get("order_info");
//        Object order_products=params.get("order_products");
//        List m=objectutils.getFiledsInfo(order_info);
//        JSONObject a=JSONObject.fromObject(order_info);
//        JSONObject b=JSONObject.fromObject(order_products);
//        String a=params.get("order_info");;
//        String b=params.get("order_products");
        JSONObject order_info=param.getJSONObject("order_info");
        String username=order_info.getString("username");
        String order_time=order_info.getString("order_time");
        int num=order_info.getInt("num");
        int value= order_info.getInt("value");
        int state=order_info.getInt("state");
        Order_info orderInfo=new Order_info(0,username,order_time,num,value,state);
        List<Order_product> order_products=new ArrayList<>();
        JSONArray products=param.getJSONArray("order_products");
        for(int i=0;i<products.size();i++){
            JSONObject tmp=(JSONObject) products.get(i);
            order_products.add(new Order_product(tmp.getInt("product_id"),0,tmp.getString("name"),tmp.getInt("num"),tmp.getInt("price"),tmp.getString("image"),tmp.getString("author")));
        }
        Order_wrap order_wrap=new Order_wrap(orderInfo,order_products);
        this.orderService.addOrder_wrap(order_wrap);
        return;
    }
}
