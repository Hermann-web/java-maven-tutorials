package config;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

public class MongoConnectionManager {

    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27017;
    
    
    private static final String atlasURI = "mongodb+srv://farel-user:HIagJrhrLAzvqpN6@cluster0.uohcgoh.mongodb.net/?retryWrites=true&w=majority";
    public static MongoClient initConnection() {
        boolean useAtlas = true; // set this to true or false depending on whether you want to use MongoDB Atlas or a local MongoDB instance
        try {
            if (useAtlas) {
                MongoClient client = MongoClients.create(atlasURI);
                return client;
            }
            else {

                List<ServerAddress> mongoHostList = new ArrayList<>();
                mongoHostList.add(new ServerAddress(MONGO_HOST, MONGO_PORT));
                MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                        .applyToClusterSettings(clusterSettingsBuilder -> clusterSettingsBuilder.hosts(mongoHostList))
                        .writeConcern(WriteConcern.W1)
                        .readConcern(ReadConcern.MAJORITY)
                        .readPreference(ReadPreference.nearest())
                        .retryWrites(true)
                        .build();

                MongoClient client = MongoClients.create(mongoClientSettings);
                return client;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
