package zn.zyh.back_code.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import zn.zyh.back_code.service.LuceneService;

@Component
@Order(value = 1)
public class BookIndexRunner implements ApplicationRunner {
    @Autowired
    private LuceneService luceneService;

    @Override
    public void run(ApplicationArguments arg0) throws Exception{
        luceneService.synProductCreatIndex();
    }
}
