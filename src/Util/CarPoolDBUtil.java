package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CarPoolDBUtil {

	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String username = "root";
			String password = "Passtrics28!";
			String url = "jdbc:mysql://localhost:3306/SherPool?verifyServerCertificate=false&useSSL=true";
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
