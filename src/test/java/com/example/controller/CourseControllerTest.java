package com.example.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.dao.CourseRepository;
import com.example.dao.CourseService;
import com.example.dto.User;
import com.example.model.CourseBean;
@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CourseService service;
	
	@MockBean
	CourseRepository repo;

	@Test
	void testShowcourse() throws Exception{
		User user=new User();
		user.setUsername("Kyaw Thura");
		this.mockMvc.perform(get("/showcourse").sessionAttr("user", user))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD003"))
		.andExpect(model().attributeExists("courseBean"));
	}

	@Test
	void testAddcourse() throws Exception {
		CourseBean course=new CourseBean();
		course.setId("S1");
		course.setCourse_name("Java");
		this.mockMvc.perform(post("/Addcourse").flashAttr("courseBean",course))
		.andExpect(redirectedUrl("/showcourse"));	
	}
	@Test
	void testAddcourseFail() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		CourseBean course=new CourseBean();
		this.mockMvc.perform(post("/Addcourse").sessionAttr("user", user).flashAttr("courseBean",course))
		.andExpect(view().name("BUD003"));	
	}


}
