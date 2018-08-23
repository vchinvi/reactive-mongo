package com.example.spring.mongo.reactivemongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.mongo.reactivemongo.db.IBookCatalogueMappingDao;
import com.example.spring.mongo.reactivemongo.db.ICourseBookCatalogueDao;
import com.example.spring.mongo.reactivemongo.model.BookCatalogueDetail;
import com.example.spring.mongo.reactivemongo.model.CourseBookCatalogue;

import reactor.core.publisher.Mono;

@Service
public class CourseBookCatelougeService {

	@Autowired(required = false)
	ICourseBookCatalogueDao courseBookCatalogueDao;
	@Autowired(required = false)
	IBookCatalogueMappingDao bookCatalogueMappingDao;

	public Mono<CourseBookCatalogue> upsertCourseBookCatelogue(CourseBookCatalogue courseBookCatalogue) {

		courseBookCatalogueDao.saveCourseBookCatalogue(courseBookCatalogue).subscribe(catalogue -> {
			bookCatalogueMappingDao.addBookCatalogueMapping(convertToBookCatalogue(catalogue)).subscribe();
		});

		return Mono.just(courseBookCatalogue);
	}

	private BookCatalogueDetail convertToBookCatalogue(CourseBookCatalogue catalogue) {
		return new BookCatalogueDetail(catalogue.getBook_id(), catalogue.getCourseBookCatalogueId());
	}

}
