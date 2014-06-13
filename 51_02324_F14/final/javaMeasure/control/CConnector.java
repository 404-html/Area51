package javaMeasure.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javaMeasure.Measurement;
import javaMeasure.Measurement.MeasurementType;
import javaMeasure.control.interfaces.ICConnector;

/**
 * Class to manage the connection between JAVA and C#
 * @author Magnus, Eirik
 *
 */
public class CConnector implements ICConnector
{
	private Socket socket;
	private InetAddress IP = null;
	private int networkPort = 4567;

	/**
	 * Constructor, sets up the Default IP = The local machine
	 */
	public CConnector()
	{
		try
		{
			this.IP = InetAddress.getLocalHost();
		} 
		catch (UnknownHostException e1)
		{
			e1.printStackTrace();
		}

		System.out.println("IP in CConnector is: "+this.IP.getHostAddress());
	}


	@Override
	public Measurement[] readMeasurements(MeasurementType measurementType, int number, int period) throws ConnectionException
	{
		connect();

		//Gets returns an array of measurment with lenght number with updated data, port and timestamp
		Measurement[] measurementArray = new Measurement[number];
		int hardwarePort = 0;
		switch(measurementType)
		{
		//port 0 = physical hardware
		//port 99 = random data
		//port 98 = always returns 1
		case LEAK:
			//ports=1234;
			hardwarePort=99;
			break;
		case STROKE:
			hardwarePort=0;
			break;
		}
		String returnString = writeToSocket(hardwarePort + ";" + number + ";" + period + ";<EOF>", period, number);

		System.out.println("Sent to server:" + hardwarePort+";"+number+";"+period+";<EOF>");

		String[] returnArray = returnString.split(";");

		try
		{
			int j = 0;
			for (int i = 0; i < returnArray.length/2; i++)
			{
				measurementArray[i] = new Measurement(Float.parseFloat(returnArray[j]),measurementType,Long.parseLong(returnArray[j+1]));

				j++;
				j++;
			}	
		}
		catch(NumberFormatException e)
		{
			if(returnArray[0].equals("ERROR"))
			{
				int x=Integer.parseInt(returnArray[1]);
				throw new ConnectionException(x , true);
			}
			else
			{
				throw new ConnectionException(2);
			}
		}
		if(!socket.isClosed())
		{
			try
			{
				socket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return measurementArray;
	}

	/**
	 * Connects the socket to the current IP
	 * @throws ConnectionException If the connection attempt fails
	 */
	private void connect() throws ConnectionException
	{
		socket = new Socket();
		try
		{
			socket.connect(new InetSocketAddress(IP,networkPort));
		} 
		catch (IOException e)
		{
			throw new ConnectionException(5);
		}
	}

	/**
	 * Sets the timeout of the socket to a multiple of the requested number 
	 * of measurements, and the given period between them. Then adds a 5 
	 * second grace period.
	 * 
	 * @param period The delay between measurements
	 * @param antal The number of requested measurements
	 * @throws ConnectionException 
	 */
	private void setSocketTimeout(int period, int antal) throws ConnectionException
	{
		try
		{
			socket.setSoTimeout(period*antal+5000);
		}
		catch ( SocketException e)
		{
			throw new ConnectionException(7);
		}
	}

	/**
	 * Method that takes a string and writes it to the sockets outputstream,
	 * it then waits for an answer on the socket, and returns it as a string.
	 * 
	 * @param printString The string to be written to the socket.
	 * @param period the time between measurements (needed to set socketTimeout)
	 * @param number the number of requested measurements (needed to set socketTimeout)
	 * @return The string as returned from the C# aplication
	 * @throws ConnectionException
	 */
	private String writeToSocket(String printString, int period, int number) throws ConnectionException
	{
		String returnString = null;
		try
		{
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			setSocketTimeout(period, number);

			writer.print(printString);
			writer.flush();

			returnString = reader.readLine();
			reader.close();
		}
		catch(SocketTimeoutException ste)
		{
			//ste.printStackTrace();
			throw new ConnectionException(4);
		}
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
			throw new ConnectionException(9);
		}

		return returnString;
	}

	@Override
	public void setIP(String ip)
	{
		try
		{
			this.IP= InetAddress.getByName(ip);
			System.out.println("IP in CConnector is: "+this.IP.getHostAddress());
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setPort(int port)
	{
		this.networkPort=port;
	}


}
