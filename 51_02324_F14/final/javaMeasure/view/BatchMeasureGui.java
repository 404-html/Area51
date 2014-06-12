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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.io.File;
import java.util.ArrayList;

import javaMeasure.Batch;
import javaMeasure.BatchSetting;
import javaMeasure.Measurement;
import javaMeasure.PropertyHelper;
import javaMeasure.control.interfaces.IBatchMeasureController;
import javaMeasure.view.interfaces.IBatchMeasureGui;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BatchMeasureGui extends JFrame implements IBatchMeasureGui {
	private static final String DASY_PATH = "dasyPath";
	private static final int COLUMNS_LENGTH = 4;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
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

		JButton btnNewBatch = new JButton("New Batch");
		btnNewBatch.setActionCommand("newBatch");
		btnNewBatch.setBounds(360, 10, 100, 25);
		getContentPane().add(btnNewBatch);

		JButton btnGetBatch = new JButton("Load Batch");
		btnGetBatch.setActionCommand("getBatch");
		btnGetBatch.setBounds(470, 10, 100, 25);
		getContentPane().add(btnGetBatch);

		JButton btnEditBatchSettings = new JButton("Edit Settings");
		btnEditBatchSettings.setActionCommand("editBatchSettings");
		btnEditBatchSettings.setBounds(360, 50, 215, 25);
		getContentPane().add(btnEditBatchSettings);
		
		JButton btnLeakCurrentMeasurement = new JButton("Leak Current Measurement");
		btnLeakCurrentMeasurement.setActionCommand("leakCurrent");
		btnLeakCurrentMeasurement.setBounds(360, 90, 215, 25);
		getContentPane().add(btnLeakCurrentMeasurement);

		JButton btnStrokeMeasurement = new JButton("Stroke Measurement");
		btnStrokeMeasurement.setActionCommand("stroke");
		btnStrokeMeasurement.setBounds(360, 130, 215, 25);
		getContentPane().add(btnStrokeMeasurement);
		
		JButton btnDeleteLeak = new JButton("Delete last leak");
		btnDeleteLeak.setBounds(360, 367, 210, 23);
		getContentPane().add(btnDeleteLeak);
		btnDeleteLeak.setActionCommand("deleteLeak");		
		
		JButton btnDeleteStroke = new JButton("Delete last stroke");
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

		//setup for the log
		logModel = new DefaultTableModel(null, columnNames);
		logModel.setColumnCount(1);
		log = new JList<Object>();
		JScrollPane logScroll = new JScrollPane();
		log.ensureIndexIsVisible(log.getMaxSelectionIndex());
		logScroll.setViewportView(log);
		log.setEnabled(false);
		logScroll.setBounds(10, 443, 340, 165);
		getContentPane().add(logScroll);

		// table setup for measurements
		this.tableData = new String[][]{{null,null,null,null}};
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
				System.out.println("normal value: " + i);
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
				System.out.println("tolerance: " + (i));
			}	
		}

		// text fields for inspection level
		for(int i = 0; i < 16; i++){
			JLabel text = new JLabel();
			text.setBounds(955, 160+25*i, 30, 20);
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
				text.setBounds(775, 435+25*i, 50, 20);
				text.setText("-");
				getContentPane().add(text);
				profileSettings.add(text);
			}
		}
	}

	// extracted method because it is so large. sets up the JTable with all the extra features needed
	private void setupTable() {

		

		model = new DefaultTableModel(tableData, columnNames);
		table = new JTable(model){
			// overwriting jtable to highlight background colour of the last row
			public Component prepareRenderer(
					TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				
//				System.out.println("row: " + row);
//				System.out.println("column: " + column);
				if(row == getRowCount()-1){
					c.setBackground(Color.YELLOW);
					c.setForeground(Color.BLACK);
				} else {
					if(getValueAt(row, 0).toString().equalsIgnoreCase("false")){
//						batchMeasureController.verifyElement(false, row);
						c.setBackground(Color.RED);
						c.setForeground(Color.BLACK);
					} else{
//						batchMeasureController.verifyElement(true, row);
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
//				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() );
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 10, 340, 380);
		table.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);
		
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			int rowCount = 0;
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0)
			{
				model.getRowCount();
				if(model.getRowCount() != rowCount){
				scrollPane.getVerticalScrollBar().setValue(model.getRowCount() * 16);
				
				}
				rowCount = model.getRowCount();
				// TODO Auto-generated method stub	
			}
		});

		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// column 0 is where checkboxes are
				if(table.columnAtPoint(e.getPoint()) == 0){
					int row = table.rowAtPoint(e.getPoint());
					System.out.println("table data verified: " + tableData[row][0]);
					
					boolean verified = (boolean) table.getValueAt(row, 0);
					System.out.println("verified: " + verified);
					batchMeasureController.updateMeasurements(row, verified);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});;;
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
	
	public void updateTable(Batch batch){
		ArrayList<Object[]> list = batch.getMeasurementsList();
		Object[][] updatedList = new Object[list.size()][4];
		for(int i = 0; i < list.size(); i++){
			updatedList[i][0] = list.get(i)[0];
			updatedList[i][1] = list.get(i)[1];
			updatedList[i][2] = list.get(i)[2];
			updatedList[i][3] = list.get(i)[3];
		}
		updateTable(updatedList);
	}

	// updates whole table
	public void updateTable(Object[][] data){
		this.tableData = data;
		Object[][] list = new Object[data.length][COLUMNS_LENGTH];
		for(int i = 0; i < data.length; i++)
		{
			try{
			list[i][0] = data[i][0];
			list[i][1] = data[i][1];
			
			Measurement m = (Measurement) data[i][2];
			if(m != null){
			list[i][2] = m.getMeasureValue();
			}
			m = (Measurement) data[i][3];
			if(m != null){
			list[i][3] = m.getMeasureValue();
			}
				} catch(Exception e){
					System.out.println("out of bounds when updating table");
//					list[i][j] = null;
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
				data[i][0] = list.get(i).get(0);
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
			batchMeasureController.btnLeakCurrent();
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
	public String getLoadBatchName(String[] batchList){
		// by using combobox, we can have a dropdown list inside the popup ShowMessageDialog
		JComboBox<Object> jcb = new JComboBox<Object>(batchList);
		jcb.setEditable(true);
		JOptionPane.showMessageDialog(getContentPane(), jcb, "Enter batchnumber:", JOptionPane.QUESTION_MESSAGE);
		return (String) jcb.getSelectedItem();
	}
	// TODO not up to date because of the new feature: DirectoryListener
	// browse for file to read
	public String getDasyPath(){
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
//		FileNameExtensionFilter filter = new FileNameExtensionFilter(
//				"ASCII", "ascii", "asc");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		chooser.setFileFilter(filter);

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
