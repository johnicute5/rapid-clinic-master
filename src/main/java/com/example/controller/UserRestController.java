package com.example.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.User;

@RestController
@RequestMapping(path ="api/user")
public class UserRestController {


	private final UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public List<User> getAllUsers () throws InterruptedException, ExecutionException {
		return userService.getAllUsers();
	}
	@PostMapping("/add")
	public String addUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		System.out.println("staff controller create");
		return userService.addUser(user);
	}
	
	@GetMapping("/getById")
	public User getUserbyId(@RequestParam String id) throws InterruptedException, ExecutionException {
		return userService.getUserbyId(id);
	}
	
	@GetMapping("/getByName")
	public User getPatientByName(@RequestParam String firstName) throws InterruptedException, ExecutionException {
		return userService.getPatientByName(firstName);
	}
	
	
	@PutMapping("/edit")
	public String editUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return userService.updateUser(user);
	}

	
	@GetMapping("/test")
	public ResponseEntity<String> testGetEndpoint(){
		return ResponseEntity.ok("Test Get Endpoint is Working");
	}
	
	@PostMapping("/upload")
	public String uploadImage(@RequestParam("image") MultipartFile file) {
		return userService.uploadImage(file);
	}
	
	@GetMapping("/download/{fileName}")
	public String downloadImage(@PathVariable String fileName) {
		return userService.downloadImage(fileName);
	}
	

}
