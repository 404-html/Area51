package javaMeasure.control.interfaces;

import javaMeasure.Measurement;
import javaMeasure.control.ConnectionException;
public interface ICConnector {

	/**
	 * requests measurements from the C# application
	 * 
	 * @param measurmentType The type of measurement LEAK / STROKE
	 * @param number how many measurements to request from C#
	 * @param period The time between measurements
	 * @return A Measurement array, containing the data returned from C#
	 * @throws ConnectionException In case something breaks
	 */
	Measurement[] readMeasurements(Measurement.MeasurementType measurmentType, int number, int period) throws ConnectionException;
	/**
	 * Chances the IP that the CConnector connects to.
	 * @param iP The IP of the Remote system
	 */
	void setIP(String iP);
	/**
	 * Chances the network port that the CConnector connects to.
	 * @param port The port on the remote system that the C# application runs on.
	 */
	void setPort(int port);
}

