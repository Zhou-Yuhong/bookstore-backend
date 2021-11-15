package zn.zyh.back_code.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.service.BookService;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@Component
@WebService(name="SearchService", targetNamespace = "http://server.webservice.search",
            endpointInterface = "zn.zyh.back_code.webservice.WebserviceApi")
public class WebserviceApiImpl implements WebserviceApi{
    @Autowired
    private BookService bookService;
    @Override
    public List<Book> searchBook(@WebParam String data){
        return bookService.searchBook(data);
    }
}
