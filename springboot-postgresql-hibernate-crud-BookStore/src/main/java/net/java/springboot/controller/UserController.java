package net.java.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import net.java.springboot.exception.ResourceNotFoundException;
import net.java.springboot.model.User;
import net.java.springboot.repository.UserRepository;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// get users
	@GetMapping("users")
	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}

	// get users by id
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userID) throws ResourceNotFoundException {
		User user = userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Usernot found for this id ::" + userID));

		return ResponseEntity.ok().body(user);
	}

	// *Get Book by user by username
	@GetMapping("/users/name/{username}")
	public User getBookByName(@PathVariable String username) {

		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);

		for (int i = 0; i < users.size(); i++) {

			if (users.get(i).getUsername().equalsIgnoreCase(username)) {
				return users.get(i);
			}
		}

		return null;
	}

	// save visitor
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	// update visitor
	@PutMapping("users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userID,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {

		User user = userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id ::" + userID));

		user.setUsername(userDetails.getUsername());
		user.setAddress(userDetails.getAddress());
		user.setEmail(userDetails.getEmail());
		user.setName(userDetails.getName());
		user.setPassword(userDetails.getPassword());

		return ResponseEntity.ok(this.userRepository.save(user));

	}

	// delete visitor

	@DeleteMapping("users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userID) throws ResourceNotFoundException {

		User user = userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id ::" + userID));

		this.userRepository.delete(user);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}

}
