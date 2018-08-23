package com.example.spring.mongo.reactivemongo.model;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDetail {
	@Id
	private BookDetailKey bookdetailKey;
	private String idPid;
	private boolean enabled;
	private String mobileIndicator;
	private String primaryAssociation;
	private String productId;
	private String registrationUri;
	private String type;
}
