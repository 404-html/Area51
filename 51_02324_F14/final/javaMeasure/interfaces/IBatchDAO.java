package javaMeasure.interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;

import javaMeasure.Batch;
import javaMeasure.BatchSetting;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public interface IBatchDAO {

	ArrayList<Batch> getBatches() throws DataBaseException;
	
	ArrayList<String> getBatchNames() throws DataBaseException;

	void addToDB(Batch batch) throws DataBaseException;
	
	void addtoDB(BatchSetting batchSetting) throws DataBaseException;

	Batch getBatch(String batchname) throws DataBaseException;

	boolean isBatchInDB(String batchName) throws DataBaseException;
	
	Batch getBatch(int batchId) throws DataBaseException;
	
	void deleteBatchSettings(Batch batch) throws DataBaseException;

	void updateBatchSettings(ArrayList<BatchSetting> settings, int profileID) throws DataBaseException;

	void updateBatch(Batch activeBatch) throws DataBaseException;

	ArrayList<Batch> getBatches(String partialBatchName, String fieldName,
			Timestamp startDate, Timestamp endDate) throws DataBaseException;

}