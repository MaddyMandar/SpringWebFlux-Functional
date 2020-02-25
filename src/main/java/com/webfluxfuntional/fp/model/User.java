package com.webfluxfuntional.fp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Setter
@Getter
@ToString
public class User {
	
	@Id
	private String id;
	private String name;
	private String cityName;
	
		

}
