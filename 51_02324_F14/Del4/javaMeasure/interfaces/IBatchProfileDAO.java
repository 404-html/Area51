package javaMeasure.interfaces;

import java.util.ArrayList;

import javaMeasure.BatchProfile;
import javaMeasure.BatchSetting;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public interface IBatchProfileDAO {

	BatchProfile getBatchProfile(String profileName) throws DataBaseException;

	BatchProfile getBatchProfile(int profileId) throws DataBaseException;

	ArrayList<String> getSavedBatchProfilesNames() throws DataBaseException;

	int saveBatchProfile(BatchProfile profile) throws DataBaseException;

	void saveBatchSetting(BatchSetting b, int profileID)
			throws DataBaseException;

	void deleteBatchProfile(BatchProfile bp) throws DataBaseException;

}