package com.example.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.dao.UserRepository;
import com.example.dao.UserService;
import com.example.dto.User;
import com.example.model.LoginBean;
import com.example.model.UserBean;
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
@Autowired
private MockMvc mockMvc;
@MockBean
UserService service;
@MockBean
UserRepository repo;
	@Test
	void testDisplayView() throws Exception {
		this.mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("LGN001"));
	}

	@Test
	void testMenu() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		this.mockMvc.perform(get("/menu").sessionAttr("user", user))
		.andExpect(status().isOk())
		.andExpect(view().name("MNU001"));
	}

	@Test
	void testMenuLoginBeanModelMapHttpSessionDate() throws Exception {
		User user1=new User();
		user1.setId("USR001");
		user1.setUsername("Kyaw Thura");
		user1.setUseremail("tkyaw@gmail.com");
		user1.setPassword("1234");
		user1.setRole("Admin");
		User user2=new User();
		user2.setId("USR002");
		user2.setUsername("Kyaw Thura");
		user2.setUseremail("tkyaw@gmail.com");
		user2.setPassword("12345");
		user2.setRole("Admin");
		List<User>list=new ArrayList<>();
		list.add(user1);
		list.add(user2);
		Date date=new Date();
		LoginBean bean=new LoginBean();
		bean.setId("USR002");
		bean.setPassword("12345");
		when(service.getAllUser()).thenReturn(list);
		this.mockMvc.perform(get("/login").sessionAttr("date", date).flashAttr("loginBean",bean))
		.andExpect(view().name("MNU001"));
		
		
	}

	@Test
	void testCreateuser() throws Exception {
		this.mockMvc.perform(get("/createuser"))
		.andExpect(view().name("LGN002"))
		.andExpect(model().attributeExists("UserBean"));
	}

	@Test
	void testAccount() throws Exception {
		UserBean bean=new UserBean();
		bean.setId("USR001");
		bean.setUsername("Kyaw Thura");
		bean.setUseremail("tkyaw@gmial.com");
		bean.setPassword("1234");
		bean.setConfirm_password("1234");
		bean.setRole("Admin");
		this.mockMvc.perform(post("/CreateUser").flashAttr("UserBean", bean))
		.andExpect(redirectedUrl("/"));
	}

	@Test
	void testDisplayuserlist() throws Exception{
		User user=new User();
		user.setUsername("Kyaw Thura");
		this.mockMvc.perform(get("/userall").sessionAttr("user", user))
		.andExpect(status().isOk())
		.andExpect(view().name("USR003"))
		.andExpect(model().attributeExists("list"));
	}

	@Test
	void testInsertUser() throws Exception {
		User user=new User();
		user.setUsername("Kyaw Thura");
		this.mockMvc.perform(get("/adduserview").sessionAttr("user", user))
		.andExpect(status().isOk())
		.andExpect(view().name("USR001"))
		.andExpect(model().attributeExists("UserBean"));
	}

	@Test
	void testAdduser() throws Exception {
		User ses=new User();
		ses.setUsername("Kyaw Thura");
		UserBean user=new UserBean();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setConfirm_password("1234");
		user.setRole("Admin");
		this.mockMvc.perform(post("/Adduser").flashAttr("UserBean", user).sessionAttr("user", ses))
		.andExpect(redirectedUrl("/userall"));
	}
	
	@Test
	void testAdduserFail() throws Exception {
		User ses=new User();
		ses.setUsername("Kyaw Thura");
		UserBean user=new UserBean();
		this.mockMvc.perform(post("/Adduser").flashAttr("UserBean", user).sessionAttr("user", ses))
		.andExpect(view().name("USR001"));
	}
	@Test
	void testsetupUpdateuser() throws Exception {
		User user=new User();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		when(service.selectbyid("USR001")).thenReturn(user);
		this.mockMvc.perform(get("/setupupdateuser").sessionAttr("user", user).param("id","USR001"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR002"))
		.andExpect(model().attributeExists("User"));	
	}
	@Test
	void testUpdate() throws Exception {
		User ses=new User();
		ses.setId("USR001");
		ses.setUsername("Kyaw Thura");
		UserBean user=new UserBean();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setConfirm_password("1234");
		user.setRole("Admin");
		this.mockMvc.perform(post("/Updateuser").flashAttr("User",user).sessionAttr("user",ses))
		.andExpect(redirectedUrl("/userall"));
	}

	@Test
	void testDeleteuser() throws Exception {
		User user=new User();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		this.mockMvc.perform(get("/setupdeleteuser").param("id","USR001").sessionAttr("user", user))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/userall"));	
	}

	@Test
	void testSearchUsersearchBeanModelMap() throws Exception {
		User u=new User();
		u.setUsername("Kyaw Thura");
		this.mockMvc.perform(post("/setupsearchuser").param("id", "USR001").param("username","Zaw Zaw").sessionAttr("user", u))
		.andExpect(view().name("USR003"))
		.andExpect(model().attributeExists("list"));

	}

}
