package javaMeasure.control;

import java.sql.*;
import java.util.ArrayList;
import javaMeasure.*;
import javaMeasure.interfaces.*;
import javaMeasure.control.interfaces.IDatabaseController;
import javaMeasure.control.interfaces.ISQLConnector;


public class DataBaseController implements IDatabaseController {

	IUserDAO userDAO;
	IBatchDAO batchDAO;
	IBatchProfileDAO batchProfileDAO;
	IMeasurementDAO measurementDAO;
	
	private ISQLConnector sqlConnector = new SQLConnector();
	//TODO should if there is time extend the amount of exceptions!
	public DataBaseController() {
		super();
		
		userDAO = new UserDAO(sqlConnector);
		batchDAO = new BatchDAO(sqlConnector);
		batchProfileDAO = new BatchProfileDAO(sqlConnector);
		measurementDAO = new MeasurementDAO(sqlConnector);
	}

	//User methods	
	@Override
	public ArrayList<User> getUserList() throws DataBaseException {
		return this.userDAO.getUserList();
	}

	@Override
	public boolean isUserNameInDB(String userName) throws DataBaseException {
		return this.userDAO.isUserNameInDB( userName);
	}

	@Override
	public void addToDB(User user) throws DataBaseException{
		this.userDAO.addToDB(user);

	}

	@Override
	public User getUserFromString(String userString) throws DataBaseException, UserNotFoundException {
		return this.userDAO.getUserFromString(userString);
	}
	//Measurements

	@Override
	public void addToDB(Measurement measurement) throws DataBaseException {
		measurementDAO.addToDB(measurement);
	}

	@Override
	public ArrayList<Measurement> getMeasurementsByBatch(Batch batch) throws DataBaseException {
		return measurementDAO.getMeasurementsByBatch(batch);
	}
	//Batches
	@Override
	public void addToDB(Batch batch) throws DataBaseException {
		batchDAO.addToDB(batch);
	}

	public ArrayList<Batch> getBatches() throws DataBaseException {
		return batchDAO.getBatches();
		}

	public Batch getBatch(String batchname) throws DataBaseException {
		return batchDAO.getBatch(batchname);
	}
	@Override
	public Batch getBatch(int batchId) throws DataBaseException {
		return batchDAO.getBatch(batchId);
	}
	@Override
	public boolean isBatchInDB(String batchName) throws DataBaseException {
		return batchDAO.isBatchInDB(batchName);
	}
	// Batchprofiles

	@Override
	public BatchProfile getBatchProfile(String profileName) throws DataBaseException {
		return batchProfileDAO.getBatchProfile(profileName); 	
	}

	@Override
	public BatchProfile getBatchProfile(int profileId) throws DataBaseException{
		return batchProfileDAO.getBatchProfile(profileId);
	}



	@Override
	public ArrayList<String> getSavedBatchProfilesNames() throws DataBaseException {
		return batchProfileDAO.getSavedBatchProfilesNames();
	}
	@Override
	public int saveBatchProfile(BatchProfile profile) throws DataBaseException {
		return batchProfileDAO.saveBatchProfile(profile);
	}


	//helper method
	@Override
	public void saveBatchSetting(BatchSetting b, int profileID) throws DataBaseException{
		batchProfileDAO.saveBatchSetting(b, profileID);
	}

	@Override
	public void deleteBatchProfile(BatchProfile bp) throws DataBaseException {
		batchProfileDAO.deleteBatchProfile(bp);
	}
}
