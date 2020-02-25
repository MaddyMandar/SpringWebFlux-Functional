package com.webfluxfuntional.fp.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;

import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webfluxfuntional.fp.handler.UserHandler;

@Configuration
public class UserRouter {

	private static final String USR_PATH = "/user";
	
	@Bean
	public RouterFunction<ServerResponse> routeU(UserHandler handler){
		
		return RouterFunctions
					// Get All Users
					.route(RequestPredicates.GET(USR_PATH)
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::getUsers)
					// Create User
					.andRoute(RequestPredicates.POST(USR_PATH)
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::createUser);
	}
}
