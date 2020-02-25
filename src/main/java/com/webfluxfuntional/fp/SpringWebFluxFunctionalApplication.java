 package com.webfluxfuntional.fp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.webfluxfuntional.fp")
public class SpringWebFluxFunctionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxFunctionalApplication.class, args);
		System.out.println("Welcome to Functional Pragramming");
	}

}
