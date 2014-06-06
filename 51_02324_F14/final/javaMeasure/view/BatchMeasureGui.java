package javaMeasure.view;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class BatchMeasureGui extends JFrame implements IBatchMeasureGui {
	private static final String DASY_PATH = "dasyPath";
	private static final int COLUMNS_LENGTH = 4;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel logModel;
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

		setBounds(100, 100, 1040, 500);

		JButton btnNewBatch = new JButton("New Batch");
		btnNewBatch.setActionCommand("newBatch");
		btnNewBatch.setBounds(361, 12, 100, 25);
		getContentPane().add(btnNewBatch);

		JButton btnGetBatch = new JButton("Load Batch");
		btnGetBatch.setActionCommand("getBatch");
		btnGetBatch.setBounds(471, 12, 100, 25);
		getContentPane().add(btnGetBatch);

		JButton btnEditBatchSettings = new JButton("Edit Settings");
		btnEditBatchSettings.setActionCommand("editBatchSettings");
		btnEditBatchSettings.setBounds(361, 48, 210, 23);
		getContentPane().add(btnEditBatchSettings);
		
		JButton btnLeakCurrentMeasurement = new JButton("Leak Current Measurement");
		btnLeakCurrentMeasurement.setActionCommand("leakCurrent");
		btnLeakCurrentMeasurement.setBounds(361, 82, 213, 25);
		getContentPane().add(btnLeakCurrentMeasurement);

		JButton btnStrokeMeasurement = new JButton("Stroke Measurement");
		btnStrokeMeasurement.setActionCommand("stroke");
		btnStrokeMeasurement.setBounds(361, 118, 213, 25);
		getContentPane().add(btnStrokeMeasurement);

		JButton btnLogout = new JButton("Log out");
		btnLogout.setBounds(361, 438, 213, 23);
		btnLogout.setActionCommand("logout");
		getContentPane().add(btnLogout);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.WHITE);
		separator.setForeground(Color.GRAY);
		separator.setBounds(581, 11, 7, 450);
		getContentPane().add(separator);

		//setup for the log
		logModel = new DefaultTableModel(null, columnNames);
		logModel.setColumnCount(1);
		log = new JList<Object>();
		JScrollPane logScroll = new JScrollPane();
		log.ensureIndexIsVisible(log.getMaxSelectionIndex());
		logScroll.setViewportView(log);
		log.setEnabled(false);
		logScroll.setBounds(10, 298, 341, 163);
		getContentPane().add(logScroll);

		// table setup for measurements
		setupTable();
		// label setup for settings. settings are all shown on labels
		setupLabels();

		// adding action listeners for all buttons
		btnNewBatch.addActionListener(this);
		btnGetBatch.addActionListener(this);
		btnLeakCurrentMeasurement.addActionListener(this);
		btnStrokeMeasurement.addActionListener(this);
		btnLogout.addActionListener(this);

	}

	private void setupLabels(){
		//		labels with value names
		for(int i = 1; i < 21; i++){
			JLabel lblTitle = new JLabel();
			if(i>17){
				lblTitle.setBounds(670+90*(i-17), 35, 130, 15);
			} else{
				lblTitle.setBounds(610, 10+25*i, 130, 15);
			}
			String text = PropertyHelper.readFromProperty("newBatchGuiSetup", String.valueOf(i));
			lblTitle.setText(text);
			getContentPane().add(lblTitle);
		}

		JLabel lblSettings = new JLabel("Settings:");
		lblSettings.setBounds(636, 12, 46, 14);
		getContentPane().add(lblSettings);

		JLabel lbllog = new JLabel("<html><b>Event log: </b></html>");
		lbllog.setBounds(10, 273, 87, 14);
		getContentPane().add(lbllog);

		// labels for the "+-" label 
		for(int i = 0; i < 14 ; i++){
			if(i == 3 || i == 4  || i == 10 || i == 11 || i == 12 ){}
			else{
				JLabel label = new JLabel("\u00B1");
				label.setBounds(835, 60+25*i, 15, 15);
				getContentPane().add(label);
			}
		}
		//*************************** PROFILE SETTINGS*************************************
		//		 textfields for normal value
		for(int i = 0; i < 14; i++){
			if(i == 11 || i == 12 ){

			}
			else{
				JLabel text = new JLabel();
				text.setBounds(775, 60+25*i, 50, 20);
				text.setText("-");
				getContentPane().add(text);
				profileSettings.add(text);
				System.out.println("normal value: " + i);
			}
		}

		// textfields for tolerance
		for(int i = 0; i < 14 ; i++){
			if(i == 3 || i == 4 || i == 10 || i == 11 || i == 12){}
			else{
				JLabel text = new JLabel();
				text.setBounds(885, 60 + 25*i, 30, 20);
				text.setText("-");
				getContentPane().add(text);
				profileSettings.add(text);
				System.out.println("tolerance: " + (i));
			}	
		}

		// text fields for inspection level
		for(int i = 0; i < 16; i++){
			JLabel text = new JLabel();
			text.setBounds(955, 60+25*i, 30, 20);
			text.setText("-");
			getContentPane().add(text);
			profileSettings.add(text);
			System.out.println("inspection level: " + (i));
		}

		// text fields for checkboxes has to be last, because the checkboxes are added last to the settings array 
		for(int i = 0; i < 5; i++){
			if(i == 2){	
			}
			else{
				JLabel text = new JLabel();
				text.setBounds(775, 335+25*i, 50, 20);
				text.setText("-");
				getContentPane().add(text);
				profileSettings.add(text);
			}
		}
	}

	// extracted method because it is so large. sets up the JTable with all the extra features needed
	private void setupTable() {

		String[][] data = new String[][]{{null,null,null,null}};

		model = new DefaultTableModel(data, columnNames);
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
					return Boolean.class;
				}
			}
		};

		table.setEditingColumn(0);
		table.setBounds(10, 12, 341, 239);
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE){
					
					if(e.getColumn() == 0){
						e.getLastRow();
						update(getGraphics()); // very important. updates graphics: when one checkbox is changed the whole row changes colour 
					}
				}
