package com.mason.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.reactivestreams.client.FindPublisher;
import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现响应式编程案例
 * Publisher：数据的发布者。Publisher接口只有一个subscribe方法， 用于添加数据的订阅者（Subscriber）
 * Subscriber：数据的订阅者，用来实现对不同事件的处理逻辑。
 * Subscription：表示订阅关系。可以对Subscription对象使用request
 * 方法请求事件流，或者使用cancel方法取消订阅。在调用cancel 方法之后，发布者仍然有可能继续发布通知，但订阅最终会被 取消
 *
 */
public class ReactiveDemo {
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
        MongoDatabase dataBase = client.getDatabase("test");
        MongoCollection<Document> collection = dataBase.getCollection("test");

        //异步返回发布者:这里转换出现类型转换异常
        FindPublisher<Document> publisher = (FindPublisher<Document>) collection.find();

        //订阅实现
        publisher.subscribe(new Subscriber<Document>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                //，在onSubscribe（Subscription s）方法中使用 Subscription的request（n）方法来请求发布者发布n条数据
                System.out.println("start...");
                //执行请求
                subscription.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Document document) {
                // 数据通知：对应onNext方法，表示发布者产生的数据
                //获得文档
                System.out.println("document:" + document.toJson());
            }

            @Override
            public void onError(Throwable throwable) {
                //错误通知：对应onError方法，表示发布者产生了错误
                System.out.println("error occur...");
            }

            @Override
            public void onComplete() {
                //结束通知：对应onComplete方法，表示发布者已经完成了所有数 据的发布。
                System.out.println("finished.");
            }
        });


        //执行请求





    }


}
