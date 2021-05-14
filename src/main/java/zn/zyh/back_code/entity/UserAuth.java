package zn.zyh.back_code.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="userAuth")
public class UserAuth {
    private Integer userId;
    private String username;
    private String password;
    private Integer userType;
    public UserAuth(Integer id,String name,String pwd,Integer type)
    {
        userId=id;
        username=name;
        password=pwd;
        userType=type;
    }

    public String getUsername()
    {
        return username;
    }
    public Integer getUserType(){return userType;}
    public Integer getUserID(){return userId;}
}
