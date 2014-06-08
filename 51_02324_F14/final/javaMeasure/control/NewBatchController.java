package javaMeasure.control;
import java.util.ArrayList;

import javaMeasure.Batch;
import javaMeasure.BatchProfile;
import javaMeasure.BatchSetting;
import javaMeasure.PropertyHelper;
import javaMeasure.control.interfaces.*;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.view.interfaces.*;
import javaMeasure.view.*;

public class NewBatchController implements INewBatchController {
	private IMainController mainController;
	private INewBatchGui newBatchGui;
	private ArrayList<BatchProfile> allBatchProfiles;

	public NewBatchController(IMainController mainController){

		this.mainController = mainController;
		this.newBatchGui = new NewBatchGui(this);
		try {
			this.newBatchGui.setSettings(getDefaultBatchProfile());
		} catch (DataBaseException e) {
			System.err.println("Database error when trying to retrieve default batch profile");
			System.err.println(e);
		}
		this.newBatchGui.setVisibility(true);
	}

	public IMainController getMainController() {
		return mainController;
	}

	/**
	 * call to this method should handle null pointer exceptions in case. key, value, or profile with specific key does not exist
	 * should probably be a private method. Not sure if it is needed elsewhere - Rúni
	 */
	@Override
	public BatchProfile getDefaultBatchProfile() throws DataBaseException {
		return getBatchProfile(PropertyHelper.readFromProperty("defaultProfile"));
	}
	
	/**
	 * is currently not being used, but should be in the future - Rúni
	 */
	public void setDefaultBatchProfile(String batchProfilename) {
		PropertyHelper.writeToProperty("defaultProfile", batchProfilename);
	}

	@Override
	public BatchProfile getBatchProfile(String profileName) throws DataBaseException {
		return mainController.getDatabaseController().getBatchProfile(profileName);
	}
	//TODO cleanup and handle exceptions
	@Override
	public ArrayList<String> getSavedBatchProfiles(){
		try{
			return mainController.getDatabaseController().getSavedBatchProfilesNames();
		} catch (DataBaseException dbe){
			return null;
		}
	}

	/**
	 * saves batch settings with chosen name.
	 * notice that the name can not be under 2 characters. 
	 * the null check appears when the user presses cancel instead, then we just have to make sure that code is not run.
	 */
	@Override
	public void saveBatchSettingsPressed(String profileName, ArrayList<String> profileSettings) {

		if(profileName == null){System.out.println("canceled profile saving");}
		else if(profileName.length() < 2){
			this.newBatchGui.showInformationMessage("Name should be at least 2 characters", "Invalid name"); //TODO remove hardcoded 2, should be regular expression input verification - Rúni 
		}
		else if(profileName != null){
			ArrayList<BatchSetting> profile = new ArrayList<>();
			BatchProfile bp = null;
			try {
				bp = mainController.getDatabaseController().getBatchProfile(profileName);
			} catch (DataBaseException e1) {
			}
			if(bp == null){
				for(int i = 0; i < profileSettings.size(); i++){
					profile.add(new BatchSetting(i, null, null, profileSettings.get(i)));
				}
				try {
					mainController.getDatabaseController().saveBatchProfile(new BatchProfile(profileName, profile));
				} catch (DataBaseException e) {
					// TODO should be more specific. profileAlreadyExistsException
					// TODO needs further implementation
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void createBatchpressed(String batchString, ArrayList<String> profileSettings) {
		ArrayList<BatchSetting> settings = new ArrayList<>();
		boolean verification = false;
		//Reading settings from GUI textboxes
		for(int i = 0; i < profileSettings.size(); i++){
			settings.add(new BatchSetting(i, null, null, profileSettings.get(i)));
		}
		try {
			verification = isBatchInDB(batchString);
		} catch (DataBaseException e1) {
			e1.printStackTrace();
		}
		if(batchString.equals("")){
			newBatchGui.showInformationMessage("Batch name has to be chosen", "No batch name");
		}
		else if(verification){
			newBatchGui.showInformationMessage("A batch with this ID already exists!", "Could not create batch");;
		} else{
			// Creating batchProfile with settings from above
			BatchProfile bp = new BatchProfile(null, settings);
			int profileID = -1;
			try {
				//Saves batchProfile in DB and creates a batch with the batchProfile ID
				profileID = mainController.getDatabaseController().saveBatchProfile(bp);
				Batch b = new Batch(-1, batchString, profileID);
				//Saves batch and sets the active batch in batchMeasureController
				mainController.getDatabaseController().addToDB(b);
				mainController.getBatchMeasureController().setActiveBatch(b);
			} catch (DataBaseException e) {
				e.printStackTrace();
			}
			//Shift view to BatchMeasure view
			newBatchGui.setVisibility(false);
			mainController.getBatchMeasureController().showGui(true);
		}
	}

	@Override
	public void loadBatchSettingsPressed(String profilename) {
		BatchProfile bp = null;
		try {
			//Loads a batchProfile from DB
			bp =  getBatchProfile(profilename);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		//Updates settings on GUI.
		this.newBatchGui.setSettings(bp);
	}

	@Override
	public void annullerPressed() {
		newBatchGui.setVisibility(false);
		mainController.getBatchMeasureController().showGui(true);

	}

	@Override
	public boolean isBatchInDB(String batchNumber) throws DataBaseException {
		return mainController.getDatabaseController().isBatchInDB(batchNumber);

	}

	@Override
	public void deleteBatchProfilePressed(String profileName) throws DataBaseException {
		BatchProfile bp = null;
		try {
			//Loads a batchProfile from DB
			bp =  getBatchProfile(profileName);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		mainController.getDatabaseController().deleteBatchProfile(bp);

	}

	public void editBatchProfilePressed(String profileName)	throws DataBaseException {
		BatchProfile bp = null;
		try{
			bp = getBatchProfile(profileName);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		mainController.getDatabaseController().editBatchProfile(bp);
	}

	@Override
	public void saveEditedBatchSettingsPressed(String profileNameEdit,
			ArrayList<String> savingSettingsEdit) throws DataBaseException {
		deleteBatchProfilePressed(profileNameEdit);

	}
}
