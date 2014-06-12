package javaMeasure.view.interfaces;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public interface ILoginGui extends Runnable,ActionListener, WindowListener {
	void setUserList(String[] users);
	void showGui(boolean b);
	String getSelectedUser();
	void showNoSuchUser();
	void showUserAlreadyExists();
	void enableButtons(boolean b);
}
