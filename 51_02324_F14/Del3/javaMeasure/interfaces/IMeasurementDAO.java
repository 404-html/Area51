package javaMeasure.interfaces;

import java.util.ArrayList;

import javaMeasure.Batch;
import javaMeasure.Measurement;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public interface IMeasurementDAO {

	void addToDB(Measurement measurement)
			throws DataBaseException,
			javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

	ArrayList<Measurement> getMeasurementsByBatch(Batch batch)
			throws DataBaseException;

}