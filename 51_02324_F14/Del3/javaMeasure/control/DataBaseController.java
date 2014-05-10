package javaMeasure.control;

import java.sql.*;
import java.util.ArrayList;
import javaMeasure.*;
import javaMeasure.interfaces.*;
import javaMeasure.Measurement.MeasurementType;
import javaMeasure.control.interfaces.IDatabaseController;
import javaMeasure.control.interfaces.ISQLConnector;


public class DataBaseController implements IDatabaseController {

	IUserDAO user= new UserDAO();
	
	private static ISQLConnector sqlConnector = new SQLConnector();
	//TODO should if there is time extend the amount of exceptions!
	public DataBaseController() {
		super();
	}

	//User methods	
	@Override
	public ArrayList<User> getUserList() throws DataBaseException {
		return this.user.getUserList();
	}

	@Override
	public boolean isUserNameInDB(String userName) throws DataBaseException {
		return this.user.isUserNameInDB( userName);
	}

	@Override
	public void addToDB(User user) throws DataBaseException{
		this.user.addToDB(user);

	}

	@Override
	public User getUserFromString(String userString) throws DataBaseException, UserNotFoundException {
		return this.user.getUserFromString(userString);
	}
	//Measurements

	@Override
	public void addToDB(Measurement measurement) throws DataBaseException {
		//		Statement statement = sqlConnector.getStatement();
		//		String qString = "INSERT INTO measurements (elementnumber, measurementtype, measurementvalue, timestamp) VALUES ("
		//				+ measurement.getElementNo() + 	",'" 
		//				+ measurement.getMeasurementType().name() + "',"
		//				+ measurement.getMeasureValue() +"," 
		//				+ measurement.getTimeStamp() 				
		//				+ ")";
		String query = "INSERT INTO measurements (batchid, elementnumber, measurementtype, measurementvalue, timestamp) VALUES (?,?,?,?,?)";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);

		try {
			statement.setInt(1, measurement.getBatchID());
			statement.setInt(2, measurement.getElementNo());
			statement.setString(3, measurement.getMeasurementType().name());
			statement.setFloat(4, measurement.getMeasureValue());
			statement.setLong(5, measurement.getTimeStamp());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
	}

