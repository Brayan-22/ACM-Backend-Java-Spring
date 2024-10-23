package com.acm.mongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.concurrent.TimeUnit;

/*
* Descomentar si se requiere configurar la conexion de manera más detallada
*/

//@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    @Override
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings
                .builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .applyToConnectionPoolSettings(b -> b.maxWaitTime(30, TimeUnit.SECONDS).minSize(5).maxSize(10))
                .build();
        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return "acm";
    }
}
