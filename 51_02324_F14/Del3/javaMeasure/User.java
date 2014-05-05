package javaMeasure;

public class User {
	private String userName;
	private int userID;
	
	public User(String userName, int userID) {
		super();
		this.userName = userName;
		this.userID = userID;
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

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userID=" + userID + "]";
	}

}
