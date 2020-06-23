package net.greet;

import java.sql.SQLException;

import net.greet.greeter.Greet;

public class App {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Greet greet = new Greet();
		
		System.out.println(greet.appText());
		greet.runMainAppLoop();
	}

}
