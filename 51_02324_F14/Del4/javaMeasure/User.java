package javaMeasure;

public class User {
	private String userName;
	private int userID;
	private String passWord; 
	
	public User(String userName, int userID) {
		super();
		this.userName = userName;
		this.userID = userID;
		this.passWord = null;
	}
	
	public User(String userName, int userID, String passWord) {
		super();
		this.userName = userName;
		this.userID = userID;
		this.passWord = passWord;
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

}
