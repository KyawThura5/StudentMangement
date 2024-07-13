package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.User;
import com.example.model.UserBean;

@Service
public class UserService
{
@Autowired
UserRepository usersRepository;

public List<User> getAllUser()
{
List<User> list = (List<User>) usersRepository.findAll();
return list;
}

public int save(User user) {
	usersRepository.save(user);
	return 1;
}

public void delete(String id) {

	usersRepository.deleteById(id); {
	
}
}
public User selectbyid(String id) {
	return usersRepository.findById(id).get();
}
public List<User> searchByid(String id,String username){
	return usersRepository.findById(id, username);
	
}
public Optional<User> check(String id){
	return usersRepository.findById(id);
	
}
	}