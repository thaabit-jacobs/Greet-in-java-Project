package user;

import java.util.*;

public class User {
	
	private String userName;
	
	private HashMap<String, String> languages;
	
	private int greetCount;
	
	public User(String userName, int greetCount) {
		
		this.userName = userName;
		
		this.greetCount = greetCount;
		
		languages = new HashMap<String, String>();
		
		languages.put("English", "Good day " + this.userName);
		languages.put("Afrikaans", "Goie dag " + this.userName);
		languages.put("Zulu", "Usuku oluhle " + this.userName);
		languages.put("Xhosa", "Mholweni emini nje " + this.userName);
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getGreetCount() {
		return greetCount;
	}
	
	public String greet() {
		greetCount++;
		return languages.get("English");
	}
	
	public String greet(String language) {
		Set<Map.Entry<String, String>> set = languages.entrySet();
		
		for(Map.Entry<String, String> me: set) {
			if(me.getKey().equals(language)) {
				greetCount++;
				return me.getValue();
			} 
		}
		
		return "invalid language";
	}
	
	public void setGreetCount() {
		greetCount = 0;
	}
	
	public String toString() {
		Formatter f = new Formatter();
		String s = f.format("Number of times %s was greeted: %d", userName, greetCount).toString();
		f.close();
		return s;
	}
}