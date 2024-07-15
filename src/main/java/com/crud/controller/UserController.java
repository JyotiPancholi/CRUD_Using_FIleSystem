package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.User;
import com.crud.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	 @Autowired
	    private UserService userService;

	    @GetMapping
	    public List<User> getAllUsers() {
	        return userService.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable String id) {
	        User user = userService.findById(id);
	        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    @PostMapping
	    public ResponseEntity<Void> createUser(@RequestBody User user) {
	        userService.save(user);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody User user) {
	        user.setId(id);
	        userService.update(user);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
	        userService.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
