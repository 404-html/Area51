package javaMeasure.control;

import java.sql.*;

import javaMeasure.PropertyHelper;
import javaMeasure.control.interfaces.ISQLConnector;
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
			throw new DataBaseException("SQL exception - getPreparedStatement: " + e.getMessage());
		} catch (DBConnectFailedException e) {
			throw new DataBaseException("DatabaseConnectionException - SQLConnector.getPreparedStatement(): " + e.getMessage());
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
			throw new DBConnectFailedException();
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
			System.err.println("No mySQL driver found!" + e.getMessage());
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
		}
		return false;
	}

	

}
