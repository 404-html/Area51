package javaMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javaMeasure.Measurement.MeasurementType;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.ISQLConnector;
import javaMeasure.interfaces.IMeasurementDAO;



public class MeasurementDAO implements IMeasurementDAO{
	private ISQLConnector sqlConnector;
	
	
	 public MeasurementDAO(ISQLConnector sqlConnector) {
		 this.sqlConnector = sqlConnector;
	}

	/* (non-Javadoc)
	 * @see javaMeasure.IMeasurementDAO#addToDB(javaMeasure.Measurement)
	 */
	public void addToDB(Measurement measurement) throws DataBaseException, javaMeasure.control.interfaces.IDatabaseController.DataBaseException {
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
	 
	/* (non-Javadoc)
	 * @see javaMeasure.IMeasurementDAO#getMeasurementsByBatch(javaMeasure.Batch)
	 */
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
}
