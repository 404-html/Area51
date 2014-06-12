

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaMeasure.PropertyHelper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Configuration implements ActionListener {

	private JFrame frmConfigureProgram;
	private JTextField serverField;
	private JTextField portField;
	private JTextField databaseField;
	private JTextField usernameField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuration window = new Configuration();
					window.frmConfigureProgram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Configuration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfigureProgram = new JFrame();
		frmConfigureProgram.setResizable(false);
		frmConfigureProgram.setTitle("Configure Program");
		frmConfigureProgram.setBounds(100, 100, 300, 200);
		frmConfigureProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConfigureProgram.getContentPane().setLayout(null);
		
		JLabel serverlabel = new JLabel("server");
		serverlabel.setBounds(10, 11, 64, 14);
		frmConfigureProgram.getContentPane().add(serverlabel);
		
		JLabel portlabel = new JLabel("port");
		portlabel.setBounds(10, 36, 64, 14);
		frmConfigureProgram.getContentPane().add(portlabel);
		
		JLabel lblDatabase = new JLabel("database");
		lblDatabase.setBounds(10, 61, 64, 14);
		frmConfigureProgram.getContentPane().add(lblDatabase);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(10, 86, 64, 14);
		frmConfigureProgram.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(10, 112, 64, 14);
		frmConfigureProgram.getContentPane().add(lblPassword);
		
		serverField = new JTextField();
		serverField.setText(getProp("server"));
		serverField.setBounds(84, 8, 185, 20);
		frmConfigureProgram.getContentPane().add(serverField);
		serverField.setColumns(10);
		
		portField = new JTextField();
		portField.setText(getProp("port"));
		portField.setColumns(10);
		portField.setBounds(84, 33, 185, 20);
		frmConfigureProgram.getContentPane().add(portField);
		
		databaseField = new JTextField();
		databaseField.setText(getProp("database"));
		databaseField.setColumns(10);
		databaseField.setBounds(84, 58, 185, 20);
		frmConfigureProgram.getContentPane().add(databaseField);
		
		usernameField = new JTextField();
		usernameField.setText(getProp("username"));
		usernameField.setColumns(10);
		usernameField.setBounds(84, 83, 185, 20);
		frmConfigureProgram.getContentPane().add(usernameField);
		
		passwordField = new JTextField();
		passwordField.setText(getProp("password"));
		passwordField.setColumns(10);
		passwordField.setBounds(84, 109, 185, 20);
		frmConfigureProgram.getContentPane().add(passwordField);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(81, 140, 89, 23);
		btnOk.setActionCommand("ok");
		btnOk.addActionListener(this);
		frmConfigureProgram.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(180, 140, 89, 23);
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(this);
		frmConfigureProgram.getContentPane().add(btnCancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "ok":
			writeProp("server", serverField);
			writeProp("port", portField);
			writeProp("database", databaseField);
			writeProp("username", usernameField);
			writeProp("password", passwordField);
			System.exit(0);
			break;
		case "cancel":
			System.exit(0);
			break;
		default:
			break;
		}
	}

	private void writeProp(String key, JTextField field) {
		PropertyHelper.writeToProperty(key, field.getText());
	}
	
	private String getProp(String string) {
		return PropertyHelper.readFromProperty(string);
	}

}
