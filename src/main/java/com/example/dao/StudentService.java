package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Student;

@Service
public class StudentService {
@Autowired
StudentRepository sturepository;
public void save(Student student) {
	sturepository.save(student);
}
public List<Student> selectAll(){
	List<Student>list=sturepository.findAll();
	return list;
}
public Student selectbyid(String id) {
	return sturepository.findById(id).get();
}
public void delete(String id) {
	sturepository.deleteById(id);
}
public List<Student> search(String studentid,String studentname,String course_name){
	List<Student>list=sturepository.searchStudent(studentid,studentname,course_name);
	return list;
}
public Optional<Student> check(String id){
	return sturepository.findById(id);
}
}
