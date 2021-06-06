package zn.zyh.back_code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/getUsers")
    public List<UserAuth> getUsers(){
        return userService.getUsers();
    }
}
