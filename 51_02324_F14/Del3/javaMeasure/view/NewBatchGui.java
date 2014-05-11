package javaMeasure.view;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javaMeasure.BatchProfile;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.INewBatchController;
import javaMeasure.view.interfaces.INewBatchGui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class NewBatchGui extends JFrame implements INewBatchGui{
	ArrayList<Component> profileSettings = new ArrayList<Component>();
	BatchProfile standardProfile;
	private JLabel lblBatch;
	private JTextField textBatch;
	private JSeparator separator;

	private INewBatchController newBatchController;
	private JButton btnSaveBatchSettings;
	
	public NewBatchGui(INewBatchController newBatchController) {
		setTitle("New Batch");
		this.newBatchController = newBatchController;
		getContentPane().setLayout(null);
		setBounds(0, 0, 552, 633);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBounds(10, 124, 529, 470);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSpecifications = new JLabel("<html><b>Specifications</b></html>");
		lblSpecifications.setBounds(10, 11, 129, 14);
		panel.add(lblSpecifications);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "1", "<html><b>Specifications</b></html>");

		JLabel lblOuterDiameter = new JLabel("outer diameter:");
		lblOuterDiameter.setBounds(10, 36, 129, 14);
		panel.add(lblOuterDiameter);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "2", "outer diameter:");

		JLabel lblInnerDiameter = new JLabel("inner diameter:");
		lblInnerDiameter.setBounds(10, 61, 129, 14);
		panel.add(lblInnerDiameter);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "3", "inner diameter:");

		JLabel lblThickness = new JLabel("thickness:");
		lblThickness.setBounds(10, 86, 129, 14);
		panel.add(lblThickness);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "4", "thickness:");

		JLabel lblElektMargOd = new JLabel("elekt. marg. OD:");
		lblElektMargOd.setBounds(10, 112, 129, 14);
		panel.add(lblElektMargOd);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "5", "elekt. marg. OD:");

		JLabel lblElektMargId = new JLabel("elekt. marg. ID:");
		lblElektMargId.setBounds(10, 137, 129, 14);
		panel.add(lblElektMargId);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "6", "elekt. marg. ID:");

		JLabel lblNumberOfActive = new JLabel("number of active layers:");
		lblNumberOfActive.setBounds(10, 162, 129, 14);
		panel.add(lblNumberOfActive);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "7", "number of active layers:");

		JLabel lblLayerThickness = new JLabel("layer thickness:");
		lblLayerThickness.setBounds(10, 187, 129, 14);
		panel.add(lblLayerThickness);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "8", "layer thickness:");

		JLabel lblCapacitans = new JLabel("capacitans:");
		lblCapacitans.setBounds(10, 212, 129, 14);
		panel.add(lblCapacitans);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "9", "capacitans:");

		JLabel lblStroke = new JLabel("stroke:");
		lblStroke.setBounds(10, 237, 129, 14);
		panel.add(lblStroke);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "10", "stroke");

		JLabel lblBlockingForce = new JLabel("blocking force:");
		lblBlockingForce.setBounds(10, 262, 129, 14);
		panel.add(lblBlockingForce);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "11", "blocking force:");

		JLabel lblMeasureCurrent = new JLabel("measure voltage:");
		lblMeasureCurrent.setBounds(10, 287, 129, 14);
		panel.add(lblMeasureCurrent);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "12", "measure voltage:");

		JLabel lblDcResistance = new JLabel("DC resistance:");
		lblDcResistance.setBounds(10, 362, 129, 14);
		panel.add(lblDcResistance);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "13", "DC resistance:");

		JLabel lblSpecialVisualInspection = new JLabel("special visual inspection:");
		lblSpecialVisualInspection.setBounds(10, 387, 129, 14);
		panel.add(lblSpecialVisualInspection);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "14", "special visual inspection:");

		JLabel lblSpectrum = new JLabel("spectrum:");
		lblSpectrum.setBounds(10, 412, 129, 14);
		panel.add(lblSpectrum);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "15", "spectrum:");

		JLabel lblNormValue = new JLabel("<html> <b>norm value</b></html>");
		lblNormValue.setBounds(149, 11, 68, 14);
		panel.add(lblNormValue);
		//		PropertyHelper.writeToProperty("newBatchGuiSetup", "16", "<html> <b>norm value</b></html>");

		// textfields for normal value
		for(int i = 0; i < 14; i++){
			if(i == 11 || i == 12){
			}
			else{
				JTextField text = new JTextField();
				text.setBounds(149, i*25+33, 68, 20);
				panel.add(text);
				profileSettings.add(text);
			}
		}
		// labels for the "+-" label 
		for(int i = 0; i < 14 ; i++){
			if(i == 3 || i == 4  || i == 10 || i == 11 || i == 12 ){

			}
			else{
				JLabel label = new JLabel("\u00B1");
				label.setBounds(227, 33+25*i, 33, 14);
				panel.add(label);
			}
		}

		JLabel lblTolerance = new JLabel("<html><b>tolerance</b></html>");
		lblTolerance.setBounds(274, 11, 68, 14);
		panel.add(lblTolerance);

		// textfields for tolerance
		for(int i = 0; i < 14 ; i++){
			if(i == 3 || i == 4 || i == 10 || i == 11 || i == 12){

			}
			else{
				JTextField text = new JTextField();
				text.setBounds(274, 33 + 25*i, 68, 20);
				panel.add(text);
				profileSettings.add(text);	
			}	
		}

		// text fields for inspection level
		for(int i = 0; i < 16; i++){
			JTextField text = new JTextField();
			text.setBounds(352, 33+25*i, 110, 20);
			panel.add(text);
			profileSettings.add(text);	
		}

		JLabel lblLeakCurrentMax = new JLabel("leak current, max:");
		lblLeakCurrentMax.setBounds(10, 337, 129, 14);
		panel.add(lblLeakCurrentMax);
		
		JCheckBox chckbxDynamicTest = new JCheckBox("");
		chckbxDynamicTest.setHorizontalTextPosition(SwingConstants.CENTER);
		chckbxDynamicTest.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxDynamicTest.setBounds(149, 311, 68, 23);
		profileSettings.add(chckbxDynamicTest);
		panel.add(chckbxDynamicTest);

		JCheckBox chckbxLeakCurrentMax = new JCheckBox("");
		chckbxLeakCurrentMax.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxLeakCurrentMax.setHorizontalTextPosition(SwingConstants.CENTER);
		chckbxLeakCurrentMax.setBounds(149, 333, 68, 23);
		profileSettings.add(chckbxLeakCurrentMax);
		panel.add(chckbxLeakCurrentMax);

		

		JCheckBox chckbxSpecialVisualInspection = new JCheckBox("");
		chckbxSpecialVisualInspection.setHorizontalTextPosition(SwingConstants.CENTER);
		chckbxSpecialVisualInspection.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxSpecialVisualInspection.setBounds(149, 383, 68, 23);
		profileSettings.add(chckbxSpecialVisualInspection);
		panel.add(chckbxSpecialVisualInspection);

		JCheckBox chckbxSpectrum = new JCheckBox("");
		chckbxSpectrum.setHorizontalTextPosition(SwingConstants.CENTER);
		chckbxSpectrum.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxSpectrum.setBounds(149, 408, 68, 23);
		profileSettings.add(chckbxSpectrum);
		panel.add(chckbxSpectrum);



		JLabel lblDynamicTest = new JLabel("dynamic test:");
		lblDynamicTest.setBounds(10, 312, 129, 14);
		panel.add(lblDynamicTest);

		JLabel lblInspectionLevel = new JLabel("<html><b> inspection level </b></html>");
		lblInspectionLevel.setBounds(352, 11, 110, 14);
		panel.add(lblInspectionLevel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 529, 113);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblBatch = new JLabel("<html><b> Batch:</b></html>");
		lblBatch.setBounds(10, 70, 46, 14);
		panel_1.add(lblBatch);

		textBatch = new JTextField();
		textBatch.setBounds(66, 67, 86, 20);
		panel_1.add(textBatch);
		textBatch.setColumns(10);

		separator = new JSeparator();
		separator.setBounds(10, 100, 509, 2);
		panel_1.add(separator);

		setDefaultSettings();

		JButton btnLoadBatchSettings = new JButton("Load Batch Settings");
		btnLoadBatchSettings.setBounds(270, 66, 129, 23);
		btnLoadBatchSettings.setActionCommand("loadSettings");
		panel_1.add(btnLoadBatchSettings);

		btnSaveBatchSettings = new JButton("Save Batch Settings");
		btnSaveBatchSettings.setBounds(270, 32, 129, 23);
		btnSaveBatchSettings.setActionCommand("saveBatchSettings");
		panel_1.add(btnSaveBatchSettings);
		
		
				JButton btnCreateBatch = new JButton("Create Batch");
				btnCreateBatch.setBounds(409, 32, 110, 23);
				panel_1.add(btnCreateBatch);
				btnCreateBatch.setActionCommand("createBatch");
				
				JButton btnAnnuller = new JButton("Annuller");
				btnAnnuller.setBounds(409, 66, 110, 23);
				btnAnnuller.setActionCommand("annuller");
				panel_1.add(btnAnnuller);
				
				JButton btnDeleteSettings = new JButton("Delete Settings");
				btnDeleteSettings.setActionCommand("deleteSettings");
				btnDeleteSettings.setBounds(131, 32, 129, 23);
				panel_1.add(btnDeleteSettings);
				

		btnLoadBatchSettings.addActionListener(this);
		btnSaveBatchSettings.addActionListener(this);
		btnDeleteSettings.addActionListener(this);
		btnCreateBatch.addActionListener(this);
		btnAnnuller.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setDefaultSettings() {

		try {
			setSettings(newBatchController.getDefaultBatchProfile());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	// TODO not up to date need refreshening
	public void setSettings(BatchProfile settingList){
		if(settingList != null){
			for(int i = 0; i < profileSettings.size(); i++){
				if(i >= profileSettings.size() || i >= settingList.getProfileSettings().size()){
					break;
				}
				Component current = profileSettings.get(i);

				if(current instanceof JTextField){
					JTextField text = (JTextField) current;
					text.setText(settingList.getProfileSettings().get(i).getValue());
				}
				else if(current instanceof JCheckBox){
					JCheckBox box = (JCheckBox) current;
					box.setSelected(Boolean.parseBoolean(settingList.profileSettings.get(i).getValue()));
				}
				else System.err.println("fatal error :(");
			}
		}
	}

	public void setVisibility(boolean visible) {
		setVisible(visible);

	}
	public ArrayList<String> getSettings(){
		ArrayList<String> settings = new ArrayList<>();
		for(int i = 0; i < profileSettings.size(); i++){
			if(profileSettings.get(i) instanceof JCheckBox){
				JCheckBox box = (JCheckBox) profileSettings.get(i);
				settings.add(String.valueOf(box.isSelected()));
				box.enableInputMethods(true);
			}
			else if(profileSettings.get(i) instanceof JTextField){
				JTextField field = (JTextField) profileSettings.get(i);
				settings.add(field.getText());
			}
			else System.err.println("failed to get settings or some of them");
		}
		return settings;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		//TODO move control to control class
		case "createBatch":
			ArrayList<String> settings = getSettings();
			String batchNumber = textBatch.getText();
			newBatchController.createBatchpressed(batchNumber, settings);
			break;
		case "loadSettings":
			 ArrayList<String> list = newBatchController.getSavedBatchProfiles();
			 Object o = JOptionPane.showInputDialog(getContentPane(), "Choose profile: ", "Profile selection", JOptionPane.QUESTION_MESSAGE, null, list.toArray(), list.get(0));
			 if(o != null){
			 String profilename = o.toString();
			 newBatchController.loadBatchSettingsPressed(profilename);
			 }
			// TODO finish implementation when loading saved settings
			break;
		case "saveBatchSettings":
			String profileName = JOptionPane.showInputDialog(getContentPane(), "Enter profile name: ", "Saving profile", JOptionPane.QUESTION_MESSAGE);
			ArrayList<String> savingSettings = getSettings();
			newBatchController.saveBatchSettingsPressed(profileName, savingSettings);
			System.out.println("save batch settings");
			break;
		case "annuller":
			newBatchController.annullerPressed();
			break;
		case "deleteSettings":
			 ArrayList<String> list2 = newBatchController.getSavedBatchProfiles();
			 Object o2 = JOptionPane.showInputDialog(getContentPane(), "Choose profile to delete: ", "Delete Profile", JOptionPane.QUESTION_MESSAGE, null, list2.toArray(), list2.get(0));
			 if(o2 != null){
				 profileName = o2.toString();
				 try {
					newBatchController.deleteBatchProfilePressed(profileName);
				} catch (DataBaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
			break;
		case "editProfile":
			 ArrayList<String> list3 = newBatchController.getSavedBatchProfiles();
			 Object o3 = JOptionPane.showInputDialog(getContentPane(), "Choose profile to edit: ", "Edit Profile", JOptionPane.QUESTION_MESSAGE, null, list3.toArray(), list3.get(0));
			 if(o3 != null){
				 profileName = o3.toString();
				 try {
					newBatchController.editBatchProfilePressed(profileName);
				} catch (DataBaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
			break;
		default: System.err.println("ActionCommand is not recognized");
		}		
	}

	@Override
	public void run() {
		setVisible(true);		
	}

	@Override
	public void showInformationMessage(String message, String title){
		JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
