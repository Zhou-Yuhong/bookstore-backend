package zn.zyh.back_code.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="userAuth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id",unique = true,nullable = false)

    private Integer userId;
    private String username;
    private String password;
    private Integer userType;
    private Integer userstate;
    public UserAuth(String username,String password){
        this.username=username;
        this.password=password;
        this.userType=0;
        this.userstate=0;
    }
    public UserAuth(){

    }
}
