package zn.zyh.back_code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BackCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackCodeApplication.class, args);
    }

}
