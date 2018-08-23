package com.example.spring.mongo.reactivemongo.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class CourseBookBase implements Serializable {

	private static final long serialVersionUID = -1733522187696012416L;
	private String title;
	private String description;
	private String thumbnail;
	private String cover_image_url;
	private String author;
	private String version;
	private String book_uri;
	private List<String> toc;
	private String toc_uri;
	private String attachment_id;
	private String src;
	private String book_id;
	private String product_id;
	private String index_id;
	private boolean is_active;
	private String filename;
	private String isbn;
	private String last_access_ts;
	// Holds the name of the last internal user to create/modify this record
	private String last_modified_by;
	private String lastModifiedTimestamp;
	private String rights;
	private String subject;
	private String publisher;
	private String date;
	private String language;
	private String expiration_date;
	private boolean is_expired;
	private boolean in_warning_period;
	private long expiring_within;
	private String status;
	private String opf_book_id;
	// This is the book id which is passed in create book api
	private String api_book_id;
	// Represents the book id that will be displayed in Golden Gate and Mission Bay
	// as "Book ID", however there is already a field here with that name used differently
	// so a new name was needed.
	private String public_book_id;
	private String ingested_by;

}
