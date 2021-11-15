//package zn.zyh.back_code.init;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import zn.zyh.back_code.entity.Book;
//import zn.zyh.back_code.entity.BookNode;
//import zn.zyh.back_code.entity.TagNode;
//import zn.zyh.back_code.repository.BookNodeRepository;
//import zn.zyh.back_code.repository.TagNodeRepository;
//import zn.zyh.back_code.service.BookService;
//
//import java.util.ArrayList;
//import java.util.List;
//@Component
//@Order(value = 2)
//public class Neo4jInit implements ApplicationRunner {
//    @Autowired
//    BookService bookService;
//
//    @Autowired
//    BookNodeRepository bookNodeRepository;
//
//    @Autowired
//    TagNodeRepository tagNodeRepository;
//
//    @Override
//    public void run(ApplicationArguments arg0) {
//        List<Book> books=bookService.getBooks();
//        //first add bookNode
//        List<BookNode> bookNodeList=new ArrayList<>();
//        for(Book it:books){
//            bookNodeList.add(new BookNode(it.getName(),it.getId()));
//        }
//        bookNodeRepository.saveAll(bookNodeList);
//        for(Book it:books){
//            TagNode tagNode=tagNodeRepository.findByTagName(it.getType());
//            if(tagNode==null){
//                tagNode=new TagNode(it.getType());
//                List<BookNode> bookNodes=new ArrayList<>();
//                bookNodes.add(new BookNode(it.getName(), it.getId()));
//                tagNode.setBookNodeList(bookNodes);
//            }
//            else{
//                tagNode.getBookNodeList().add(new BookNode(it.getName(), it.getId()));
//            }
//            tagNodeRepository.save(tagNode);
//        }
//    }
//}
