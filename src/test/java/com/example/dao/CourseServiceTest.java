package com.example.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dto.Course;

@SpringBootTest
class CourseServiceTest {

	@Mock
	CourseRepository repo;
	
	@InjectMocks
	CourseService service;
	
	@Test
	void testSave() {
		Course c=new Course();
		c.setId("S1");
		c.setCourse_name("Java");
		service.save(c);
		verify(repo).save(c);
	}

	@Test
	void testSelectAll() {
		List<Course> list=new ArrayList<Course>();
		Course course=new Course();
		course.setId("S1");
		course.setCourse_name("Java");
		
		Course course1=new Course();
		course1.setId("S2");
		course1.setCourse_name("PHP");
		
		Course course2=new Course();
		course2.setId("S3");
		course2.setCourse_name("Mongo");
		
		list.add(course);
		list.add(course1);
		list.add(course2);
		when(repo.findAll()).thenReturn(list);
		List<Course> courselist=service.selectAll();
		assertEquals(3,courselist.size());
		verify(repo).findAll();
		
		
	}

	@Test
	void testCheck() {
		Course course=new Course();
		course.setId("S1");
		course.setCourse_name("Java");
		when(repo.findById("S1")).thenReturn(Optional.ofNullable(course));
		Course co=service.check("S1").get();
		assertEquals("Java",co.getCourse_name());
		verify(repo).findById("S1");

	}

}
