package javaMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javaMeasure.control.SQLConnector;
import javaMeasure.control.interfaces.ISQLConnector;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.interfaces.IBatchDAO;

public class BatchDAO implements IBatchDAO {
	private  ISQLConnector sqlConnector;
	
	public BatchDAO(ISQLConnector sqlConnector) {
		this.sqlConnector = sqlConnector;
	}
	/* (non-Javadoc)
	 * @see javaMeasure.IBatchDAO#getBatches()
	 */
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
	
	@Override
	public ArrayList<String> getBatchNames() throws DataBaseException {
		ArrayList<Batch> batches = getBatches();
		ArrayList<String> batchNames = new ArrayList<>();
		for (Batch batch : batches) {
			batchNames.add(batch.getBatchString());
		}
		return batchNames;
	}
	
	
	/* (non-Javadoc)
	 * @see javaMeasure.IBatchDAO#addToDB(javaMeasure.Batch)
	 */
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

	/* (non-Javadoc)
	 * @see javaMeasure.IBatchDAO#getBatch(java.lang.String)
	 */
	public Batch getBatch(String batchname) throws DataBaseException {
		// TODO needs testing   measurements (elementnumber, measurementtype, measurementvalue, timestamp)
		ArrayList<Measurement> stroke = new ArrayList<>(); // list needed for new way of adding measurements to batch
		ArrayList<Measurement> leak = new ArrayList<>();	// list needed for new way of adding measurements to batch
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
						leak.add(new Measurement(result.getInt("batchid"), result.getInt("elementnumber"), result.getFloat("measurementvalue"), result.getBoolean("verified"), Measurement.MeasurementType.LEAK, result.getLong("timestamp")));						
					}
					else if (result.getString("measurementtype").equals("STROKE")){
						stroke.add(new Measurement(result.getInt("batchid"), result.getInt("elementnumber"), result.getFloat("measurementvalue"), result.getBoolean("verified"), Measurement.MeasurementType.STROKE, result.getLong("timestamp")));
					}
				}
			}
			else {
				throw new DataBaseException();
			}
			returBatch.setMeasurementList(stroke, leak);
			return returBatch;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
	}
	/* (non-Javadoc)
	 * @see javaMeasure.IBatchDAO#isBatchInDB(java.lang.String)
	 */
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
	public Batch getBatch(int batchId) throws DataBaseException {
		//TODO testing
		Batch returBatch;
		Measurement.MeasurementType type; 
		String query = "SELECT * FROM batches WHERE id=?";
		PreparedStatement p = sqlConnector.getPreparedStatement(query);

		try {
			p.setInt(1, batchId);
			ResultSet result = p.executeQuery();
			if(result.next()){
				returBatch = new Batch(result.getInt("id"), result.getString("name"), result.getInt("profile"));


				query = "SELECT * FROM measurements WHERE batchid=?";
				p = sqlConnector.getPreparedStatement(query);
				p.setInt(1, batchId);
				result = p.executeQuery();

				//				if(result.next()){
				//					if(result.getString("measurementtype").equals("LEAK")){
				//						translate = Measurement.MeasurementType.LEAK;						
				//					}
				//					else{
				//						translate = Measurement.MeasurementType.STROKE;
				//					}
				//					Measurement measure = new Measurement(result.getFloat("measurmentvalue"), translate, result.getInt("timestamp"));
				//					measure.setElementNo(result.getInt("elementnumber"));
				//					measure.setBatchID(result.getInt("id"));
				//					returBatch.addMeasurement(measure);
				while(result.next()){
					if(result.getString("measurementtype").equals("LEAK")){
						type = Measurement.MeasurementType.LEAK;						
					}
					else{
						type = Measurement.MeasurementType.STROKE;
					}
					returBatch.addMeasurement(new Measurement(result.getInt("id"), result.getInt("elementnumber"), result.getFloat("measurementvalue"), result.getBoolean("verified"), type, result.getLong("timestamp")));
					//						measure = new Measurement(result.getFloat("measurmentvalue"), translate, result.getInt("timestamp"));
					//						measure.setElementNo(result.getInt("elementnumber"));
					//						measure.setBatchID(result.getInt("id"));
					//						returBatch.addMeasurement(measure);
				}
				//				} else{
				//					throw new DataBaseException();
				//				}

			} else{
				throw new DataBaseException();
			}

			return returBatch;

		} catch (SQLException e1) {
			System.err.println("error in returning batch by batchId");
			e1.printStackTrace();
			throw new DataBaseException();
		}

		//*****************************************************************
		//		Statement statement = sqlConnector.getStatement();
		//		String qString = "SELECT * FROM batches WHERE id = " 
		//				+ batchId + ")";
		//		try {
		//			ResultSet result = statement.executeQuery(qString);
		//			if (result.next()){
		//				return new Batch(result.getInt("id"), result.getString("name"), result.getInt("profile"));
		//			} else {
		//				throw new DataBaseException();
		//			}
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//			throw new DataBaseException();
		//		}
		//*********************************************************************
	}


}