//				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() );
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 12, 341, 244);
		table.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);
		
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			int rowCount = 0;
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0)
			{
				model.getRowCount();
				if(model.getRowCount() != rowCount){
					System.out.println(scrollPane.getVerticalScrollBar().getMaximum());
				scrollPane.getVerticalScrollBar().setValue(model.getRowCount() * 16);
				
				}
				System.out.println(model.getRowCount());
				rowCount = model.getRowCount();
				// TODO Auto-generated method stub	
			}
		});

	}

	// updates batch settings. used when batch is created or loaded 
	public void updateSettings(ArrayList<BatchSetting> settings){
		for(int i = 0; i < profileSettings.size(); i++){
			JLabel l = (JLabel) profileSettings.get(i);
			String value = settings.get(i).getValue();
			System.out.println("update value: " + value);
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
		logModel.fireTableDataChanged();
	}

	// not used yet, but propably will be
	public void updateTable(ArrayList<ArrayList<String>> data) {
		updateTable(arrayListToArray(data));
	}

	// updates whole table
	public void updateTable(Object[][] data){
		Object[][] list = new Object[data.length][COLUMNS_LENGTH];
		for(int i = 0; i < data.length; i++)
		{
			list[i][0] = true;
			for(int j = 1; j < COLUMNS_LENGTH; j++)
			{
				try{
					list[i][j] = data[i][j-1];
				} catch(IndexOutOfBoundsException e){
					list[i][j] = null;
				}
			}
		}
		table.setGridColor(Color.BLUE); // just for fun
		model.setDataVector(list, columnNames);
		model.fireTableDataChanged(); // updates table
		model.getColumnClass(0).cast(Boolean.class); // not sure about the effect of this one

		
		//TODO finish!! find out how to get to bottom
		// does not work: ancestorListener, ContainerListener, HierarchyBoundsListener, hierarchyChanged, inputMethodListener
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		
//		scrollPane.getVerticalScrollBar().setValue(scrollPane.getMaximumSize().height);
//		int test = scrollPane.getHeight()-1;
		
	}
	

	// converter so it is possibly to add rows to the jtable with arraylist
	private Object[][] arrayListToArray(ArrayList<ArrayList<String>> list){
		Object[][] data = new Object[list.size()][COLUMNS_LENGTH];
		if(list.size() > 2)
			for(int i = 0; i < list.size(); i++)
			{
				data[i][0] = true;
				for(int j = 1; j < COLUMNS_LENGTH; j++)
				{
					data[i][j] =	list.get(i).get(j);
				}
			}
		return data;
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
			batchMeasureController.btnLeakCurrent(getDasyPath());
			break;
		case "logout":
			System.out.println("logging out");
			batchMeasureController.btnLogOutPressed();
			break;
		default: System.err.println("Action event not recognized!");

		}

	}

	// returns the name of the chosen batch. for now, chosen batch will also be returned, even if exit is pressed
	public String getLoadBatchName(String[] batchList){
		// by using combobox, we can have a dropdown list inside the popup ShowMessageDialog
		JComboBox<Object> jcb = new JComboBox<Object>(batchList);
		jcb.setEditable(true);
		JOptionPane.showMessageDialog(getContentPane(), jcb, "Enter batchnumber:", JOptionPane.QUESTION_MESSAGE);
		return (String) jcb.getSelectedItem();
	}

	// browse for file to read
	private String getDasyPath(){
		File f = null;
		File defaultPath = null;
		// the PropertyHelper class is made in a way that, if no filename is chosen, it will read the default "config" file
		// therefore it only needs the key. this is used to be able to start from the last position a file was loaded
		if(PropertyHelper.readFromProperty(DASY_PATH) != null){
			defaultPath = new File(PropertyHelper.readFromProperty(DASY_PATH));
		}
		// JFileChooser is used to browse for files
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(defaultPath);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setDialogTitle("Select DasyLab file");

		// a filter is added to the FileChooser, so there can only be loaded ascii/asc files. other files are not visible
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"ASCII", "ascii", "asc");
		chooser.setFileFilter(filter);

		// checks if ok is pressed or if the browse window is canceled. if canceled null is returned
		if(chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION){
			updateLog("You chose to open this file: " + chooser.getSelectedFile().getName());
			f = chooser.getSelectedFile();
			// stores the filepath in property to be loaded again next time
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
	//TODO rename to meaningful name...
	@Override
	public void showInformationMessage(String message, String title){
		JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public void setVisibility(boolean visible){
		this.setVisible(visible);
	}

	@Override
	public void run() {
		this.setVisible(true);
	}
}
