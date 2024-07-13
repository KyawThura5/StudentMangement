package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.dto.Student;
public interface StudentRepository extends JpaRepository<Student,String>{
@Query("select s from Student s join s.course c where s.studentid=?1 or s.studentname=?2 or c.course_name=?3")
public List<Student> searchStudent(String studentid,String studentname,String course_name);
}
