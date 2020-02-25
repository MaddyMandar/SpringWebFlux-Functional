package com.webfluxfuntional.fp.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webfluxfuntional.fp.model.User;

public interface UserDao extends ReactiveMongoRepository<User, String> {

}
