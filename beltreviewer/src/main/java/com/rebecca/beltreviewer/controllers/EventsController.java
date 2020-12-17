package com.rebecca.beltreviewer.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.rebecca.beltreviewer.models.Event;
import com.rebecca.beltreviewer.models.Message;
import com.rebecca.beltreviewer.models.User;
import com.rebecca.beltreviewer.services.EventService;
import com.rebecca.beltreviewer.services.MessageService;
import com.rebecca.beltreviewer.services.UserService;

@Controller
public class EventsController {
	private EventService eventServ; 
	
	private MessageService messageServ; 
	
	private UserService userServ; 
	
	
	public EventsController(EventService eventServ, MessageService messageServ, UserService userServ) {
		this.eventServ = eventServ; 
		this.messageServ = messageServ; 
		this.userServ = userServ; 
	}

	
	@GetMapping("/events")
	public String dashboard(@ModelAttribute("event") Event event, HttpSession session, Model model) {
//		if (session.getAttribute("user_id") == null) {
//			return "redirect:/"; 
//		}
		Long userId = (Long) session.getAttribute("user_id"); 
		User loggedInUser = userServ.findById(userId); 
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("user", loggedInUser); 
		modelMap.put("localEvents", eventServ.findByState(loggedInUser.getState())); 
		modelMap.put("nonlocalEvents", eventServ.findOutOfState(loggedInUser.getState()));
//		List<Event> localEvents = eventServ.findByState(loggedInUser.getState()); 
//		List<Event> nonlocalEvents = eventServ.findOutOfState(loggedInUser.getState()); 
		model.addAllAttributes(modelMap); 
		
		return "/events/dashboard.jsp"; 
		
	}
	
	@PostMapping("/createEvent")
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
		if(result.hasErrors()) {
			return "/events/dashboard.jsp"; 
		}
		
		eventServ.saveEvent(event); 
		return "redirect:/events"; 
		
	}
	
	@GetMapping("/events/{id}")
	public String showEvent(@PathVariable("id") Long id, @ModelAttribute("message") Message message, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id"); 
		User loggedInUser = userServ.findById(userId); 
		Event thisEvent = eventServ.findById(id); 
		model.addAttribute("user", loggedInUser); 
		model.addAttribute("event", thisEvent);
		
		return "/events/show.jsp"; 
	}
	
	@PostMapping("/createMessage")
	public String createMessage(@Valid @ModelAttribute("message") Message message, BindingResult result) {
		if(result.hasErrors()) {
			return "/events/show.jsp"; 
		} 
		
		messageServ.saveMessage(message); 
		return "redirect:/events/" + message.getEvent().getId(); 
	}
	
	@GetMapping("/events/{id}/edit")
	public String editEvent(@PathVariable("id") Long id, Model model) {
		Event event = eventServ.findById(id); 
		model.addAttribute("event", event); 
		return "/events/edit.jsp"; 
	}
	
	@PutMapping("/events/{id}/edit")
	public String updateEvent(@PathVariable("id") Long id, @Valid @ModelAttribute("event") Event event, BindingResult result) {
		if(result.hasErrors()) {
			return "/events/edit.jsp";
		}
		eventServ.saveEvent(event); 
		return "redirect:/events/";
	}
	
	@GetMapping("/join/{id}")
	public String joinEvent(@PathVariable("id") Long id, HttpSession session) {
		System.out.println("Trying to join the party...");
		Event event = eventServ.findById(id); 
		User user = userServ.findById((Long) session.getAttribute("user_id")); 
		event.getAttendees().add(user);
		eventServ.saveEvent(event); 
		return "redirect:/events/"+event.getId(); 
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEvent(@PathVariable("id") Long id) {
		eventServ.deleteEvent(id);
		return "redirect:/events"; 
	}
}
