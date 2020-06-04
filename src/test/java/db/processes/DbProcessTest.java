package db.processes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import user.User;

class DbProcessTest {

	@Test
	void shouldMoveDataFromDbToArrayList() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		final String jdbcURL = "jdbc:h2:file:./target/user_db";
		Connection con = DriverManager.getConnection(jdbcURL, "sa", "");
		DbProcess dbprocess = new DbProcess(con);
		ArrayList<User> user = new ArrayList<User>();
		
		user = dbprocess.moveDataToList();
		
		assertEquals(true, user.get(0).getUserName().equals("Thaabit"));
		
		dbprocess.getConnection().close();
	}
	
	@Test
	void shouldMoveDataFromArrayListToDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		final String jdbcURL = "jdbc:h2:file:./target/user_db";
		Connection con = DriverManager.getConnection(jdbcURL, "sa", "");
		DbProcess dbprocess = new DbProcess(con);
		ArrayList<User> user = new ArrayList<User>();
		
		user.add(new User("Thaabit", 2));
		
		dbprocess.moveDataToDataDase(user);
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
		
		rs.next();
		
		assertEquals(2, rs.getInt("GREET_COUNT"));
		
		stmt.executeUpdate("UPDATE USERS SET GREET_COUNT=0 WHERE ID=1");
		
		dbprocess.getConnection().close();
	}
}
