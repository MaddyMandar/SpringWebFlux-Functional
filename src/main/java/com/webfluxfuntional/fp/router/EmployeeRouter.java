package com.webfluxfuntional.fp.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webfluxfuntional.fp.handler.EmployeeHandler;

@Configuration
public class EmployeeRouter {

	private static final String EMP_PATH = "/employee";
	
	
	@Bean
	public RouterFunction<ServerResponse> route(EmployeeHandler handler){
		
		return RouterFunctions
				.route(RequestPredicates.GET(EMP_PATH)
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getEmployees)
				//Get employee data by id
			   .andRoute(RequestPredicates.GET(EMP_PATH+"/{id}")
					.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getEmployeById)
			   //Create ->insert data
			   .andRoute(RequestPredicates.POST(EMP_PATH)
					   .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::createEmployee) 
			   //Delete data by id
			   .andRoute(RequestPredicates.DELETE(EMP_PATH+"/{id}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::deleteEmployeById)
			   //Update data by id
			   .andRoute(RequestPredicates.PUT(EMP_PATH+"/{id}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::updateEmploye)
					   ;
	           
	
	}
}
