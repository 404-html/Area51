import javaMeasure.control.MainController;
import javaMeasure.control.interfaces.IMainController;


public class MainNoTestMode {

	public static void main(String[] args) {
		IMainController mctrl = new MainController(false);
		mctrl.run();
	}

}
