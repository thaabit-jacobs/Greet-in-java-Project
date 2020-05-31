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
		ListIterator<User> listItr = UserList.listIterator();
		
		while(listItr.hasNext()) {
			User u = listItr.next();
			
			if(u.getGreetCount() > 0)
				listItr.remove();
		}	
	}
	
	public void clear(String userName) {
		for(User u: UserList) {
			if(u.getUserName().equalsIgnoreCase(userName))
				u.setGreetCount();
		}
	}
	
	public String help() {
		return "Commands: \n<greet name language> \n<greeted> \n<greeted username> \n<counter> \n<clear> \n<clear username> \n<exit> \n<help>";
	}
	
	public ArrayList<User> getUserList(){
		return UserList;
	}
}
