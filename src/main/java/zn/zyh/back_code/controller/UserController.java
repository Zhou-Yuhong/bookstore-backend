package zn.zyh.back_code.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping("/getUsers")
    public List<UserAuth> getUsers(@RequestBody Map<String, String> params){
        UserService userService=applicationContext.getBean(UserService.class);
        System.out.print("look here");
        System.out.print(userService);
        List<UserAuth> result=userService.getUsers();
        return result;
    }
    @RequestMapping("/disableUsers")
    public boolean disableUsers(@RequestBody JSONObject param){
        UserService userService=applicationContext.getBean(UserService.class);
        JSONArray array=param.getJSONArray("keySet");
        Integer keyset[]=new Integer[array.size()];
        for(int i=0;i<array.size();i++){
           keyset[i]=(Integer)array.get(i);
        }
        userService.disableUsers(keyset);
        return true;
    }
    @RequestMapping("/enableUsers")
    public boolean enableUsers(@RequestBody JSONObject param){
        UserService userService=applicationContext.getBean(UserService.class);
        JSONArray array=param.getJSONArray("keySet");
        Integer keyset[]=new Integer[array.size()];
        for(int i=0;i<array.size();i++){
            keyset[i]=(Integer)array.get(i);
        }
        userService.enableUsers(keyset);
        return true;
    }

}
