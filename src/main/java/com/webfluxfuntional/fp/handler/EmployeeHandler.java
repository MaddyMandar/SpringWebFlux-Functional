package com.webfluxfuntional.fp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webfluxfuntional.fp.dao.EmployeeDao;
import com.webfluxfuntional.fp.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EmployeeHandler {

	@Autowired
	private EmployeeDao empDao;
	private Mono<ServerResponse> not_Found = ServerResponse.notFound().build();
	
	
	// Get all employees
	public Mono<ServerResponse> getEmployees(ServerRequest req){
		
		Flux<Employee> emps = empDao.findAll();
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(emps, Employee.class);
	}
	
	// Get Employee by Id
	public Mono<ServerResponse> getEmployeById(ServerRequest req){
		
		String id = req.pathVariable("id");	
		Mono<Employee> emp = empDao.findById(id);
		
		return emp.flatMap(r -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(emp, Employee.class)).switchIfEmpty(not_Found);
		
		}
   
   //Create => insert employee object
   public Mono<ServerResponse> createEmployee(ServerRequest req){
	   
	   Mono<Employee> emp=req.bodyToMono(Employee.class);
	   
	   return emp.flatMap(r -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(empDao.save(r), Employee.class));
	   
     }
   
   //For Delete , Delete by id
   public Mono<ServerResponse> deleteEmployeById(ServerRequest req){
	   
		String id = req.pathVariable("id");
		Mono<Void> emp = empDao.deleteById(id);
		
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(emp, Void.class);
		
		}
  
   //update a data
   public  Mono<ServerResponse>  updateEmploye(ServerRequest req){
	   
	   String id = req.pathVariable("id"); 
           Mono<Employee> emp = req.bodyToMono(Employee.class);

		   Mono<Employee> updateemp = emp.flatMap(e -> {
			   
			                Mono<Employee> curEmp = empDao.findById(id);
			                return curEmp.flatMap(t -> {
			                	t.setId(e.getId());
							                	t.setName(e.getName());
							                	t.setAge(e.getAge());
							                	t.setSalary(e.getSalary());
							                	return empDao.save(t);
			                });
		     });
	   
		   return updateemp.flatMap(e1 -> ServerResponse.ok()
					   .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(e1)))
					   .switchIfEmpty(not_Found);
   }

}
