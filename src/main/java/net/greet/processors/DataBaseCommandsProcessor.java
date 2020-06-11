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
	
	final String jdbcURL = "jdbc:h2:file:./target/user_db";
	
	public ArrayList<User> moveDataToList() {
		ArrayList<User> users = new ArrayList<User>();
		
		try(Connection con = DriverManager.getConnection(jdbcURL, "sa", "")) {
			Class.forName("org.h2.Driver");
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
			
			int index = 0;
			
			while(rs.next()) {
				users.add(new User(rs.getString("NAME")));
				users.get(index++).setGreetCountAmount(rs.getInt("GREET_COUNT"));
			}
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}
		
		return users;
	}
	
	public void addUserToDataBase(User u) {
		try(Connection con = DriverManager.getConnection(jdbcURL, "sa", "")) { 
			Class.forName("org.h2.Driver");
			
			String updateDataBase = "INSERT INTO USERS (ID, NAME, GREET_COUNT) VALUES(?, ?, 0)";
			PreparedStatement pstmt = con.prepareStatement(updateDataBase);
			pstmt.setInt(1, moveDataToList().size() + 1);
			pstmt.setString(2, u.getUserName());
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}

		
	}
	
	public void deleteGreetedRecordsFromDataBase() {
		try(Connection con = DriverManager.getConnection(jdbcURL, "sa", "")) { 
			Class.forName("org.h2.Driver");
			
			String deleteRow = "DELETE FROM USERS WHERE GREET_COUNT>0";
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(deleteRow);
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}


	}
	
	public void updateDataBase(User u) {
		try(Connection con = DriverManager.getConnection(jdbcURL, "sa", "")) { 
			Class.forName("org.h2.Driver");
			
			String updatedb = "UPDATE USERS SET GREET_COUNT=? WHERE NAME=?";
			
			PreparedStatement pstmt = con.prepareStatement(updatedb);
			
			pstmt.setInt(1, u.getGreetCount());
			pstmt.setString(2, u.getUserName());
			
			pstmt.executeUpdate();
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}
	}
	
	public void clearDataBase() {
		try(Connection con = DriverManager.getConnection(jdbcURL, "sa", "")) { 
			Class.forName("org.h2.Driver");
			
			String updatedb = "DELETE FROM USERS";
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(updatedb);
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}
	}
	
	public boolean checkIfRecordExists(String n) {
		boolean recordExist = false;
		
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "")) { 
			Class.forName("org.h2.Driver");
			
			String recordQuery= "SELECT * FROM USERS WHERE EXISTS (SELECT * FROM USERS WHERE NAME=?);";
			
			PreparedStatement pstmt = con.prepareStatement(recordQuery);
			pstmt.setString(1, n);
			
			recordExist = pstmt.executeQuery().next();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}
		
		return recordExist;
	}

}
