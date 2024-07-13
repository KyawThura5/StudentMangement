package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.dao.CourseRepository;
import com.example.dao.CourseService;
import com.example.dao.StudentRepository;
import com.example.dao.StudentService;
import com.example.dto.Course;
import com.example.dto.Student;
import com.example.dto.User;
import com.example.model.StudentBean;
@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	StudentService service;
	
	@MockBean
	StudentRepository repo;
	
	@MockBean
	CourseService service1;
	
	@MockBean
	CourseRepository repo1;
	@Test
	void testShowstudent() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		this.mockMvc.perform(get("/showAll").sessionAttr("user", user))
		.andExpect(status().isOk())
		.andExpect(view().name("STU003"))
		.andExpect(model().attributeExists("list"));
		}
	@Test
	void testShowstuform() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		this.mockMvc.perform(get("/showstudentform").sessionAttr("user", user))
		.andExpect(status().isOk())
		.andExpect(view().name("STU001"))
		.andExpect(model().attributeExists("bean"));
	}

	@Test
	void testAddstudent() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		StudentBean stu=new StudentBean();
		stu.setStudentid("STU001");
		stu.setStudentname("Kyaw Thura");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=service1.selectAll();
		stu.setCourse(list);
		this.mockMvc.perform(post("/Addstudent").sessionAttr("user",user).flashAttr("bean",stu))
		.andExpect(redirectedUrl("/showstudentform"));
	}
	@Test
	void testAddstudentFail() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		StudentBean stu=new StudentBean();
		this.mockMvc.perform(post("/Addstudent").sessionAttr("user",user).flashAttr("bean",stu))
		.andExpect(view().name("STU001"));
	}

	@Test
	void testShowbyid() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		Student stu=new Student();
		stu.setStudentid("STU001");
		stu.setStudentname("Kyaw Thura");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=service1.selectAll();
		stu.setCourse(list);
		when(service.selectbyid("STU001")).thenReturn(stu);
		this.mockMvc.perform(get("/setupshowbyid").sessionAttr("user", user).param("id", "STU001"))
		.andExpect(status().isOk())
		.andExpect(view().name("STU002"))
		.andExpect(model().attributeExists("bean"));		
		}

	@Test
	void testUpdateStudent() throws Exception {
		User user=new User();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		StudentBean stu=new StudentBean();
		stu.setStudentid("STU001");
		stu.setStudentname("Kyaw Thura");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=new ArrayList<>();
		stu.setCourse(list);
		this.mockMvc.perform(post("/updateStudent").sessionAttr("user", user).flashAttr("bean", stu))
		.andExpect(redirectedUrl("/showAll"));
	}

	@Test
	void testDelete() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		Student stu=new Student();
		stu.setStudentid("STU001");
		stu.setStudentname("Mg Mg");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=service1.selectAll();
		stu.setCourse(list);
		this.mockMvc.perform(get("/deletestudent").sessionAttr("user", user).param("id","STU001"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/showAll"));
	}

	@Test
	void testSearch() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		this.mockMvc.perform(post("/SearchStudent").param("studentid","STU001").param("studentname", "Kyaw Thura").param("course_name", "Java").sessionAttr("user", user))
		.andExpect(view().name("STU003"))
		.andExpect(model().attributeExists("list"));
	}

}
