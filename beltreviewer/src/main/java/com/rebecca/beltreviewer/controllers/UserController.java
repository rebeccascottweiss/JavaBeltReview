package com.rebecca.beltreviewer.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rebecca.beltreviewer.models.User;
import com.rebecca.beltreviewer.services.UserService;
import com.rebecca.beltreviewer.validator.UserValidator;

@Controller
public class UserController {

	private final UserService userServ; 
	
	private final UserValidator userVal; 
	
	public UserController(UserService userServ, UserValidator userVal) {
		this.userServ = userServ; 
		this.userVal = userVal; 
	}
	
	@GetMapping("/")
	public String loginAndReg(@ModelAttribute("user") User user, HttpSession session) {
		if(session.getAttribute("user_id") != null) {
			return "redirect:/events"; 
		}
		return "index.jsp"; 
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userVal.validate(user, result);
		
		if(result.hasErrors()) {
			return "index.jsp"; 
		} 
		
		userServ.registerUser(user); 
		session.setAttribute("user_id", user.getId());
		return "redirect:/events"; 
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(userServ.authenticateUser(email, password)) {
			User currentUser = userServ.findByEmail(email); 
			session.setAttribute("user_id", currentUser.getId());
		} else {
			model.addAttribute("error", "Sorry! User not found."); 
			return "index.jsp"; 
		}
		
		return "redirect:/events"; 
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/"; 
	}
}
