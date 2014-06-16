package javaMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javaMeasure.control.interfaces.IDatabaseController.UserNotFoundException;
import javaMeasure.control.interfaces.ISQLConnector;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.interfaces.IUserDAO;

public class UserDAO implements IUserDAO {
	private ISQLConnector sqlConnector;

	public UserDAO(ISQLConnector sqlConnector) {
		this.sqlConnector = sqlConnector;
	}
	//User methods	
	public ArrayList<User> getUserList() throws DataBaseException {
		String query = "SELECT * FROM users ORDER BY username";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
//		if (statement == null) throw new DataBaseException();
		ArrayList<User> users = new ArrayList<>();		
		try {
			ResultSet result = statement.executeQuery();
			while (result.next()){
				String userName = result.getString("username");
				int userID = result.getInt("id");
				users.add(new User(userName, userID));	
			}
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - getUserList(): " + e.getMessage());
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
			if (result.next()){
				return true;
			}
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - isUserNameInDB(String userName): " + e.getMessage());
		}
		
		return false;
	}

	@Override
	public boolean validateUser(User user) throws DataBaseException {
		String query = "SELECT * FROM users WHERE username=? AND password=?";
		ResultSet result = null;
		PreparedStatement pstat = sqlConnector.getPreparedStatement(query);
		try {
			pstat.setString(1, user.getUserName());
			pstat.setString(2, user.getPassWord());
			result = pstat.executeQuery();
			if (result.next()){
				return true;
			}
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - validateUser(User user)" + e.getMessage());
		}
		return false;
	}


	public void addToDB(User user) throws DataBaseException{
		String query = "INSERT INTO users (username,password,admin,active) VALUES (?,?,?,?)";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try {
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassWord());
			statement.setBoolean(3, user.isAdmin());
			statement.setBoolean(4, user.isActive());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - addToDB(User user): " + e.getMessage());
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
			if (result.next()){
				User user = new User(result.getString("username"), result.getInt("id"),result.getString("password"),result.getBoolean("active"),result.getBoolean("admin"));
				return user;
			} else {
				throw new UserNotFoundException();
			}
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - getUserFromString(String userString): " + e.getMessage()); 
		}
	}

	public void updateUser(User change)throws DataBaseException{
		String query = "UPDATE users SET password = ?, admin = ?, active = ?, username = ? WHERE id=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try {
			int ac=0;
			if(change.isActive()){
				ac=1;
			}
			int ad=0;
			if(change.isAdmin()){
				ad=1;
			}
			statement.setString(1, change.getPassWord());
			statement.setInt(2,ad );
			statement.setInt(3,ac );
			statement.setString(4, change.getUserName());
			statement.setInt(5,change.getUserID());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - updateUser(User change): " + e.getMessage());
		}
	}
	
	public boolean canWeRemoveAnotherAdmin()throws DataBaseException{
		String query = "Select * FROM users WHERE active=1 and admin=1";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		ResultSet result;
		try{
			result=statement.executeQuery();
			int i = 0;
			while(result.next()){
				i++;
				if(i>1){
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - canWeRemoveAnotherAdmin(): " + e.getMessage());
		}

	}
	@Override
	public void deleteUser(User user) throws DataBaseException {
		String query = "DELETE FROM users WHERE id=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try{
			statement.setInt(1, user.getUserID());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - deleteUser(User user): " + e.getMessage());
		}
	}
	@Override
	public ArrayList<User> getActiveUserList() throws DataBaseException {
		String query = "SELECT * FROM users WHERE active=1 ORDER BY username";
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
			throw new DataBaseException("SQLException UserDAO - getActiveUserList(): " + e.getMessage());
		}			
		return  users; 
	}
	
	public User getActiveUserFromString(String loginString) throws DataBaseException, UserNotFoundException{
		String query = "SELECT * FROM users WHERE active=1 and username=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		ResultSet result = null;
		//Query DB
		try {
			statement.setString(1, loginString);
			result = statement.executeQuery();
			if (result.next()){
				User user = new User(result.getString("username"), result.getInt("id"),result.getString("password"),result.getBoolean("active"),result.getBoolean("admin"));
				return user;
			} else {
				throw new UserNotFoundException();
			}
		} catch (SQLException e) {
			throw new DataBaseException("SQLException UserDAO - getUserFromString(String userString): " + e.getMessage()); 
		}
	}
}
