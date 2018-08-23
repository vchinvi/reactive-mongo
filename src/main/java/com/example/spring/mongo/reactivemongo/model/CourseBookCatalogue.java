package com.example.spring.mongo.reactivemongo.model;

import java.util.Map;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseBookCatalogue extends CourseBookBase {

	private static final long serialVersionUID = 5009046082783643284L;

	@Id
	private String courseBookCatalogueId;
	private Map<String, Object> bookProfileSettings;

	/**
	 * Excluding from the response need to ignore
	 */
	@JsonIgnore
	public Map<String, Object> getBookProfileSettings() {
		return bookProfileSettings;
	}

	/**
	 * Needed at de-serialization to read
	 */
	@JsonProperty
	public void setBookProfileSettings(Map<String, Object> bookProfileSettings) {
		this.bookProfileSettings = bookProfileSettings;
	}

	public String getCourseBookCatalogueId() {
		return courseBookCatalogueId;
	}

	public void setCourseBookCatalogueId(String courseBookCatalogueId) {
		this.courseBookCatalogueId = courseBookCatalogueId;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof CourseBookCatalogue) {
			CourseBookCatalogue catalog = (CourseBookCatalogue) o;
			return this.getApi_book_id().equals(catalog.getApi_book_id());
		}
		return false;
	}
}
