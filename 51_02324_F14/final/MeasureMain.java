import javaMeasure.control.MainController;
import javaMeasure.control.interfaces.IMainController;

public class MeasureMain {

	public static void main(String[] args) {
		IMainController mctrl = new MainController();
		mctrl.run();
	}

}
