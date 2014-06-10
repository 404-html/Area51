package javaMeasure.control;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javaMeasure.*;
import javaMeasure.Measurement.MeasurementType;
import javaMeasure.control.interfaces.*;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.view.*;
import javaMeasure.view.interfaces.*;


public class BatchMeasureController implements IBatchMeasureController {
	private IBatchMeasureGui batchGUI;
	private IMainController mainController;
	private Batch activeBatch;
	private DirectoryListener dl;

	public BatchMeasureController(IMainController mainController){
		super();
		this.activeBatch = null;
		this.mainController = mainController;
		this.batchGUI = new BatchMeasureGui(this);
		EventQueue.invokeLater(batchGUI);
		System.out.println("Active user is: " + this.mainController.getActiveUser().getUserName());
	}

	public void showGui(boolean visible){
		batchGUI.setVisibility(visible);
	}

	// opens new user interface where specifications and other things can be set
	public void btnNewBatchPressed() {
		mainController.startNewBatchController();
		batchGUI.updateLog("New Batch window opened...");
	}

	// TODO Finish this - Martin
	public void btnEditBatchSettingsPressed(){
		if(getActiveBatch() != null){
		mainController.startNewBatchController(getActiveBatch());
		batchGUI.updateLog("Edit batch settings window opened...");
		} else {
			batchGUI.updateLog("Could not open edit batch window - No batch loaded.");
		}
	}

	// user need to enter a batchnumber before this method is running. that is being taken care of in BatchMeasureGui
	public void btnGetBatchPressed() {
		ArrayList<Batch> list = null;
		batchGUI.updateLog("Loading batchnames...");
		try {
			list = mainController.getDatabaseController().getBatches();
		} catch (DataBaseException e1) {
			batchGUI.updateLog("Error in receiving batches from database!");
		}
		String[] batchList = new String[list.size()];
		for(int i = 0; i < list.size(); i++){
			batchList[i] = list.get(i).getBatchString();
		}

		String batchnumber = batchGUI.getLoadBatchName(batchList);
		if(batchnumber != null){
			boolean exists = false;
			for(int i = 0; i < batchList.length; i++){
				if(batchnumber.equalsIgnoreCase(list.get(i).getBatchString())){
					try {
						setActiveBatch(mainController.getDatabaseController().getBatch(batchnumber));
					} catch (DataBaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					exists = true;
					break;
				}
			}
			if(!exists){
				batchGUI.showInformationMessage("There is no batch with this name.", "No batch found");
			}
		} else batchGUI.updateLog("batch loading canceled");
	}

	// finish implementation
	public void btnStrokePressed() {

		Measurement[] measurement = null;
		if(activeBatch != null){
			try {

				//TODO is not working optimal. throwing exceptions. but gets the measurement
				measurement = mainController.getcConnector().readMeasurements(MeasurementType.STROKE, 1, 1000);

				measurement[0].setBatchID(activeBatch.getBatchID());
				measurement[0].setMeasurementType(MeasurementType.STROKE);
				measurement[0].setElementNo(activeBatch.getCurrentStrokeElement());
				if(measurement != null)
					try {
						boolean measurementAdded = activeBatch.addMeasurement(measurement[0]);
						if(!measurementAdded)
						{
							batchGUI.showInformationMessage("measurements should be taken equally", "measurements not equal");
						} else
						{
							mainController.getDatabaseController().addToDB(measurement[0]);
							batchGUI.updateTable(activeBatch);
						}

					} catch (DataBaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (ConnectionException e) {
				//				e.printStackTrace();
				batchGUI.updateLog("Was not able to connect to DAQ");
				batchGUI.updateLog("Check your connection to the DAQ");
			}
		} else batchGUI.showInformationMessage("You have to create or load batch before making measurements", "No active batch");

		System.out.println("measuring stroke value...");
		// TODO something fancy to get the stroke measurement
	}

	public void btnLeakCurrent() {

		

		if(activeBatch == null){
			batchGUI.showInformationMessage("You have to create batch before reading measurements", "No batch settings loaded");
		} else{
			String path = batchGUI.getDasyPath();
			if(path != null){
				if(this.dl != null){
					this.dl.setPath(path);
				}
				else{
					this.dl = new DirectoryListener(path, this);
					dl.start();
				}
			}
		}
	}

	public void btnLogOutPressed() {
		if(this.dl != null){
		this.dl.interrupt();
		System.out.println(this.dl.interrupted());
		}
		mainController.logOut();
	}
	
	public void addLeakMeasurement(String path, String filename){
		// creating leak measurement to be added to batch and saved in database
		long timestamp = Long.parseLong(PropertyHelper.readFromProperty("config", "leakvalue"));
		
		System.out.println("timestamp: " + timestamp);
		System.out.println(path + "/" + filename);
		
		Measurement measurement = mainController.getDasyController().getCurrentValue(timestamp, path + "/" + filename);
		measurement.setBatchID(activeBatch.getBatchID());
		measurement.setElementNo(activeBatch.getCurrentLeakElement());
		// adds the measurement to guis JTable before saved in database
		
		batchGUI.updateLog("new leak measurement: " + measurement.getMeasureValue());
		batchGUI.updateLog("read from: " + filename);

		try {
			boolean measurementAdded = activeBatch.addMeasurement(measurement);
			if(!measurementAdded)
			{
				batchGUI.showInformationMessage("measurements should be taken equally", "measurements not equal");
			} else
			{
				mainController.getDatabaseController().addToDB(measurement);
				batchGUI.updateTable(activeBatch);
			}
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteStrokeMeasurement(){
		if(activeBatch.deleteLastStrokeMeasurement()){
			mainController.getDatabaseController().deleteMeasurement(activeBatch.getBatchID(), activeBatch.getCurrentStrokeElement()-1, MeasurementType.STROKE);
		}
		else{
			batchGUI.showInformationMessage("Not able to delete selected measurement!", "Error");
		}
	}
	
	public void deleteLeakMeasurement(){
		if(activeBatch.deleteLastLeakMeasurement()){
			mainController.getDatabaseController().deleteMeasurement(activeBatch.getBatchID(), activeBatch.getCurrentLeakElement()-1, MeasurementType.LEAK);
		}
		else{
			batchGUI.showInformationMessage("Not able to delete selected measurement!", "Error");
		}
	}

	public IMainController getMainController() {
		return mainController;
	}

	public void setActiveBatch(Batch activeBatch){
		this.activeBatch = activeBatch;
		int profileId = this.activeBatch.getProfileID();
		BatchProfile bp = null;
		batchGUI.updateLog("active batch: " + this.activeBatch.getBatchString());
		try {
			batchGUI.updateLog("loading batch settings...");
			bp = mainController.getDatabaseController().getBatchProfile(profileId);
			this.batchGUI.updateSettings(bp.getProfileSettings());
		} catch (DataBaseException e) {
			batchGUI.updateLog("failed to load batch profile from database");
			e.printStackTrace();
		}	
		batchGUI.updateTable(this.activeBatch);
	}

	public Batch getActiveBatch(){
		return activeBatch;
	}

	public IBatchMeasureGui getBatchMeasureGui(){
		return batchGUI;
	}

	@Override
	public void updateLog(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyElement(boolean verified, int elementNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMeasurements(int elementNumber, boolean verified) {
		System.out.println("updateMeasurements, verified: " + verified);
		activeBatch.updateMeasurements(elementNumber, verified);
		
	}



}
