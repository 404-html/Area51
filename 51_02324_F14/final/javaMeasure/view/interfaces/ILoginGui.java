package javaMeasure.view.interfaces;

import java.awt.event.ActionListener;

public interface ILoginGui extends Runnable,ActionListener {
	void setUserList(String[] users);
	void showGui(boolean b);
	String getSelectedUser();
	void showNoSuchUser();
	void showUserAlreadyExists();
	void enableButtons(boolean b);
	public enum Status {Connecting, Connected, Noconnection};
	void changestatus(Status s);
	void showInvalidUsername();	
	
}
