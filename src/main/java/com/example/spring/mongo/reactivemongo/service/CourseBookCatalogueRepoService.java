package com.example.spring.mongo.reactivemongo.service;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.mongo.reactivemongo.model.BookCatalogueDetail;
import com.example.spring.mongo.reactivemongo.model.BookDetail;
import com.example.spring.mongo.reactivemongo.model.BookDetailDto;
import com.example.spring.mongo.reactivemongo.model.BookDetailKey;
import com.example.spring.mongo.reactivemongo.model.CourseBookCatalogue;
import com.example.spring.mongo.reactivemongo.repo.IBookCatalogueDetailDataService;
import com.example.spring.mongo.reactivemongo.repo.IBookDetailRepository;
import com.example.spring.mongo.reactivemongo.repo.ICourseBookCatalogueDataService;
import com.example.spring.mongo.reactivemongo.repo.ICourseBookNonReactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseBookCatalogueRepoService {
	
	@Autowired(required = false)
	ICourseBookCatalogueDataService courseBookCatalogueDataService;
	@Autowired(required = false)
	IBookCatalogueDetailDataService bookCatalogueMappingDataService;
	@Autowired(required = false)
	IBookDetailRepository bookDetailRepo;
	
	@Autowired(required = false)
	ICourseBookNonReactive nonreactive;

	public Mono<CourseBookCatalogue> upsertCourseBookCatelogue(CourseBookCatalogue courseBookCatalogue) {
		long startTime = System.currentTimeMillis();
		notNull(courseBookCatalogue, "Invalid argument courseBookCatalogue");
		// Saving book first time then status will be unpublished.This will be changed
		// to in progress/published if any entry added to book book_details
		if (courseBookCatalogue.getStatus() == null) {
			courseBookCatalogue.setStatus("UNPUBLISHED");
		}
		courseBookCatalogue.setCourseBookCatalogueId(UUID.randomUUID().toString());
		
		CourseBookCatalogue updatedCatalogue = courseBookCatalogueDataService.save(courseBookCatalogue).block();
		BookCatalogueDetail bookDeatil = new BookCatalogueDetail(updatedCatalogue.getBook_id(), updatedCatalogue.getCourseBookCatalogueId());
		bookCatalogueMappingDataService.save(bookDeatil).subscribe();
		System.out.println("time taken by Blocking approach:  "+ (System.currentTimeMillis() - startTime));
		return Mono.just(updatedCatalogue);
	}
	
	public Mono<CourseBookCatalogue> upsertCourseBookCatelogueNonBlocking(CourseBookCatalogue courseBookCatalogue) {
		long startTime = System.currentTimeMillis();
		notNull(courseBookCatalogue, "Invalid argument courseBookCatalogue");
		// Saving book first time then status will be unpublished.This will be changed
		// to in progress/published if any entry added to book book_details
		if (courseBookCatalogue.getStatus() == null) {
			courseBookCatalogue.setStatus("UNPUBLISHED");
		}
		courseBookCatalogue.setCourseBookCatalogueId(UUID.randomUUID().toString());
		
		CourseBookCatalogue updatedCatalogue = courseBookCatalogueDataService.save(courseBookCatalogue).block();
		nonreactive.save(courseBookCatalogue);
		BookCatalogueDetail bookDeatil = new BookCatalogueDetail(updatedCatalogue.getBook_id(), updatedCatalogue.getCourseBookCatalogueId());
		bookCatalogueMappingDataService.save(bookDeatil).subscribe();
		System.out.println("time taken by non reactive approach:  "+ (System.currentTimeMillis() - startTime));
		return Mono.just(updatedCatalogue);
	}
	
	
	public Flux<BookDetailDto> saveBookDetail(List<BookDetailDto> bookDetails) {
		List<BookDetail> details = new ArrayList<>();
		Flux<BookDetail> flux = bookDetailRepo.findByBookId("U5UQO3UFO");
		Map<BookDetailKey, BookDetail> existingBooks = flux.collectMap(book -> book.getBookdetailKey(), book -> book).block();
		
	    bookDetails.forEach(book -> {
    		BookDetail detail = new BookDetail();
			BeanUtils.copyProperties(book, detail);
			BookDetailKey key = new BookDetailKey(book.getBookId(), book.getModuleId());
			detail.setBookdetailKey(key);
			details.add(detail);
			existingBooks.remove(key);
	    });
	    bookDetailRepo.saveAll(details).subscribe();

	    bookDetailRepo.deleteAll(existingBooks.values()).subscribe();
		return Flux.fromIterable(bookDetails);
	}
	
	private Mono<BookCatalogueDetail> convertToBookCatalogue(CourseBookCatalogue catalogue) {
		BookCatalogueDetail bookcat = new BookCatalogueDetail(catalogue.getBook_id(), catalogue.getCourseBookCatalogueId());
		return Mono.just(bookcat);
	}

	
	public Mono<CourseBookCatalogue> getAllCourseBook(boolean blocking) {
		CourseBookCatalogue catalogues = null;
		long startTime = System.currentTimeMillis();
		if(blocking) {
			catalogues = courseBookCatalogueDataService.findAll().collectList().block().stream().findFirst().get();
			System.out.println("time taken by Blocking approach:  "+ (System.currentTimeMillis() - startTime));
		} else {
			catalogues =  StreamSupport.stream(nonreactive.findAll().spliterator(), false).findFirst().get();
			System.out.println("time taken by NON Blocking approach:  "+ (System.currentTimeMillis() - startTime));
		}
		return Mono.just(catalogues);
	}

}
