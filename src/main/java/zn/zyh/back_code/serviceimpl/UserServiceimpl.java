package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.dao.UserDao;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.service.UserService;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserAuth checkUser(String username, String password)
    {
        return userDao.checkUser(username, password);
    }
    @Override
    public void register(String username,String password){
        userDao.register(username,password);
    }

}
