package com.example.spring.mongo.reactivemongo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.spring.mongo.reactivemongo.model.BookDetailDto;
import com.example.spring.mongo.reactivemongo.model.CourseBookCatalogue;
import com.example.spring.mongo.reactivemongo.service.CourseBookCatalogueRepoService;
import com.example.spring.mongo.reactivemongo.service.CourseBookCatelougeService;

import reactor.core.publisher.Mono;

@Component
public class ControllerHandler {
	@Autowired
	CourseBookCatalogueRepoService repoSservice;
	@Autowired
	CourseBookCatelougeService service;

	public Mono<ServerResponse> saveCourceBookCatelogue(ServerRequest sevletRequest) {

		Mono<CourseBookCatalogue> updatedData = sevletRequest.bodyToMono(CourseBookCatalogue.class)
				.flatMap(body -> service.upsertCourseBookCatelogue(body));

		return updatedData.transform(this::serverResponse);
	}

	public Mono<ServerResponse> saveCourceBookCatelogue2(ServerRequest sevletRequest) {

		Mono<CourseBookCatalogue> updatedData = sevletRequest.bodyToMono(CourseBookCatalogue.class)
				.flatMap(body -> repoSservice.upsertCourseBookCatelogue(body));

		return updatedData.transform(this::serverResponse);
	}
	
	
	public Mono<ServerResponse> saveCourceBookCatelogue3(ServerRequest sevletRequest) {

		Mono<CourseBookCatalogue> updatedData = sevletRequest.bodyToMono(CourseBookCatalogue.class)
				.flatMap(body -> repoSservice.upsertCourseBookCatelogueNonBlocking(body));

		return updatedData.transform(this::serverResponse);
	}

//	public Mono<ServerResponse> saveBookDetail(ServerRequest sevletRequest) {
//		Mono<BookDetailDto> updatedData = sevletRequest.bodyToFlux(BookDetailDto.class).collectList()
//				.flatMap(body -> repoSservice.saveBookDetail(body));
//		return updatedData.transform(this::serverResponse2);
//	}

	Mono<ServerResponse> serverResponse(Mono<CourseBookCatalogue> bookServiceResponseMono) {
		return bookServiceResponseMono.flatMap(bookResponse -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(bookResponse), CourseBookCatalogue.class));
	}
	
	Mono<ServerResponse> serverResponse2(Mono<BookDetailDto> bookServiceResponseMono) {
		return bookServiceResponseMono.flatMap(bookResponse -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(bookResponse), BookDetailDto.class));
	}
	
	public Mono<ServerResponse> getCourceBookCatelogue(ServerRequest sevletRequest) {
		Mono<CourseBookCatalogue> data = repoSservice.getAllCourseBook(true);
		return data.transform(this::serverResponse);
	}
	
	public Mono<ServerResponse> getCourceBookCatelogue2(ServerRequest sevletRequest) {
		Mono<CourseBookCatalogue> data = repoSservice.getAllCourseBook(false);
		return data.transform(this::serverResponse);
	}
	
	
}
