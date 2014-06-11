package javaMeasure;
//DataTransferObject for a Measurement
public class Measurement {
	public enum MeasurementType {LEAK, STROKE};
	private int batchID;
	private MeasurementType measurementType;
	private int elementNo;
	private float measureValue;
	private long timeStamp;
	private boolean verified;
	
	public Measurement(float measureValue,MeasurementType readType, long timeStamp){
		this(-1, -1, measureValue, readType, timeStamp);
	}
	
	public Measurement(int batchID, int elementNo, float measureValue,MeasurementType readType, long timeStamp){
		this.timeStamp = timeStamp;
		this.measurementType=readType;
		this.measureValue = measureValue;
		this.batchID = batchID;
		this.elementNo = elementNo;
		this.verified = true;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public MeasurementType getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(MeasurementType measurementType) {
		this.measurementType = measurementType;
	}

	public int getElementNo() {
		return elementNo;
	}

	public void setElementNo(int elementNo) {
		this.elementNo = elementNo;
	}

	public float getMeasureValue() {
		return measureValue;
	}

	public void setMeasureValue(float measureValue) {
		this.measureValue = measureValue;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public boolean getVerified(){
		return this.verified;
	}
}
