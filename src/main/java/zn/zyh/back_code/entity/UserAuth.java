package zn.zyh.back_code.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="userAuth")
public class UserAuth {
    @Id
    @Column(name="user_id")
    private Integer userId;
    private String username;
    private String password;
    private Integer userType;
    public UserAuth(String username,String password){
        this.username=username;
        this.password=password;
        this.userType=0;
    }
    public UserAuth(){

    }
}
