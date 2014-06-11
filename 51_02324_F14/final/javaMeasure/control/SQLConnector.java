package javaMeasure.control;

import java.sql.*;

import javaMeasure.PropertyHelper;
import javaMeasure.control.interfaces.ISQLConnector;
import javaMeasure.control.interfaces.ISQLConnector.DBConnectFailedException;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public class SQLConnector implements ISQLConnector {
	private Connection connection;
	private String server;
	private String port;
	private String database;
	private String username;
	private String password;

	public SQLConnector(){
		server = PropertyHelper.readFromProperty("server");
		port = PropertyHelper.readFromProperty("port");
		database = PropertyHelper.readFromProperty("database");
		username = PropertyHelper.readFromProperty("username");
		password = PropertyHelper.readFromProperty("password");
		loadDriver();
	}

	public SQLConnector(String server, String port,
			String database, String username, String password){
		this.server = server;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		loadDriver(); 
	}

	/* (non-Javadoc)
	 * @see javaMeasure.control.IDBConnector#connect()
	 */
	public Statement getStatement() throws DataBaseException  {
		Statement statement = null;
		try {
			statement = getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		} catch (DBConnectFailedException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		return statement;
	}
	public PreparedStatement getPreparedStatement(String sqlStatement) throws DataBaseException{
		return getPreparedStatement(sqlStatement, -1);
	}
	
	public PreparedStatement getPreparedStatement(String sqlStatement, int extraArg) throws DataBaseException{
		PreparedStatement prepStatement = null;
		try{
			if(extraArg != -1)
				prepStatement = getConnection().prepareStatement(sqlStatement, extraArg);
			else
			prepStatement = getConnection().prepareStatement(sqlStatement);
		} catch (SQLException e){
			e.printStackTrace();
		} catch (DBConnectFailedException e) {
			e.printStackTrace();
		}
		return prepStatement;
	}
	
	
	private Connection getConnection() throws DBConnectFailedException {
		if (connection == null){
			this.connection = connect();
			return this.connection;
		}
		boolean isAlive = false;
		try {
			isAlive = this.connection.isValid(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (isAlive){
		return this.connection;
		} 
		return connect();

	}
	
	private Connection connect() throws DBConnectFailedException {
		if (!sqlConnect()) throw new DBConnectFailedException();
		return this.connection;
	}

	private boolean loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("loaded mySQLdriver");
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("No mySQL driver found!");
			e.printStackTrace();
		}
		return false;
	}

	private boolean sqlConnect()  {
		try {
			this.connection = DriverManager
					.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database , username,password);
			System.out.println("connected");
			return true;
		} catch (SQLException e) {
			System.err.println("Connection to DB failed!");
			e.printStackTrace();
		}
		return false;
	}

	

}
