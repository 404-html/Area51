package javaMeasure.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javaMeasure.control.LoginController;
import javaMeasure.control.MainController;
import javaMeasure.control.interfaces.ILoginController;
import javaMeasure.view.interfaces.ILoginGui;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LoginGui extends JFrame implements ILoginGui { 
	private JComboBox<String> userBox;
	private JButton btnLogin;
	private JButton btnNewUser;
	private JPanel contentPane;
	private JLabel headLine;
	private JLabel labelStatus;
	private ILoginController loginController;

	@Override
	public void run() {
		try {
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public LoginGui(LoginController lgctrl) {
		super();
		this.loginController = lgctrl;
		
		setResizable(false);
		setTitle("MeasureMax");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 220, 200);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.headLine = new JLabel("Select User");
		headLine.setHorizontalAlignment(SwingConstants.CENTER);
		headLine.setBounds(10, 11, 193, 23);
		contentPane.add(headLine);
		
		btnLogin = new JButton("Login");
		btnLogin.setEnabled(false);
		btnLogin.setBounds(10, 79, 193, 23);
		btnLogin.setActionCommand("login");
		contentPane.add(btnLogin);
		
		btnNewUser = new JButton("New User");
		btnNewUser.setEnabled(false);
		btnNewUser.setBounds(10, 113, 193, 23);
		btnNewUser.setActionCommand("newuser");
		contentPane.add(btnNewUser);
		
		userBox = new JComboBox<String>();
		userBox.setToolTipText("Select a preexisting user or type a new user name and press New User");
		userBox.setEditable(true);
		userBox.setBounds(10, 45, 193, 23);
		contentPane.add(userBox);
		
		labelStatus = new JLabel("Connecting...");
		labelStatus.setBounds(10, 147, 193, 14);
		contentPane.add(labelStatus);
		
		//Setup listeners
		btnLogin.addActionListener(this);
		btnNewUser.addActionListener(this);
		
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{userBox, btnLogin, btnNewUser, headLine}));
		
	}
	//Only for testing
	public LoginGui() {
		this(new LoginController(new MainController(true)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "login":
				this.loginController.bthLoginPressed();
			break;
		case "newuser":
				this.loginController.btnNewUserPressed();
			break;
		default:
			System.err.println("Actionlistener cannot understand ActionCommand");
			break;
		}
		
	}

	@Override
	public void setUserList(String[] users) {
		for (String string : users) {
			userBox.addItem(string);
		};	
	}
	@Override
	public void showNoSuchUser() {
		this.headLine.setText("User does not exist");
		this.headLine.setForeground(Color.red);
	}
	
	@Override
	public void showUserAlreadyExists() {
		this.headLine.setText("User already exists");
		this.headLine.setForeground(Color.red);
	}

	@Override
	public void showInvalidUsername() {
		this.headLine.setText("Invalid Username - use A-Z and 0-9");
		this.headLine.setForeground(Color.red);
	}
	@Override
	public void showGui(boolean b) {
		this.setVisible(b);
	}

	@Override
	public String getSelectedUser() {
		return userBox.getSelectedItem().toString();
	}
	
	@Override
	public void enableButtons(boolean b) {
		btnLogin.setEnabled(b);
		btnNewUser.setEnabled(b);
	}
	
	@Override
	public void changestatus(Status s){
		switch (s){
		case Connected:
			labelStatus.setText("Connection to database established");
			labelStatus.setForeground(Color.GREEN.darker());
			break;
		case Connecting:
			labelStatus.setText("Connecting...");
			labelStatus.setForeground(Color.BLACK);
			break;
		case Noconnection:
			labelStatus.setText("No Connection");
			labelStatus.setForeground(Color.RED);
			JOptionPane.showMessageDialog(this, "No connection to database - check internet and databasesettings", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(ERROR);
			break;
		default:
			break;
		
		}
	}

	//TODO move main to separate class
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginGui frame = new LoginGui();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}
