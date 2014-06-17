package javaMeasure;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javaMeasure.Measurement.MeasurementType;
import javaMeasure.Measurement;

public class Batch {
	private int BatchID;
	private String BatchString;
	private int profileID;

	private ArrayList<Measurement[]> measurements = new ArrayList<Measurement[]>();
	private final static int STROKE_INDEX = 0; // used in list above to keep track which index the measurement should be in
	private final static int LEAK_INDEX = 1; // used in list above to keep track which index the measurement should be in
	private int nextStrokeIndex = 0; // counter for where to put the next stroke measurement, this is also an easy way to give measurements an elementnumber
	private int nextLeakIndex = 0; // counter for where to put the next leak measurement.

	private String created_by;
	private Timestamp created_date;
	private String approved_by;
	private Timestamp approved_date;

	/**
	 * Constuctor for simple Batch
	 * @param batchID
	 * @param batchString
	 * @param profileID
	 */
	public Batch(int batchID, String batchString, int profileID) {

		this(batchID, batchString, profileID, null, null, null, null);
	}

	/**
	 * Overloaded constructor, adds fields for created_by/date and approved_by/date
	 * @param batchID
	 * @param batchString
	 * @param profileID
	 * @param created_by
	 * @param created_date
	 * @param approved_by
	 * @param approved_date
	 */
	public Batch(int batchID, String batchString, int profileID, String created_by, Timestamp created_date, String approved_by, Timestamp approved_date) {
		super();
		this.BatchID = batchID;
		this.BatchString = batchString;
		this.profileID = profileID;

		this.created_by = created_by;
		this.created_date = created_date;
		this.approved_by = approved_by;
		this.approved_date = approved_date;
	}

	public int getProfileID() {
		return profileID;
	}

	public void setProfileID(int profileID) {
		this.profileID = profileID;
	}

	public int getBatchID() {
		return BatchID;
	}

	public void setBatchID(int batchID) {
		BatchID = batchID;
	}

	public String getBatchString() {
		return BatchString;
	}

	public void setBatchString(String batchString) {
		BatchString = batchString;
	}

	public boolean deleteLastStrokeMeasurement(){

		if(nextStrokeIndex < nextLeakIndex || nextStrokeIndex == 0)
			//Checks if Stroke list is shorter than Leak list and returns false - to avoid asynchronous data collection
			return false;
		else{
			measurements.get(nextStrokeIndex-1)[STROKE_INDEX] = null;
			if(measurements.get(nextStrokeIndex-1)[LEAK_INDEX] == null){
				measurements.remove(nextStrokeIndex-1);
			}
			nextStrokeIndex--;
			return true;
		}
	}

	public boolean deleteLastLeakMeasurement(){

		if(nextLeakIndex < nextStrokeIndex || nextLeakIndex == 0)
			return false;
		else{
			measurements.get(nextLeakIndex-1)[LEAK_INDEX] = null;
			if(measurements.get(nextLeakIndex-1)[STROKE_INDEX] == null){
				measurements.remove(nextLeakIndex-1);
			}
			nextLeakIndex--;
			return true;
		}
	}

	// adds a single measurement to the batch
	// as of now, the measurements kan be taken in any chronological order, which means as of now, you have to make all measurements on all elements
	public boolean addMeasurement(Measurement measurement){

		MeasurementType type = measurement.getMeasurementType();
		
		// checking if measurements are taken in sync - if difference between currentLeakElement and currentStrokeElement gets bigger than 1 or lower than -1
		// measurement will not be added and false will be returned
		int dif = nextLeakIndex - nextStrokeIndex;
		if(type.equals(MeasurementType.LEAK))
			// difference between currentLeak index and currentStroke index
			dif++;
		else dif--;
		if(dif > 1 || dif < -1){
			return false;
		}

		MeasurementType t = measurement.getMeasurementType();
		if(t.equals(MeasurementType.LEAK)){
			try{
				// leak measurement is at index 1
				measurements.get(nextLeakIndex)[1] = measurement;
			} catch(IndexOutOfBoundsException e){
				measurements.add(new Measurement[]{null, measurement});
			}
			nextLeakIndex++;
		}
		else if(t.equals(MeasurementType.STROKE)){
			try{
				// stroke measurement is at index 0
				measurements.get(nextStrokeIndex)[0] = measurement;
			} catch(IndexOutOfBoundsException e){
				measurements.add(new Measurement[]{measurement, null});
			}
			nextStrokeIndex++;
		}
		return true;
	}
	
	// only thing needed to update in measurements is if they are verified or not
	// therefore this method
	public void updateMeasurements(int elementNumber, boolean verified) {
		measurements.get(elementNumber)[STROKE_INDEX].setVerified(verified);;
		measurements.get(elementNumber)[LEAK_INDEX].setVerified(verified);;
	}

	// used to report generation - gets the average stroke measured for batch
	public float getAverageStroke(){
		float total = 0;
		// adding all stroke values together
		for(int i = 0; i < nextStrokeIndex; i++)
		{
			if(measurements.get(i)[STROKE_INDEX] != null){
				Measurement measurement = measurements.get(i)[STROKE_INDEX];
				float value = measurement.getMeasureValue();
				total = total + value;
				
			}
		}
		// returns the average - nextStrokeIndex matches the number of stroke measurements because it is 0 indexed
		return total/nextStrokeIndex;
	}

	// used to report generation - gets the average leak measured for batch
	public float getAverageLeak(){
		float total = 0;
		// adding all leak values together
		for(int i = 0; i < nextLeakIndex; i++)
		{
			if(measurements.get(i)[LEAK_INDEX] != null){
				Measurement measurement = measurements.get(i)[LEAK_INDEX];
				float value =  measurement.getMeasureValue();
				total = total + value;
			}
		}
		// returns the average - nextLeakIndex matches the number of leak measurements because it is 0 indexed
		return total/nextLeakIndex;
	}

	
	public Measurement getMeasurement(int elementnumber, MeasurementType type){
		if(type == MeasurementType.STROKE){
			return getMeasurementsList().get(elementnumber)[STROKE_INDEX];
		}
		else{
			return getMeasurementsList().get(elementnumber)[LEAK_INDEX];
		}
	}

	//used in jsp
	public String getDateAsString(Timestamp date){
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public int getCurrentLeakElement(){
		return nextLeakIndex;
	}

	public int getCurrentStrokeElement(){
		return nextStrokeIndex;
	}

	public ArrayList<Measurement[]> getNewList(){
		return measurements;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

	public Timestamp getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Timestamp approved_date) {
		this.approved_date = approved_date;
	}

	public ArrayList<Measurement[]> getMeasurementsList() {
		return measurements;
	}

}


