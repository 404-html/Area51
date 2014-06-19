package javaMeasure.view;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.io.File;
import java.util.ArrayList;

import javaMeasure.BatchSetting;
import javaMeasure.PropertyHelper;
import javaMeasure.control.interfaces.IBatchMeasureController;
import javaMeasure.view.interfaces.IBatchMeasureGui;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;




@SuppressWarnings("serial")
public class BatchMeasureGui extends JFrame implements IBatchMeasureGui {
	private static final String DASY_PATH = "dasyPath";
	private static final int COLUMNS_LENGTH = 4;
	private JButton btnEditBatchSettings;
	private JButton btnDeleteLeak;
	private JButton btnLeakCurrentMeasurement;
	private JButton btnStrokeMeasurement;
	private JButton btnDeleteStroke;
	private JButton btnApproveBatch;

	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollTable;
	private JScrollPane logScroll;
	private DefaultTableModel logModel;
	private Object[][] tableData;
	private ArrayList<String> logInfo = new ArrayList<>(); // list used to update log. updates are added to this list
	private JList<Object> log; // actual log list.

	// 0 to 13 is normal values, 14 to 22 is tolerance and 23 to 36 is inspection level
	private ArrayList<Component> profileSettings = new ArrayList<>();
	private IBatchMeasureController batchMeasureController;
	private Object columnNames[] = 	{"verified", "Element", "Stroke Value", "Leak Current"};

	// constructor
	public BatchMeasureGui(IBatchMeasureController batchMeasureController) {

		this.batchMeasureController = batchMeasureController;
		setTitle("Logged in as: " + batchMeasureController.getMainController().getActiveUser().getUserName());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setBounds(100, 100, 1040, 650);

		for(Frame f : getFrames()){
			// WindowAdapter() implements all window listeners as empty methods. therefore it is only neccessary to override needed methods
			// this is used to make sure the C# component process is killed when program is closed
			f.addWindowListener(new WindowAdapter()  {
				@Override
				public void windowClosing(WindowEvent arg0) {
					BatchMeasureGui.this.batchMeasureController.getMainController().getcConnector().closeProcess();
				}
			});
		}

		JButton btnNewBatch = new JButton("New Batch");
		btnNewBatch.setActionCommand("newBatch");
		btnNewBatch.setBounds(360, 10, 100, 25);
		getContentPane().add(btnNewBatch);

		JButton btnGetBatch = new JButton("Load Batch");
		btnGetBatch.setActionCommand("getBatch");
		btnGetBatch.setBounds(470, 10, 100, 25);
		getContentPane().add(btnGetBatch);

		btnEditBatchSettings = new JButton("Edit Settings");
		btnEditBatchSettings.setActionCommand("editBatchSettings");
		btnEditBatchSettings.setBounds(360, 50, 215, 25);
		getContentPane().add(btnEditBatchSettings);

		btnLeakCurrentMeasurement = new JButton("Leak Current Measurement");
		btnLeakCurrentMeasurement.setActionCommand("leakCurrent");
		btnLeakCurrentMeasurement.setBounds(360, 90, 215, 25);
		getContentPane().add(btnLeakCurrentMeasurement);

		btnStrokeMeasurement = new JButton("Stroke Measurement");
		btnStrokeMeasurement.setActionCommand("stroke");
		btnStrokeMeasurement.setBounds(360, 130, 215, 25);
		getContentPane().add(btnStrokeMeasurement);

		btnApproveBatch = new JButton("Approve batch");
		btnApproveBatch.setActionCommand("approveBatch");
		btnApproveBatch.addActionListener(this);
		btnApproveBatch.setBounds(360, 290, 210, 23);
		getContentPane().add(btnApproveBatch);

		btnDeleteLeak = new JButton("Delete last leak");
		btnDeleteLeak.setBounds(360, 367, 210, 23);
		getContentPane().add(btnDeleteLeak);
		btnDeleteLeak.setActionCommand("deleteLeak");		

		btnDeleteStroke = new JButton("Delete last stroke");
		btnDeleteStroke.setBounds(360, 330, 210, 23);
		getContentPane().add(btnDeleteStroke);
		btnDeleteStroke.setActionCommand("deleteStroke");

		JButton btnLogout = new JButton("Log out");
		btnLogout.setBounds(360, 583, 215, 25);
		btnLogout.setActionCommand("logout");
		getContentPane().add(btnLogout);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.WHITE);
		separator.setForeground(Color.GRAY);
		separator.setBounds(580, 10, 10, 601);
		getContentPane().add(separator);

		setupLog();

		// table setup for measurements
		this.tableData = new String[][]{};
		setupTable();
		// label setup for settings. settings are all shown on labels
		setupLabels();