	@Override
	public ArrayList<Measurement> getMeasurementsByBatch(Batch batch) throws DataBaseException {
		//TODO needs testing 
		String query = "SELECT * FROM measurements WHERE batchid=?";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		ResultSet result = null;
		try {
			statement.setInt(1, batch.getBatchID());
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		ArrayList<Measurement> measurements = new ArrayList<Measurement>();
		try {
			while (result.next()){
				Measurement m = new Measurement(result.getFloat("measurementvalue"), 
						MeasurementType.valueOf(result.getString("measurementtype")), 
						result.getLong("timestamp"));
				measurements.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}

		return measurements;
	}

	@Override
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

	public ArrayList<Batch> getBatches() throws DataBaseException {
		//TODO needs testing 
		String query = "SELECT * FROM batches";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		//		Statement statement = sqlConnector.getStatement();
		//		String qString = "SELECT * FROM batches";
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

	public Batch getBatch(String batchname) throws DataBaseException {
		// TODO needs testing   measurements (elementnumber, measurementtype, measurementvalue, timestamp)
		Batch returBatch;
		Measurement.MeasurementType type; 
		//		Statement statement = sqlConnector.getStatement();
		//		String qString = "SELECT * FROM batches WHERE name = '" 
		//				+ batchname + "'";
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

				//				if(result.next()){

				//					if(result.getString("measurementtype").equals("LEAK")){
				//						translate = Measurement.MeasurementType.LEAK;						
				//					}
				//					else{
				//						translate = Measurement.MeasurementType.STROKE;
				//					}
				//					Measurement measure = new Measurement(result.getFloat("measurementvalue"), translate, result.getInt("timestamp"));
				//					measure.setElementNo(result.getInt("elementnumber"));
				//					measure.setBatchID(result.getInt("batchid"));
				//					returBatch.addMeasurement(measure);
				while(result.next()){
					if(result.getString("measurementtype").equals("LEAK")){
						type = Measurement.MeasurementType.LEAK;						
					}
					else{
						type = Measurement.MeasurementType.STROKE;
					}
					returBatch.addMeasurement(new Measurement(result.getInt("batchid"), result.getInt("elementnumber"), result.getFloat("measurementvalue"), type, result.getLong("timestamp")));

					//						measure = new Measurement(result.getFloat("measurementvalue"), type, result.getInt("timestamp"));
					//						measure.setElementNo(result.getInt("elementnumber"));
					//						measure.setBatchID(result.getInt("batchid"));
					//						returBatch.addMeasurement(measure);
				}
			}
			//				else{
			//					throw new DataBaseException();
			//				}
			//			} 
			else {
				throw new DataBaseException();
			}

			return returBatch;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
	}
	@Override
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
					returBatch.addMeasurement(new Measurement(result.getInt("id"), result.getInt("elementnumber"), result.getFloat("measurementvalue"), type, result.getLong("timestamp")));
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

	@Override
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
	// Batchprofiles

	@Override
	public BatchProfile getBatchProfile(String profileName) throws DataBaseException {
		//TODO testing!!
		//Initialising variables
		BatchProfile batchProfile = new BatchProfile();
		batchProfile.setProfileName(profileName);
		int profileId = 0;
		ResultSet result = null;
		//Getting profileID from DB
		String query = "SELECT id FROM batchprofile WHERE profilename=?;";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		try {
			statement.setString(1, profileName);
			result = statement.executeQuery();
			if(result.next()){
				profileId = result.getInt(1);	
			} else {
				throw new DataBaseException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		//Getting settings from DB
		String query2 = "SELECT * FROM batchsettings WHERE profileid=?;";
		PreparedStatement statement2 = sqlConnector.getPreparedStatement(query2);
		ResultSet result2 = null;
		try {
			statement2.setInt(1, profileId);
			result2 = statement2.executeQuery();

			while (result2.next()){
				System.out.println("getting batchsettings");
				BatchSetting b = new BatchSetting(result2.getInt("id"), result2.getString("settingname"), result2.getString("valuetype"), result2.getString("value"));
				batchProfile.addSetting(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		return batchProfile;
	}

	@Override
	public BatchProfile getBatchProfile(int profileId) throws DataBaseException{
		//TODO implement
		String query = "SELECT * FROM batchsettings WHERE profileid=?";
		PreparedStatement p = sqlConnector.getPreparedStatement(query);
		BatchProfile batchProfile = new BatchProfile();
		ResultSet r = null;
		try{
			p.setInt(1, profileId);
			r = p.executeQuery();

			while(r.next()){
				BatchSetting bs = new BatchSetting(r.getInt("id"), r.getString("settingname"), r.getString("valuetype"), r.getString("value"));
				batchProfile.addSetting(bs);
			}
		} catch(SQLException e){
			throw new DataBaseException();
		}

		return batchProfile;
	}



	@Override
	public ArrayList<String> getSavedBatchProfilesNames() throws DataBaseException {
		//TODO testing!!
		ArrayList<String> nameList = new ArrayList<String>();
		String query = "SELECT * FROM batchprofile WHERE profilename IS NOT NULL";
		PreparedStatement statement = sqlConnector.getPreparedStatement(query);
		ResultSet result = null;;

		try {
			result = statement.executeQuery();
			while (result.next()){
				nameList.add(result.getString("profilename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		return nameList;
	}
	@Override
	public int saveBatchProfile(BatchProfile profile) throws DataBaseException {
		//TODO testing!!!

		String query = "INSERT INTO batchprofile (profilename) VALUES(?);";
		PreparedStatement s = sqlConnector.getPreparedStatement(query, Statement.RETURN_GENERATED_KEYS);
		int profileId = -1;
		try {
			s.setString(1, profile.getProfileName());
			s.executeUpdate();
			ResultSet r = s.getGeneratedKeys();
			r.next();
			profileId = r.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
		//Saving Settings.  
		for (BatchSetting b : profile.getProfileSettings()){
			saveBatchSetting(b, profileId);			
		}

		return profileId;
	}


	//helper method
	@Override
	public void saveBatchSetting(BatchSetting b, int profileID) throws DataBaseException{
		//TODO Test!!
		String query = "INSERT INTO batchsettings (profileid, settingname, valuetype, value) VALUES(?,?,?,?)";
		PreparedStatement s = sqlConnector.getPreparedStatement(query);

		try {
			s.setInt(1, profileID);
			s.setString(2, b.getSettingName());
			s.setString(3, b.getValueType());
			s.setString(4, b.getValue());
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		}
	}

	@Override
public void deleteBatchProfile(BatchProfile bp) throws DataBaseException {
	// TODO test
	String query = "DELETE FROM batchprofile WHERE profilename=?";
	PreparedStatement s = sqlConnector.getPreparedStatement(query);
	try{
		s.setString(1, bp.getProfileName());
		s.executeUpdate();
	} catch (SQLException e){
		e.printStackTrace();
		throw new DataBaseException();
		
	}
}
}
