package javaMeasure.view.interfaces;

import java.awt.event.ActionListener;
import java.util.EventListener;

public interface ICalibrationGui extends ActionListener, EventListener, Runnable{
	 void setZeroVolt(double zeroVolt);
	 void setCalibrationVolt(double calibrationVolt);
	 void setCalibrationConstant(double calibrationConstant);
	 void enableOk(boolean enabled);
	 double getCalibrationLength();
	 void showGui(boolean b);
}