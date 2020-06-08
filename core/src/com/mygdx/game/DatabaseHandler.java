package com.mygdx.game;

import com.mongodb.*;
import com.mongodb.util.JSON;

/*
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;
import org.bson.Document;

import java.util.Arrays;
 */
public class DatabaseHandler {
/*
    private static JSON json;

    public static void main(String[] args) throws Exception {

        try {
            MongoClientURI uri = new MongoClientURI("mongodb://admin:pass@cluster0-shard-00-00-8ppze.gcp.mongodb.net:27017,cluster0-shard-00-01-8ppze.gcp.mongodb.net:27017,cluster0-shard-00-02-8ppze.gcp.mongodb.net:27017/test?replicaSet=Cluster0-shard-0&ssl=true&authSource=admin");
            MongoClient mongoClient = new MongoClient(uri);
            DB database = mongoClient.getDB("users_lama");
            System.out.println("success");
            DBCollection collection = database.getCollection("users");
            DBCursor cursor = collection.find();
            DBObject object = cursor.next();
            System.out.println(object.toString());

            json = new JSON();

            User user = new User("l", "p");
            System.out.println(user.toString());

            int i=1;
            while(cursor.hasNext()){
                System.out.println(cursor.next());

                i++;
            }

        } catch (Exception e){
            System.out.println(e);
        }



    }

 */


}
