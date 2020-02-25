package com.webfluxfuntional.fp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document
@Setter
@Getter
public class Employee {

	@Id
	private String id;
	private String name;
	private int age;
	private double salary;
	
	
	
	
}
