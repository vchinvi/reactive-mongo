package com.example.spring.mongo.reactivemongo.db;

import com.example.spring.mongo.reactivemongo.model.CourseBookCatalogue;

import reactor.core.publisher.Mono;

public interface ICourseBookCatalogueDao {

	Mono<CourseBookCatalogue> saveCourseBookCatalogue(CourseBookCatalogue courseBookCatalogue);
	
	void deleteAll();

}
