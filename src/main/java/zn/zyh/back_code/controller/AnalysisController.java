package zn.zyh.back_code.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zn.zyh.back_code.dto.Order_product_wrap;
import zn.zyh.back_code.dto.User_Comsumption;
import zn.zyh.back_code.service.OrderService;

import java.util.List;

@RestController
public class AnalysisController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/getBP")
    List<Order_product_wrap> getBP(@RequestBody JSONObject param){
        int userid=param.getInt("userid");
        JSONArray date=param.getJSONArray("date");
        String begin=(String)date.get(0);
        String end=(String)date.get(1);
        return  orderService.getproductsByUseridandTime(userid,begin,end);
    }
    @RequestMapping("/getBA")
    List<Order_product_wrap> getBA(@RequestBody JSONObject param){
        JSONArray date=param.getJSONArray("date");
        String begin=(String)date.get(0);
        String end=(String)date.get(1);
       return  orderService.getProductsByTime(begin,end);
    }
    @RequestMapping("/getUA")
    List<User_Comsumption> getUA(@RequestBody JSONObject param){
        JSONArray date=param.getJSONArray("date");
        String begin=(String)date.get(0);
        String end=(String)date.get(1);
        return orderService.getUser_ComByTime(begin,end);
    }
}
