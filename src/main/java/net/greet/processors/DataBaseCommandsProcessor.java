package net.greet.processors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.greet.users.*;

public class DataBaseCommandsProcessor {
	
	final private String jdbcURL = "jdbc:h2:file:./target/user_db";
	
	private Connection con;
	
	public ArrayList<User> moveDataToList() throws SQLException, ClassNotFoundException{
		Class.forName("org.h2.Driver");
		
		con = DriverManager.getConnection(jdbcURL, "sa", "");
		
		ArrayList<User> users = new ArrayList<User>();
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
		
		int index = 0;
		
		while(rs.next()) {
			users.add(new User(rs.getString("NAME")));
			users.get(index++).setGreetCountAmount(rs.getInt("GREET_COUNT"));
		}
			
		con.close();
		
		return users;
	}
	
	public void addUserToDataBase(User u) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		
		con = DriverManager.getConnection(jdbcURL, "sa", "");
		
		String updateDataBase = "INSERT INTO USERS (ID, NAME, GREET_COUNT) VALUES(?, ?, 0)";
		PreparedStatement pstmt = con.prepareStatement(updateDataBase);
		pstmt.setInt(1, moveDataToList().size() + 1);
		pstmt.setString(2, u.getUserName());
		pstmt.executeUpdate();
		
		con.close();
	}
	
	public void deleteGreetedRecordsFromDataBase() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		con = DriverManager.getConnection(jdbcURL, "sa", "");
		
		String deleteRow = "DELETE FROM USERS WHERE GREET_COUNT>0";
		Statement stmt = con.createStatement();
		
		stmt.executeUpdate(deleteRow);
		
		con.close();
	}
	
	public void updateDataBase(User u) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		con = DriverManager.getConnection(jdbcURL, "sa", "");
		
		String updatedb = "UPDATE USERS SET GREET_COUNT=? WHERE NAME=?";
		
		PreparedStatement pstmt = con.prepareStatement(updatedb);
		
		pstmt.setInt(1, u.getGreetCount());
		pstmt.setString(2, u.getUserName());
		
		pstmt.executeUpdate();
		
		con.close();
	}
}
