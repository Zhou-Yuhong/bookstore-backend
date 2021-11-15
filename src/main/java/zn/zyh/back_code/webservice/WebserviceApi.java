
package zn.zyh.back_code.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.service.BookService;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService( name="SearchService", targetNamespace = "http://server.webservice.search")
public interface WebserviceApi {
    @WebMethod
    List<Book> searchBook(@WebParam String data);
}
