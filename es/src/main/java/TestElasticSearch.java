import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestElasticSearch {



    public static void main(String[] args) throws IOException {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").put("client.transport.sniff", false).build();

        Client client = new PreBuiltTransportClient(settings).
                addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));


        //添加
//        List<String> data = DataFactory.getInitJsonData();
//        for (int i = 0; i < data.size(); i++) {
//            IndexResponse indexResponse = client.prepareIndex("blog", "article").setSource(data.get(i)).execute().actionGet();
//            System.out.println("id:"+indexResponse.getId());
//        }


        // 修改
//        XContentBuilder builder = XContentFactory.jsonBuilder()
//                .startObject()
//                .field("title","java")
//                .field("content","java")
//                .endObject();
//
//        UpdateRequest updateRequest = new UpdateRequest("blog","article","AV-5Rrm2gkRgHOGspujW")
//                .doc(builder);
//        client.update(updateRequest).actionGet();


//        Map<String, String> soruces = new HashMap<String, String>();
//        soruces.put("title","JAVA");
//        UpdateResponse updateResponse = client.prepareUpdate("blog", "article", "AV-5Rr79gkRgHOGspujX")
//                .setDoc(soruces)
//                .execute().actionGet();




        //查询，ch查询全部内容不需要设置Query即可
//        TermsQueryBuilder query = QueryBuilders.termsQuery("title", "shell", "hibernate");// 列名 title 中包含值shell、hibernate
//        MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery("基本", "title", "content");// 列名title或列名content中包含值svn
//
//        SearchResponse response = client.prepareSearch("blog").setTypes("article").setQuery(query).execute().actionGet();
//        SearchHits responseHits = response.getHits();
//        if(responseHits.totalHits > 0){
//            SearchHit[] hits = responseHits.getHits();
//            for (SearchHit hit : hits) {
//                System.out.println(hit.getId() + "\t" + hit.getScore() + "\t" + hit.getSource());
//            }
//        }

        //删除
        DeleteRequest deleteRequest = new DeleteRequest("blog","article","AV-5Rrm2gkRgHOGspujW");
        DeleteResponse deleteResponse = client.delete(deleteRequest).actionGet();



        client.close();
    }

}
