package javaMeasure.control.interfaces;

import java.util.ArrayList;

import javaMeasure.BatchProfile;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public interface INewBatchController {
	
	IMainController getMainController();

	BatchProfile getDefaultBatchProfile() throws DataBaseException;
	
	void setDefaultBatchProfile(String batchProfilename);
	
	BatchProfile getBatchProfile(String profileName) throws DataBaseException;

	ArrayList<String> getSavedBatchProfiles();

	/**
	 * @param profileName is required as seperate parameter because it is used to fx identify batch therefore not always a part of the settings 
	 * @param profileSettings Array of Strings but will be parsed to ArrayList<BatchSetting> the normal values and tolerance values are validated before going further
	 * Batch is created with profileName and the belonging profileSettings.
	 * The whole batch is saved in database if values are succesfully validated and the batch is set as active batch in BatchMeasureController 
	 */
	void saveBatchSettingsPressed(String profileName, ArrayList<String> profileSettings);
	
	void deleteBatchProfilePressed(String profileName) throws DataBaseException;

	void createBatchpressed(String batchString,
			ArrayList<String> profileSettings);

	boolean isBatchInDB(String batchNumber) throws DataBaseException;
	
	void loadBatchSettingsPressed(String profilename);

	void cancelPressed();

	void saveEditedBatchSettingsPressed(ArrayList<String> profileSettings) throws DataBaseException;

}
