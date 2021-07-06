package zn.zyh.back_code.dao;

import zn.zyh.back_code.entity.UserAuth;

import java.util.List;

public interface UserDao {
    UserAuth checkUser(String username, String password);
    Boolean register(String username,String password,String tel,String email,String gender);
    Boolean checkUsername(String username);
    List<UserAuth> getUsers();

    void disableUsers(Integer keyset[]);

    void enableUsers(Integer keyset[]);

    UserAuth getUser(int userid);
}
