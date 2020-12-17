package com.rebecca.beltreviewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rebecca.beltreviewer.models.Message;
import com.rebecca.beltreviewer.repositories.MessageRepository;

@Repository
public class MessageService {
	@Autowired
	private MessageRepository messageRepo; 
	
	//create and update
	public Message saveMessage(Message message) {
		return messageRepo.save(message); 
	}
	
	//read
	
	public List<Message> allMessages(){
		return messageRepo.findAll(); 
	}
	
	public Message findById(Long id) {
		Optional<Message> mess = messageRepo.findById(id); 
		
		if(mess.isPresent()) {
			return mess.get(); 
		} else {
			return null; 
		}
	}
	
	
	//delete
	public void deleteMessage(Long id) {
		messageRepo.deleteById(id);
	}
}
