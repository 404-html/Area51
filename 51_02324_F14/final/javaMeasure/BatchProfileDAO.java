package javaMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaMeasure.control.interfaces.ISQLConnector;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.interfaces.IBatchProfileDAO;
public class BatchProfileDAO implements IBatchProfileDAO {

	private ISQLConnector sqlConnector;
		public BatchProfileDAO(ISQLConnector sqlConnector) {
			this.sqlConnector = sqlConnector;
	}


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
					batchProfile.setProfileID(profileId);
				} else {
					return null;
				}
			} catch (SQLException e) {
				throw new DataBaseException("SQLException BatchProfileDAO - getBatchProfile(String profilename) - getting id from batchprofile: " + e.getMessage());
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
				throw new DataBaseException("SQLException BatchProfileDAO - getBatchProfile(String profilename) - getting profilesettings: " + e.getMessage());
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
				throw new DataBaseException("SQLException BatchProfileDAO - getBatchProfile(int profileId): " + e.getMessage());
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
				throw new DataBaseException("SQLException BatchProfileDAO - getSavedBatchProfilesNames(): " + e.getMessage());
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
				throw new DataBaseException("SQLException BatchProfileDAO - saveBatchProfile(BatchProfile profile): " + e.getMessage());
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
				throw new DataBaseException("SQLException BatchProfileDAO - saveBatchSetting(BatchSetting b, int profileID): " + e.getMessage());
			}
		}

		@Override
		public void deleteBatchProfile(BatchProfile bp) throws DataBaseException {
			String query = "DELETE FROM batchsettings WHERE profileid=?";
			PreparedStatement s = sqlConnector.getPreparedStatement(query);
			try{
				System.out.println(bp.getProfileID());
				s.setInt(1, bp.getProfileID());
				s.executeUpdate();
			} catch (SQLException e){
				throw new DataBaseException("SQLException BatchProfileDAO - deleteBatchProfile(BatchProfile bp) - deleteBatchSettings: " + e.getMessage());
			}
			
			query = "DELETE FROM batchprofile WHERE profilename=?";
			s = sqlConnector.getPreparedStatement(query);
			try{
				s.setString(1, bp.getProfileName());
				s.executeUpdate();
			} catch (SQLException e){
				throw new DataBaseException("SQLException BatchProfile DAO - deleteBatchProfile(BatchProfile bp) - deleteBatchProfile: " + e.getMessage());
				
			}
	}

//		public void editBatchProfile(BatchProfile bp, String newId) throws DataBaseException{
//			NewBatchController nbc = null;
//			nbc.loadBatchSettingsPressed(bp.getProfileName());
//		}
//		public void saveEditedBatchSettings(BatchProfile oldProfile) throws DataBaseException {
//			
//		}
}
