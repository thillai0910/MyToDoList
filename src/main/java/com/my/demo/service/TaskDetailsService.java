package com.my.demo.service;

import com.my.demo.dto.TaskRequest;
import com.my.demo.dto.TaskResponse;

public interface TaskDetailsService {
	
	public TaskResponse getAllTaskDetails(String user);
	
	public TaskResponse getTaskDetailsById(long id);
	
	public TaskResponse saveOrUpdateTask(TaskRequest task);
	
	public TaskResponse deleteTask(long id);

}
