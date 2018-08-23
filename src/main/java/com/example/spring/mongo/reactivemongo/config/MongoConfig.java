package com.example.spring.mongo.reactivemongo.config;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.WriteResultChecking;

import com.mongodb.MongoClientURI;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@ConditionalOnProperty(name = "redirect.to.nextext", havingValue = "false")
@Configuration
public class MongoConfig  {
	String mongoUri = "mongodb://127.0.0.1:27017";

	@Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() {
		System.out.println("-------------------1");
            return new SimpleReactiveMongoDatabaseFactory(MongoClients.create(mongoUri), getDatabaseName());
    }
	
	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		System.out.println("-------------------2");
		ReactiveMongoTemplate reactiveMongoTemplate = new ReactiveMongoTemplate(reactiveMongoDatabaseFactory());
		reactiveMongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
		return reactiveMongoTemplate;
	}
	
	@Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(mongoDBURI());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException{
         MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
         mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
         return mongoTemplate;
    }
    
    private MongoClientURI mongoDBURI(){
        MongoClientURI mongoClientURI = new MongoClientURI(mongoUri+"/"+getDatabaseName());
        return mongoClientURI;
    }


	public MongoClient reactiveMongoClient() {
		return MongoClients.create(mongoUri);
	}

	protected String getDatabaseName() {
		return "nextext";
	}
}
