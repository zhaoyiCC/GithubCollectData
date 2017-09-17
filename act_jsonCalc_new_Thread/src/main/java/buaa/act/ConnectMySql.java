package buaa.act;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySql {
	public static Connection getTCConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://192.168.7.109:3306/topcoder";
		String username = "root";
		String password = "123456";
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
}
