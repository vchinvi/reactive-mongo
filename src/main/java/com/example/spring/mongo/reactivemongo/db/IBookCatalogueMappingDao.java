package com.example.spring.mongo.reactivemongo.db;

import com.example.spring.mongo.reactivemongo.model.BookCatalogueDetail;

import reactor.core.publisher.Mono;

public interface IBookCatalogueMappingDao {
	Mono<BookCatalogueDetail> addBookCatalogueMapping(BookCatalogueDetail bookCatalogueDetail);
}
