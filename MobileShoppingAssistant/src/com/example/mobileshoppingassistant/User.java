package com.example.mobileshoppingassistant;

/**
 * Created by coder on 10/4/14.
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String notes;
    
	public User(int id, String firstName, String lastName, String phoneNumber,
			String emailAddress, String notes) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.notes = notes;
	}
	public User(int id, String firstName, String phoneNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", emailAddress="
				+ emailAddress + ", notes=" + notes + "]";
	}

	
	
	
}
