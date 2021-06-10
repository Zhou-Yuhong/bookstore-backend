package zn.zyh.back_code.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zn.zyh.back_code.dao.UserDao;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceimpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserAuth checkUser(String username, String password)
    {
        return userDao.checkUser(username, password);
    }
    @Override
    public Boolean register(String username,String password,String tel,String email,String gender){
        return userDao.register(username,password,tel,email,gender);
    }

    @Override
    public List<UserAuth> getUsers(){
        return userDao.getUsers();
    }
    @Override
    public void disableUsers(Integer keyset[]){
         userDao.disableUsers(keyset);
    }
    @Override
    public void enableUsers(Integer keyset[]){
        userDao.enableUsers(keyset);
    }
}
