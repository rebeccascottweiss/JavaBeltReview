package com.rebecca.beltreviewer.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	
	private String name; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventDate;
	
	private String city; 
	private String state; 
	
	//for createdAt, updatedAt stamps
	@Column(updatable=false)
	private Date createdAt; 
	
	private Date updatedAt; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User host; 
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="events_users",
		joinColumns = @JoinColumn(name="event_id"),
		inverseJoinColumns = @JoinColumn(name="user_id")
	)
	private List<User> attendees; 
	
	@OneToMany(orphanRemoval=true, mappedBy="event", fetch=FetchType.LAZY)
	private List<Message> messages; 
	
	public Event() {
		
	}
	//setters for createdAt, updatedAt stamps; 
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date(); 
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date(); 
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getHost() {
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	} 
	
	public List<User> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
}
