package javaMeasure;

import java.util.ArrayList;
import java.util.Date;

import javaMeasure.Measurement.MeasurementType;

public class Batch {
	private int BatchID;
	private String BatchString;
	private int profileID;
	private ArrayList<Object[]> measurementsList; // this list is made compatible with the JTable in BatchMeasureGui. now it is easier to copy over
	private int currentStrokeElement = 0; // counter for where to put the next stroke measurement, this is also an easy way to give measurements an elementnumber
	private int currentLeakElement = 0; // counter for where to put the next leak measurement.

	private String created_by;
	private Date created_date;
	private String approved_by;
	private Date approved_date;
	
	public Batch(int batchID, String batchString, int profileID) {
		super();
		setBatchID(batchID);
		setBatchString(batchString);
		this.profileID = profileID;
		this.measurementsList = new ArrayList<>();
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
	public Batch(int batchID, String batchString, int profileID, String created_by, Date created_date, String approved_by, Date approved_date) {
		super();
		setBatchID(batchID);
		setBatchString(batchString);
		this.profileID = profileID;
		this.measurementsList = new ArrayList<>();
		
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
		Object[] row = getMeasurementsList().get(currentStrokeElement-1);
		ArrayList<Object[]> list = getMeasurementsList();
		if(currentStrokeElement < currentLeakElement || currentLeakElement == 0)
			return false;
		else{
			row[2] = null;
			if(row[3] == null){
				list.remove(currentStrokeElement-1);
			}
			currentStrokeElement--;
			return true;
		}
	}
	
	public boolean deleteLastLeakMeasurement(){
		Object[] row = getMeasurementsList().get(currentLeakElement-1);
		ArrayList<Object[]> list = getMeasurementsList();
		if(currentLeakElement < currentStrokeElement || currentLeakElement == 0)
			return false;
		else{
			row[3] = null;
			if(row[2] == null){
				list.remove(currentLeakElement-1);
			}
			currentLeakElement--;
			return true;
		}
	}

	// adds a single measurement to the batch
	// as of now, the measurements kan be taken in any chronological order, which means as of now, you have to make all measurements on all elements
	public boolean addMeasurement(Measurement measurement){
		MeasurementType type = measurement.getMeasurementType();
		int dif = currentLeakElement - currentStrokeElement;
		if(type.equals(MeasurementType.LEAK))
			dif++;
		else dif--;
		if(dif > 1 || dif < -1){
			return false;
		}

		if(type.equals(MeasurementType.LEAK)){
			// the try catch is to make sure that the measurement never is put out bounds
			// if the currentLeakElement points out of bounds, a new object is created in the catch clause
			try{ measurementsList.get(currentLeakElement)[3] = measurement;
			} catch (IndexOutOfBoundsException e){
				measurementsList.add(new Object[]{measurement.getVerified(), currentLeakElement, null, measurement});
			}
			currentLeakElement++;
		}
		if(type.equals(MeasurementType.STROKE)){
			// the try catch is to make sure that the measurement never is put out bounds
			// if the currentStrokeElement points out of bounds, a new object is created in the catch clause
			try{ measurementsList.get(currentStrokeElement)[2] = measurement;
			} catch(IndexOutOfBoundsException e){
				measurementsList.add(new Object[]{measurement.getVerified(), currentStrokeElement, measurement, null});
			}
			currentStrokeElement++;
		}
		return true;
	}

	// method for database to add the whole list without trouble
	public void setMeasurementList(ArrayList<Measurement> stroke, ArrayList<Measurement> leak){
		int size = 0;
		if(stroke.size() > leak.size()){
			size = stroke.size();
		} else size = leak.size();
		if(size != 0){
			System.out.println(size);
			System.out.println("stroke: " + stroke.size());
			System.out.println("leak: " + leak.size());
			for(int i = 0; i < size; i++)
			{
				if(stroke.size() > i && !stroke.isEmpty())
					addMeasurement(stroke.get(i));
				if(leak.size() > i && !leak.isEmpty())
					addMeasurement(leak.get(i));
			}
		}
	}
	public float getAverageStroke(){
		int length = 0;
		float total = 0;
		for(int i = 0; i < measurementsList.size(); i++)
		{
			if(measurementsList.get(i)[1] != null){
				Measurement measurement = (Measurement) measurementsList.get(i)[2];
				float value = (float) measurement.getMeasureValue();
				total = total + value;
				length++;
			}
		}
		return total/length;
	}

	public float getAverageLeak(){
		int length = 0;
		float total = 0;
		for(int i = 0; i < measurementsList.size(); i++)
		{
			if(measurementsList.get(i)[1] != null){
				Measurement measurement = (Measurement) measurementsList.get(i)[3];
				float value = (float)  measurement.getMeasureValue();
				total = total + value;
				length++;
			}
		}
		return total/length;
	}
	
	public Measurement getMeasurement(int elementnumber, MeasurementType type){
		if(type == MeasurementType.STROKE){
			return (Measurement) getMeasurementsList().get(elementnumber)[2];
		}
		else{
			return (Measurement) getMeasurementsList().get(elementnumber)[3];
		}
	}

	public int getCurrentLeakElement(){
		return currentLeakElement;
	}

	public int getCurrentStrokeElement(){
		return currentStrokeElement;
	}

	public ArrayList<Object[]> getMeasurementsList(){
		return measurementsList;	
	}

	public void updateMeasurements(int elementNumber, boolean verified) {
		Object[] element = getMeasurementsList().get(elementNumber);
		measurementsList.get(elementNumber)[0] = verified;
		Measurement stroke = (Measurement) element[2];
		Measurement leak = (Measurement) element[3];
		stroke.setVerified(verified);
		leak.setVerified(verified);
	}
	
	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

	public Date getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	
}


