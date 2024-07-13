package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.Course;

public interface CourseRepository extends JpaRepository<Course,String> {

}
