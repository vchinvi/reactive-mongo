package com.example.spring.mongo.reactivemongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@ToString
@Document
public class BookCatalogueDetail {
	private String bookId;
	private String catalogueId;
}
