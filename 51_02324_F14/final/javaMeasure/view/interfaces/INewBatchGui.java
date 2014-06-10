package javaMeasure.view.interfaces;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;

import javaMeasure.BatchProfile;

public interface INewBatchGui extends ActionListener, EventListener, Runnable{
	void setVisibility(boolean visible);
	void setSettings(BatchProfile settingList);
	ArrayList<String> getSettings();
	void showInformationMessage(String message, String title);

}
