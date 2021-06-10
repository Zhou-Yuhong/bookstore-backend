package zn.zyh.back_code.service;

import zn.zyh.back_code.entity.UserAuth;

import java.util.List;

public interface UserService {
    UserAuth checkUser(String username, String password);

    Boolean register(String username,String password,String tel,String email,String gender);

    List<UserAuth> getUsers();

    void disableUsers(Integer keyset[]);

    void enableUsers(Integer keyset[]);
}
