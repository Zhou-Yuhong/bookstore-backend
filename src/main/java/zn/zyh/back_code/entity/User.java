package zn.zyh.back_code.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id",unique = true,nullable = false)
    private Integer userId;
    private String tel;
    private String email;
    private String gender;
    public User(Integer userId,String tel,String email,String gender){
        this.userId=userId;
        this.tel=tel;
        this.email=email;
        this.gender=gender;
    }
    public User(){

    }
}
