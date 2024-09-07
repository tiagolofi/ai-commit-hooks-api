package com.github.tiagolofi.mongo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "eventosLogs")
public class Logs extends PanacheMongoEntity {
    
    public String email;
    public String typeEvent;
    public String event;
    public long timestamp;

}
