package javaMeasure.interfaces;

import java.util.ArrayList;

import javaMeasure.Batch;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public interface IBatchDAO {

	ArrayList<Batch> getBatches() throws DataBaseException;

	void addToDB(Batch batch) throws DataBaseException;

	Batch getBatch(String batchname) throws DataBaseException;

	boolean isBatchInDB(String batchName) throws DataBaseException;
	
	Batch getBatch(int batchId) throws DataBaseException;

}