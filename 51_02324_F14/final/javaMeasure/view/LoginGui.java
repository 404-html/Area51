package javaMeasure.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

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
		setBounds(100, 100, 219, 176);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.headLine = new JLabel("Select User");
		headLine.setHorizontalAlignment(SwingConstants.CENTER);
		headLine.setBounds(10, 11, 193, 23);
		contentPane.add(headLine);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 79, 193, 23);
		btnLogin.setActionCommand("login");
		contentPane.add(btnLogin);
		
		btnNewUser = new JButton("New User");
		btnNewUser.setBounds(10, 113, 193, 23);
		btnNewUser.setActionCommand("newuser");
		contentPane.add(btnNewUser);
		
		userBox = new JComboBox<String>();
		userBox.setToolTipText("Select a preexisting user or type a new user name and press New User");
		userBox.setEditable(true);
		userBox.setBounds(10, 45, 193, 23);
		contentPane.add(userBox);
		
		//Setup listeners
		btnLogin.addActionListener(this);
		btnNewUser.addActionListener(this);
		
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{userBox, btnLogin, btnNewUser, headLine}));
		
	}
	//Only for testing
	public LoginGui() {
		this(new LoginController(new MainController()));
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
	public void showGui(boolean b) {
		this.setVisible(b);
	}

	@Override
	public String getSelectedUser() {
		return userBox.getSelectedItem().toString();
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
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
