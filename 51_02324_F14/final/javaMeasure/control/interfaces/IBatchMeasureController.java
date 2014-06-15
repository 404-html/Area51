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
	
	Batch getActiveBatch();

	void btnGetBatchPressed();
	
	void addLeakMeasurement(String path, String filename);

	void updateMeasurements(int rowAtPoint, boolean tableData);

	void btnDeleteStroke();

	void btnDeleteLeak();

	void btnApproveBatchPressed();
	
	
	
	
}
