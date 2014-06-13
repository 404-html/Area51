package javaMeasure;

import javaMeasure.Measurement.MeasurementType;

public class MeasureElement {

	private Measurement stroke;
	private Measurement leakCurrent;
	private String BatchID;
	private int elementNumber;
//	new Measurement(123, MeasurementType.STROKE, 124124)
//			new Measurement(1241, MeasurementType.LEAK, 35132)

	public MeasureElement(String batchID, int elementNumber){
		this(batchID, elementNumber, null, null);
	}
	
	public MeasureElement(String batchID, int elementNumber, Measurement stroke, Measurement leakCurrent){
		
		this.elementNumber = elementNumber;
		this.BatchID = batchID;
		this.stroke = stroke;
		this.leakCurrent = leakCurrent;
		
	}

	public void setMeasurements(Measurement measurement){
		if(measurement.equals(MeasurementType.LEAK))
			leakCurrent = measurement;
		else stroke = measurement;
	}
	
	public Measurement getStroke(){
		return stroke;
	}
	
	public Measurement getLeakCurrent(){
		return leakCurrent;
	}

	public int getElementNumber(){
		return elementNumber;
	}
	public String getBatchId(){
		return this.BatchID;
	}

}
