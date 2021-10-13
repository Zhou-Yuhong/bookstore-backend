package zn.zyh.back_code.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zn.zyh.back_code.entity.User;
import zn.zyh.back_code.entity.UserAuth;
import zn.zyh.back_code.dao.UserAuthDao;
import zn.zyh.back_code.dao.UserDao;
import zn.zyh.back_code.repository.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {


@Autowired
UserAuthDao userAuthDao;
@Autowired
UserDao userRepository;

@Override
    public UserAuth checkUser(String username,String password){
    return userAuthDao.checkUser(username,password);
}
@Override
    public Boolean register(String username,String password,String tel,String email,String gender){
       UserAuth userAuth=new UserAuth(username,password);
       List<UserAuth> allUsers= userAuthDao.getUsers();
       for(int i=0;i<allUsers.size();i++){
           if(allUsers.get(i).getUsername().equals(username)) return false;
       }
       UserAuth newUserAuth= userAuthDao.save(userAuth);
       int userid=newUserAuth.getUserId();
       User newUser=new User(userid,tel,email,gender);
       userRepository.saveAndFlush(newUser);
       return true;
}
@Override
  public Boolean checkUsername(String username){
    List<UserAuth> allUsers= userAuthDao.getUsers();
    for(int i=0;i<allUsers.size();i++){
        //注意String的比较要用equals
        if(allUsers.get(i).getUsername().equals(username)) return false;

    }
    return true;
}
@Override
    public List<UserAuth> getUsers(){
       return userAuthDao.getUsers();
}
@Override
    public void disableUsers(Integer keyset[]){
     for(int i=0;i< keyset.length;i++){
         UserAuth user= userAuthDao.getOne(keyset[i]);
         user.setUserstate(1);
         userAuthDao.saveAndFlush(user);
     }
}
@Override
    public void enableUsers(Integer keyset[]){
    for(int i=0;i<keyset.length;i++){
        UserAuth user= userAuthDao.getOne(keyset[i]);
        user.setUserstate(0);
        userAuthDao.saveAndFlush(user);
    }
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
     public UserAuth getUser(int userid){
    return userAuthDao.getOne(userid);
    }
}
