package com.spring.controller;

import java.util.List;

import com.spring.mail.EmailSenderService;
import com.spring.model.User;
import com.spring.exception.ResourceNotFoundException;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//http://localhost:8080

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailSenderService senderService;

	// get all users
	//http://localhost:8080/api/users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}


	// get user by id
	//http://localhost:8080/api/user/1
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable ("id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	// create user
	//http://localhost:8080/api/users
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		senderService.sendEmail(user);
		return this.userRepository.save(user);
	}

	// update user
	//http://localhost:8080/api/users/1
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable ("id") long userId) {
		 User existingUser = this.userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 existingUser.setFirstName(user.getFirstName());
		 existingUser.setLastName(user.getLastName());
		 existingUser.setEmail(user.getEmail());
		 return this.userRepository.save(existingUser);
	}

	
	// delete user by id
	//http://localhost:8080/api/users/1
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
		 User existingUser = this.userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 this.userRepository.delete(existingUser);
		 return ResponseEntity.ok().build();
	}



}
