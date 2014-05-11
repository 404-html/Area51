package JUnitTest;

import static org.junit.Assert.fail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javaMeasure.User;
import javaMeasure.control.DataBaseController;
import javaMeasure.control.SQLConnector;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.IDatabaseController.UserNotFoundException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDatabaseConnector
{
	private static SQLConnector sqlConn;
	private static DataBaseController dataCon;
	private static String username ="NotAUserName" ;
	@BeforeClass
	public static void doBeforeClass()
	{
		sqlConn = new SQLConnector();
		dataCon = new DataBaseController();
	}
	
	@AfterClass
	public static void doAfterClass()
	{
		
	}
	
	@Test
	public void testUserDatabaseInteraction()
	{
		User user = new User(username, 987654321);
		try
		{
			dataCon.addToDB(user);
		}
		catch (DataBaseException e)
		{
			fail();
		}
		
		ArrayList<User> userList = null;
		
		try
		{
			userList = dataCon.getUserList();
		}
		catch (DataBaseException e)
		{
			e.printStackTrace();
			fail();
		}
		boolean foundTheUser = false;
		for (int i = 0; i < userList.size(); i++)
		{
			System.out.println(userList.get(i).getUserName());
			if(userList.get(i).getUserName().equals(username))
				foundTheUser = true;
		}
		Assert.assertTrue(foundTheUser);
		
		User u = null;
		try
		{
			u = dataCon.getUserFromString(username);
		}
		catch (DataBaseException | UserNotFoundException e)
		{
			e.printStackTrace();
			fail();
		}
		Assert.assertTrue(u.getUserName().equals(username));
		
		try
		{
			String query = "DELETE FROM users WHERE username=?";
			PreparedStatement statement = sqlConn.getPreparedStatement(query);
			statement.setString(1, username);
			statement.executeUpdate();
		}
		catch (DataBaseException e)
		{
			fail();
		}
		catch (SQLException e)
		{
			fail();
		}
	}
	
//	@Test
//	public void testAddMeasurementToDB()
//	{
//		Measurement m = new Measurement(0.678f, MeasurementType.STROKE, 786587658);
//	}
//	
//	@Test
//	public void testGetMeasurementsByBatch()
//	{
//		
//	}
//	
//	@Test
//	public void testAddMeasurementToDB()
//	{
//		
//	}
//	
//	@Test
//	public void testGetSpecificBatch()
//	{
//		
//	}
//	
//	@Test
//	public void testGetAllBatches()
//	{
//		
//	}
}
