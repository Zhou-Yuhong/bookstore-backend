package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.dao.Order_infoDao;
import zn.zyh.back_code.entity.Order_info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Repository
public class Order_infoDaoImpl implements Order_infoDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Order_info> findByUsername(String username){
        List<Order_info> orders=new ArrayList<>();
        String sql="SELECT * FROM order_info WHERE username=?";
        jdbcTemplate.query(sql,
                (rs,rowNum)-> new Order_info(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("order_time"),
                        rs.getInt("num"),
                        rs.getInt("value"),
                        rs.getInt("state")
                ),new Object[]{username}).forEach(order->orders.add(order));
        return orders;
    }
    //添加一个订单，返回自动生成的订单id
    @Override
    public int addOrder_info(Order_info order_info){
        String sql = "INSERT INTO `order_info` (`username`, `order_time`, `num`,`value`,`state`) VALUES (?, ?, ?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                // 指定主键
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, order_info.getUsername());
                preparedStatement.setString(2, order_info.getOrder_time());
                preparedStatement.setInt(3, order_info.getNum());
                preparedStatement.setInt(4,order_info.getValue());
                preparedStatement.setInt(5,order_info.getState());
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

}
