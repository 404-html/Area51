package javaMeasure.control;
import java.sql.*;

import javaMeasure.DataBaseException;
import javaMeasure.control.interfaces.ISQLConnector;

public class TestMySQLConnection {
	static ISQLConnector sqlConnector;
	static Connection connection;

	public static void main(String[] args) {
		sqlConnector = new SQLConnector();
		Statement statement = null;
		try {
			statement = sqlConnector.getStatement();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultset = null;
		try {
			resultset = statement.executeQuery("select * from users");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = null;
		System.out.println(resultset);

		try {
			while(resultset.next()){
			username = resultset.getString("username");
			System.out.println("usernames: " + username);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
