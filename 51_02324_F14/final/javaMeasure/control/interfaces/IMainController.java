package javaMeasure.control.interfaces;

import javaMeasure.Batch;
import javaMeasure.User;

public interface IMainController {
	void run();
	void userLoggedIn(User user);
	User getActiveUser();
	public ILoginController getLoginController();
	public IDatabaseController getDatabaseController();
	public ICConnector getcConnector();
	void startNewBatchController();
	void startNewBatchController(Batch activeBatch);
//	void startBatchMeasure(); not neccessary after mainMenuController is removed
	void logOut();
	IDasyFileReader getDasyController();
	IBatchMeasureController getBatchMeasureController();
	int closeProgram();
}
