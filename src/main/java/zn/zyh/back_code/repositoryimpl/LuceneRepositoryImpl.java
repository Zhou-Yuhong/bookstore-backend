package zn.zyh.back_code.repositoryimpl;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.function.valuesource.IntFieldSource;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zn.zyh.back_code.entity.Book;
import zn.zyh.back_code.repository.LuceneRepository;
import zn.zyh.back_code.search.BookSearch;

import javax.websocket.OnError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class LuceneRepositoryImpl implements LuceneRepository {
    @Autowired
    private IndexWriter indexWriter;

    @Autowired
    private Analyzer analyzer;

    @Autowired
    private SearcherManager searcherManager;
    @Override
    public void createBookIndex(List<BookSearch> bookSearches){
        List<Document> documents = new ArrayList<>();
        for(BookSearch b:bookSearches){
            Document doc = new Document();
            Integer id=b.getId();
            doc.add(new StringField("id",id.toString(), Field.Store.YES));
            doc.add(new TextField("description",b.getDescription(),Field.Store.NO));
            documents.add(doc);
        }
        try {
            indexWriter.addDocuments(documents);
            indexWriter.commit();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public  List<BookSearch> getBookByWord(String word){
        try {
            searcherManager.maybeRefresh();
            IndexSearcher indexSearcher = searcherManager.acquire();
            QueryParser queryParser = new QueryParser("description",analyzer);
            Query query =queryParser.parse(word);
            TopDocs topDocs = indexSearcher.search(query,10);
//            BooleanQuery.Builder builder = new BooleanQuery.Builder();
//            builder.add(new QueryParser("description", analyzer).parse(word), BooleanClause.Occur.MUST);
//            TopDocs topDocs = indexSearcher.search(builder.build(), 10);
            ScoreDoc[] hits = topDocs.scoreDocs;
            List<BookSearch> bookSearches=new ArrayList<>();
            IndexReader reader=indexSearcher.getIndexReader();
            Document m=reader.document(15);
            for(int i=0;i<hits.length;i++){
                Document doc = indexSearcher.doc(hits[i].doc);
                String id=doc.get("id");
                BookSearch bookSearch=new BookSearch();
                bookSearch.setId(Integer.parseInt(id));
                bookSearches.add(bookSearch);
            }
            return bookSearches;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void deleteBookIndex(Integer bookId){
        try {
            String strId = bookId.toString();
            indexWriter.deleteDocuments(new Term("id", strId));
            indexWriter.commit();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void updateBookIndex(List<Book> books){
        try{
            for(Book it:books){
                Integer id=it.getId();
                Document doc = new Document();
                doc.add(new StringField("id",id.toString(), Field.Store.YES));
                doc.add(new TextField("description",it.getDescription(),Field.Store.NO));
                indexWriter.updateDocument(new Term("id",id.toString()),doc);
            }
            indexWriter.commit();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
