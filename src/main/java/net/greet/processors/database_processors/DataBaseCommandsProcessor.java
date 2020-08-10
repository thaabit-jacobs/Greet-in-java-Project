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
	
	final String jdbcURL = "jdbc:postgresql://localhost:5432/greeter";
	final String addUserSql = "INSERT INTO users(USER_NAME, COUNT) VALUES(?, ?)";
	final String deleteUserSql = "DELETE FROM users WHERE COUNT>0";
	final String getUserSql = "SELECT * FROM users WHERE USER_NAME=?";
	final String cleardbSql = "DELETE FROM users";
	final String updatedbSql2 = "UPDATE users SET COUNT=? WHERE USER_NAME=?";
	final String recordQuery= "SELECT * FROM USERS WHERE EXISTS (SELECT * FROM USERS WHERE USER_NAME=?);";
	final String greetedUsersCountSql = "SELECT COUNT(*) FROM USERS WHERE COUNT>0;";
	final String greetedUsersSql = "SELECT * FROM USERS WHERE COUNT>0;";

	private PreparedStatement pstmt;
	private Statement stmt;

	public DataBaseCommandsProcessor(Connection connection) {
		this.connection = connection;
	}
	
	public void addUserToDataBase(User u) {
		try { 
			Class.forName("org.postgresql.Driver");
			
			pstmt = connection.prepareStatement(addUserSql);

			pstmt.setString(1, u.getUserName());
			pstmt.setInt(2, u.getGreetCount());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	public void clearDataBase() {
		try { 
			Class.forName("org.postgresql.Driver");
			
			stmt = connection.createStatement();
			
			stmt.executeUpdate(cleardbSql);
			stmt.close();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	
	public void deleteGreetedRecordsFromDataBase() {
		try { 
			Class.forName("org.postgresql.Driver");
			
			stmt = connection.createStatement();
			
			stmt.executeUpdate(deleteUserSql);
			stmt.close();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	} 
	
	public boolean checkIfRecordExists(String name) {
		boolean recordExist = false;
		
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); 
		
		try { 
			Class.forName("org.postgresql.Driver");
			
			pstmt = connection.prepareStatement(recordQuery);
			pstmt.setString(1, name);
			
			recordExist = pstmt.executeQuery().next();
			pstmt.close();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return recordExist;
	}
	
	public void updateDataBase(String name) {
		try { 
			Class.forName("org.postgresql.Driver");
			
			if(checkIfRecordExists(name)) {
				PreparedStatement pstmt1 = connection.prepareStatement(getUserSql);
				pstmt1.setString(1, name);
				
				ResultSet rs = pstmt1.executeQuery();
				rs.next();
				
				int greetCount = rs.getInt("COUNT");
				
				String updatedb = "UPDATE USERS SET COUNT=? WHERE USER_NAME=?";
				
				pstmt = connection.prepareStatement(updatedb);
				
				pstmt.setInt(1, ++greetCount);
				pstmt.setString(2, name);
				pstmt.executeUpdate();
				pstmt.close();
			}
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	public int countGreetedUsers() {
		int greetedUserCount = 0;
		
		try { 
			Class.forName("org.postgresql.Driver");
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(greetedUsersCountSql);
			
			rs.next();
			
			greetedUserCount = rs.getInt(1);
			
			stmt.close();
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return greetedUserCount;
	}
	
	public ArrayList<String> getAllGreetedUsers() {
		ArrayList<String> greetedUsers = new ArrayList<>();
		
		try { 
			Class.forName("org.postgresql.Driver");
						
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(greetedUsersSql);
			
			while(rs.next()) {
				greetedUsers.add(rs.getString("USER_NAME") + " has been greeted " + rs.getInt("COUNT") + " time(s)");
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
			try { 
				Class.forName("org.postgresql.Driver");
				
				String greetedUserQuery = "SELECT * FROM USERS WHERE USER_NAME=?;";
				
				PreparedStatement pstmt = connection.prepareStatement(greetedUserQuery);
				pstmt.setString(1, name);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();
				
				return rs.getString("USER_NAME") + " has been greeted " + rs.getInt("COUNT") + " time(s)";
				
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
	
	
	public void clearUserDataBase(String name) {
		try{ 
			Class.forName("org.postgresql.Driver");

			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			
			pstmt = connection.prepareStatement(updatedbSql2);
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
			
	public int getUserGreetCount(String name) {
		int greetCount = 0;
		
		try(Connection con = DriverManager.getConnection(jdbcURL, "postgres", "Password98")) { 
			Class.forName("org.postgresql.Driver");
			
			String userGreetCountQuery = "SELECT * FROM USERS WHERE USER_NAME=?;";
			
			PreparedStatement ps = connection.prepareStatement(userGreetCountQuery);
			ps.setString(1,  name);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			greetCount = rs.getInt("COUNT");
		
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
