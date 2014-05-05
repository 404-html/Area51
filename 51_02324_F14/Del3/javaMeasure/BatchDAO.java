package javaMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javaMeasure.control.SQLConnector;
import javaMeasure.control.interfaces.ISQLConnector;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public class BatchDAO {
	private static ISQLConnector sqlConnector = new SQLConnector();
	
	public ArrayList<Batch> getBatches() throws DataBaseException {
		//TODO needs testing 
		String query = "SELECT * FROM batches";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		ResultSet result = null;
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		ArrayList<Batch> batches = new ArrayList<Batch>();
		try {
			while (result.next()){
				Batch b = new Batch(result.getInt("id"), result.getString("name"), result.getInt("profile"));
				batches.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		return batches;
	}
	public void addToDB(Batch batch) throws DataBaseException {
		String query = "INSERT INTO batches (name, profile) VALUES (?,?)";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try {
			statement.setString(1, batch.getBatchString());
			statement.setInt(2, batch.getProfileID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
	}

	public Batch getBatch(String batchname) throws DataBaseException {
		// TODO needs testing   measurements (elementnumber, measurementtype, measurementvalue, timestamp)
		Batch returBatch;
		Measurement.MeasurementType type; 
		String query1 = "SELECT * FROM batches WHERE name=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query1);
		try {
			statement.setString(1, batchname);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				returBatch = new Batch(result.getInt("id"), result.getString("name"), result.getInt("profile"));

				String query2 = "SELECT * FROM measurements WHERE batchid=?";
				PreparedStatement p = sqlConnector.getPreparedStatement(query2);
				p.setInt(1, result.getInt("id"));
				result = p.executeQuery();


				while(result.next()){
					if(result.getString("measurementtype").equals("LEAK")){
						type = Measurement.MeasurementType.LEAK;						
					}
					else{
						type = Measurement.MeasurementType.STROKE;
					}
					returBatch.addMeasurement(new Measurement(result.getInt("batchid"), result.getInt("elementnumber"), result.getFloat("measurementvalue"), type, result.getLong("timestamp")));


				}
			}
			else {
				throw new DataBaseException();
			}

			return returBatch;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
	}
	public boolean isBatchInDB(String batchName) throws DataBaseException {
		String qString = "SELECT * FROM batches WHERE name =?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(qString);
		try {
			statement.setString(1, batchName);
			ResultSet result = statement.executeQuery();
			if (result.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		return false;
	}
	

}
