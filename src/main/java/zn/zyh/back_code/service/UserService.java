package zn.zyh.back_code.service;

import zn.zyh.back_code.entity.UserAuth;

public interface UserService {
    UserAuth checkUser(String username,String password);

    void register(String username,String password);
}
