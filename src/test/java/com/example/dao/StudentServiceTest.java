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
import com.example.dto.Student;

@SpringBootTest
class StudentServiceTest {
@Mock
StudentRepository repo;
@InjectMocks
StudentService service;

	@Test
	void testSave() {
		Student stu=new Student();
		stu.setStudentid("STU001");
		stu.setStudentname("Kyaw Thura");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=new ArrayList<>();
		stu.setCourse(list);
		service.save(stu);
		verify(repo).save(stu);
		}
	
	@Test
	void testSaveFail() {
		Student stu=new Student();
		service.save(stu);
		verify(repo).save(stu);
		}

	@Test
	void testSelectAll() {
		Student stu=new Student();
		stu.setStudentid("STU001");
		stu.setStudentname("Kyaw Thura");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=new ArrayList<>();
		stu.setCourse(list);
		
		Student stu1=new Student();
		stu1.setStudentid("STU002");
		stu1.setStudentname("Mg Mg");
		stu1.setDate("2000-3-22");
		stu1.setGender("male");
		stu1.setPhone("09-034234");
		stu1.setEducation("Bachelor of Computer Science");
		List<Course>list1=new ArrayList<>();
		stu1.setCourse(list1);
		
		List<Student>stulist=new ArrayList<>();
		stulist.add(stu);
		stulist.add(stu1);
		when(repo.findAll()).thenReturn(stulist);
		List<Student>stulist1=service.selectAll();
		assertEquals(2,stulist1.size());
		verify(repo).findAll();
	}

	@Test
	void testSelectbyid() {
		Student stu=new Student();
		stu.setStudentid("STU001");
		stu.setStudentname("Kyaw Thura");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=new ArrayList<>();
		stu.setCourse(list);
		when(repo.findById("STU001")).thenReturn(Optional.ofNullable(stu));
		Student student=service.selectbyid("STU001");
		assertEquals("Kyaw Thura",student.getStudentname());
		assertEquals("2000-3-22",student.getDate());
		assertEquals("male",student.getGender());
		assertEquals("09-034234",student.getPhone());
		assertEquals("Bachelor of Computer Science",student.getEducation());
		assertEquals(list,student.getCourse());
		verify(repo).findById("STU001");
	}

	@Test
	void testDelete() {
		service.delete("STU001");
		verify(repo).deleteById("STU001");
	}

	@Test
	void testSearch() {
		Student stu=new Student();
		stu.setStudentid("STU001");
		stu.setStudentname("Kyaw Thura");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=new ArrayList<>();
		stu.setCourse(list);
		
		Student stu1=new Student();
		stu1.setStudentid("STU002");
		stu1.setStudentname("Mg Mg");
		stu1.setDate("2000-3-22");
		stu1.setGender("male");
		stu1.setPhone("09-034234");
		stu1.setEducation("Bachelor of Computer Science");
		List<Course>list1=new ArrayList<>();
		stu1.setCourse(list1);
		
		Student stu2=new Student();
		stu2.setStudentid("STU003");
		stu2.setStudentname("Zaw Zaw");
		stu2.setDate("2000-3-22");
		stu2.setGender("male");
		stu2.setPhone("09-034234");
		stu2.setEducation("Bachelor of Computer Science");
		List<Course>list2=new ArrayList<>();
		stu2.setCourse(list2);
		
		List<Student>stulist=new ArrayList<>();
		stulist.add(stu);
		stulist.add(stu1);
		stulist.add(stu2);
		when(repo.searchStudent("STU001","Mg Mg","Java")).thenReturn(stulist);
		List<Student>student=service.search("STU001","Mg Mg","Java");
		assertEquals(3,student.size());
		verify(repo).searchStudent("STU001","Mg Mg","Java");

	}

	@Test
	void testCheck() {
		Student stu=new Student();
		stu.setStudentid("STU001");
		stu.setStudentname("Kyaw Thura");
		stu.setDate("2000-3-22");
		stu.setGender("male");
		stu.setPhone("09-034234");
		stu.setEducation("Bachelor of Computer Science");
		List<Course>list=new ArrayList<>();
		stu.setCourse(list);
		when(repo.findById("STU001")).thenReturn(Optional.ofNullable(stu));
		Student student=service.selectbyid("STU001");
		student.setStudentname("Kyaw Thura");
		student.setDate("2000-3-22");
		student.setGender("male");
		student.setPhone("09-034234");
		student.setEducation("Bachelor of Computer Science");
		List<Course>list1=new ArrayList<>();
		student.setCourse(list1);
		verify(repo).findById("STU001");
	}

}
