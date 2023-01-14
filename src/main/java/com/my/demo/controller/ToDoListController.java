package com.my.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.demo.dto.TaskRequest;
import com.my.demo.dto.TaskResponse;
import com.my.demo.service.TaskDetailsService;

@RestController
public class ToDoListController {
	
	@Autowired
	TaskDetailsService service;
	
	@GetMapping("/task/{name}")
	public TaskResponse getTask(@PathVariable(name = "name") String name ){
		return service.getAllTaskDetails(name);		
	}
	
	@GetMapping("/taskbyid/{id}")
	public TaskResponse getTaskById(@PathVariable(name = "id") Long id ){
		return service.getTaskDetailsById(id);		
	}
	
	@PostMapping("/task")
	public TaskResponse saveTask(@RequestBody TaskRequest request) {
		request.setCreate(true);
		return service.saveOrUpdateTask(request); 
	}
	
	@PutMapping("/task")
	public TaskResponse updateTask(@RequestBody TaskRequest request) {
		request.setCreate(false);
		return service.saveOrUpdateTask(request); 
	}
	
	@DeleteMapping("/task/{id}")
	public TaskResponse deleteTaskById(@PathVariable(name = "id") Long id ){
		return service.deleteTask(id);		
	}

}
