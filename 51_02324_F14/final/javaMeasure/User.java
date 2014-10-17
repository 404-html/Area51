package javaMeasure;

public class User {
	private String userName;
	private int userID;
	private String passWord; 
	private boolean active;
	private boolean admin;
	
	public User(String userName, int userID) {
		this(userName,userID, "12345");
	}
	
	public User(String userName, int userID, String passWord) {
		this(userName, userID, passWord, true, false);
	}
	
	public User(String userName, int userID, String passWord, boolean active, boolean admin) {
		
		super();
		this.userName = userName;
		this.userID = userID;
		this.passWord = passWord;
		this.active=active;
		this.admin=admin;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userID=" + userID + "]";
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
