package com.my.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.my.demo.dto.TaskResponse;
import com.my.demo.model.TaskModel;
import com.my.demo.service.TaskDetailsService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ToDoListController.class)
class ToDoListControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskDetailsService service;
	
	
	@Test
	public void retrieveTaskDetails() throws Exception {
		
		String expResponse = "{\"status\":\"200\",\"success\":true,\"reason\":null,\"allTaskList\":[{\"id\":1,\"name\":\"user1\",\"completed\":false,\"taskName\":null,\"taskDetails\":null,\"taskCreatedDate\":null,\"taskUpdatedDate\":null}],\"task\":null}";
		
		TaskModel mockData= new TaskModel();
		mockData.setId(1l);
		mockData.setCompleted(false);
		mockData.setName("user1");
		List<TaskModel>dataList = Arrays.asList(mockData);
		TaskResponse res =new TaskResponse();
		res.setStatus("200");
		res.setSuccess(true);
		res.setAllTaskList(dataList);
		
		Mockito.when(service.getAllTaskDetails("user1"))
				.thenReturn(res);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/task/user1").accept(
				MediaType.APPLICATION_JSON);
		

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				
		JSONAssert.assertEquals(expResponse, result.getResponse()
				.getContentAsString(), false);
		
	}
	
	@Test
	public void retrieveTaskDetailsEmpty() throws Exception {
		
		String expResponse = "{\"status\":\"200\",\"success\":true,\"reason\":null,\"allTaskList\":[],\"task\":null}\r\n"
				+ "";
		
	
		List<TaskModel>dataList = new ArrayList();
		TaskResponse res =new TaskResponse();
		res.setStatus("200");
		res.setSuccess(true);
		res.setAllTaskList(dataList);
		
		Mockito.when(service.getAllTaskDetails("user2"))
				.thenReturn(res);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/task/user2").accept(
				MediaType.APPLICATION_JSON);
		

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expResponse, result.getResponse()
				.getContentAsString(), false);
		
	}
	


}
