package zn.zyh.back_code.controller;


import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import zn.zyh.back_code.constant.Constant;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.service.UserService;
import zn.zyh.back_code.utils.msgutils.Msg;
import zn.zyh.back_code.utils.msgutils.MsgCode;
import zn.zyh.back_code.utils.msgutils.MsgUtil;
import zn.zyh.back_code.utils.sessionutils.SessionUtil;

import java.util.Map;

@RestController
@CrossOrigin
public class LoginController {
//    @Autowired
//    private UserService userService;
    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public Msg login(@RequestBody Map<String,String> params){

        String username=params.get(Constant.USERNAME);
        String password=params.get(Constant.PASSWORD);
        UserService userService=applicationContext.getBean(UserService.class);
        UserAuth auth=userService.checkUser(username,password);
        if(auth!=null&&auth.getUserstate()==0){
            JSONObject obj=new JSONObject();
            obj.put (Constant.USER_ID,auth.getUserId());
            obj.put(Constant.USERNAME,auth.getUsername());
            obj.put(Constant.USER_TYPE,auth.getUserType());
            SessionUtil.setSession(obj);
            //session
            JSONObject auth2 = SessionUtil.getAuth();
            System.out.print("session2"+auth2);
            //session
            JSONObject data = JSONObject.fromObject(auth);
            data.remove(Constant.PASSWORD);

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);

        }
        else if(auth==null){
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
        else{
            return MsgUtil.makeMsg(MsgUtil.DISABLE_USER_ERROR,MsgUtil.DISABLE_USER);
        }
    }
    @RequestMapping("/logout")
    public Msg logout(){
        Boolean status = SessionUtil.removeSession();

        if(status){
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG);
        }
        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
    }
    @RequestMapping("/checkSession")
    public Msg checkSession(){
        System.out.print("服务器session\n");
        JSONObject auth = SessionUtil.getAuth();
        System.out.print(auth);
        if(auth == null){
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        }
        else{
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, auth);
        }
    }
    @RequestMapping("/register")
    public boolean register(@RequestBody Map<String,String> params){
        UserService userService=applicationContext.getBean(UserService.class);
        String username=params.get(Constant.USERNAME);
        String password=params.get(Constant.PASSWORD);
        String tel=params.get("tel");
        String email=params.get("email");
        String gender=params.get("gender");
        return  userService.register(username,password,tel,email,gender);
    }
    @RequestMapping("/checkUsername")
    public boolean checkUsername(@RequestBody Map<String,String> params){
        UserService userService=applicationContext.getBean(UserService.class);
        String username=params.get(Constant.USERNAME);
        return userService.checkUsername(username);
    }
}
