package zn.zyh.back_code.dao;

import zn.zyh.back_code.entity.UserAuth;

public interface UserDao {
    UserAuth checkUser(String username, String password);
    UserAuth register(String username,String password);
}
