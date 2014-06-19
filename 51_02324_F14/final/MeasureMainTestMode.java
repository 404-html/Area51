import javaMeasure.control.MainController;
import javaMeasure.control.interfaces.IMainController;

public class MeasureMainTestMode {

	public static void main(String[] args) {
		IMainController mctrl = new MainController(true);
		mctrl.run();
	}
}
