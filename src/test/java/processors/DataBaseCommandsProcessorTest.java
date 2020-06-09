package processors;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import net.greet.commands.Command;
import net.greet.processors.CommandsProcessor;
import net.greet.processors.DataBaseCommandsProcessor;
import net.greet.users.*;

class DataBaseCommandsProcessorTest {

	@Test
	void shouldMoveDataFromDbToArrayList() {
		ArrayList<User> ul = new ArrayList<User>();
		ArrayList<User> ul2 = new ArrayList<User>();
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		
		cp.processGreet("Thaabit", "");
		
		ul2 = dbcp.moveDataToList();
		
		assertEquals(true, ul2.get(0).getUserName().equals("Thaabit"));
		
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "")) {
			Class.forName("org.h2.Driver");
			
			String deleteRow = "DELETE FROM USERS WHERE ID=1";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(deleteRow);
			
		} catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		} 
	}
	
	@Test
	void shouldAddUserToDataBase() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		
		dbcp.addUserToDataBase(new User("Thaabit"));
		
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "")) {
			Class.forName("org.h2.Driver");
			
			String retrieveTable = "SELECT * FROM USERS";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(retrieveTable);
			rs.next();
			
			assertEquals(true, rs.getString("NAME").equals("Thaabit"));
			
			String deleteRow = "DELETE FROM USERS WHERE ID=1";
			stmt.executeUpdate(deleteRow);
			
		}  catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}  
	}
	
	@Test
	void shouldUpdateUserGreetCountInDataBase() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		Command com = new Command(ul);
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		
		dbcp.addUserToDataBase(com.getUserList().get(0));
		
		com.getUserList().get(0).greet();
		com.getUserList().get(0).greet();
		com.getUserList().get(0).greet();
		com.getUserList().get(0).greet();
		
		dbcp.updateDataBase(com.getUserList().get(0));
		
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "")) {
			Class.forName("org.h2.Driver");
			
			String retrieveTable = "SELECT * FROM USERS";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(retrieveTable);
			rs.next();
			
			assertEquals(4, rs.getInt("GREET_COUNT"));
			
			String deleteRow = "DELETE FROM USERS WHERE ID=1";
			stmt.executeUpdate(deleteRow);
			
		}  catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}
	}
	
	@Test
	void shouldDeleteRecordFromDataBase() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		Command com = new Command(ul);
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		
		com.getUserList().get(0).greet();
		dbcp.updateDataBase(com.getUserList().get(0));
		
		dbcp.deleteGreetedRecordsFromDataBase();
				
		try(Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "")) {
			Class.forName("org.h2.Driver");
			
			String count = "SELECT COUNT(*) FROM USERS";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(count);
			rs.next();
			
			assertEquals(0, rs.getInt(1));
			
		}  catch(ClassNotFoundException cne) {
			System.out.println(cne + " : Drivers failed to load");
			
		} catch (SQLException se) {
			System.out.println(se + " : Sql query issues or database");
		}

	}
	
}
