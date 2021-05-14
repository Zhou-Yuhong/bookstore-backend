package zn.zyh.back_code.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    private int userId;
    private String nickname;
    private String name;
    private String tel;
    private String address;
    public User(int userId,String nickname,String name,String tel,String address){
        this.userId=userId;
        this.nickname=nickname;
        this.name=name;
        this.tel=tel;
        this.address=address;
    }
}
