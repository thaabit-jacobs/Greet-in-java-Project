package net.greet.processors.database_processors;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import net.greet.users.*;

class DataBaseCommandsProcessorTest {

	@Test
	void shouldAddUserToDataBase() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		
		dbcp.clearDataBase();
		
		dbcp.addUserToDataBase(new User("Thaabit"));
		
		try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "Password98")) {
			Class.forName("org.postgresql.Driver");
			
			String retrieveTable = "SELECT * FROM USERS";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(retrieveTable);
			rs.next();
			
			assertEquals("Thaabit", rs.getString("USER_NAME"));
			
			dbcp.clearDataBase();
			
		}  catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}  
	}
	
	
	@Test
	void shouldUpdateUserGreetCountInDataBase() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		john.greet();
		john.greet();
		
		dbcp.addUserToDataBase(john);
		dbcp.updateDataBase("John");
		
		try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "Password98")) {
			Class.forName("org.postgresql.Driver");
			
			String retrieveTable = "SELECT * FROM USERS";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(retrieveTable);
			rs.next();
			
			assertEquals(4, rs.getInt("COUNT"));
			
			dbcp.clearDataBase();
			
		}  catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
	
	
	@Test
	void shouldReturnTheCountOfUsersGreeted() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		
		dbcp.addUserToDataBase(john);
		dbcp.updateDataBase("John");
		
		try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "Password98")) {
			Class.forName("org.postgresql.Driver");
			
			String retrieveTable = "SELECT COUNT(*) FROM USERS WHERE COUNT>0";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(retrieveTable);
			rs.next();
			
			assertEquals(1, rs.getInt(1));
			
			dbcp.clearDataBase();
			
		}  catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			cne.printStackTrace();
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
	}
		
	@Test
	void shouldReturnListGreetedUsers() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		
		dbcp.addUserToDataBase(john);
			
		assertEquals(true, dbcp.getAllGreetedUsers().toString().equalsIgnoreCase("[John has been greeted 1 time(s)]"));
			
		dbcp.clearDataBase();
	}
	
	@Test
	void shouldReturnGreetedUsers() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		
		dbcp.addUserToDataBase(john);
			
		assertEquals(true, dbcp.queryGreetedUser("John").equalsIgnoreCase("John has been greeted 1 time(s)"));
			
		dbcp.clearDataBase();
	}
	
	@Test
	void shouldReturnUserDoesNotExistForInvalidUserName() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		
		dbcp.addUserToDataBase(john);
		dbcp.updateDataBase("John");
			
		assertEquals(true, dbcp.queryGreetedUser("Mike").equalsIgnoreCase("User does not exist"));
			
		dbcp.clearDataBase();
	}
	
	
}