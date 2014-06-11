package javaMeasure.control.interfaces;

public interface ICalibrationController {
	void zeroBtnPressed();
	void calibrateBtnPressed();
	void okBtnPressed();
	void cancelBtnPressed();
	double getCalibrationConstant();
}
