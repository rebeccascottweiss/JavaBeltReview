package com.rebecca.beltreviewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebecca.beltreviewer.models.Event;
import com.rebecca.beltreviewer.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepo; 
	
	//create and update
	public Event saveEvent(Event event) {
		return eventRepo.save(event); 
	}
	
	//read
	
	public List<Event> allEvents(){
		return eventRepo.findAll(); 
	}
	
	public Event findById(Long id) {
		Optional<Event> ev = eventRepo.findById(id); 
		
		if(ev.isPresent()) {
			return ev.get(); 
		} else {
			return null; 
		}
	}
	
	public List<Event> findByState(String state){
		return eventRepo.findByState(state); 
	}
	
	public List<Event> findOutOfState(String state){
		return eventRepo.findByStateNot(state); 
	}
	
	//delete
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}

}
