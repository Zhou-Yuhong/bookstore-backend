package zn.zyh.back_code.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import zn.zyh.back_code.entity.BookNode;
import zn.zyh.back_code.entity.TagNode;

public interface BookNodeRepository extends Neo4jRepository<BookNode,Long> {

}
