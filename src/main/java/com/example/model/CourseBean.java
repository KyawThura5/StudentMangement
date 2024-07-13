package com.example.model;

import javax.validation.constraints.NotEmpty;

public class CourseBean {
	@NotEmpty
	private String id;
	@NotEmpty
	private String course_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
}
