package javaMeasure.control.interfaces;
import java.util.ArrayList;

import javaMeasure.*;
import javaMeasure.Measurement.MeasurementType;

public interface IDatabaseController {
	
	//User functionality
	ArrayList<User> getUserList() throws DataBaseException;
	boolean isUserNameInDB(String userName) throws DataBaseException;
	boolean validateUser(User user) throws DataBaseException;
	User getUserFromString(String userString) throws DataBaseException, UserNotFoundException;
	void addToDB(User user) throws DataBaseException;
	void deleteUser(User user) throws DataBaseException;
	public void updateUser(User change)throws DataBaseException;
	
	// Measurements
	void addToDB(Measurement measurement) throws DataBaseException;
	ArrayList<Measurement> getMeasurementsByBatch(Batch batch) throws DataBaseException;
	void deleteMeasurement(int batchID, int elementNumber, MeasurementType type) throws DataBaseException;
	void updateMeasurement(Measurement measurement) throws DataBaseException;
		
	//Batches
	void addToDB(Batch batch) throws DataBaseException;
	ArrayList<Batch> getBatches() throws DataBaseException;
	ArrayList<String> getBatchNames() throws DataBaseException;
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
	
	//Batchsettings
	void addToDB(BatchSetting batchSetting) throws DataBaseException;
	
	void deleteBatchProfile(BatchProfile bp) throws DataBaseException;
	void editBatchProfile(BatchProfile bp) throws DataBaseException; 
	void saveEditedBatchProfile(BatchProfile oldProfile, String newName) throws DataBaseException;
	void deleteBatchSettings(Batch batch) throws DataBaseException;
	void saveBatchSetting(BatchSetting b, int profileID) throws DataBaseException;
	void updateBatchSettings(ArrayList<BatchSetting> settings, int profileID) throws DataBaseException;
//	void updateBatchSettings(BatchSetting b) throws DataBaseException;

	//Exceptions
	@SuppressWarnings("serial")
	public class DataBaseException extends Exception {
		public DataBaseException(String e){
			super(e);
		}
	}
	@SuppressWarnings("serial")
	public class UserNotFoundException extends Exception {

	}


	
//	ArrayList<BatchProfile> getBatchProfiles();
	

	

	
}
