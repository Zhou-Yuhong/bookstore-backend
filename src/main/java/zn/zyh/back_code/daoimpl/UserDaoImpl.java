package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.dao.UserDao;
import zn.zyh.back_code.entity.UserAuth;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public UserAuth checkUser(String name,String pwd){
        UserAuth result;
        String sql="SELECT * FROM user_auth WHERE username=? and password=?";
        result=jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->new UserAuth(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("user_type")),
                new Object[]{name,pwd});

        return result;
    }

}
