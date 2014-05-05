package javaMeasure.control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javaMeasure.Measurement;
import javaMeasure.Measurement.MeasurementType;
import javaMeasure.control.interfaces.IDasyFileReader;

public class DasyFileReader implements IDasyFileReader {	

	public DasyFileReader(){
	}
	
	
	// Denne metode smider målinger fra en fil ind i et array af Measurements.
	// Den konverterer tiden fra double til long. Den ganger tiden med 1000, for at gøre dem til millisekunder i stedet for sekunder.
	@Override
	public Measurement[] readFile(String path, Measurement.MeasurementType measurementType) {
		ArrayList<Measurement> measurement = new ArrayList<Measurement>();
		ArrayList<double[]> dArrayList;
		dArrayList = toArrayList(path);
		float measureValue;
		long timeStamp;
		for(double[] index: dArrayList){
			timeStamp = (long) index[1]*1000;
			measureValue = (float) index[0];
			measurement.add( new Measurement(measureValue, measurementType, timeStamp));
		}
		Measurement[] measurements = new Measurement[measurement.size()];
		measurement.toArray(measurements);
		return measurements;
	}
	
	// TODO probably consider exceptions handling. either throw exceptions or handle them
	
	// Takes a time in milliseconds(time in DASY file x 1000) and the path to a DASY file and gives you the value to the time you have entered.
	public Measurement getCurrentValue(long time, String path){
		double doubleTime = (double) time/1000;
		Measurement value = null;
		ArrayList<double[]> dArrayList = toArrayList(path);
		for(double[] index: dArrayList){
			if(index[0] == doubleTime){
				value = new Measurement((float)index[1], MeasurementType.LEAK, time);
				System.out.println(value.getMeasureValue());
				break;
			}
		}
		
		return value;
	}

	// Takes the path to a DASY file and makes an arrayList of doubles with the values, index[0] is the given time, and index[1] is the value
	public ArrayList<double[]> toArrayList(String path){
		ArrayList<String> header = new ArrayList<>();
		ArrayList<double[]> timeAndValue = new ArrayList<>();
		String currentLine = null;
		try {
			Scanner sc = new Scanner(new FileReader(path));
			
			String line = sc.nextLine();
			while(Character.isDigit(line.charAt(0)) == false){
					line = sc.nextLine();
					header.add(line);
					}
			currentLine = sc.nextLine();
			while(currentLine != null){

//				System.out.println(currentLine);
				String[] splitLine = currentLine.split("\t");
				double[] doubleArray = new double[2];
				doubleArray[0] = Double.parseDouble(splitLine[0]);
				doubleArray[1] = Double.parseDouble(splitLine[1]);
//				timeAndValue.add(double[2]);
				timeAndValue.add(doubleArray);
				if(!sc.hasNextLine())
					break;
				currentLine = sc.nextLine();
			}
			sc.close();
		} 
		catch (FileNotFoundException fnfe) {System.err.println("file was not found");}
//		catch(IOException ioe){System.err.println("IO exception during reading file");}
		return timeAndValue;
	}

	public void getArrayListOfDoubles(ArrayList<double[]> arrayList){
		for(double[] dArray: arrayList){
			for (double i : dArray)
			System.out.println(i);
		}
	}

	public static void main(String[] args){
		DasyFileReader d = new DasyFileReader();
		
		System.out.println(d.getCurrentValue(62000, "C:/Users/Voermadal/Desktop/ML-14-005-04.asc"));
		System.out.println(d.getCurrentValue(62000, "C:/Users/Voermadal/Desktop/dasytest/ML-14-005-01.asc"));
		System.out.println(d.getCurrentValue(62000, "C:/Users/Voermadal/Desktop/dasytest/ML-14-005-02.asc"));
		System.out.println(d.getCurrentValue(62000, "C:/Users/Voermadal/Desktop/dasytest/ML-14-005-03.asc"));
		System.out.println(d.getCurrentValue(62000, "C:/Users/Voermadal/Desktop/dasytest/ML-14-005-05.asc"));
		System.out.println(d.getCurrentValue(62000, "C:/Users/Voermadal/Desktop/dasytest/ML-14-005-06.asc"));
		System.out.println(d.getCurrentValue(62000, "C:/Users/Voermadal/Desktop/dasytest/ML-14-005-07.asc"));
		System.out.println(d.getCurrentValue(62000, "C:/Users/Voermadal/Desktop/dasytest/ML-14-005-08.asc"));
	}

//	public static void main(String[] args){
//
//		DasyFileReader dsr = new DasyFileReader(
//								"C:/Dropbox/M�n mappa/DTU/2. semester/Area51_2014Edition/test.asc");
//				"C:/Users/Martin/Documents/Skúlating/Videregående Programmering/ML-14-005-01.ASC");
//		dsr.toArrayList("C:/Users/Martin/Documents/Skúlating/Videregående Programmering/ML-14-005-01.ASC");
//		dsr.readFile("C:/Users/Martin/Documents/Skúlating/Videregående Programmering/ML-14-005-01.ASC", Measurement.MeasurementType.LEAK);
//		dsr.getCurretValue(10000, "C:/Users/Martin/Documents/Skúlating/Videregående Programmering/ML-14-005-01.ASC");
//	}



}

