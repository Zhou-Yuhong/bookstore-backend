package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.dao.UserDao;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.repository.UserAuthRepository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


@Autowired
UserAuthRepository userAuthRepository;

@Override
    public UserAuth checkUser(String username,String password){
    return userAuthRepository.checkUser(username,password);
}
@Override
    public UserAuth register(String username,String password){
       UserAuth userAuth=new UserAuth(username,password);
       return userAuthRepository.saveAndFlush(userAuth);
}
@Override
    public List<UserAuth> getUsers(){
       return userAuthRepository.getUsers();
}
}
