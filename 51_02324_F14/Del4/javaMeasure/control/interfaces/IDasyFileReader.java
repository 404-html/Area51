package javaMeasure.control.interfaces;

import javaMeasure.Measurement;

public interface IDasyFileReader {
	
	Measurement[] readFile(String path, Measurement.MeasurementType measurementType);
	Measurement getCurrentValue(long time, String path);
	
}
