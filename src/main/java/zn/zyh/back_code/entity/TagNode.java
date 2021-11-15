package zn.zyh.back_code.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;
import javax.validation.constraints.NotNull;
@Data
@Node()
public class TagNode {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String tagName;

    public TagNode(){};
    public TagNode(String tagName){
        this.tagName=tagName;
    }
    @Relationship("R_TAG_BOOK")
    private List<BookNode> bookNodeList;
}
