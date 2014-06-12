package javaMeasure.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.text.DecimalFormat;

import javaMeasure.control.interfaces.ICalibrationController;
import javaMeasure.view.interfaces.ICalibrationGui;

public class CalibrationGui extends JFrame implements ICalibrationGui {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField zeroVolt;
	private JTextField calibrateVolt;
	private JTextField calibrationLength;
	private JTextField calibrationConstant;
	private ICalibrationController calibCtrl;
	private JButton btnOk;
	private DecimalFormat dec = new DecimalFormat("#0.000000");


	/**
	 * Create the frame.
	 */
	public CalibrationGui(ICalibrationController calibCtrl) {
		this.calibCtrl = calibCtrl;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		zeroVolt = new JTextField();
		zeroVolt.setEditable(false);
		zeroVolt.setText("-");
		zeroVolt.setBounds(338, 47, 86, 20);
		contentPane.add(zeroVolt);
		zeroVolt.setColumns(10);
		
		JLabel lblZeroValue = new JLabel("Zero Value");
		lblZeroValue.setBounds(10, 50, 123, 14);
		contentPane.add(lblZeroValue);
		
		JLabel lblNewLabel = new JLabel("Micrometer Screw Calibration");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 286, 14);
		contentPane.add(lblNewLabel);
		
		calibrateVolt = new JTextField();
		calibrateVolt.setEditable(false);
		calibrateVolt.setText("-");
		calibrateVolt.setBounds(338, 77, 86, 20);
		contentPane.add(calibrateVolt);
		calibrateVolt.setColumns(10);
		
		calibrationLength = new JTextField();
		calibrationLength.setText("1000");
		calibrationLength.setBounds(105, 77, 86, 20);
		contentPane.add(calibrationLength);
		calibrationLength.setColumns(10);
		
		JLabel lblCalibrationValue = new JLabel("Calibration value");
		lblCalibrationValue.setBounds(10, 80, 123, 14);
		contentPane.add(lblCalibrationValue);
		
		JLabel lbls = new JLabel("µm");
		lbls.setBounds(201, 80, 19, 14);
		contentPane.add(lbls);
		
		JLabel lblV = new JLabel("V");
		lblV.setBounds(434, 50, 46, 14);
		contentPane.add(lblV);
		
		JLabel label = new JLabel("V");
		label.setBounds(434, 80, 46, 14);
		contentPane.add(label);
		
		calibrationConstant = new JTextField();
		calibrationConstant.setEditable(false);
		calibrationConstant.setText("-");
		calibrationConstant.setBounds(338, 108, 86, 20);
		contentPane.add(calibrationConstant);
		calibrationConstant.setColumns(10);
		
		JLabel lblVm = new JLabel("µm/V");
		lblVm.setBounds(434, 111, 46, 14);
		contentPane.add(lblVm);
		
		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.setBounds(239, 142, 89, 23);
		btnOk.setActionCommand("Ok");
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(335, 142, 89, 23);
		btnCancel.setActionCommand("Cancel");
		contentPane.add(btnCancel);
		
		JButton btnCalibrate = new JButton("Calibrate");
		btnCalibrate.setBounds(239, 76, 89, 23);
		btnCalibrate.setActionCommand("Calibrate");
		contentPane.add(btnCalibrate);
		
		JButton btnZero = new JButton("Zero");
		btnZero.setBounds(239, 46, 89, 23);
		btnZero.setActionCommand("Zero");
		contentPane.add(btnZero);
		
		JLabel lblCalibrationConstant = new JLabel("Calibration constant");
		lblCalibrationConstant.setBounds(10, 111, 123, 14);
		contentPane.add(lblCalibrationConstant);
		
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		btnCalibrate.addActionListener(this);
		btnZero.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Ok":
			this.calibCtrl.okBtnPressed();
			break;
		case "Cancel":
			this.calibCtrl.cancelBtnPressed();
			break;
		case "Calibrate":
			this.calibCtrl.calibrateBtnPressed();
			break;
		case "Zero":
			this.calibCtrl.zeroBtnPressed();
			break;
		default:
			break;
		}
		
	}

	@Override
	public void run() {
		this.showGui(true);
		
	}

	@Override
	public void setZeroVolt(double zeroVolt) {
		this.zeroVolt.setText(dec.format(zeroVolt));
		
	}

	@Override
	public void setCalibrationVolt(double calibrationVolt) {
		this.calibrateVolt.setText(dec.format(calibrationVolt));
		
	}

	@Override
	public void setCalibrationConstant(double calibrationConstant) {
		this.calibrationConstant.setText(dec.format(calibrationConstant));
		
	}

	@Override
	public void enableOk(boolean enabled) {
		btnOk.setEnabled(enabled);
		
	}

	@Override
	public double getCalibrationLength() throws NumberFormatException {
		double CalibrationLength;
			CalibrationLength = Double.parseDouble(this.calibrationLength.getText());
		return CalibrationLength;
	}

	@Override
	public void showGui(boolean b) {
		setVisible(b);
		
	}
}
