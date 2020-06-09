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
	void shouldMoveDataFromDbToArrayList() throws ClassNotFoundException, SQLException {
		ArrayList<User> ul = new ArrayList<User>();
		ArrayList<User> ul2 = new ArrayList<User>();
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		
		cp.processGreet("Thaabit", "");
		
		ul2 = dbcp.moveDataToList();
		
		assertEquals(true, ul2.get(0).getUserName().equals("Thaabit"));
		
		Class.forName("org.h2.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "");
		String deleteRow = "DELETE FROM USERS WHERE ID=1";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(deleteRow);
		con.close();
	}
	
	@Test
	void shouldAddUserToDataBase() throws ClassNotFoundException, SQLException {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		
		dbcp.addUserToDataBase(new User("Thaabit"));
		
		Class.forName("org.h2.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "");
		String retrieveTable = "SELECT * FROM USERS";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(retrieveTable);
		rs.next();
		
		assertEquals(true, rs.getString("NAME").equals("Thaabit"));
		
		String deleteRow = "DELETE FROM USERS WHERE ID=1";
		stmt.executeUpdate(deleteRow);
		
		con.close();
	}
	
	@Test
	void shouldUpdateUserGreetCountInDataBase() throws ClassNotFoundException, SQLException {
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
		
		Class.forName("org.h2.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "");
		String retrieveTable = "SELECT * FROM USERS";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(retrieveTable);
		rs.next();
		
		assertEquals(4, rs.getInt("GREET_COUNT"));
		
		String deleteRow = "DELETE FROM USERS WHERE ID=1";
		stmt.executeUpdate(deleteRow);
		
		con.close();
	}
	
	@Test
	void shouldDeleteRecordFromDataBase() throws ClassNotFoundException, SQLException {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		Command com = new Command(ul);
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		
		com.getUserList().get(0).greet();
		dbcp.updateDataBase(com.getUserList().get(0));
		
		dbcp.deleteGreetedRecordsFromDataBase();
		
		Class.forName("org.h2.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:h2:file:./target/user_db", "sa", "");
		String count = "SELECT COUNT(*) FROM USERS";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(count);
		rs.next();
		
		assertEquals(0, rs.getInt(1));
		
		con.close();
	}
	
}
