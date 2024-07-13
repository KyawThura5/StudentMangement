package com.example.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {
	@Id
	@NotEmpty
	@Column(name="id")
	private String id;
	@NotEmpty
	@Column(name="username")
	private String username;
	
	@NotEmpty
	@Column(name="useremail")
	private String useremail;
	
	@NotEmpty
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
