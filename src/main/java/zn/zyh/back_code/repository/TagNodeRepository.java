package zn.zyh.back_code.repository;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import zn.zyh.back_code.entity.BookNode;
import zn.zyh.back_code.entity.TagNode;
import java.util.List;
public interface TagNodeRepository extends Neo4jRepository<TagNode,Long> {
    TagNode findByTagName(String name);

    @Query("match (t:TagNode)-[r:R_TAG_BOOK]->(b:BookNode)<-[:R_TAG_BOOK]-(end:TagNode) where t.tagName=$Name return end")
    List<TagNode> getTagNodes(String Name);

}
