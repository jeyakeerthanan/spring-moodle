package com.moodle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.moodle.entities.User;
import com.moodle.repositories.UserRepository;

@Service
public class UserService {
	
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private  UserRepository userRepository;

	
	public  User getOneByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	public User getOneByContactPrimary(String contactPrimary) {
		System.out.println(contactPrimary);
		return userRepository.findByContactPrimary(contactPrimary);
	}

	public Integer getMaxId(String userType) {
		return userRepository.countByUserType(userType);
	}


	public  User saveUser(User userData) {
		userData.setPassword(passwordEncoder.encode(userData.getPassword()));
		return userRepository.save(userData);
	}
	
           
}
