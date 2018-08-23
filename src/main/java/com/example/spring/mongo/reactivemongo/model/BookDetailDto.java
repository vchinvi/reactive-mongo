package com.example.spring.mongo.reactivemongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDetailDto {
	private String bookId;
	private String moduleId;
	private String idPid;
	private boolean enabled;
	private String mobileIndicator;
	private String primaryAssociation;
	private String productId;
	private String registrationUri;
	private String type;

}
