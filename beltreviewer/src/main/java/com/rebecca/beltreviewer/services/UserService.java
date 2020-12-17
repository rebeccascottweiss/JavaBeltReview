package com.rebecca.beltreviewer.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebecca.beltreviewer.models.Event;
import com.rebecca.beltreviewer.models.User;
import com.rebecca.beltreviewer.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo; 
	
	//create
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()); 
		user.setPassword(hashed);
		return userRepo.save(user); 
	}
	
	//read
	public User findByEmail(String email) {
		return userRepo.findByEmail(email); 
	}
	
	public User findById(Long id) {
		Optional<User> u = userRepo.findById(id); 
		
		if(u.isPresent()) {
			return u.get(); 
		} else {
			return null; 
		}
	}
	
	public boolean authenticateUser(String email, String password) {
		User user = userRepo.findByEmail(email); 
		
		if(user == null) {
			return false; 
		} else {
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true; 
			} else {
				return false; 
			}
		}
	}
	
	public Long attendees(Event event) {
		return userRepo.countByEvents(event); 
	}
	
	//update
	
	public User updateUser(User user) {
		return userRepo.save(user); 
	}
	
	//delete
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

}
