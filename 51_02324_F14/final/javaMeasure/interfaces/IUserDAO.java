package javaMeasure.interfaces;

import java.util.ArrayList;
import javaMeasure.User;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.IDatabaseController.UserNotFoundException;

public interface IUserDAO {
	ArrayList<User> getUserList() throws DataBaseException;
	boolean isUserNameInDB(String userName) throws DataBaseException;
	boolean validateUser(User user) throws DataBaseException;
	User getUserFromString(String userString) throws DataBaseException, UserNotFoundException;
	void addToDB(User user) throws DataBaseException;
}
