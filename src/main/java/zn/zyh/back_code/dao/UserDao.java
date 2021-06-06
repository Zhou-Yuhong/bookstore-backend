package zn.zyh.back_code.dao;

import zn.zyh.back_code.entity.UserAuth;

import java.util.List;

public interface UserDao {
    UserAuth checkUser(String username, String password);
    UserAuth register(String username,String password);

    List<UserAuth> getUsers();

    void disableUsers(Integer keyset[]);

    void enableUsers(Integer keyset[]);
}
