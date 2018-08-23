package com.example.spring.mongo.reactivemongo.db;

import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.spring.mongo.reactivemongo.model.CourseBookCatalogue;

import reactor.core.publisher.Mono;
@ConditionalOnProperty(name = "redirect.to.nextext", havingValue = "false")
@Repository
public class CourseBookCatelogueDaoMongoImpl extends AbstractReactiveMongoDataService implements ICourseBookCatalogueDao {

	@Override
	public Mono<CourseBookCatalogue> saveCourseBookCatalogue(CourseBookCatalogue courseBookCatalogue) {
		System.out.println("inside CourseBookDao");
		//Assert.notNull(courseBookCatalogue, "Invalid argument courseBookCatalogue");
		//If the catalogueId is not set the code creates a new random ID and sets the ID
		if(courseBookCatalogue.getCourseBookCatalogueId() == null) {
			courseBookCatalogue.setCourseBookCatalogueId(UUID.randomUUID().toString());
		}
		// Saving book first time then status will be unpublished.This will be changed to in progress/published if any entry added to book book_details
		if(courseBookCatalogue.getStatus() == null)  {
			courseBookCatalogue.setStatus("UNPUBLISHED");
		}
			
		Mono<CourseBookCatalogue> updatedObj =  this.save(courseBookCatalogue,"course_book_catalogue");
		System.out.println("return form save Mono<CourseBookCatalogue>");
		return updatedObj;
	}

	@Override
	public void deleteAll() {
		dropCollection("course_book_catalogue");
		System.out.println("finished droping collection in dao");
		
	}
	

}
