package com.example.spring.mongo.reactivemongo.db;

import org.springframework.stereotype.Repository;

import com.example.spring.mongo.reactivemongo.model.BookCatalogueDetail;

import reactor.core.publisher.Mono;

@Repository
public class BookCatalogueDetailMongoImpl extends AbstractReactiveMongoDataService implements IBookCatalogueMappingDao {

	@Override
	public Mono<BookCatalogueDetail> addBookCatalogueMapping(BookCatalogueDetail bookCatalogueDetail) {
		System.out.println("inside Book Catalogue DAO");
		Mono<BookCatalogueDetail> tt = this.save(bookCatalogueDetail, "book_catalog_mapping");
		 tt.subscribe();
		 return tt;
	}

}
