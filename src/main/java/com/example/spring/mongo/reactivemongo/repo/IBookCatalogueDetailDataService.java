package com.example.spring.mongo.reactivemongo.repo;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.mongo.reactivemongo.model.BookCatalogueDetail;

@ConditionalOnProperty(name = "redirect.to.nextext", havingValue = "false")
@Repository
public interface IBookCatalogueDetailDataService extends ReactiveCrudRepository<BookCatalogueDetail, String>{
	

}