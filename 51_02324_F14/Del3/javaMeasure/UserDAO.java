package javaMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javaMeasure.control.SQLConnector;
import javaMeasure.control.interfaces.ISQLConnector;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.interfaces.IUserDAO;
import javaMeasure.control.interfaces.IDatabaseController.UserNotFoundException;

public class UserDAO implements IUserDAO {
	private ISQLConnector sqlConnector;

	public UserDAO(ISQLConnector sqlConnector) {
		this.sqlConnector = sqlConnector;
	}
	//User methods	
	public ArrayList<User> getUserList() throws DataBaseException {
		String query = "SELECT * FROM users ORDER BY username";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);

		ArrayList<User> users = new ArrayList<>();		
		try {

			ResultSet result = statement.executeQuery();

			while (result.next()){
				String userName = result.getString("username");
				int userID = result.getInt("id");
				users.add(new User(userName, userID));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}			
		return  users; 
	}
	public boolean isUserNameInDB(String userName) throws DataBaseException {
		String query = "SELECT * FROM users WHERE username=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		ResultSet result = null;
		try {
			statement.setString(1, userName);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		try {
			if (result.next()){
				return true;
			}
		} catch (SQLException e) {
			throw new DataBaseException();
		}
		return false;
	}

	
	public void addToDB(User user) throws DataBaseException{
		String query = "INSERT INTO users (username) VALUES (?)";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try {
			statement.setString(1, user.getUserName());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}

	}

	
	public User getUserFromString(String userString) throws DataBaseException, UserNotFoundException {
		String query = "SELECT * FROM users WHERE username=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		ResultSet result = null;
		//Query DB
		try {
			statement.setString(1, userString);
			result = statement.executeQuery();
		} catch (SQLException e) {
			throw new DataBaseException(); 
		}
		//Parse results
		try {
			if (result.next()){
				User user = new User(result.getString("username"), result.getInt("id"));
				return user;
			} 
		} catch (SQLException e) {
			throw new DataBaseException();
		}

		throw new UserNotFoundException();
	}

}
