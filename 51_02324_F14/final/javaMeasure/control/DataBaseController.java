package javaMeasure.control;

import java.util.ArrayList;

import javaMeasure.*;
import javaMeasure.Measurement.MeasurementType;
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
	public boolean validateUser(User user) throws DataBaseException {
		return this.userDAO.validateUser(user);
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
	
	public void updateMeasurement(Measurement measurement) throws DataBaseException {
		measurementDAO.updateMeasurement(measurement);
	}

	@Override
	public void addToDB(BatchSetting batchSetting) throws DataBaseException {
		batchDAO.addtoDB(batchSetting);
		
	}
	
	@Override
	public ArrayList<Measurement> getMeasurementsByBatch(Batch batch) throws DataBaseException {
		return measurementDAO.getMeasurementsByBatch(batch);
	}
	
	@Override
	public void deleteMeasurement(int batchID, int elementNumber, MeasurementType type) throws DataBaseException {
		measurementDAO.deleteMeasurement(batchID, elementNumber, type);
	}
	//Batches
	@Override
	public void addToDB(Batch batch) throws DataBaseException {
		batchDAO.addToDB(batch);
	}

	public ArrayList<Batch> getBatches() throws DataBaseException {
		return batchDAO.getBatches();
		}
	
	@Override
	public ArrayList<String> getBatchNames() throws DataBaseException {
		return batchDAO.getBatchNames();
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
	public void editBatchProfile(BatchProfile bp) throws DataBaseException {
		((IDatabaseController) batchProfileDAO).editBatchProfile(bp);
	}
	

	@Override
	public void saveEditedBatchProfile(BatchProfile oldProfile, String newName)
			throws DataBaseException {
		
		
	}

	@Override
	public void updateUser(User change) throws DataBaseException {
		this.userDAO.updateUser(change);
		
	}

	@Override
	public void deleteUser(User user) throws DataBaseException {
		this.userDAO.deleteUser(user);
	}

	@Override
	public void deleteBatchSettings(Batch batch) throws DataBaseException {
		 batchDAO.deleteBatchSettings(batch);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBatchSettings(ArrayList<BatchSetting> settings, int profileID)
			throws DataBaseException {
		batchDAO.updateBatchSettings(settings, profileID);
		
	}

	@Override
	public boolean canWeRemoveAnotherAdmin() throws DataBaseException {
		return this.userDAO.canWeRemoveAnotherAdmin();
	}

	@Override
	public void updateBatch(Batch activeBatch) throws DataBaseException {
		batchDAO.updateBatch(activeBatch);
		
	}

	
}
