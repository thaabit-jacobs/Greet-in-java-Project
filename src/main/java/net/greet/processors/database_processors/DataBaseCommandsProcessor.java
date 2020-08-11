package net.greet.processors.database_processors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.greet.users.*;

public class DataBaseCommandsProcessor {

	private final Connection connection;

	final String addUserSql = "INSERT INTO PERSON(FIRST_NAME, COUNTER) VALUES(?, ?)";
	final String deleteUserSql = "DELETE FROM PERSON WHERE COUNTER>0";
	final String getUserSql = "SELECT * FROM PERSON WHERE FIRST_NAME=?";
	final String cleardbSql = "DELETE FROM PERSON";
	final String updatedbSql2 = "UPDATE PERSON SET COUNTER=? WHERE FIRST_NAME=?";
	final String recordQuery = "SELECT * FROM PERSON WHERE EXISTS (SELECT * FROM PERSON WHERE FIRST_NAME=?);";
	final String greetedUsersCountSql = "SELECT COUNT(*) FROM PERSON WHERE COUNTER>0;";
	final String greetedUsersSql = "SELECT * FROM PERSON WHERE COUNTER>0;";

	private PreparedStatement pstmt;
	private Statement stmt;

	public DataBaseCommandsProcessor(Connection connection) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Drivers not loaded");
			e.printStackTrace();
		}
		this.connection = connection;
	}
	
	public void addUserToDataBase(User u) {
		try { 
			pstmt = connection.prepareStatement(addUserSql);

			pstmt.setString(1, u.getUserName());
			pstmt.setInt(2, u.getGreetCount());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	public void clearDataBase() {
		try { 
			stmt = connection.createStatement();
			
			stmt.executeUpdate(cleardbSql);
			stmt.close();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	
	public void deleteGreetedRecordsFromDataBase() {
		try { 
			stmt = connection.createStatement();
			
			stmt.executeUpdate(deleteUserSql);
			stmt.close();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	} 
	
	public boolean checkIfRecordExists(String name) {
		boolean recordExist = false;
		
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); 
		
		try { 
			pstmt = connection.prepareStatement(recordQuery);
			pstmt.setString(1, name);
			
			recordExist = pstmt.executeQuery().next();
			pstmt.close();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return recordExist;
	}
	
	public void updateDataBase(String name) {
		try { 
			if(checkIfRecordExists(name)) {
				PreparedStatement pstmt1 = connection.prepareStatement(getUserSql);
				pstmt1.setString(1, name);
				
				ResultSet rs = pstmt1.executeQuery();
				rs.next();
				
				int greetCount = rs.getInt("COUNTER");
				
				String updatedb = "UPDATE PERSON SET COUNTER=? WHERE FIRST_NAME=?";
				
				pstmt = connection.prepareStatement(updatedb);
				
				pstmt.setInt(1, ++greetCount);
				pstmt.setString(2, name);
				pstmt.executeUpdate();
				pstmt.close();
			}
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	public int countGreetedUsers() {
		int greetedUserCount = 0;
		
		try { 
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(greetedUsersCountSql);
			
			rs.next();
			
			greetedUserCount = rs.getInt(1);
			
			stmt.close();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return greetedUserCount;
	}
	
	public ArrayList<String> getAllGreetedUsers() {
		ArrayList<String> greetedUsers = new ArrayList<>();
		
		try { 				
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(greetedUsersSql);
			
			while(rs.next()) {
				greetedUsers.add(rs.getString("FIRST_NAME") + " has been greeted " + rs.getInt("COUNTER") + " time(s)");
			}
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return greetedUsers;
	}
	
	public String queryGreetedUser(String name) {
		if(checkIfRecordExists(name)) {
			try { 		
				String greetedUserQuery = "SELECT * FROM PERSON WHERE FIRST_NAME=?;";
				
				PreparedStatement pstmt = connection.prepareStatement(greetedUserQuery);
				pstmt.setString(1, name);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();
				
				return rs.getString("FIRST_NAME") + " has been greeted " + rs.getInt("COUNTER") + " time(s)";
				
			} catch (SQLException se) {
				System.out.println(se + " : Sql query issues or database");
				se.printStackTrace();
			}
		}
		
		return "User does not exist";
	}
	
	
	public void clearUserDataBase(String name) {
		try{ 
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			
			pstmt = connection.prepareStatement(updatedbSql2);
			pstmt.setInt(1, 0);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
			
	public int getUserGreetCount(String name) {
		int greetCount = 0;
		
		try { 	
			String userGreetCountQuery = "SELECT * FROM PERSON WHERE FIRST_NAME=?;";
			
			PreparedStatement ps = connection.prepareStatement(userGreetCountQuery);
			ps.setString(1,  name);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			greetCount = rs.getInt("COUNTER");
		
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return greetCount;
	}

}
