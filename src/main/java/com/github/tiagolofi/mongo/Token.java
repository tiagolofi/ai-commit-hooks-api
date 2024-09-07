package com.github.tiagolofi.mongo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "tokens")
public class Token extends PanacheMongoEntity {
    
    public String email;
    public String token;
    public long expiresIn;
    public boolean blocked;
    public boolean pending;

}
