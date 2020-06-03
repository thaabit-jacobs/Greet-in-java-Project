package db_processor;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
