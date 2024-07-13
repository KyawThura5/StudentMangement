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

import com.example.dto.User;
import com.example.model.UsersearchBean;

@SpringBootTest
class UserServiceTest {
	@Mock
	UserRepository repo;
	
	@InjectMocks
	UserService service;

	@Test
	void testGetAllUser() {
		List<User> list=new ArrayList<>();
		User user=new User();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		
		User user1=new User();
		user.setId("USR002");
		user.setUsername("Zaw Zaw");
		user.setUseremail("zaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		
		list.add(user);
		list.add(user1);
		when(repo.findAll()).thenReturn(list);
		List<User>userlist=service.getAllUser();
		assertEquals(2,userlist.size());
		verify(repo).findAll();
	}

	@Test
	void testSave() {
		User user=new User();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		service.save(user);
		verify(repo).save(user);
	}

	@Test
	void testDelete() {
		service.delete("USR001");
		verify(repo).deleteById("USR001");
	}

	@Test
	void testSelectbyid() {
		User user=new User();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		when(repo.findById("USR001")).thenReturn(Optional.ofNullable(user));
		User u=service.selectbyid("USR001");
		assertEquals("Kyaw Thura",u.getUsername());
		assertEquals("tkyaw@gmail.com",u.getUseremail());
		assertEquals("1234",u.getPassword());
		assertEquals("Admin",u.getRole());
		verify(repo).findById("USR001");
	}

	@Test
	void testSearchByid() {
		UsersearchBean bean=new UsersearchBean();
		bean.setId("USR001");
		bean.setUsername("Zaw Zaw");
		List<User> list=new ArrayList<>();
		User user=new User();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		
		User user1=new User();
		user.setId("USR002");
		user.setUsername("Zaw Zaw");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		list.add(user);
		list.add(user1);
		when(repo.findById("USR001","Zaw Zaw")).thenReturn(list);
		List<User> u=service.searchByid("USR001","Zaw Zaw");
		assertEquals(2,u.size());
		verify(repo).findById("USR001","Zaw Zaw");
	}

	@Test
	void testCheck() {
		User user=new User();
		user.setId("USR001");
		user.setUsername("Kyaw Thura");
		user.setUseremail("tkyaw@gmail.com");
		user.setPassword("1234");
		user.setRole("Admin");
		when(repo.findById("USR001")).thenReturn(Optional.ofNullable(user));
		User u=service.check("USR001").get();
		assertEquals("Kyaw Thura",u.getUsername());
		assertEquals("tkyaw@gmail.com",u.getUseremail());
		assertEquals("1234",u.getPassword());
		assertEquals("Admin",u.getRole());
		verify(repo).findById("USR001");
	}

}
