package com.mason.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.jndi.MongoClientFactory;
import org.bson.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Mason
 * @Description TODO
 * @date 2022/6/7 22:42
 */
public class Config {
    public static void main(String[] args) {
        //权限认证
        ServerAddress serverAddress = new ServerAddress("101.35.200.6", 27017);
        List<MongoCredential> credentialsList = new ArrayList<>();
        String username = "admin";
        String password = "123456";
        String database = "admin";
        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
        credentialsList.add(credential);
        //连接池参数配置
        MongoClientOptions.builder()
                //设置链接超长时间为10s
                .connectTimeout(1000 * 100)
                //设置最长等待时间为10s
                .maxWaitTime(1000 * 100)
                .build();
        MongoClient client = new MongoClient(serverAddress, credentialsList);
        MongoDatabase test = client.getDatabase("test");
        //遍历所有集合
        test.listCollectionNames().forEach((Consumer<String>) c ->{
            System.out.println("collection: " + c);
            MongoCollection<Document> collection = test.getCollection(c);
            //遍历所有的索引
            collection.listIndexes().forEach((Consumer<Document>) i->{
                System.out.println("\tIndex: " + i.toJson());
            } );
        });

        // 2.插入文档
        Date currentDate = new Date();
         Document doc = new Document("title", "下半生再见")
                 .append("author",new Document("name","莉莉丝")
                 .append("gender","female"))
                 .append("summary","待续。。。")
                 .append("type","诗歌")
                 .append("tags", Arrays.asList("写实","励志"))
                 .append("views",0)
                 .append("updateAt",currentDate)
                 .append("createAt",currentDate);
        test.getCollection("test").insertOne(doc);

        //批量插入文档
        List<Document> docs = new ArrayList<>();
        //分别将每个doc放到动态数组中
        //然后使用insertmany(docs)方法进行批量导入文档

        //2.查询文档
        test.getCollection("test").find().forEach((Consumer<Document>) doc1->{
            System.out.println("document: " + doc1.toJson());
        } );


    }


}
