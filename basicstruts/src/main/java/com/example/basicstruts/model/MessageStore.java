package com.example.basicstruts.model;

public class MessageStore {
    
    private String message;
     
    public MessageStore() {
         
        setMessage("Hi From MessageStore");
    }
 
    public String getMessage() {
 
        return message;
    }
 
    public void setMessage(String message) {
 
        this.message = message;
    }
 
}
