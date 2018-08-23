package com.example.spring.mongo.reactivemongo.repo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.mongo.reactivemongo.model.BookDetail;
import com.example.spring.mongo.reactivemongo.model.BookDetailKey;

import reactor.core.publisher.Flux;

@ConditionalOnProperty(name = "redirect.to.nextext", havingValue = "false")
@Repository
public interface IBookDetailRepository extends ReactiveCrudRepository<BookDetail, BookDetailKey>{
	
	@Query("{ '_id.bookId' : ?0 }")
	Flux<BookDetail> findByBookId(String bookId);

}