		// adding action listeners for all buttons
		btnNewBatch.addActionListener(this);
		btnGetBatch.addActionListener(this);
		btnEditBatchSettings.addActionListener(this);
		btnLeakCurrentMeasurement.addActionListener(this);
		btnStrokeMeasurement.addActionListener(this);
		btnDeleteLeak.addActionListener(this);
		btnDeleteStroke.addActionListener(this);
		btnLogout.addActionListener(this);

	}

	private void setupLabels(){
		//		labels with value names
		for(int i = 1; i < 21; i++){
			JLabel lblTitle = new JLabel();
			if(i>17){
				lblTitle.setBounds(670+90*(i-17), 135, 130, 15);
			} else{
				lblTitle.setBounds(610, 110+25*i, 130, 15);
			}
			String text = PropertyHelper.readFromProperty("newBatchGuiSetup", String.valueOf(i));
			lblTitle.setText(text);
			getContentPane().add(lblTitle);
		}

		JLabel lbllog = new JLabel("<html><b>Event log: </b></html>");
		lbllog.setBounds(10, 420, 90, 15);
		getContentPane().add(lbllog);

		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setBounds(610, 20, 90, 15);
		getContentPane().add(lblCustomer);

		JLabel lblItemDescription = new JLabel("Item description:");
		lblItemDescription.setBounds(610, 45, 90, 15);
		getContentPane().add(lblItemDescription);

		JLabel lblItemCode = new JLabel("Item code:");
		lblItemCode.setBounds(610, 70, 90, 15);
		getContentPane().add(lblItemCode);

		JLabel lblInternalOrderNumber = new JLabel("Internal order number:");
		lblInternalOrderNumber.setBounds(610, 95, 110, 15);
		getContentPane().add(lblInternalOrderNumber);

		JLabel lblDrawingNumber = new JLabel("Drawing number:");
		lblDrawingNumber.setBounds(825, 45, 85, 15);
		getContentPane().add(lblDrawingNumber);

		JLabel lblSpecification = new JLabel("Specification");
		lblSpecification.setBounds(825, 70, 85, 15);
		getContentPane().add(lblSpecification);

		JLabel lblVisualInspection = new JLabel("Visual inspection");
		lblVisualInspection.setBounds(825, 95, 85, 15);
		getContentPane().add(lblVisualInspection);

		JLabel lblCustomertxt = new JLabel("-");
		lblCustomertxt.setBounds(730, 20, 140, 15);
		getContentPane().add(lblCustomertxt);
		profileSettings.add(lblCustomertxt);

		JLabel lvlItemtxt = new JLabel("-");
		lvlItemtxt.setBounds(730, 45, 90, 15);
		getContentPane().add(lvlItemtxt);
		profileSettings.add(lvlItemtxt);

		JLabel lblItemCodetxt = new JLabel("-");
		lblItemCodetxt.setBounds(730, 70, 80, 15);
		getContentPane().add(lblItemCodetxt);
		profileSettings.add(lblItemCodetxt);

		JLabel lblInternaltxt = new JLabel("-");
		lblInternaltxt.setBounds(730, 95, 90, 15);
		getContentPane().add(lblInternaltxt);
		profileSettings.add(lblInternaltxt);

		JLabel lblDrawingtxt = new JLabel("-");
		lblDrawingtxt.setBounds(955, 45, 90, 15);
		getContentPane().add(lblDrawingtxt);
		profileSettings.add(lblDrawingtxt);

		JLabel lblSpecificationtxt = new JLabel("-");
		lblSpecificationtxt.setBounds(955, 70, 80, 15);
		getContentPane().add(lblSpecificationtxt);
		profileSettings.add(lblSpecificationtxt);

		JLabel lblVisualtxt = new JLabel("-");
		lblVisualtxt.setBounds(955, 95, 80, 15);
		getContentPane().add(lblVisualtxt);
		profileSettings.add(lblVisualtxt);

		JSeparator separator = new JSeparator();
		separator.setBounds(585, 120, 440, 2);
		getContentPane().add(separator);

		// labels for the "+-" label 
		for(int i = 0; i < 14 ; i++){
			if(i == 3 || i == 4  || i == 10 || i == 11 || i == 12 ){}
			else{
				JLabel label = new JLabel("\u00B1");
				label.setBounds(835, 160+25*i, 15, 15);
				getContentPane().add(label);
			}
		}
		//*************************** PROFILE SETTINGS*************************************
		//		 textfields for normal value
		// notice that textboxes that should only have true/false values, are not added here, but further down
		for(int i = 0; i < 14; i++){
			if(i == 11 || i == 12 ){

			}
			else{
				JLabel text = new JLabel();
				text.setBounds(775, 160+25*i, 50, 20);
				text.setText("-");
				getContentPane().add(text);
				profileSettings.add(text);
			}
		}

		// textfields for tolerance
		for(int i = 0; i < 14 ; i++){
			if(i == 3 || i == 4 || i == 10 || i == 11 || i == 12){}
			else{
				JLabel text = new JLabel();
				text.setBounds(885, 160 + 25*i, 30, 20);
				text.setText("-");
				getContentPane().add(text);
				profileSettings.add(text);
			}	
		}

		// text fields for inspection level
		for(int i = 0; i < 16; i++){
			JLabel text = new JLabel();
			text.setBounds(955, 160+25*i, 30, 20);
			text.setText("-");
			getContentPane().add(text);
			profileSettings.add(text);
		}

		// text fields for checkboxes has to be last, because the checkboxes are added last to the settings array 
		for(int i = 0; i < 5; i++){
			if(i == 2){	
			}
			else{
				JLabel text = new JLabel();
				text.setBounds(775, 435+25*i, 50, 20);
				text.setText("-");
				getContentPane().add(text);
				profileSettings.add(text);
			}
		}
	}

	//setup for the log
	private void setupLog(){

		logModel = new DefaultTableModel(null, columnNames);
		logModel.setColumnCount(1);

		log = new JList<Object>();
		logScroll = new JScrollPane();
		logScroll.setViewportView(log);
		log.setEnabled(false);
		logScroll.setBounds(10, 443, 340, 165);
		log.setBounds(10, 443, 340, 160);
		getContentPane().add(logScroll);

		logScroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			int rowCount = 0;
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0)
			{
				logModel.getRowCount();
				if(logModel.getRowCount() != rowCount){
					logScroll.getVerticalScrollBar().setValue(logModel.getRowCount() * 16);
				}
				rowCount = logModel.getRowCount();	
			}
		});
	}

	// extracted method because it is so large. sets up the JTable with all the extra features needed
	private void setupTable() {



		model = new DefaultTableModel(tableData, columnNames);
		table = new JTable(model){
			// overwriting jtable to highlight background colour of the last row
			public Component prepareRenderer(
					TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);

				if(row == getRowCount()-1){
					c.setBackground(Color.YELLOW);
					c.setForeground(Color.BLACK);
				} else {
					if(getValueAt(row, 0).toString().equalsIgnoreCase("false")){
						c.setBackground(Color.RED);
						c.setForeground(Color.BLACK);
					} else{
						c.setBackground(Color.WHITE);
						c.setForeground(Color.BLACK);
					}
				}
				return c;
			}

			// overwrite to make the first column (with checkboxes) the only one to be edited
			public boolean isCellEditable(int row, int col){
				switch (col){
				case 0: return true;
				default: return false;
				}
			}
			// inserting checkboxes in all rows in jtable
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				switch (column) {
				
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				default:
					return String.class;
				}
			}

		};

		table.setEditingColumn(0);
		table.setBounds(10, 10, 340, 380);
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE){

					if(e.getColumn() == 0){
						e.getLastRow();
						update(getGraphics()); // very important. updates graphics: when one checkbox is changed the whole row changes colour 
					}
				}
			}
		});

		scrollTable = new JScrollPane();
		scrollTable.setViewportView(table);
		scrollTable.setBounds(10, 10, 340, 380);
		table.setFillsViewportHeight(true);
		getContentPane().add(scrollTable);
		scrollTable.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			int rowCount = 0;
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0)
			{
				model.getRowCount();
				if(model.getRowCount() != rowCount){
					scrollTable.getVerticalScrollBar().setValue(model.getRowCount() * 16);

				}
				rowCount = model.getRowCount();	
			}
		});

		// used to find row when checkboxes are clicked
		// MouseAdapter implements MouseListener as empty methods, so only needed methods have to be overridden 
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// column 0 is where checkboxes are
				if(table.columnAtPoint(e.getPoint()) == 0){
					int row = table.rowAtPoint(e.getPoint());
					boolean verified = (boolean) table.getValueAt(row, 0);
					batchMeasureController.updateMeasurements(row, verified);
				}
			}
		});
	}

	// updates batch settings. used when batch is created or loaded 
	public void updateSettings(ArrayList<BatchSetting> settings){
		for(int i = 0; i < profileSettings.size(); i++){
			JLabel l = (JLabel) profileSettings.get(i);
			String value = settings.get(i).getValue();
			if(value.equalsIgnoreCase("")){
				l.setText("-"); // only for appearences to show that nothing is inserted
			}else{
				l.setText(settings.get(i).getValue());
			}
		}
	}

	// updates eventLog
	public void updateLog(String update){
		logInfo.add(update);
		Object[] list = logInfo.toArray();
		log.setListData(list);
		logModel.addColumn("log", list);

		log.updateUI();
		logModel.fireTableDataChanged();
		logScroll.getVerticalScrollBar().setValue(logScroll.getVerticalScrollBar().getMaximum());

	}

	// updates whole table
	public void updateTable(String[][] data){
		Object[][] list = new Object[data.length][COLUMNS_LENGTH];
		for(int i = 0; i < data.length; i++){
			for(int j = 0; j < COLUMNS_LENGTH; j++){
				if(j == 0)
				list[i][j] = Boolean.parseBoolean(data[i][j]);
				else list[i][j] = data[i][j];
			}
		}
		this.tableData = list;

		table.setGridColor(Color.BLUE); // just for fun
		
		model.setDataVector(list, columnNames);
		model.fireTableDataChanged(); // updates table
		model.getColumnClass(0).cast(Boolean.class); // not sure about the effect of this one
		scrollTable.getVerticalScrollBar().setValue(scrollTable.getVerticalScrollBar().getMaximum());


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "stroke":
			batchMeasureController.btnStrokePressed();
			break;
		case "newBatch": 
			batchMeasureController.btnNewBatchPressed();
			break;
		case "editBatchSettings":
			batchMeasureController.btnEditBatchSettingsPressed();
			break;
		case "getBatch":
			batchMeasureController.btnGetBatchPressed();
			break;
		case "leakCurrent":
			batchMeasureController.btnLeakCurrent();
			break;
		case "approveBatch":
			batchMeasureController.btnApproveBatchPressed();
			break;
		case "deleteLeak":
			batchMeasureController.btnDeleteLeak();
			break;
		case "deleteStroke":
			batchMeasureController.btnDeleteStroke();
			break;
		case "logout":
			System.out.println("logging out");
			batchMeasureController.btnLogOutPressed();
			break;
		default: System.err.println("Action event not recognized!");

		}

	}

	// returns the name of the chosen batch. for now, chosen batch will also be returned, even if exit is pressed
	public String getLoadBatchName(){
	return (String)	JOptionPane.showInputDialog(getContentPane(), "Insert batch name to download from database", "Get batch", JOptionPane.QUESTION_MESSAGE, null, null, "EKSAMEN  BATCH");
	}
	
	// browse for folder to be listened to
	public String getDasyPath(){
		File f = null;
		File defaultPath = null;
		// the PropertyHelper class is made in a way that, if no filename is chosen, it will read the default "config" file
		// therefore it only needs the key. this is used to be able to start from the last position a file was loaded
		if(PropertyHelper.readFromProperty(DASY_PATH) != null){
			defaultPath = new File(PropertyHelper.readFromProperty(DASY_PATH));
		}
		// JFileChooser is used to browse for folders
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(defaultPath); // sets the last folder as default in search window
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setDialogTitle("Select DasyLab folder");
		
		// makes only directories visible to user to choose
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// checks if ok is pressed or if the browse window is canceled. if canceled null is returned
		if(chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION){
			updateLog("You chose to open this folder: " + chooser.getSelectedFile().getName());
			f = chooser.getSelectedFile();
			// stores the folderpath in property to be loaded again next time
			PropertyHelper.writeToProperty(DASY_PATH, f.getAbsolutePath());
			return f.getAbsolutePath();
		}
		else return null;

	}

	@Override
	public boolean createNewBatch() {
		int answer = JOptionPane.showConfirmDialog(getContentPane(), "Do you want to make a new batch?", "new batch?", 0);
		if(answer == 0) return true;
		else return false;
	}

	// method used to show information messages. fx if there is no batch to load with the chosen name
	@Override
	public void showPopupMessage(String message, String title){
		JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public void setVisibility(boolean visible){
		this.setVisible(visible);
	}

	@Override
	public void run() {
		this.setVisible(true);
	}

	@Override
	public void btnBatchApproved(boolean enable) {
		if(!enable)
			btnApproveBatch.setText("Disapprove batch");
		else btnApproveBatch.setText("Approve batch");
	}

	@Override
	public void enableDeleteStroke(boolean enable) {
		btnDeleteStroke.setEnabled(enable);
	}

	@Override
	public void enableDeleteLeak(boolean enable) {
		btnDeleteLeak.setEnabled(enable);
	}

	@Override
	public void enableEditBatchSettings(boolean enable) {
		btnEditBatchSettings.setEnabled(enable);
	}

	@Override
	public void enableStrokeMeasurement(boolean enable) {
		btnStrokeMeasurement.setEnabled(enable);
	}

	@Override
	public void enableLeakMeasurement(boolean enable) {
		btnLeakCurrentMeasurement.setEnabled(enable);
	}
}
