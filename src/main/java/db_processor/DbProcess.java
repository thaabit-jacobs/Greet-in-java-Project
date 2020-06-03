package db_processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import user.User;

public class DbProcess {
	
	private Connection con;
	
	public DbProcess(Connection con) throws SQLException {
		
		this.con = con;

	}
	
	public ArrayList<User> moveDataToList() throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
		
		while(rs.next())
			users.add(new User(rs.getString("NAME"), rs.getInt("GREET_COUNT")));
		
		return users;
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public void persistDataToDb(ArrayList<User> u) throws SQLException {
		int id = 0;
		String updateDataBase = "UPDATE USERS SET GREET_COUNT=? WHERE ID=?";
		PreparedStatement pstmt = con.prepareStatement(updateDataBase);
		
		for(User user: u) {
			pstmt.setInt(1, user.getGreetCount());
			pstmt.setInt(2, ++id);
			pstmt.executeUpdate();

		}
		
	}
}
