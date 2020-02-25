package com.webfluxfuntional.fp.handler;

import javax.xml.ws.Response;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webfluxfuntional.fp.dao.UserDao;
import com.webfluxfuntional.fp.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	@Autowired
	private UserDao userDao;
	private Mono<ServerResponse> not_Found = ServerResponse.notFound().build();
	
	
	//Get all users 
	public Mono<ServerResponse> getUsers(ServerRequest req) {
		
		Flux<User> uFlux = userDao.findAll();
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(uFlux,User.class);
		
	}
	
	//Create => insert user object
	public Mono<ServerResponse> createUser(ServerRequest req) {
		
		Mono<User> uMono = req.bodyToMono(User.class);
		
		
		
		return uMono.flatMap(r -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(userDao.save(r), User.class)
						
				);
		
	}
	
	
	
}
