package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "datos";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String username = "root";
	private static String password = "";
	
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	public static Connection connect() {
		
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return conn;
		
	}
	
}