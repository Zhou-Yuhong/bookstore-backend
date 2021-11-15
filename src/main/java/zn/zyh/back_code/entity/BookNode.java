package zn.zyh.back_code.entity;

import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;
@Node()
public class BookNode {
    @Id
    @GeneratedValue
    private Long id;

    private String BookName;

    private Integer bookId;
    private BookNode(){}

    public BookNode(String BookName,Integer bookId){
        this.BookName=BookName;
        this.bookId=bookId;
    }



}
