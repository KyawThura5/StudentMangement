package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Course;

@Service
public class CourseService{
@Autowired
CourseRepository courserepository;
public void save(Course courses) {
	courserepository.save(courses);
}
public List<Course> selectAll(){
	List<Course>list=courserepository.findAll();
	return list;
}
public Optional<Course> check(String id){
	return courserepository.findById(id);
}
}
