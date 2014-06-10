package javaMeasure.control.interfaces;

import java.util.ArrayList;

import javaMeasure.Batch;
import javaMeasure.BatchProfile;
import javaMeasure.BatchSetting;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public interface INewBatchController {
	
	IMainController getMainController();

	BatchProfile getDefaultBatchProfile() throws DataBaseException;
	
	void setDefaultBatchProfile(String batchProfilename);
	
	BatchProfile getBatchProfile(String profileName) throws DataBaseException;

	ArrayList<String> getSavedBatchProfiles();

	void saveBatchSettingsPressed(String profileName, ArrayList<String> profileSettings);
	
	void deleteBatchProfilePressed(String profileName) throws DataBaseException;
	
//	void editBatchProfilePressed(String profileName) throws DataBaseException;

	void createBatchpressed(String batchString,
			ArrayList<String> profileSettings);

	boolean isBatchInDB(String batchNumber) throws DataBaseException;
	
	void loadBatchSettingsPressed(String profilename);

	void annullerPressed();

	void saveEditedBatchSettingsPressed() throws DataBaseException;
	
	String getActiveBatchName(Batch activeBatch);

}
