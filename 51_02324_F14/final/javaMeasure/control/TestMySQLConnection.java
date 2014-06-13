package javaMeasure.control;
import java.sql.*;

import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.ISQLConnector;

public class TestMySQLConnection {
	static ISQLConnector sqlConnector;
	static Connection connection;

	public static void main(String[] args) {
		sqlConnector = new SQLConnector();
		String query = "select * from users";
		PreparedStatement statement = null;
		try {
			statement = sqlConnector.getPreparedStatement(query);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultset = null;
		try {
			resultset = statement.executeQuery();
		} catch (SQLException e) {
			System.err.println("failed to executeQuery()");
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
