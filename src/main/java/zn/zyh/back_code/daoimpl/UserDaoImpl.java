package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.dao.UserDao;
import zn.zyh.back_code.entity.User;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.repository.UserAuthRepository;
import zn.zyh.back_code.repository.UserRepository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


@Autowired
UserAuthRepository userAuthRepository;
@Autowired
UserRepository userRepository;

@Override
    public UserAuth checkUser(String username,String password){
    return userAuthRepository.checkUser(username,password);
}
@Override
    public Boolean register(String username,String password,String tel,String email,String gender){
       UserAuth userAuth=new UserAuth(username,password);
       List<UserAuth> allUsers=userAuthRepository.getUsers();
       for(int i=0;i<allUsers.size();i++){
           if(allUsers.get(i).getUsername().equals(username)) return false;
       }
       UserAuth newUserAuth=userAuthRepository.save(userAuth);
       int userid=newUserAuth.getUserId();
       User newUser=new User(userid,tel,email,gender);
       userRepository.saveAndFlush(newUser);
       return true;
}
@Override
  public Boolean checkUsername(String username){
    List<UserAuth> allUsers=userAuthRepository.getUsers();
    for(int i=0;i<allUsers.size();i++){
        //注意String的比较要用equals
        if(allUsers.get(i).getUsername().equals(username)) return false;

    }
    return true;
}
@Override
    public List<UserAuth> getUsers(){
       return userAuthRepository.getUsers();
}
@Override
    public void disableUsers(Integer keyset[]){
     for(int i=0;i< keyset.length;i++){
         UserAuth user=userAuthRepository.getOne(keyset[i]);
         user.setUserstate(1);
         userAuthRepository.saveAndFlush(user);
     }
}
@Override
    public void enableUsers(Integer keyset[]){
    for(int i=0;i<keyset.length;i++){
        UserAuth user=userAuthRepository.getOne(keyset[i]);
        user.setUserstate(0);
        userAuthRepository.saveAndFlush(user);
    }
    }
    @Override
     public UserAuth getUser(int userid){
    return userAuthRepository.getOne(userid);
    }
}
