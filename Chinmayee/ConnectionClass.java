package project1;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionClass {
	Connection conn;
	public Connection getConnection(){
		return conn;
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
