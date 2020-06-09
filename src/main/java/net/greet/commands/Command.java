package net.greet.commands;

import java.util.*;

import net.greet.processors.DataBaseCommandsProcessor;

import net.greet.users.*;

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
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		
		for(User u: UserList)
			if(u.getGreetCount() > 0)
				u.setGreetCount();
		
		dbcp.deleteGreetedRecordsFromDataBase();
	}
	
	public void clear(String userName) {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		
		for(User u: UserList) {
			if(u.getUserName().equalsIgnoreCase(userName)) {
				u.setGreetCount();
				dbcp.updateDataBase(u);
			}
		}
	}
	
	public String help() {
		return "\ngreet   [username] [language] \ngreeted \ngreeted [username] \ncounter \nclear \nclear   [username] \n* \nhelp \nexit";
	}
	
	public String languages() {
		return "\nlanguages \n========= \nENGLISH \nAFRIKAANS \nXHOSA \nZULU \nSPANISH \nJAPANESE \nARABIC \nHINDI \nFRENCH \nRUSSIAN";
	}
	
	public ArrayList<User> getUserList(){
		return UserList;
	}

}
