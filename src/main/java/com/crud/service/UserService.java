package com.crud.service;

import java.util.List;

import com.crud.entity.User;

public interface UserService {
	List<User> findAll();

	User findById(String id);

	void save(User user);

	void update(User user);

	void delete(String id);
}
