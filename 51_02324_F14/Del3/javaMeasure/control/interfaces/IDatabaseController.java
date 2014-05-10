package javaMeasure.control.interfaces;
import java.util.ArrayList;

import javaMeasure.*;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public interface IDatabaseController {
	
	//User functionality
	ArrayList<User> getUserList() throws DataBaseException;
	boolean isUserNameInDB(String userName) throws DataBaseException;
	User getUserFromString(String userString) throws DataBaseException, UserNotFoundException;
	void addToDB(User user) throws DataBaseException;
	
	// MeasureMents
	void addToDB(Measurement measurement) throws DataBaseException;
	ArrayList<Measurement> getMeasurementsByBatch(Batch batch) throws DataBaseException;
		
	//Batches
	void addToDB(Batch batch) throws DataBaseException;
	ArrayList<Batch> getBatches() throws DataBaseException;	
	public Batch getBatch(String batchName) throws DataBaseException;
	public Batch getBatch(int batchID) throws DataBaseException;
	boolean isBatchInDB(String batchName) throws DataBaseException;
	
	//Batchprofiles
		// for dropdown list
	ArrayList<String> getSavedBatchProfilesNames() throws DataBaseException;
		// sorted after id 
	BatchProfile getBatchProfile(String profileName) throws DataBaseException;
	BatchProfile getBatchProfile(int profileId) throws DataBaseException;
		// use index as id 
	int saveBatchProfile(BatchProfile profile) throws DataBaseException; // returns id of saved batchprofile
	
	
	void deleteBatchProfile(BatchProfile bp) throws DataBaseException;
	
	//Exceptions
	@SuppressWarnings("serial")
	public class DataBaseException extends Exception {
	}
	@SuppressWarnings("serial")
	public class UserNotFoundException extends Exception {

	}
	void saveBatchSetting(BatchSetting b, int profileID)
			throws DataBaseException;
	
//	ArrayList<BatchProfile> getBatchProfiles();
	

	

	
}
