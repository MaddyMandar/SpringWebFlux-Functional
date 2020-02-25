package com.webfluxfuntional.fp.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webfluxfuntional.fp.model.Employee;

public interface EmployeeDao extends ReactiveMongoRepository<Employee, String>{

}
