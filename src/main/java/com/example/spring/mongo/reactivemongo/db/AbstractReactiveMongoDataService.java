package com.example.spring.mongo.reactivemongo.db;

import java.util.Collection;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoCollection;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractReactiveMongoDataService {

	@Autowired (required = false)
	ReactiveMongoTemplate mongoTemplate;

	public <T> Mono<T> save(T objectToSave, String collectionName) {

		return mongoTemplate.save(objectToSave, collectionName);
	}

	public <T> Mono<DeleteResult> remove(Query query, Class<T> entityClass, String collectionName) {
		return mongoTemplate.remove(query, entityClass, collectionName);
	}

	public <T> Mono<T> findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass,
			String collectionName) {
		return mongoTemplate.findAndModify(query, update, options, entityClass, collectionName);
	}

	public <T> Mono<T> findAndModify(Query query, Update update, Class<T> entityClass, String collectionName) {
		return mongoTemplate.findAndModify(query, update, entityClass, collectionName);
	}

	public <T> Mono<T> findOne(Query query, Class<T> entityClass, String collectionName) {
		return mongoTemplate.findOne(query, entityClass, collectionName);
	}

	public <T> Flux<T> findAll(Class<T> entityClass, String collectionName) {
		return mongoTemplate.findAll(entityClass, collectionName);
	}

	public <T> Mono<UpdateResult> updateMulti(Query query, Update update, Class<T> entityClass, String collectionName) {
		return mongoTemplate.updateMulti(query, update, entityClass, collectionName);
	}

	public <T> Mono<UpdateResult> updateFirst(Query query, Update update, Class<T> entityClass, String collectionName) {
		return mongoTemplate.updateFirst(query, update, entityClass, collectionName);
	}

	public void dropCollection(String collectionName) {
		mongoTemplate.dropCollection(collectionName);
		System.out.println("finished droping collection in abstract");
	}

	public <T> Flux<T> find(Query query, Class<T> entityClass, String collectionName) {
		return mongoTemplate.find(query, entityClass, collectionName);
	}

	public <T> Flux<T> findAllRemove(Query query, Class<T> entityClass, String collectionName) {
		return mongoTemplate.findAllAndRemove(query, entityClass, collectionName);
	}

	public Mono<DeleteResult> remove(Query query, String collectionName) {
		return mongoTemplate.remove(query, collectionName);
	}

	public MongoCollection<Document> getCollection(String collectionName) {
		return mongoTemplate.getCollection(collectionName);
	}

	public <T> void insert(Collection<T> batchToSave, String collectionName) {
		mongoTemplate.insert(batchToSave, collectionName);
	}
}
