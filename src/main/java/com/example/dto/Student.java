package com.example.dto;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="student")
public class Student {
	@Id
	@Column(name="studentid")
	private String studentid;
	
	@Column(name="studentname")
	private String studentname;
	
	@Column(name="date")
	private String date;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="education")
	private String education;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "student_course", 
	joinColumns = { @JoinColumn(name = "studentid")},
	inverseJoinColumns = { @JoinColumn(name = "id")})
	private List<Course> course;
	
	
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
}
