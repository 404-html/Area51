package javaMeasure.control.interfaces;

import javaMeasure.Batch;

public interface IBatchMeasureController {

	void showGui(boolean visible);

	void btnStrokePressed();

	void btnNewBatchPressed();
	
	void btnEditBatchSettingsPressed();

	void btnLeakCurrent();

	void btnLogOutPressed();
	
	IMainController getMainController();

	void setActiveBatch(Batch b);

	void btnGetBatchPressed();

	void updateLog(String string);
	
	void addLeakMeasurement(String path, String filename);
	
	void verifyElement(boolean verified, int elementNumber);
	
	void deleteStrokeMeasurement();
	
	void deleteLeakMeasurement();
	
	
	
}
