package javaMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaMeasure.control.SQLConnector;
import javaMeasure.control.interfaces.ISQLConnector;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.interfaces.IBatchProfileDAO;
public class BatchProfileDAO implements IBatchProfileDAO {

	private static ISQLConnector sqlConnector = new SQLConnector();
		/* (non-Javadoc)
		 * @see javaMeasure.IBatchProfileDAO#getBatchProfile(java.lang.String)
		 */
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

		/* (non-Javadoc)
		 * @see javaMeasure.IBatchProfileDAO#getBatchProfile(int)
		 */
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



		/* (non-Javadoc)
		 * @see javaMeasure.IBatchProfileDAO#getSavedBatchProfilesNames()
		 */
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
		/* (non-Javadoc)
		 * @see javaMeasure.IBatchProfileDAO#saveBatchProfile(javaMeasure.BatchProfile)
		 */
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
		/* (non-Javadoc)
		 * @see javaMeasure.IBatchProfileDAO#saveBatchSetting(javaMeasure.BatchSetting, int)
		 */
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
		
		/* (non-Javadoc)
		 * @see javaMeasure.IBatchProfileDAO#deleteBatchProfile(javaMeasure.BatchProfile)
		 */
		public void deleteBatchProfile(BatchProfile bp) throws DataBaseException {
			// TODO test
			String query = "DELETE FROM batchsettings WHERE profileID=?";
			PreparedStatement s = sqlConnector.getPreparedStatement(query);
			try{
				s.setInt(1, bp.getProfileID());
				s.executeUpdate();
			} catch (SQLException e){
				e.printStackTrace();
				throw new DataBaseException();
				
			}
		}
		

}
