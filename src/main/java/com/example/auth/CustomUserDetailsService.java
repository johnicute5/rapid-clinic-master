package com.example.auth;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.concurrent.ExecutionException;

public class CustomUserDetailsService implements UserDetailsService {

	/*@Autowired
	private UserRepository userRepo;*/

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*User user = userRepo.findByUsername(username);*/
		try {
			User user2 = userService.getUserByUsername(username);
			return new CustomUserDetails(user2);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		/*try {
			User user1 = userService.getUserByUsername(username);
			System.out.println(user1.getId());
			System.out.println(user1.getUsername());
			System.out.println(user1.getEmail());
			System.out.println(user1.getPassword());
		} catch (InterruptedException e) {
			System.out.println("User not found");
		} catch (ExecutionException e) {
			System.out.println("User not found");
		}*/

		/*if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);*/

	}

}
