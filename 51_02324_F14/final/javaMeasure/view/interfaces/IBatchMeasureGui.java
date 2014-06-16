package javaMeasure.view.interfaces;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;

import javaMeasure.Batch;
import javaMeasure.BatchSetting;

public interface IBatchMeasureGui extends ActionListener, EventListener, Runnable {

	void updateTable(Object[][] list);
	void setVisibility(boolean visible);
	boolean createNewBatch();
	void updateSettings(ArrayList<BatchSetting> settings);
	public void updateTable(ArrayList<ArrayList<String>> data);
	void showPopupMessage(String message, String title);
	public void updateLog(String update);
	public String getLoadBatchName(String[] batchList);
	public String getDasyPath();
	public void updateTable(Batch batch);
	void btnBatchApproved(boolean enable);
	void enableDeleteStroke(boolean enable);
	void enableDeleteLeak(boolean enable);
	void enableEditBatchSettings(boolean enable);
	void enableStrokeMeasurement(boolean enable);
	void enableLeakMeasurement(boolean enable);

}
