package javaMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
		//returns all batches with "" in name - In effect All batches
		return getBatches(null,null,null,null);
	}

	/**
	 * Overloaded for getting batches with partial names
	 * @param partialBatchName String matching partial batch name 
	 * @return Collection of batches
	 * @throws DataBaseException
	 */
	public ArrayList<Batch> getBatches(String partialBatchName, String fieldName, Timestamp startDate, Timestamp endDate) throws DataBaseException {
		//TODO needs testing AND COMMENTING!!!
		ArrayList<Batch> batches = new ArrayList<Batch>();
		String query = "SELECT * FROM batches WHERE UPPER(name) LIKE UPPER(?) AND ";
		
		if(fieldName==null){
			fieldName="created_date";
		}
		
		query = query + fieldName +">=(?) AND ";
		query = query + "(" + fieldName + (fieldName=="Approved_Date" && endDate==null ?"<=(?) OR Approved_Date IS NULL)" :"<=(?)) ");
		
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		ResultSet result = null;
		Batch b;
		try {
			if(partialBatchName == null){
				partialBatchName = "";
			}
			statement.setString(1, "%" + partialBatchName + "%");
			statement.setTimestamp(2, startDate==null? new Timestamp(0): startDate);
			
			//opretter nyt date objekt med dags dato
			java.util.Date date= new java.util.Date();
			//hvis enddate = null, indsæt dags dato
			statement.setTimestamp(3, endDate==null? new Timestamp(date.getTime()): endDate);
			
			System.out.println(statement.toString());
			result = statement.executeQuery();
			while (result.next()){
				b = new Batch(result.getInt("id"), result.getString("name"), result.getInt("profile"), 
						result.getString("created_by"), result.getTimestamp("created_date"), 
						result.getString("approved_by"), result.getTimestamp("approved_date"));
				batches.add(b);
			}
		} catch (SQLException e) {
			throw new DataBaseException("SQLException BatchDAO - getBatches(String partialBatchName): " + e.getMessage());
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
		String query = "INSERT INTO batches (name, profile, Created_By) VALUES (?,?,?)";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try {
			statement.setString(1, batch.getBatchString());
			statement.setInt(2, batch.getProfileID());
			statement.setString(3, batch.getCreated_by());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DataBaseException("SQLException BatchDAO - addToDB(Batch batch): " + e.getMessage());
		}
	}

	@Override
	public void addtoDB(BatchSetting batchSetting) throws DataBaseException {
		String query = "INSERT INTO batchesettings (profileid, value) VALUES (?,?)";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try {
			statement.setInt(1, batchSetting.getId());
			statement.setString(2, batchSetting.getValue());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DataBaseException("SQLException BatchDAO - addToDB(BatchSetting batchSettings): " + e.getMessage());
		}

	}
	// Method for updating the batchsettings of a batch. Is used by the NewBatchController
	@Override
	public void updateBatchSettings(ArrayList<BatchSetting> settings, int profileID) throws DataBaseException {
		String query = null;
		for(int i = 0; i < settings.size(); i++){
			query = "UPDATE batchsettings SET value =? WHERE (id=?)";

			try {
				PreparedStatement statement = sqlConnector.getPreparedStatement(query);
				statement.setString(1, settings.get(i).getValue());
				statement.setInt(2, settings.get(i).getId());
				System.out.println("updating test.. " + " Value: " + settings.get(i).getValue() + " Id: " + settings.get(i).getId());
				statement.executeUpdate();
			}	catch (SQLException e){
				throw new DataBaseException("SQLException BatchDAO - updateBatchSettings(BatchSetting b): " + e.getMessage());
			}
		}
	}

	//	Method for deleting a batch's settings from the Database. Is currently not used.
	@Override
	public void deleteBatchSettings(Batch batch) throws DataBaseException {
		String query = "DELETE FROM batchsettings WHERE profileid =?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try {
			statement.setInt(1, batch.getProfileID());
			statement.executeUpdate();
		} catch (SQLException e) {

			throw new DataBaseException("SQLException BatchDAO - deleteBatchSettings(Batch batch)" + e.getMessage());

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

		//Measurement.MeasurementType type; 
		String query1 = "SELECT * FROM batches WHERE name=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query1);
		try {
			statement.setString(1, batchname);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				returBatch = new Batch(result.getInt("id"), result.getString("name"), result.getInt("profile"),
						result.getString("created_by"), result.getTimestamp("created_date"), result.getString("approved_by"), 
						result.getTimestamp("approved_date"));

				String query2 = "SELECT * FROM measurements WHERE batchid=?";
				PreparedStatement p = sqlConnector.getPreparedStatement(query2);
				p.setInt(1, result.getInt("id"));
				result = p.executeQuery();


				while(result.next()){
					if(result.getString("measurementtype").equals("LEAK")){
						leak.add(new Measurement(result.getInt("batchid"), result.getInt("elementnumber"), 
								result.getFloat("measurementvalue"), result.getBoolean("verified"), Measurement.MeasurementType.LEAK, 
								result.getLong("timestamp")));						
					}
					else if (result.getString("measurementtype").equals("STROKE")){
						stroke.add(new Measurement(result.getInt("batchid"), result.getInt("elementnumber"), 
								result.getFloat("measurementvalue"), result.getBoolean("verified"), Measurement.MeasurementType.STROKE, 
								result.getLong("timestamp")));
					}
				}
			}
			else {
				return null; // if no Batch is found in database with selected batchname
			}
			returBatch.setMeasurementList(stroke, leak);
			return returBatch;

		} catch (SQLException e) {
			throw new DataBaseException("SQLException BatchDAO - getBatch(String batchname): " + e.getMessage());
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
			throw new DataBaseException("SQLException BatchDAO - isBatchInDB(String batchName): " + e.getMessage());
		}
		return false;
	}
	public Batch getBatch(int batchId) throws DataBaseException {
		//TODO testing and commenting
		Batch returBatch;
		Measurement.MeasurementType type; 
		String query = "SELECT * FROM batches WHERE id=?";
		PreparedStatement p = sqlConnector.getPreparedStatement(query);

		try {
			p.setInt(1, batchId);
			ResultSet result = p.executeQuery();
			if(result.next()){
				returBatch = new Batch(result.getInt("id"), result.getString("name"), result.getInt("profile"),
						result.getString("created_by"), result.getTimestamp("created_date"), 
						result.getString("approved_by"), result.getTimestamp("approved_date"));

				query = "SELECT * FROM measurements WHERE batchid=?";
				p = sqlConnector.getPreparedStatement(query);
				p.setInt(1, batchId);
				result = p.executeQuery();

				while(result.next()){
					if(result.getString("measurementtype").equals("LEAK")){
						type = Measurement.MeasurementType.LEAK;						
					}
					else{
						type = Measurement.MeasurementType.STROKE;
					}
					returBatch.addMeasurement(new Measurement(result.getInt("id"), result.getInt("elementnumber"), 
							result.getFloat("measurementvalue"), result.getBoolean("verified"), type, result.getLong("timestamp")));

				}

			} else{
				return null; // if returned table is empty null is returned
			}

			return returBatch;

		} catch (SQLException e) {
			throw new DataBaseException("SQLException BatchDAO - getBatch(int batchId): " + e.getMessage());
		}
	}
	@Override
	public void updateBatch(Batch activeBatch) throws DataBaseException {
		String query = "UPDATE batches SET Approved_By=?, Approved_Date=? WHERE id=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		
		try{
			statement.setString(1, activeBatch.getApproved_by());
			statement.setTimestamp(2, activeBatch.getApproved_date());
			statement.setInt(3, activeBatch.getBatchID());
			statement.executeUpdate();
		}
		catch(SQLException e){
			throw new DataBaseException("Failed to update batch - batchDAO.updateBatch(Batch batch) " + e.getMessage());
		}
		
	}

}
