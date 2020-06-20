package net.greet.users;

import java.util.*;

import net.greet.greetings_languages.Language;

public class User {
	
	private String userName;
	
	private int greetCount;
	
	public User(String userName) {
		
		this.userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();
		
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getGreetCount() {
		return greetCount;
	}
	
	public void setGreetCount() {
		greetCount = 0;
	}
	
	public void setGreetCountAmount(int amount) {
		greetCount = amount;
	}
	
	public String greet() {
		greetCount++;
		return Language.ENGLISH.getGreeting() + userName; 
	}
	
	public String greet(String language) {
		
		for(Language lan: Language.values())
			if(lan.toString().equalsIgnoreCase(language)) {
				greetCount++;
				return lan.getGreeting() + userName;
			}  
		
		greetCount++;
		return Language.ENGLISH.getGreeting() + userName;	
	}

	public String toString() {
		Formatter f = new Formatter();
		String s = f.format("%s has been greeted %d time(s)", userName, greetCount).toString();
		f.close();
		return s;
	}
	
}