package zn.zyh.back_code.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.dao.Order_productDao;
import zn.zyh.back_code.entity.Order_product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Order_productDaoImpl implements Order_productDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Order_product> getProducts(int orderid){
        List<Order_product> order_products=new ArrayList<>();
        String sql="SELECT * FROM order_product WHERE order_id=?";
        jdbcTemplate.query(sql,
                (rs,rowNum)->new Order_product(
                 rs.getInt("product_id"),
                        rs.getInt("order_id"),
                        rs.getString("name"),
                        rs.getInt("num"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getString("author")
                ),new Object[]{orderid}
        ).forEach(order_product -> order_products.add(order_product));
        return order_products;
    }
    @Override
    public void addProducts(List<Order_product> order_products,int orderid){
        String sql="INSERT INTO `order_product`(`product_id`,`order_id`,`name`,`num`,`price`,`image`,`author`) VALUES (?,?,?,?,?,?,?)";
        for(int i=0;i<order_products.size();i++){
            Order_product tmp=order_products.get(i);
            jdbcTemplate.update(sql,tmp.getProduct_id(),orderid,tmp.getName(),tmp.getNum(),tmp.getPrice(),tmp.getImage(),tmp.getAuthor());
        }
    }
}
