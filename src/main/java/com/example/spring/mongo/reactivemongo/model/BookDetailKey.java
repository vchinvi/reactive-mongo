package com.example.spring.mongo.reactivemongo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
public class BookDetailKey implements Serializable{

	private static final long serialVersionUID = -3594143812641395781L;
	private String bookId;
	private String moduleId;
}
