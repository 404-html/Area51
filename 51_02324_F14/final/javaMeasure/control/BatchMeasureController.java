package javaMeasure.control;

import java.awt.EventQueue;
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
		// TODO Auto-generated method stub

	}

	// TODO Finish this - Martin
	public void btnEditBatchSettingsPressed(){
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
							updateTable();
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

	public void btnLeakCurrent(String path) {

		if(activeBatch == null){
			batchGUI.showInformationMessage("You have to create batch before reading measurements", "No batch settings loaded");
		} else{
			// for now using JOptionPane, later we may be able to make it automatic..?
			if(path != null){
				System.out.println("batchId: " + activeBatch.getBatchID());
				// creating leak measurement to be added to batch and saved in database
				long timestamp = Long.parseLong(PropertyHelper.readFromProperty("config", "leakvalue"));
				Measurement measurement = mainController.getDasyController().getCurrentValue(timestamp, path);
				measurement.setBatchID(activeBatch.getBatchID());
				measurement.setElementNo(activeBatch.getCurrentLeakElement());
				// adds the measurement to guis JTable before saved in database

				try {
					boolean measurementAdded = activeBatch.addMeasurement(measurement);
					if(!measurementAdded)
					{
						batchGUI.showInformationMessage("measurements should be taken equally", "measurements not equal");
					} else
					{
						mainController.getDatabaseController().addToDB(measurement);
						updateTable();
					}
				} catch (DataBaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	
	private void updateTable(Batch batch){
		ArrayList<Object[]> list = batch.getMeasurementsList();
		Object[][] updatedList = new Object[list.size()][3];
		for(int i = 0; i < list.size(); i++){
			updatedList[i][0] = list.get(i)[0];
			updatedList[i][1] = list.get(i)[1];
			updatedList[i][2] = list.get(i)[2];
		}
		batchGUI.updateTable(updatedList);
	}

	private void updateTable(){

		ArrayList<Object[]> list = activeBatch.getMeasurementsList();
		Object[][] updatedList = new Object[list.size()][3];

		for(int i = 0; i < updatedList.length; i++){
			updatedList[i][0] = list.get(i)[0];
			updatedList[i][1] = list.get(i)[1];
			updatedList[i][2] = list.get(i)[2];
		}
		batchGUI.updateTable(updatedList);

	}

	public void btnLogOutPressed() {
		mainController.logOut();
	}

	public IMainController getMainController() {
		return mainController;
	}
	//	public void addMeasurementToTable(Measurement measurement){
	//		ArrayList<Measurement> leak = activeBatch.getLeak();
	//		ArrayList<Measurement> stroke = activeBatch.getStroke();

	//		int size = 0;
	//		if(stroke.size() == leak.size())
	//			size = leak.size()+1;
	//		else size = stroke.size();
	//
	//		Object[][] values = new Object[size][3];
	//
	//		int i = 0;
	//		for(; i <size; i++){
	//			values[i][0] = String.valueOf(i);
	//			if(i < stroke.size()){
	//				values[i][1] = (String.valueOf(stroke.get(i).getMeasureValue()));
	//			}
	//			if(i < leak.size()){
	//				values[i][2] = (String.valueOf(leak.get(i).getMeasureValue()));
	//			}
	//		}
	//		if(measurement.getMeasurementType().equals(MeasurementType.LEAK))
	//			values[i+1][2] = String.valueOf(measurement.getMeasureValue());
	//		else values[i+1][1] = String.valueOf(measurement.getMeasureValue());
	//		this.batchGUI.updateTable(values);

	//	}

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
		updateTable(this.activeBatch);
	}

	public Batch getActiveBatch(){
		return activeBatch;
	}




}
