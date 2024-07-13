package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.dto.User;

public interface UserRepository extends JpaRepository<User,String> {
@Query("select u from User u where id=?1 or username=?2")
public List<User> findById(String id,String username);
}

