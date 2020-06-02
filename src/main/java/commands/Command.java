package commands;

import java.util.*;
import user.User;

public class Command {
	
	private ArrayList<User> UserList;
	
	public Command(ArrayList<User> UserList) {
		
		this.UserList = UserList;
		
	}
	
	public ArrayList<User> greeted() {
		ArrayList<User> greetedUsers = new ArrayList<User>();
		
		for(User u: UserList)
			if(u.getGreetCount() > 0)
				greetedUsers.add(u);
		
		return greetedUsers;
	}
	
	public String greeted(String userName) {
		for(User u: UserList)
			if(u.getUserName().equalsIgnoreCase(userName))
				return u.toString();
		
		return "";
	}
	
	public int counter() {
		int count = 0;
		
		for(User u: UserList)
			if(u.getGreetCount() > 0)
				count++;
		
		return count;
	}
	
	public void clear() {
		for(User u: UserList)
			if(u.getGreetCount() > 0)
				u.setGreetCount();
	}
	
	public void clear(String userName) {
		for(User u: UserList) {
			if(u.getUserName().equalsIgnoreCase(userName))
				u.setGreetCount();
		}
	}
	
	public String help() {
		return "\ngreet   [username] [language] \ngreeted \ngreeted [username] \ncounter \nclear \nclear   [username] \nhelp \nexit";
	}
	
	public ArrayList<User> getUserList(){
		return UserList;
	}

}
