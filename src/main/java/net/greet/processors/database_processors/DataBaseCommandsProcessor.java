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
	
	final String jdbcURL = "jdbc:h2:file:./target/user_database";
	final String addUserSql = "INSERT INTO USERS(NAME, GREET_COUNT) VALUES(?, ?)";
	final String deleteUserSql = "DELETE FROM USERS WHERE GREET_COUNT>0";
	final String getUserSql = "SELECT * FROM USERS WHERE NAME=?";
	final String updatedbSql = "DELETE FROM USERS";
	final String updatedbSql2 = "UPDATE USERS SET GREET_COUNT=? WHERE NAME=?";
	final String recordQuery= "SELECT * FROM USERS WHERE EXISTS (SELECT * FROM USERS WHERE NAME=?);";

	private PreparedStatement pstmt;
	private Statement stmt;

	public void addUserToDataBase(User u) {
		try(Connection con = DriverManager.getConnection(jdbcURL, "admin", "1234")) { 
			Class.forName("org.h2.Driver");
			
			pstmt = con.prepareStatement(addUserSql);

			pstmt.setString(1, u.getUserName());
			pstmt.setInt(2, u.getGreetCount());
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	public void deleteGreetedRecordsFromDataBase() {
		try(Connection con = DriverManager.getConnection(jdbcURL, "admin", "1234")) { 
			Class.forName("org.h2.Driver");
			
			stmt = con.createStatement();
			
			stmt.executeUpdate(deleteUserSql);
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}


	} 
	
	public void updateDataBase(String name) {
		try(Connection con = DriverManager.getConnection(jdbcURL, "admin", "1234")) { 
			Class.forName("org.h2.Driver");
			
			PreparedStatement pstmt1 = con.prepareStatement(getUserSql);
			pstmt1.setString(1, name);
			
			ResultSet rs = pstmt1.executeQuery();
			rs.next();
			
			int greetCount = rs.getInt("GREET_COUNT");
			
			String updatedb = "UPDATE USERS SET GREET_COUNT=? WHERE NAME=?";
			
			pstmt = con.prepareStatement(updatedb);
			
			pstmt.setInt(1, ++greetCount);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	public void clearDataBase() {
		try(Connection con = DriverManager.getConnection(jdbcURL, "admin", "1234")) { 
			Class.forName("org.h2.Driver");
			
			stmt = con.createStatement();
			
			stmt.executeUpdate(updatedbSql);
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	public void clearUserDataBase(String name) {
		try(Connection con = DriverManager.getConnection(jdbcURL, "admin", "1234")) { 
			Class.forName("org.h2.Driver");

			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			
			pstmt = con.prepareStatement(updatedbSql2);
			pstmt.setInt(1, 0);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	public boolean checkIfRecordExists(String n) {
		boolean recordExist = false;
		
		n = n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase(); 
		
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_database", "admin", "1234")) { 
			Class.forName("org.h2.Driver");
			
			pstmt = con.prepareStatement(recordQuery);
			pstmt.setString(1, n);
			
			recordExist = pstmt.executeQuery().next();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return recordExist;
	}
	
	public int countGreetedUsers() {
		int greetedUserCount = 0;
		
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_database", "admin", "1234")) { 
			Class.forName("org.h2.Driver");
			
			String greetedUsersQuery = "SELECT COUNT(*) FROM USERS WHERE GREET_COUNT>0;";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(greetedUsersQuery);
			
			rs.next();
			
			greetedUserCount = rs.getInt(1);
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return greetedUserCount;
	}
	
	public ArrayList<String> queryGreetedUsers() {
		ArrayList<String> greetedUsers = new ArrayList<>();
		
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_database", "admin", "1234")) { 
			Class.forName("org.h2.Driver");
			
			String greetedUsersQuery = "SELECT * FROM USERS WHERE GREET_COUNT>0;";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(greetedUsersQuery);
			
			while(rs.next()) {
				greetedUsers.add(rs.getString("NAME") + " has been greeted " + rs.getInt("GREET_COUNT") + " time(s)");
			}
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return greetedUsers;
	}
	
	public String queryGreetedUser(String name) {
		if(checkIfRecordExists(name)) {
			try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_database", "admin", "1234")) { 
				Class.forName("org.h2.Driver");
				
				String greetedUserQuery = "SELECT * FROM USERS WHERE NAME=?;";
				
				PreparedStatement pstmt = con.prepareStatement(greetedUserQuery);
				pstmt.setString(1, name);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();
				
				return rs.getString("NAME") + " has been greeted " + rs.getInt("GREET_COUNT") + " time(s)";
				
			} catch(ClassNotFoundException cne) {
				System.out.println(cne + " : Drivers failed to load");
				cne.printStackTrace();
				
			} catch (SQLException se) {
				System.out.println(se + " : Sql query issues or database");
				se.printStackTrace();
			}
		}
		
		return "User does not exist";
	}
	
	public int getUserGreetCount(String name) {
		int greetCount = 0;
		
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_database", "admin", "1234")) { 
			Class.forName("org.h2.Driver");
			
			String userGreetCountQuery = "SELECT * FROM USERS WHERE NAME=?;";
			
			PreparedStatement ps = con.prepareStatement(userGreetCountQuery);
			ps.setString(1,  name);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			greetCount = rs.getInt("GREET_COUNT");
		
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return greetCount;
	}

}
