package com.github.tiagolofi.mongo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "tokens")
public class Token extends PanacheMongoEntity {
    
    public String email;
    public String token;
    public long expiresIn;
    public boolean blocked;
    public boolean pending;
    public int limit;

    public Token() {}

    private Token(Builder builder) {
        this.email = builder.email;
        this.token = builder.token;
        this.expiresIn = builder.expiresIn;
        this.blocked = builder.blocked;
        this.pending = builder.pending;
        this.limit = builder.limit;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Token getTokenByEmail(String email) {
        return find("email", email).firstResult();
    }

    public static Token getTokenByToken(String token) {
        return find("token", token).firstResult();
    }

    public void debitarChamada() {
        this.limit -= 1;
    }

    public void bloquear() {
        this.blocked = true;
    }

    public void desbloquear() {
        this.blocked = false;
    }

    public void aprovar() {
        this.pending = false;
    }

    public static class Builder {

        public String email;
        public String token;
        public long expiresIn;
        public boolean blocked;
        public boolean pending;
        public int limit;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
    
        public Builder setExpiresIn(int days) {
            long dataHora = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toEpochSecond();
            this.expiresIn = dataHora + (86400 * days);
            return this;
        }
    
        public Builder setToken() {
            long now = Instant.now().getEpochSecond();
            String tokenString = this.email + now;
    
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(tokenString.getBytes());
                this.token = Base64.getEncoder().encodeToString(hash);
                return this;
            } catch (NoSuchAlgorithmException e) {
                this.token = null;
                return this;
            }
        }
    
        public Builder setBlocked(boolean blocked) {
            this.blocked = blocked;
            return this;
        }
    
        public Builder setPending(boolean pending) {
            this.pending = pending;
            return this;
        }

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }
    
        public Token build() {
            return new Token(this);
        }

    }

}
