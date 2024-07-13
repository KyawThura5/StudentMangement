package com.example.model;

import javax.validation.constraints.NotEmpty;

public class UserBean {
	@NotEmpty
	private String id;
	@NotEmpty
	private String username;
	@NotEmpty
	private String useremail;
	@NotEmpty
	private String password;
	@NotEmpty
	private String confirm_password;
	@NotEmpty
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
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}