package zn.zyh.back_code.service;

import zn.zyh.back_code.entity.UserAuth;

import java.util.List;

public interface UserService {
    UserAuth checkUser(String username, String password);

    UserAuth register(String username,String password);

    List<UserAuth> getUsers();
}
